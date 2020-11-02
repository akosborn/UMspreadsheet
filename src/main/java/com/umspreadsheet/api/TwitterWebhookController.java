package com.umspreadsheet.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/webhooks")
public class TwitterWebhookController {

    @GetMapping("/twitter")
    public Map<String, String> handleChallenge(@RequestParam("crc_token") String token) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] hmacSha256 = null;
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec("AxQgVOr5MQ7hQJsVOdP5sdQwMCTlWNtMQjxwp6ZKfjHkgGZ8rI".getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);
        hmacSha256 = mac.doFinal(token.getBytes());
        String base64HmacSha256 = Base64.getEncoder().encodeToString(hmacSha256);
        Map<String, String> m = new HashMap<>();
        m.put("response_token", "sha256=" + base64HmacSha256);
        return m;
    }
}
