
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
 * The Items Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "screen_name",
    "name",
    "id",
    "id_str",
    "indices"
})
public class UserMention {

    /**
     * The Screen_name Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("screen_name")
    public String screenName = "";
    /**
     * The Name Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("name")
    public String name = "";
    /**
     * The Id Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    public Integer id = 0;
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
     * The Indices Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("indices")
    public List<Integer> indices = null;
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
