package com.healthhub.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.healthhub.hospital.Entity.ChatBox.ChatCompletionReponse;
import com.healthhub.hospital.Entity.ChatBox.ChatCompletionRequest;

@CrossOrigin(origins = "*") 
@RestController
public class RestControll {
	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/hitOpenaiApi")
    public String getOpenaiResponse(@RequestBody String prompt) {
//        String doctorInfo = getDoctorInfoFromPrompt(prompt);

        String context = "Bạn là nhân viên tư vấn tại web đặt lịch khám trong bệnh viện tư nhân với hotline: 0342205794, tư vấn bệnh và trả lời các câu hỏi về phòng khám tư nhân DanhDuyNam với đội ngũ chuyên viên y tế cực kỳ năng suất vào chuyên nghiệp\r\n"
        		+ ", bác sĩ đảm nhận việc khám cho từng bệnh nhân là Trần Khánh Duy, với 21 năm exp trong phòng khám tư nhân \r\n"
        		+ ", lịch của dr.Duy tuần không có lịch khám nào được đặt và được cập nhật hằng tuần và lịch "
        		+ "làm việc. Hướng dẫn đặt lịch truy cập Emục 'Đặt lịch'. Câu hỏi:";
        String finalPrompt = context + " " + prompt;

        // Tạo yêu cầu ChatCompletionRequest
        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest("gpt-3.5-turbo", finalPrompt);

        // Gửi yêu cầu đến OpenAI APIs
        ChatCompletionReponse response = restTemplate.postForObject(
                "https://api.openai.com/v1/chat/completions",
                chatCompletionRequest,
                ChatCompletionReponse.class
        );
        
        // Trả về nội dung câu trả lời từ API
        System.out.println(response.getChoices().get(0).getMessage().getContent());
        return response.getChoices().get(0).getMessage().getContent();
    }
}
