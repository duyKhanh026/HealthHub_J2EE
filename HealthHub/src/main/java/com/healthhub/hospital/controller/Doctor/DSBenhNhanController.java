package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.service.BenhNhanService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
public class DSBenhNhanController {

    private final BenhNhanService benhNhanService;

    @Autowired
    public DSBenhNhanController(BenhNhanService benhNhanService) {
        this.benhNhanService = benhNhanService;
    }

    @GetMapping("/DSBenhNhan")
    public String listBenhNhan(@RequestParam(value = "sdt", required = false) String sdt, Model model) {
        List<BenhNhan> benhnhans;

        if (sdt != null && !sdt.isEmpty()) {
            // Tìm bệnh nhân theo số điện thoại nếu sdt được cung cấp
            BenhNhan benhNhan = benhNhanService.findBySDT(sdt);
            benhnhans = benhNhan != null ? List.of(benhNhan) : Collections.emptyList();
        } else {
            // Lấy 10 bệnh nhân gần đây nhất nếu không có sdt
            benhnhans = benhNhanService.getRecent10BenhNhan();
        }

        model.addAttribute("benhnhans", benhnhans);
        model.addAttribute("benhNhan", new BenhNhan());
        return "Doctor/DSBenhNhan";
    }


    // Xử lý form thêm bệnh nhân
    @PostMapping("/DSBenhNhan")
    public String addBenhNhan(@ModelAttribute("benhNhan") BenhNhan benhNhan, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "404";
        }
        benhNhanService.updateBenhNhan(benhNhan);
        return "redirect:/DSBenhNhan";
    }

    //Xuat excel
    @GetMapping("/exportBenhNhanExcel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        // Thiết lập thông tin response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=DanhSachBenhNhan.xlsx");

        // Tạo workbook và sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Danh sách Bệnh nhân");

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
        String[] headers = {"Mã Bệnh Nhân", "Họ Tên", "Ngày Sinh", "Giới Tính", "Số Điện Thoại", "Email", "Địa Chỉ", "Tiền Sử Bệnh"};
        Row headerRow = sheet.createRow(0);

        // Tạo các ô trong dòng tiêu đề
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Lấy danh sách dữ liệu
        List<BenhNhan> benhNhans = benhNhanService.getAllBenhNhan();

        // Style cho dữ liệu
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setWrapText(true);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);

        // Thêm dữ liệu vào sheet
        int rowNum = 1;
        for (BenhNhan benhNhan : benhNhans) {
            Row row = sheet.createRow(rowNum++);
            createCell(row, 0, benhNhan.getMaBN(), dataStyle);
            createCell(row, 1, benhNhan.getHoTen(), dataStyle);
            createCell(row, 2, benhNhan.getNgaySinh(), dataStyle);
            createCell(row, 3, benhNhan.getGioitinh(), dataStyle);
            createCell(row, 4, benhNhan.getSDT(), dataStyle);
            createCell(row, 5, benhNhan.getEmail(), dataStyle);
            createCell(row, 6, benhNhan.getDiachi(), dataStyle);
            createCell(row, 7, benhNhan.getTiensubenh(), dataStyle);
        }

        // Tự động căn chỉnh kích thước cột
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
