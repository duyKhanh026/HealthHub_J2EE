package com.healthhub.hospital.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthhub.hospital.service.zalo.HMACUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/zalopay-callback")
public class ZaloPayCallbackController {
    private static final String key2 = "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf";

    @PostMapping
    public ResponseEntity<Map<String, Object>> handleCallback(@RequestBody Map<String, Object> cbdata) {
        Map<String, Object> result = new HashMap<>();
        try {
            String dataStr = (String) cbdata.get("data");
            String reqMac = (String) cbdata.get("mac");

            // Tính toán MAC
            String mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, key2, dataStr);

            System.out.println("Received mac: " + mac);

            // Kiểm tra callback hợp lệ
            if (!reqMac.equals(mac)) {
                // Callback không hợp lệ
                result.put("return_code", -1);
                result.put("return_message", "mac not equal");
            } else {
                // Xử lý thanh toán thành công
                Map<String, Object> dataJson = new ObjectMapper().readValue(dataStr, HashMap.class);
                String appTransId = (String) dataJson.get("app_trans_id");

                System.out.println("Update order's status = success where app_trans_id = " + appTransId);

                // TODO: Cập nhật trạng thái đơn hàng trong cơ sở dữ liệu
                result.put("return_code", 1);
                result.put("return_message", "success");
            }
        } catch (Exception ex) {
            result.put("return_code", 0); // ZaloPay server sẽ callback lại
            result.put("return_message", ex.getMessage());
            ex.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
}
