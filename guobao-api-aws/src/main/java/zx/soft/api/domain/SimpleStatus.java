package zx.soft.api.domain;

/**
 * Created by jimbo on 5/3/15.
 */
public class SimpleStatus {
    private String statusId;
    private String sns;

    public SimpleStatus() {
        //
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getSns() {
        return sns;
    }

    public void setSns(String sns) {
        this.sns = sns;
    }

    public SimpleStatus(String statusId, String sns) {
        this.statusId = statusId;
        this.sns = sns;
    }
}
