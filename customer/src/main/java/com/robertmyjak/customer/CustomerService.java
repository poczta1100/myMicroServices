package com.robertmyjak.customer;


import com.robertmyjak.aqmp.RabbitMQMessageProducer;
import com.robertmyjak.clients.fraud.FraudCheckResponse;
import com.robertmyjak.clients.fraud.FraudClient;
import com.robertmyjak.clients.notification.NotificationClient;
import com.robertmyjak.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraud()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to this amazing fairytale...",
                        customer.getFirstName())
        );
        notificationClient.sendNotification(
                notificationRequest
        );


        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

    }
}