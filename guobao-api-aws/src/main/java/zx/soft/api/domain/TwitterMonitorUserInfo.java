package zx.soft.api.domain;

/**
 * Created by jimbo on 4/27/15.
 */
public class TwitterMonitorUserInfo {
    private String userId;
    private String screenName;
    public TwitterMonitorUserInfo() {
        //
    }

    public TwitterMonitorUserInfo(String userId, String screenName) {
        this.userId = userId;
        this.screenName = screenName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
