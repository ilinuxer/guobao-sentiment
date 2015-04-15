package zx.soft.api.domain;

import java.io.Serializable;

/**
 * Created by jimbo on 15-4-2.
 */
public class TwitterUserInfos implements Serializable {

    private long id;
    private String name;
    private String screen_name;
    private String profile_image_url;
    private String created_at;
    private String location;
    private String url;
    private int favourites_count;
    private int utc_offset;
    private int listed_count;
    private int followers_count;
    private String lang;
    private String description;
    private boolean verified;
    private String time_zone;
    private int statuses_count;
    private int friends_count;

    public TwitterUserInfos() {
        //
    }

    public TwitterUserInfos(long id, String name, String screen_name, String profile_image_url, String created_at, String location, String url, int favourites_count, int utc_offset, int listed_count, int followers_count, String lang, String description, boolean verified, String time_zone, int statuses_count, int friends_count) {
        this.id = id;
        this.name = name;
        this.screen_name = screen_name;
        this.profile_image_url = profile_image_url;
        this.created_at = created_at;
        this.location = location;
        this.url = url;
        this.favourites_count = favourites_count;
        this.utc_offset = utc_offset;
        this.listed_count = listed_count;
        this.followers_count = followers_count;
        this.lang = lang;
        this.description = description;
        this.verified = verified;
        this.time_zone = time_zone;
        this.statuses_count = statuses_count;
        this.friends_count = friends_count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFavourites_count() {
        return favourites_count;
    }

    public void setFavourites_count(int favourites_count) {
        this.favourites_count = favourites_count;
    }

    public int getUtc_offset() {
        return utc_offset;
    }

    public void setUtc_offset(int utc_offse) {
        this.utc_offset = utc_offse;
    }

    public int getListed_count() {
        return listed_count;
    }

    public void setListed_count(int listed_count) {
        this.listed_count = listed_count;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public void setStatuses_count(int statuses_count) {
        this.statuses_count = statuses_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }
}
