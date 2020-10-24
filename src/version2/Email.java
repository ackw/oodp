import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// send notifications to students - trial code

public class Email {

	public static void main(String[] args) {

		final String username = "oatarabica@gmail.com";
		final String password = "absolutmunch";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("oatarabica@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("andrelchew@icloud.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Student,"
				+ "\n\n You've registered!");

			Transport.send(message);

			System.out.println("Notification sent!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}