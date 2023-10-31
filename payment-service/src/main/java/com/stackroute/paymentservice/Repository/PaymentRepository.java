package com.stackroute.paymentservice.Repository;

import com.stackroute.paymentservice.Model.PaymentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends MongoRepository<PaymentModel,String> {

}
