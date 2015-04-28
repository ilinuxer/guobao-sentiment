package zx.soft.model.user;

/**
 * Created by jimbo on 4/21/15.
 */
public class CurrentUserInfo {
    private String userId;
    private String userName;
    private String sns;

    public CurrentUserInfo() {
        //
    }

    public CurrentUserInfo(String userId, String userName, String sns) {
        this.userId = userId;
        this.userName = userName;
        this.sns = sns;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSns() {
        return sns;
    }

    public void setSns(String sns) {
        this.sns = sns;
    }
}
