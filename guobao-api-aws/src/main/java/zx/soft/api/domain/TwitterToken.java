package zx.soft.api.domain;

/**
 * Created by jimbo on 15-3-31.
 */
public class TwitterToken {
    private String tokenkey;
    private String tokenSecret;

    public TwitterToken() {
        //
    }

    public TwitterToken(String tokenkey, String tokenSecret) {
        this.tokenkey = tokenkey;
        this.tokenSecret = tokenSecret;
    }

    public String getTokenkey() {
        return tokenkey;
    }

    public void setTokenkey(String tokenkey) {
        this.tokenkey = tokenkey;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }
}
