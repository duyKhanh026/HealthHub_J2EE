package com.healthhub.hospital.controller;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.ThanhToan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.ThanhToanService;
import com.healthhub.hospital.service.VNPAYService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class VNPAYController {
    @Autowired
    private VNPAYService vnPayService;

    private BenhNhanService benhNhanService;

    private ThanhToanService thanhToanService;

    private HttpSession session;

    public VNPAYController(BenhNhanService benhNhanService, ThanhToanService thanhToanService, HttpSession session) {
        this.benhNhanService = benhNhanService;
        this.thanhToanService = thanhToanService;
        this.session = session;
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

        Integer maTT = (Integer) session.getAttribute("maTT");

        ThanhToan thanhToan = thanhToanService.findbyid_thanhtoan(maTT);



        if (paymentStatus == 1){

            thanhToan.setSoTien(Integer.parseInt(totalPrice));
            thanhToan.setNgayThanhToan(LocalDateTime.now());
            thanhToan.setHinhThucThanhToan("VNPAY");
            thanhToanService.updateThanhToan(thanhToan);
            return "vnpay/ordersuccess";
        }else {
            return "vnpay/orderfail";
        }


    }
}
