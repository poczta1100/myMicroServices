package com.robertmyjak.notification;


import com.robertmyjak.clients.fraud.FraudCheckResponse;
import com.robertmyjak.clients.fraud.FraudClient;
import com.robertmyjak.clients.notification.NotificationClient;
import com.robertmyjak.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerName())
                        .sender("Robert the master of disaster")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}