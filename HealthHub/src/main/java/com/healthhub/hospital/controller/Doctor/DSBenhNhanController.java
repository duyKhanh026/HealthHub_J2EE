package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.config.AppConfig;
import com.healthhub.hospital.dao.BenhNhanDAO;
import com.healthhub.hospital.model.BenhNhan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DSBenhNhanController {

    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    @GetMapping({ "/DSBenhNhan" })
    public String ListBenhNhan(Model model) {

        BenhNhanDAO dao = context.getBean(BenhNhanDAO.class);
        SqlRowSet rowSet = dao.getinforBenhNhan();
        List<BenhNhan> benhnhans = new ArrayList<BenhNhan>();
        while (rowSet.next()) {
            BenhNhan bn = new BenhNhan();
            bn.setMaBN(rowSet.getInt(1));
            bn.setHoTen(rowSet.getString(2));
            bn.setNgaySinh(rowSet.getDate(3));
            bn.setGioitinh(rowSet.getString(4));
            bn.setSDT(rowSet.getString(5));
            bn.setEmail(rowSet.getString(6));
            bn.setDiachi(rowSet.getString(7));
            bn.setTiensubenh(rowSet.getString(8));
            benhnhans.add(bn);
        }
        model.addAttribute("benhnhans", benhnhans);

        return "Doctor/DSBenhNhan";
    }
}
