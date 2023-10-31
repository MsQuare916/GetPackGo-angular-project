//Java Program to Illustrate Creation Of
//Service implementation class
package com.stackroute.emailservice.service;


//Importing required classes
import com.stackroute.emailservice.model.EmailDetails;
import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
//Annotation
@Service
//Class
//Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

	@Autowired 
	private JavaMailSender javaMailSender;
	// Method 
	// To send a simple email
	public String sendSimpleMail(EmailDetails details)
	{

		// Try block to check for exceptions
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage= new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom("getpackg@gmail.com");
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject("Booking Info");

			// Sending the mail
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return "Error while Sending Mail";
		}
	}
	// Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage
            = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
 
        try {
 
            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper= new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("getpackg@gmail.com");
            mimeMessageHelper.setTo(details.getRecipient());
           // mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setText(details.getMsgBody(), true);
            mimeMessageHelper.setSubject("Booking Info with attachment");
 
            // Adding the attachment
            FileSystemResource file= new FileSystemResource(new File(details.getAttachment()));
 
            mimeMessageHelper.addAttachment(file.getFilename(), file);
 
            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully with attachment";
        }
 
        // Catch block to handle MessagingException
        catch (MessagingException e) {
 
            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
}