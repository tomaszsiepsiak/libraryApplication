package com.klb.scheduler;

import com.klb.config.AppConfig;
import com.klb.entity.User;
import com.klb.service.EmailService;
import com.klb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvService {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedDelay = 10000)
    public void sendAdv() {
        List<User> users = userService.findAll();

        if(users == null)
            return;

        for(User user: users) {
            emailService.sendEmail(AppConfig.EMAIL_FROM, user.getEmail(), "Reklama",
                    "Najlepsza biblioteka w miescie");
        }

    }
}
