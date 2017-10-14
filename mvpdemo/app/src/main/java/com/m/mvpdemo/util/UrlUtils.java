package com.m.mvpdemo.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by showLong
 */
public class UrlUtils {

    public static String formatParams(List<NameValuePair> params) {
        return URLEncodedUtils.format(params, HTTP.UTF_8);
    }

    public static String genrateGetUrl(String url, List<NameValuePair> olist) {
        if (olist != null && olist.size() > 0) {
            return url + "?" + formatParams(olist);
        } else {
            return url;
        }
    }

    public static String jointParams(String url, Map<String, String> params) {
        List<NameValuePair> olist = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            olist.add(new BasicNameValuePair(entry.getKey()
                    .toString(), entry.getValue().toString()));
        }
        if (olist != null && olist.size() > 0) {
            int idx = url.indexOf("?");
            if (idx == -1) {
                return url + "?" + formatParams(olist);
            } else {
                return url + "&" + formatParams(olist);
            }
        } else {
            return url;
        }
    }
}
