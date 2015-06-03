package zx.soft.api.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jimbo on 6/3/15.
 */
public class EncodeUrl {
    public static boolean isPerson(String url,String sourceUrl) {
        boolean result = false;
        String subSource = sourceUrl.substring(24);
        String subSourceEncode = "";
        if(subSource.charAt(0) == '+'){
            try {
                subSourceEncode = URLEncoder.encode(subSource.substring(1),"utf-8").toLowerCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            subSourceEncode = subSource;
        }

        if (url.contains(subSourceEncode)){
            result =true;
        }
        return result;
    }
}
