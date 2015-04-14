package zx.soft.api.domain;

import java.io.Serializable;

/**
 * Created by jimbo on 15-4-2.
 */
public class TwitterUserInfos implements Serializable {

    private long id;
    private String name;
    private String screenName;
    private String profileImageUrl;
    private String createdAt;
    private String location;
    private String url;
    private int favouritesCount;
    private int utcOffset;
    private int listedCount;
    private int followersCount;
    private String lang;
    private String description;
    private boolean verified;
    private String timeZone;
    private int statusesCount;
    private int friendsCount;

    public TwitterUserInfos() {
        //
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

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public int getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(int favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public int getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(int utcOffset) {
        this.utcOffset = utcOffset;
    }

    public int getListedCount() {
        return listedCount;
    }

    public void setListedCount(int listedCount) {
        this.listedCount = listedCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
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

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getStatusesCount() {
        return statusesCount;
    }

    public void setStatusesCount(int statusesCount) {
        this.statusesCount = statusesCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public TwitterUserInfos(long id, String name, String screenName, String profileImageUrl, String createdAt, String location, String url, int favouritesCount, int utcOffset, int listedCount, int followersCount, String lang, String description, boolean verified, String timeZone, int statusesCount, int friendsCount) {

        this.id = id;
        this.name = name;
        this.screenName = screenName;
        this.profileImageUrl = profileImageUrl;
        this.createdAt = createdAt;
        this.location = location;
        this.url = url;
        this.favouritesCount = favouritesCount;
        this.utcOffset = utcOffset;
        this.listedCount = listedCount;
        this.followersCount = followersCount;
        this.lang = lang;
        this.description = description;
        this.verified = verified;
        this.timeZone = timeZone;
        this.statusesCount = statusesCount;
        this.friendsCount = friendsCount;
    }
}
