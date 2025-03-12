package com.reinertisa.supapi.event.listener;

import com.reinertisa.supapi.event.UserEvent;
import com.reinertisa.supapi.service.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {
    private final EmailService emailService;

    public UserEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener
    public void onUserEvent(UserEvent event) {
        switch (event.getType()) {
            case REGISTRATION ->
                    emailService.sendNewAccountEmail(
                            event.getUser().getFirstName(),
                            event.getUser().getEmail(),
                            (String)event.getData().get("key")
                    );
            case RESET_PASSWORD ->
                emailService.sendPasswordResetEmail(
                        event.getUser().getFirstName(),
                        event.getUser().getEmail(),
                        (String)event.getData().get("key")
                );
            default -> {}
        }
    }
}
