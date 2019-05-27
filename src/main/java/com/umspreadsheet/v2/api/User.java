
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
 * The User Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "id_str",
    "name",
    "screen_name",
    "description",
    "translator_type",
    "protected",
    "verified",
    "followers_count",
    "friends_count",
    "listed_count",
    "favourites_count",
    "statuses_count",
    "created_at",
    "geo_enabled",
    "contributors_enabled",
    "is_translator",
    "profile_background_color",
    "profile_background_image_url",
    "profile_background_image_url_https",
    "profile_background_tile",
    "profile_link_color",
    "profile_sidebar_border_color",
    "profile_sidebar_fill_color",
    "profile_text_color",
    "profile_use_background_image",
    "profile_image_url",
    "profile_image_url_https",
    "default_profile",
    "default_profile_image"
})
public class User {

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
     * The Name Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("name")
    public String name = "";
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
     * The Description Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    public String description = "";
    /**
     * The Translator_type Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("translator_type")
    public String translatorType = "";
    /**
     * The Protected Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("protected")
    public Boolean _protected = false;
    /**
     * The Verified Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("verified")
    public Boolean verified = false;
    /**
     * The Followers_count Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("followers_count")
    public Integer followersCount = 0;
    /**
     * The Friends_count Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("friends_count")
    public Integer friendsCount = 0;
    /**
     * The Listed_count Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("listed_count")
    public Integer listedCount = 0;
    /**
     * The Favourites_count Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("favourites_count")
    public Integer favouritesCount = 0;
    /**
     * The Statuses_count Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("statuses_count")
    public Integer statusesCount = 0;
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
     * The Geo_enabled Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("geo_enabled")
    public Boolean geoEnabled = false;
    /**
     * The Contributors_enabled Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("contributors_enabled")
    public Boolean contributorsEnabled = false;
    /**
     * The Is_translator Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("is_translator")
    public Boolean isTranslator = false;
    /**
     * The Profile_background_color Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile_background_color")
    public String profileBackgroundColor = "";
    /**
     * The Profile_background_image_url Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile_background_image_url")
    public String profileBackgroundImageUrl = "";
    /**
     * The Profile_background_image_url_https Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile_background_image_url_https")
    public String profileBackgroundImageUrlHttps = "";
    /**
     * The Profile_background_tile Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile_background_tile")
    public Boolean profileBackgroundTile = false;
    /**
     * The Profile_link_color Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile_link_color")
    public String profileLinkColor = "";
    /**
     * The Profile_sidebar_border_color Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile_sidebar_border_color")
    public String profileSidebarBorderColor = "";
    /**
     * The Profile_sidebar_fill_color Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile_sidebar_fill_color")
    public String profileSidebarFillColor = "";
    /**
     * The Profile_text_color Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile_text_color")
    public String profileTextColor = "";
    /**
     * The Profile_use_background_image Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile_use_background_image")
    public Boolean profileUseBackgroundImage = false;
    /**
     * The Profile_image_url Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile_image_url")
    public String profileImageUrl = "";
    /**
     * The Profile_image_url_https Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile_image_url_https")
    public String profileImageUrlHttps = "";
    /**
     * The Default_profile Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("default_profile")
    public Boolean defaultProfile = false;
    /**
     * The Default_profile_image Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("default_profile_image")
    public Boolean defaultProfileImage = false;
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
