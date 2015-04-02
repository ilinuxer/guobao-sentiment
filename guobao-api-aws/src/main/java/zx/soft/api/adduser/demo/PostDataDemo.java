package zx.soft.api.adduser.demo;

import zx.soft.api.adduser.post.RestletPost;

/**
 * Created by jimbo on 15-4-2.
 */
public class PostDataDemo {
    public static void main(String[] args) {
        String url = "http://192.168.31.12:8900/user/gp";
        String data = "[{\"id\":\"id1\",\"display_name\":\"昵称1\",\"name\":\"姓名1\",\"url\":\"http://www.google.com1\",\"image_url\":\"http://www.image.url1\"},{\"id\":\"id2\",\"display_name\":\"昵称2\",\"name\":\"姓名2\",\"url\":\"http://www.google.com2\",\"image_url\":\"http://www.image.url2\"}]";

        String result = RestletPost.postData(url,data);
        System.out.println(result.charAt(13));
    }
}
