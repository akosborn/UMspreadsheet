
package com.umspreadsheet.v2.api;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * The Items Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "created_at",
    "id",
    "id_str",
    "text",
    "source",
    "truncated",
    "in_reply_to_user_id",
    "in_reply_to_user_id_str",
    "in_reply_to_screen_name",
    "user",
    "is_quote_status",
    "quote_count",
    "reply_count",
    "retweet_count",
    "favorite_count",
    "entities",
    "favorited",
    "retweeted",
    "filter_level",
    "lang",
    "timestamp_ms"
})
public class TweetCreateEvent {

    /**
     * The Created_at Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("created_at")
    public String createdAt = "";
    /**
     * The Id Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    public Long id;
    /**
     * The Id_str Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("id_str")
    public String idStr = "";
    /**
     * The Text Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("text")
    public String text = "";
    /**
     * The Source Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("source")
    public String source = "";
    /**
     * The Truncated Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("truncated")
    public Boolean truncated = false;
    /**
     * The In_reply_to_user_id Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("in_reply_to_user_id")
    public Integer inReplyToUserId = 0;
    /**
     * The In_reply_to_user_id_str Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("in_reply_to_user_id_str")
    public String inReplyToUserIdStr = "";
    /**
     * The In_reply_to_screen_name Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("in_reply_to_screen_name")
    public String inReplyToScreenName = "";
    /**
     * The User Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("user")
    public User user;
    /**
     * The Is_quote_status Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("is_quote_status")
    public Boolean isQuoteStatus = false;
    /**
     * The Quote_count Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("quote_count")
    public Integer quoteCount = 0;
    /**
     * The Reply_count Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("reply_count")
    public Integer replyCount = 0;
    /**
     * The Retweet_count Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("retweet_count")
    public Integer retweetCount = 0;
    /**
     * The Favorite_count Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("favorite_count")
    public Integer favoriteCount = 0;
    /**
     * The Entities Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("entities")
    public Entities entities;
    /**
     * The Favorited Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("favorited")
    public Boolean favorited = false;
    /**
     * The Retweeted Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("retweeted")
    public Boolean retweeted = false;
    /**
     * The Filter_level Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("filter_level")
    public String filterLevel = "";
    /**
     * The Lang Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("lang")
    public String lang = "";
    /**
     * The Timestamp_ms Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("timestamp_ms")
    public String timestampMs = "";
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
