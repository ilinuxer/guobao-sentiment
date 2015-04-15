package zx.soft.api.domain;

import java.io.Serializable;

/**
 * Created by jimbo on 15-4-1.
 */
public class GplusUserInfos implements Serializable {
    private String id;
    private String display_name;
    private String name;
    private String url;
    private String image_url;

    public GplusUserInfos() {

        //
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public GplusUserInfos(String id, String display_name, String name, String url, String image_url) {
        this.id = id;
        this.display_name = display_name;
        this.name = name;
        this.url = url;
        this.image_url = image_url;
    }



}
