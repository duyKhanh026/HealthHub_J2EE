package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.ChiTietLichKham;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.Entity.ThanhToan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;

import com.healthhub.hospital.service.ThanhToanService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class DSLichKhamController {

    private final LichKhamService lichKhamService;
    private final BenhNhanService benhNhanService;
    private final ChiTietLichKhamService chiTietLichKhamService;
    private final ThanhToanService thanhToanService;

    @Autowired
    public DSLichKhamController(LichKhamService lichKhamService, BenhNhanService benhNhanService,
                                ChiTietLichKhamService chiTietLichKhamService, ThanhToanService thanhToanService) {
        this.lichKhamService = lichKhamService;
        this.benhNhanService = benhNhanService;
        this.chiTietLichKhamService = chiTietLichKhamService;
        this.thanhToanService = thanhToanService;
    }

    @GetMapping("/DSLichKham")
    public String ListLichKham(
            @RequestParam(value = "selectedDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selectedDate,
            @RequestParam(value = "action", required = false) String action, // Nhận giá trị từ nút bấm
            Model model, HttpServletRequest request) {

        model.addAttribute("requestURI", request.getRequestURI());  // Thêm URI vào model thay vì trực tiếp sử dụng #request

        List<LichKham> lichKhamList;

        // Nếu nhấn nút "Xem toàn bộ lịch khám", lấy tất cả lịch khám
        if ("viewAll".equals(action)) {
            lichKhamList = lichKhamService.findAllnotDayoff("DayOff");
        } else {
            // Nếu không chọn ngày, mặc định là ngày hiện tại
            if (selectedDate == null) {
                selectedDate = LocalDate.now();
            }
            // Lấy lịch khám theo ngày đã chọn
            lichKhamList = lichKhamService.getLichKhamByDate_NotOff(selectedDate);
        }

        model.addAttribute("lichkhamList", lichKhamList);
        model.addAttribute("selectedDate", selectedDate); // Để giữ giá trị đã chọn

        return "Doctor/DSLichKham";
    }

    @PostMapping("/DSLichKham")
    public String addlichkham(@ModelAttribute("lichKham") LichKham lichkham, @RequestParam("date") String date,
                              @RequestParam("time") String time, @RequestParam("ten") String ten,@RequestParam("sdt") String sdt,
                              BindingResult result, Model model) throws MessagingException {
        if (result.hasErrors()) {
            return "404";
        }

        System.out.println("data về" + ten + sdt + date + time);

        LocalDate selectedDate = LocalDate.parse(date);
        LocalTime selectedTime = LocalTime.parse(time);
        LocalDateTime ngayGioDatKham = LocalDateTime.of(selectedDate, selectedTime);

        BenhNhan benhNhan = new BenhNhan();

        if (benhNhanService.findBySDT(sdt) != null){        // nếu bệnh nhân đã tồn tại thì lấy bệnh nhân đó gán vào lịch khám mới
            benhNhan = benhNhanService.findBySDT(sdt);

        }else {
            benhNhan.setHoTen(ten);                      // còn nếu chưa tồn tại thì tạo mới bệnh nhân
            benhNhan.setSDT(sdt);
            benhNhanService.updateBenhNhan(benhNhan);
        }

        ChiTietLichKham chiTietLichKham = new ChiTietLichKham();
        ThanhToan thanhToan = new ThanhToan();

        lichkham.setBenhNhan(benhNhan); // Them benh nhan
        lichkham.setHoten(ten);
        lichkham.setSDT(sdt);
        lichkham.setNgayGioDatKham(ngayGioDatKham);
        lichkham.setTrangThai("Chưa khám");

        // Lưu `LichKham` trước để có ID (MaLK)
        lichKhamService.updateLichKham(lichkham);

        chiTietLichKham.setLichKham(lichkham);
        thanhToan.setLichKham(lichkham);
        thanhToan.setTrangthai("Chưa thanh toán");

        // Lưu `ChiTietLichKham` sau khi `LichKham` đã được lưu
        chiTietLichKhamService.updateChiTietLichKham(chiTietLichKham);

        thanhToanService.updateThanhToan(thanhToan);


        // Cập nhật `ChiTietLichKham` cho `LichKham`
        lichkham.setChiTietLichKham(chiTietLichKham);
        return "redirect:/DSLichKham";
    }

    //Xuat Excel
    @GetMapping("/exportLichKhamExcel")
    public void exportToExcelLichKham(HttpServletResponse response) throws IOException {
        // Thiết lập thông tin response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=DanhSachLichKham.xlsx");

        // Tạo workbook và sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Danh sách Lịch Khám");

        // Tạo style cho header
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true); // Chữ đậm
        headerFont.setFontHeightInPoints((short) 12); // Kích thước chữ
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER); // Căn giữa
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Căn giữa dọc
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex()); // Màu nền
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);

        // Tiêu đề cột
        String[] headers = {"Mã Lịch Khám", "Mã bệnh nhân", "Tên bệnh nhân", "Số Điện Thoại", "Email", "Ngày Khám", "Ghi chú", "Trạng Thái"};
        Row headerRow = sheet.createRow(0);

        // Tạo các ô trong dòng tiêu đề
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Lấy danh sách lịch khám từ service
        List<LichKham> lichKhams = lichKhamService.getAllLichKham();

        // Style cho dữ liệu
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setWrapText(true);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);

        int rowNum = 1;
        for (LichKham lichKham : lichKhams) {
            Row row = sheet.createRow(rowNum++);
            createCell(row, 0, lichKham.getMaLK(), dataStyle);
            createCell(row, 1, lichKham.getBenhNhan().getMaBN(), dataStyle);
            createCell(row, 2, lichKham.getHoten(), dataStyle);
            createCell(row, 3, lichKham.getSDT(), dataStyle);
            createCell(row, 4, lichKham.getEmail(), dataStyle);
            createCell(row, 5, lichKham.getNgayKham().toString(), dataStyle);
            createCell(row, 6, lichKham.getNote(), dataStyle);
            createCell(row, 7, lichKham.getTrangThai(), dataStyle);
        }

        // Tự động điều chỉnh cột
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Ghi workbook vào response
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    // Hàm hỗ trợ tạo ô
    private void createCell(Row row, int column, Object value, CellStyle style) {
        Cell cell = row.createCell(column);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof LocalDate) {
            cell.setCellValue(value.toString());
        } else if (value != null) {
            cell.setCellValue(value.toString());
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(style);
    }

}
