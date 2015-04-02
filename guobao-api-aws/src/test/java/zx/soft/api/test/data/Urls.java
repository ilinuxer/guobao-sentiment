package zx.soft.api.test.data;

import java.util.ArrayList;
import java.util.List;

import zx.soft.utils.json.JsonUtils;

public class Urls {

	public static void main(String[] args) {

		List<String> urls = new ArrayList<>();
		urls.add("http://www.twitter.com");
		urls.add("http://www.facebook.com");
		urls.add("http://www.googleplus.com");
		System.out.println(JsonUtils.toJsonWithoutPretty(urls));

	}

}
