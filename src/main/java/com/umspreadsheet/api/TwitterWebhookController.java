package com.umspreadsheet.api;

import com.umspreadsheet.review.TrackReview;
import com.umspreadsheet.review.TrackReviewService;
import com.umspreadsheet.show.Show;
import com.umspreadsheet.show.ShowService;
import com.umspreadsheet.track.Track;
import com.umspreadsheet.track.TrackService;
import com.umspreadsheet.user.User;
import com.umspreadsheet.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("api/webhooks")
public class TwitterWebhookController {

    final private ShowService showService;
    final private TrackReviewService reviewService;
    final private UserService userService;
    final private TrackService trackService;

    @Value("${twitter.consumer.secret}")
    String twitterConsumerSecret;

    final private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TwitterWebhookController(ShowService showService, TrackReviewService reviewService, UserService userService, TrackService trackService) {
        this.showService = showService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.trackService = trackService;
    }

    @PostMapping("/twitter")
    public void receiveAccountActivityEvent(@RequestBody Map<String, Object> body) throws ParseException {
        log.info("Received Account Activity event: {}", body);

        ArrayList<Map<String, Object>> tweetCreateEvents = (ArrayList<Map<String, Object>>) body.get("tweet_create_events");
        log.info("tweetCreateEvents = " + tweetCreateEvents);

        Map<String, Object> twitterUser = (Map<String, Object>) tweetCreateEvents.get(0).get("user");
        String twitterUserId = (String) twitterUser.get("id_str");

        User user = userService.findByUserId(twitterUserId);
        if (user == null) {
            log.info("User not found with matching Twitter ID \"${}\". Creating user.", twitterUserId);
            user = new User();
            user.setUserId(twitterUserId);
            user.setUsername(twitterUserId);
            user.setTwitterHandle((String) twitterUser.get("screen_name"));
            user.setEmail(twitterUserId + "@umspreadsheet.com");
            user.setPassword(UUID.randomUUID().toString());
            user.setIsEnabled(true);
            user = userService.save(user);
        }

        String tweetText = (String) tweetCreateEvents.get(0).get("text");
        log.info(tweetText);

        String[] stringItems = tweetText.split("\\s+");
        // "text": "@user Rate 2019-5-1 Roulette 4"
        // "text": "@<me> <action> <show date YYYY-M-D> <song name> <rating>

        int songWords = stringItems.length - 4;

        String action = stringItems[1];
        Date date = new SimpleDateFormat("yyyy-M-d").parse(stringItems[2]);

        StringBuilder songNameBuilder = new StringBuilder(stringItems[3]);
        for (int i = 4; i < stringItems.length - 1; i++) {
            songNameBuilder.append(" ");
            songNameBuilder.append(stringItems[i]);
        }
        String songName = songNameBuilder.toString();

        double rating = Double.parseDouble(stringItems[stringItems.length - 1]);

        if (action.equalsIgnoreCase("Rate")) {
            List<Track> tracks = trackService.findBySongAndShowDate(songName, date);
            if (tracks.size() > 0) {
                Show show = this.showService.findByDate(date);
                if (show != null) {
                    log.info("Found show {}", show.toString());

                    TrackReview review = new TrackReview(show.getSets().get(0).getTracks().get(1));
                    review.setUser(user);
                    review.setReviewedOn(new Date());
                    review.setScore(rating);
                    this.reviewService.save(review);
                }
            } else {
                log.warn("No tracks found matching date \"${}\" and song name \"${}\"", date, songName);
            }
        } else {
            log.warn("Unrecognized action \"${}\"", action);
        }
    }

    @GetMapping("/twitter")
    public Map<String, String> handleChallenge(@RequestParam("crc_token") String token) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] hmacSha256 = null;
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.twitterConsumerSecret.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);
        hmacSha256 = mac.doFinal(token.getBytes());
        String base64HmacSha256 = Base64.getEncoder().encodeToString(hmacSha256);
        Map<String, String> m = new HashMap<>();
        m.put("response_token", "sha256=" + base64HmacSha256);
        return m;
    }
}