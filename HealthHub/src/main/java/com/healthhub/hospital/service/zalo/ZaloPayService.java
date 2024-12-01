package com.healthhub.hospital.service.zalo;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ZaloPayService {

    private static final Map<String, String> CONFIG = new HashMap<>() {{
        put("app_id", "2554");
        put("key1", "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn");
        put("key2", "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf");
        put("endpoint", "https://sb-openapi.zalopay.vn/v2/create");
    }};

    private String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }

    public JSONObject createOrder(String appUser, int amount, String description) throws Exception {
        Random rand = new Random();
        int random_id = rand.nextInt(1000000);
        final Map embed_data = new HashMap(){{}};

        final Map[] item = {
                new HashMap(){{}}
        };

        Map<String, Object> order = new HashMap<String, Object>(){{
            put("app_id", CONFIG.get("app_id"));
            put("app_trans_id", getCurrentTimeString("yyMMdd") +"_"+ random_id); // translation missing: vi.docs.shared.sample_code.comments.app_trans_id
            put("app_time", System.currentTimeMillis()); // miliseconds
            put("app_user", "user123");
            put("amount", 50000);
            put("description", "Lazada - Payment for the order #"+random_id);
            put("bank_code", "zalopayapp");
            put("item", new JSONObject(item).toString());
            put("embed_data", new JSONObject(embed_data).toString());
        }};

        String data = order.get("app_id") + "|" + order.get("app_trans_id") + "|" +
                order.get("app_user") + "|" + order.get("amount") + "|" +
                order.get("app_time") + "|" + order.get("embed_data") + "|" + order.get("item");

        System.out.println(data);

        order.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, CONFIG.get("key1"), data));

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(CONFIG.get("endpoint"));

        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : order.entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        }

        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse res = client.execute(post);

        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }

        System.out.println("Response JSON String: " + resultJsonStr);

        return new JSONObject(resultJsonStr);
    }
}
