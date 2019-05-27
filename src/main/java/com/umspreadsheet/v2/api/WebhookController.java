package com.umspreadsheet.v2.api;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/webhooks/")
public class WebhookController {

    Logger logger = LoggerFactory.getLogger(WebhookController.class);

    // https://developer.twitter.com/en/docs/accounts-and-users/subscribe-account-activity/guides/securing-webhooks.html
    @GetMapping("/twitter")
    public Map<String, String> twitterCrc(@RequestParam("crc_token") String crcToken) {
        final String consumerSecret = "171sGMoHkEoWC0dPXNWRuQ5nimFZNzru4KSq5y5TBDN7rZAiaa";
        try {
            SecretKeySpec secretKey = new SecretKeySpec(consumerSecret.getBytes("UTF-8"), "HmacSHA256");
            Mac sha256HMAC = null;
            sha256HMAC = Mac.getInstance("HmacSHA256");
            sha256HMAC.init(secretKey);

            String hash = Base64.encodeBase64String(sha256HMAC.doFinal(crcToken.getBytes("UTF-8")));
            Map<String, String> response = new HashMap<>();
            response.put("response_token", "sha256=" + hash);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    @PostMapping("/twitter")
    public void twitter(@RequestBody Map<String, Object> body) {
        System.out.println(body);
        logger.info(body.toString());
    }
}
