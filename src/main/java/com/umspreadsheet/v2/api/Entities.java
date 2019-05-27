
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
 * The Entities Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hashtags",
    "urls",
    "user_mentions",
    "symbols"
})
public class Entities {

    /**
     * The Hashtags Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("hashtags")
    public List<Object> hashtags = null;
    /**
     * The Urls Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("urls")
    public List<Object> urls = null;
    /**
     * The User_mentions Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("user_mentions")
    public List<UserMention> userMentions = null;
    /**
     * The Symbols Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("symbols")
    public List<Object> symbols = null;
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
