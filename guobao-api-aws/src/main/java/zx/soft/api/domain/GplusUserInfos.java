package zx.soft.api.domain;

/**
 * Created by jimbo on 15-4-1.
 */
public class GplusUserInfos {
    private String displayName;
    private String etage;
    private String userId;
    private String imageUrl;
    private String kind;
    private String objectType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String url;

    public GplusUserInfos() {
        //
    }

    public GplusUserInfos(String displayName, String etage, String userId, String imageUrl, String kind, String objectType, String url) {
        this.displayName = displayName;
        this.etage = etage;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.kind = kind;
        this.objectType = objectType;
        this.url = url;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
