package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.Entity.ThanhToan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.EmailService;
import com.healthhub.hospital.service.LichKhamService;
import com.healthhub.hospital.service.ThanhToanService;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ThanhToanController {

    private ThanhToanService thanhToanService;

    private LichKhamService lichKhamService;

    private EmailService emailService;

    private LichKham lichKham;

    private HttpSession session;

    private Integer Malk;
    @Autowired
    public ThanhToanController(ThanhToanService thanhToanService, LichKhamService lichKhamService,
                               HttpSession session, EmailService emailService) {
        this.thanhToanService = thanhToanService;
        this.lichKhamService = lichKhamService;
        this.session = session;
        this.emailService = emailService;
    }

    @GetMapping("/ThanhToan")
    public String thanhtoan(@RequestParam("id") Integer id, @RequestParam("maLK") Integer maLK, Model model) {
        // Lấy thông tin thanh toán dựa trên id
        ThanhToan thanhToan = thanhToanService.findbyid_thanhtoan(id);

        Malk = maLK;
        // Lấy thông tin lịch khám dựa trên maLK nếu cần
        lichKham = lichKhamService.getLichKhambyID(maLK);

        model.addAttribute("thanhToan", thanhToan);
        model.addAttribute("lichKham", lichKham);

        session.setAttribute("maTT", id);

        return "Doctor/ThanhToan";
    }


    @PostMapping("/ThanhToan")
    public String editThanhToan(@ModelAttribute("thanhToan") ThanhToan thanhToan, BindingResult result) {
        if (result.hasErrors()) {
            return "Doctor/404";
        }

        // Liên kết thanh toán với lịch khám
        thanhToan.setLichKham(lichKham);
        thanhToan.setHinhThucThanhToan("Tiền mặt");
        thanhToan.setNgayThanhToan(LocalDateTime.now());
        thanhToan.setTrangthai("Đã thanh toán");
        // Cập nhật thông tin thanh toán trong cơ sở dữ liệu
        thanhToanService.updateThanhToan(thanhToan);

        return "redirect:/ThanhToan?id=" + thanhToan.getMaTT() + "&maLK=" + thanhToan.getLichKham().getMaLK();
    }

    @GetMapping("/export-pdf")
    public void exportToPDF(HttpServletResponse response, @RequestParam("id") Integer id) throws IOException, MessagingException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=payment_invoice_" + id + ".pdf";
        response.setHeader(headerKey, headerValue);

        ThanhToan thanhToan = thanhToanService.findbyid_thanhtoan(id);
        LichKham lichKham = lichKhamService.getLichKhambyID(Malk);

        String hoTen = lichKham.getHoten();
        String sdt = lichKham.getSDT();
        String email = lichKham.getEmail();
        String ngayKham = lichKham.getNgayKham().toString();
        String chuanDoan = lichKham.getChiTietLichKham().getChuanDoan();
        String donThuoc = lichKham.getChiTietLichKham().getDonThuoc();
        String ghiChuThem = lichKham.getChiTietLichKham().getGhiChuThem();

        // Tạo PDF trong bộ nhớ
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Thêm nội dung PDF
        document.add(new Paragraph("Electronic Invoice").setFontSize(20).setBold().setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("\n"));

        Table infoTable = new Table(UnitValue.createPercentArray(new float[]{1, 1})).useAllAvailableWidth();

        // Patient Information (left)
        Cell leftCell = new Cell();
        leftCell.add(new Paragraph("Patient Information:").setBold().setFontSize(12));
        leftCell.add(new Paragraph("Name: " + (hoTen != null ? hoTen : "N/A")));
        leftCell.add(new Paragraph("Phone: " + (sdt != null ? sdt : "N/A")));
        leftCell.add(new Paragraph("Email: " + (email != null ? email : "N/A")));
        leftCell.add(new Paragraph("Appointment Date: " + ngayKham));
        leftCell.setBorder(Border.NO_BORDER);

        // Appointment Details (right)
        Cell rightCell = new Cell();
        rightCell.add(new Paragraph("Appointment Details:").setBold().setFontSize(12));
        rightCell.add(new Paragraph("Diagnosis: " + (chuanDoan != null ? chuanDoan : "N/A")));
        rightCell.add(new Paragraph("Prescription: " + (donThuoc != null ? donThuoc : "N/A")));
        rightCell.add(new Paragraph("Additional Notes: " + (ghiChuThem != null ? ghiChuThem : "N/A")));
        rightCell.setBorder(Border.NO_BORDER);

        // Add cells to the table
        infoTable.addCell(leftCell);
        infoTable.addCell(rightCell);
        document.add(infoTable);

        // Add payment information
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 3})).useAllAvailableWidth();
        table.addCell(new Cell().add(new Paragraph("Information")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
        table.addCell(new Cell().add(new Paragraph("Detail")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
        table.addCell("Invoice ID:");
        table.addCell(String.valueOf(thanhToan.getMaTT()));
        table.addCell("Amount Paid:");
        table.addCell(thanhToan.getSoTien() + " VNĐ");
        table.addCell("Payment Time:");
        table.addCell(thanhToan.getNgayThanhToan().toString());
        table.addCell("Payment Method:");
        table.addCell(thanhToan.getHinhThucThanhToan());
        document.add(table);

        // Footer Note
        document.add(new Paragraph("\nNote:").setBold().setFontSize(12));
        document.add(new Paragraph("Thank you for using the service!").setTextAlignment(TextAlignment.CENTER));

        document.close();

        // Chuyển PDF sang byte array
        byte[] pdfBytes = outputStream.toByteArray();

        // Ghi PDF vào response để download
        try (OutputStream responseOutputStream = response.getOutputStream()) {
            responseOutputStream.write(pdfBytes);
        }

        // Gửi email với file PDF đính kèm
        emailService.sendEmailWithAttachment(email, "Payment Invoice", "Please find your invoice attached.", pdfBytes, "invoice.pdf");
    }


}
