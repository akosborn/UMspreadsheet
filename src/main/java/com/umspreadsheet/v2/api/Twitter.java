
package com.umspreadsheet.v2.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * The Root Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "for_user_id",
    "tweet_create_events"
})
public class Twitter {

    /**
     * The For_user_id Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("for_user_id")
    public String forUserId = "";
    /**
     * The Tweet_create_events Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("tweet_create_events")
    public List<TweetCreateEvent> tweetCreateEvents = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
