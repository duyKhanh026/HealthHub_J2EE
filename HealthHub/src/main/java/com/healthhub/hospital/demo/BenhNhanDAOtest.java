package com.healthhub.hospital.demo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.healthhub.hospital.config.AppConfig;
import com.healthhub.hospital.dao.BenhNhanDAO;


public class BenhNhanDAOtest {
	public static void main(String[] args) throws SQLException {

        @SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        BenhNhanDAO dao = context.getBean(BenhNhanDAO.class);

        List<String> names = dao.getBenhNhan();

        for (String name : names) {

            System.out.println("benh nhan name: " + name);
        }

        // SqlRowSet
        SqlRowSet rowSet = dao.getBenhNhanSqlRowSet();

        while (rowSet.next()) {
           
            System.out.println("-----");
            Long empId = rowSet.getLong("MaBN"); // Index = 1
            String empNo = rowSet.getString(2); // Index = 2
            String empName = rowSet.getString("Hoten"); // Index = 3

            System.out.println("EmpID: " + empId + ", EmpNo: " + empNo + ", EmpName:" + empName);
        }
    }
}
