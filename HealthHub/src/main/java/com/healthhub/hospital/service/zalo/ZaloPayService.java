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

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Service
public class ZaloPayService {

//    private static final Map<String, String> CONFIG = new HashMap<>() {{
//        put("app_id", "2554");
//        put("key1", "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn");
//        put("key2", "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf");
//        put("endpoint", "https://sb-openapi.zalopay.vn/v2/create");
//    }};

    static String app_id = "2554";
    static String key1 = "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn";
    static String create_order_url = "https://sb-openapi.zalopay.vn/v2/create";

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

        Map<String, String> order = new HashMap<>(){{
            put("app_id", app_id);
            put("app_user", "user123");
            put("app_time", String.valueOf(System.currentTimeMillis())); // miliseconds
            put("amount", String.valueOf(50000));
            put("app_trans_id", getCurrentTimeString("yyMMdd") +"_"+ random_id); // translation missing: vi.docs.shared.sample_code.comments.app_trans_id
            put("embed_data", new JSONObject(embed_data).toString());
            put("item", "[]");
            put("description", "Lazada - Payment for the order #"+random_id);
            put("bank_code", "zalopayapp");


        }};

        String data = order.get("app_id") + "|" + order.get("app_trans_id") + "|" +
                order.get("app_user") + "|" + order.get("amount") + "|" +
                order.get("app_time") + "|" + order.get("embed_data") + "|" + order.get("item");

        System.out.println(data);

        order.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, key1, data));

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(create_order_url);

        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, String> e : order.entrySet()) {
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
        String orderUrl = handleResponse(resultJsonStr);
        return new JSONObject(resultJsonStr).put("order_url", orderUrl);

    }

    public String handleResponse(StringBuilder resultJsonStr) {
        try {
            // Chuyển chuỗi JSON thành đối tượng JSONObject
            JSONObject responseJson = new JSONObject(resultJsonStr.toString());

            // Kiểm tra nếu giao dịch thành công
            if (responseJson.getInt("return_code") == 1) {
                // Lấy giá trị order_url
                String orderUrl = responseJson.getString("order_url");
                System.out.println("Transaction successful! Order URL: " + orderUrl);

                // Trả về URL để lớp gọi có thể sử dụng
                return orderUrl;
            } else {
                System.out.println("Transaction failed: " + responseJson.getString("return_message"));
                throw new RuntimeException("Transaction failed: " + responseJson.getString("return_message"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while handling response", e);
        }
    }

    // Hàm mở link trong trình duyệt
    public void openLink(String url) {
        try {
            Desktop desktop = Desktop.getDesktop();
            if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(url));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
