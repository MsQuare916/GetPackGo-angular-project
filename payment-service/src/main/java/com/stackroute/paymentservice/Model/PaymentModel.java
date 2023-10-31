package com.stackroute.paymentservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "PaymentInfo")
@ToString

public class PaymentModel {

    @Id
    private String paymentId;
    private String orderId;
    private String userEmailId;
    private float totalPrice;
    private String status;
    private String razorpayOrderId;


}
