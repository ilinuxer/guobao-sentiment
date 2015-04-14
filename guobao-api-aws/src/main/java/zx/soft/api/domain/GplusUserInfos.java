package zx.soft.api.domain;

import java.io.Serializable;

/**
 * Created by jimbo on 15-4-1.
 */
public class GplusUserInfos implements Serializable {
    private String displayName;
    private String userId;
    private String imageUrl;
    private String url;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public GplusUserInfos() {
        //
    }

    public GplusUserInfos(String displayName, String userId, String imageUrl, String url) {
        this.displayName = displayName;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.url = url;
    }
}
