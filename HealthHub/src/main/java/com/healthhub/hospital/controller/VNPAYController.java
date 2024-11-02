package com.healthhub.hospital.controller;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.VNPAYService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VNPAYController {
    @Autowired
    private VNPAYService vnPayService;

    private BenhNhanService benhNhanService;

    public VNPAYController(BenhNhanService benhNhanService) {
        this.benhNhanService = benhNhanService;
    }

    @GetMapping("/vnpay")
    public String loadvnpay(@RequestParam("idbn") Integer idbn, Model model) {
        // Giả sử có repository lấy thông tin bệnh nhân dựa trên idbn
        BenhNhan benhNhan = benhNhanService.getBenhNhanById(idbn);

        if (benhNhan != null) {
            // Thêm thông tin bệnh nhân vào model
            model.addAttribute("orderInfo", benhNhan.getHoTen() + " chuyển khoản viện phí");
            model.addAttribute("idbn", idbn); // Để tham khảo trong view nếu cần
        }

        return "vnpay/createOrder";
    }

    // Chuyển hướng người dùng đến cổng thanh toán VNPAY
    @PostMapping("/submitOrder")
    public String submidOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request){
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(request, orderTotal, orderInfo, baseUrl);
        return "redirect:" + vnpayUrl;
    }

    // Sau khi hoàn tất thanh toán, VNPAY sẽ chuyển hướng trình duyệt về URL này
    @GetMapping("/vnpay-payment-return")
    public String paymentCompleted(HttpServletRequest request, Model model){
        int paymentStatus =vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        return paymentStatus == 1 ? "vnpay/ordersuccess" : "vnpay/orderfail";
    }
}
