package sendemail;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;
import java.io.File; 

public class EmailSender {
	public String email ; 
	
	
	public EmailSender (String email ) {
		this.email = email ; 
		
	}
	
	public void send_email_with_attachment (String subject , String content , String file ) {
		final String fromEmail = "rrok.toplana2@gmail.com";         // replace with your email
        final String password = "htho lpov skhj zqbj";       // app password if 2FA is enabled
//        final String toEmail = "kristjan.toplana2@gmail.com";    // recipient email
        final String toEmail = this.email ;    // recipient email
        
        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create message
        	// Create message
        	Message message = new MimeMessage(session);
        	message.setFrom(new InternetAddress(fromEmail));
        	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        	message.setSubject(subject);

        	// Create the message part
        	MimeBodyPart textBodyPart = new MimeBodyPart();
        	textBodyPart.setText(content);

        	// Create the attachment part
        	MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        	String filename = (new File (file)).getAbsolutePath() ; // replace with your file path
        	attachmentBodyPart.attachFile(filename);

        	// Combine both parts into a multipart
        	Multipart multipart = new MimeMultipart();
        	multipart.addBodyPart(textBodyPart);
        	multipart.addBodyPart(attachmentBodyPart);

        	// Set the content of the message
        	message.setContent(multipart);

        	// Send message
        	Transport.send(message);
        	System.out.println("Email with attachment sent successfully!");


        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public void send_email (String subject , String content ) {
		final String fromEmail = "rrok.toplana2@gmail.com";         // replace with your email
        final String password = "htho lpov skhj zqbj";       // app password if 2FA is enabled
//        final String toEmail = "kristjan.toplana2@gmail.com";    // recipient email
        final String toEmail = this.email ;    // recipient email
        
        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject(subject );
            message.setText(content ) ; 
            
            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
    public static void main(String[] args) {
        final String fromEmail = "rrok.toplana2@gmail.com";         // replace with your email
        final String password = "htho lpov skhj zqbj";       // app password if 2FA is enabled
        final String toEmail = "kristjan.toplana2@gmail.com";    // recipient email
        
        EmailSender sender_test = new EmailSender(toEmail) ; 
        sender_test.send_email_with_attachment("Test Subject", "Content Test", "file2.tex" ); 
        if (true ) {
        	return ; 
        	
        }
        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject("Test Email from Java");
            message.setText("Hello!\n\nThis is a test email sent from Java code using JavaMail.");

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
