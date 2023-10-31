package com.stackroute.paymentservice.Controller;

import com.stackroute.paymentservice.Exception.PaymentAlreadyExistsException;
import com.stackroute.paymentservice.Exception.PaymentNotFoundException;
import com.stackroute.paymentservice.Model.PaymentModel;
import com.stackroute.paymentservice.Service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payNow")
    public ResponseEntity<Object> newPayment(@RequestBody PaymentModel paymentModel){
        try {
            log.info("New payment initiated");
            return new ResponseEntity<Object>(paymentService.addPayment(paymentModel), HttpStatus.OK);
        } catch (PaymentAlreadyExistsException e) {
            log.error("Payment already exists, try to initiate new payment");
            return new ResponseEntity<Object>("Payment already exists, try to initiate new payment",HttpStatus.CONFLICT);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    //mostly unused
    @PutMapping("/updatePayment")
    public ResponseEntity<Object> updatePayment(@RequestBody PaymentModel paymentModel){
        try {
            paymentService.updatePayment(paymentModel);
            log.info("Payment updated");
            return new ResponseEntity<Object>("Payment updated", HttpStatus.OK);
        } catch (PaymentNotFoundException e) {
            log.error("Payment not found, try to initiate new payment");
            return new ResponseEntity<Object>("Payment not found, try to initiate new payment",HttpStatus.CONFLICT);
        } catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletePayment/{paymentId}")
    public ResponseEntity<Object> deletePayment(@PathVariable String paymentId){
        try {
            paymentService.deletePayment(paymentId);
            log.info("payment removed");
            return new ResponseEntity<Object>("payment removed", HttpStatus.OK);
        } catch (PaymentNotFoundException e) {
            log.error("Payment not found");
            return new ResponseEntity<Object>("Payment not found",HttpStatus.CONFLICT);
        } catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPayments")
    public ResponseEntity<Object> getAllPayments(){
        try {
            log.info("Payments list");
            return new ResponseEntity<Object>(paymentService.getPayments(), HttpStatus.OK);
        } catch (PaymentNotFoundException e) {
            log.info("No entries");
            return new ResponseEntity<Object>("0 entries",HttpStatus.CONFLICT);
        } catch(Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPayment/{paymentId}")
    public ResponseEntity<Object> getPaymentByPaymentId(@PathVariable String paymentId){
        try {
            return new ResponseEntity<Object>(paymentService.getPaymentByPaymentId(paymentId), HttpStatus.OK);
        } catch (PaymentNotFoundException e) {
            return new ResponseEntity<Object>("Payment not found",HttpStatus.CONFLICT);
        } catch(Exception e){
            return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getPayments/{userEmailId}")
    public ResponseEntity<Object> getPaymentByUserEmailId(@PathVariable String userEmailId){
        try {
            return new ResponseEntity<Object>(paymentService.getPaymentsByEmailId(userEmailId), HttpStatus.OK);
        } catch (PaymentNotFoundException e) {
            return new ResponseEntity<Object>("Payment not found",HttpStatus.CONFLICT);
        } catch(Exception e){
            return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    
//    @GetMapping("/hello")
//	public String hello() {
//		return "hello world";
 //}
	
}
