package se.casdev.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import se.casdev.mail.attribute.Subject;
import se.casdev.mail.message.EmailMessage;
import se.casdev.userInfo.Customer;
import se.casdev.warnings.Messenge;
import se.casdev.warnings.Warning;

public class SendEmail {

	public static boolean send(Customer user, String adminPassword,
			Subject subject) {

		Properties props = new Properties();

		final String email = ""; // TODO: Set email here
		final String password = ""; // TODO: Set password here
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return getAuthentication(email, password);
					}
				});

		Message message = addMessage(session, email, user.getEmail(),
				subject.getSubject(user),
				EmailMessage.getMessaage(subject, user));

		if (message == null) {
			Warning.addClientMessage(new Messenge("mail.content.empty",
					"The content of the email seem to be empty"));
		}

		try {
			Transport.send(message);

			return true;
		} catch (MessagingException e) {
			Warning.addClientMessage(new Messenge("mail.sent.fail",
					"Email couldn't be sent"));
			return false;
		}

	}

	private static PasswordAuthentication getAuthentication(String email,
			String password) {
		final String from = email;
		final String fromPassword = password;

		return new PasswordAuthentication(from, fromPassword);
	}

	private static Message addMessage(Session session, String email,
			String emailTo, String subject, String basicMessage) {

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailTo));
			message.setSubject(subject);
			message.setText(basicMessage);

			return message;

		} catch (AddressException e) {
			Warning.addClientMessage(new Messenge("mail.address.wrong",
					"Wrongly formatted address encountered"));
		} catch (MessagingException e) {
			Warning.addClientMessage(new Messenge("email.message.wrong",
					"Something is wrong with the message of this email"));
		}

		return null;

	}

}
