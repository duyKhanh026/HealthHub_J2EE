package com.healthhub.hospital.demo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.healthhub.hospital.config.AppConfig;
import com.healthhub.hospital.dao.BenhNhanDAO;


public class BenhNhanDAOtest {
	public static void main(String[] args) throws SQLException {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        BenhNhanDAO dao = context.getBean(BenhNhanDAO.class);

        List<String> names = dao.getBenhNhan();

        for (String name : names) {

            System.out.println("benh nhan name: " + name);
        }
    }
}
