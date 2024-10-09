package com.healthhub.hospital;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.healthhub.hospital.Repository.BenhNhanRepository;
import com.healthhub.hospital.model.BenhNhan;

@SpringBootApplication
public class HealthHubApplication {

	@Autowired
    private BenhNhanRepository contactDAO;

    public void testContactDAO(){
        List<BenhNhan> contacts = contactDAO.findAll();

        Iterator<BenhNhan> i = contacts.iterator();
        while(i.hasNext()){
            BenhNhan contact = i.next();
            System.out.println("Id: " + contact.getMaBN() + "Contact name: " + contact.getHoTen() + " ; Email: " + contact.getEmail());
        }
    }
	public static void main(String[] args) {
		SpringApplication.run(HealthHubApplication.class, args);
	}

}
