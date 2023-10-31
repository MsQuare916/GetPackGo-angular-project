package com.stackroute.paymentservice.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stackroute.paymentservice.Model.PaymentModel;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

@Slf4j
@Service


public class RazorpayOrderService {
    @Value("${rzp_key_id}")
    private String defaultPageTitle;


    public String newRazorpayOrder(PaymentModel paymentModel) throws RazorpayException {
        float totPrice= (float) (Math.round(paymentModel.getTotalPrice()*100)/100);

        RazorpayClient razorpay;
        razorpay = new RazorpayClient("rzp_test_WQNKvWQvERoXK4", "z8C0IJvVik9EYLujceaq4WXm"); //razorpay client credentials

        JSONObject orderRequest = new JSONObject();

        orderRequest.put("amount", totPrice*100); // amount in INR
        orderRequest.put("currency", "INR");
        String receiptId= String.valueOf(paymentModel.getPaymentId());
        orderRequest.put("receipt", receiptId);
        Order order = razorpay.orders.create(orderRequest);
        return (String) order.get("id");
    }

}
