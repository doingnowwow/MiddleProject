package kr.or.ddit.api;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Sendmail {

	// 메일내용설정

	String recipient = ""; // 받는사람
	String subject = ""; // 제목
	String body = ""; // 내용
	String filename = ""; // 경로...

	public void Send() throws MessagingException {

		// 보내는사람 메일 정보
		// 보내는 사람 메일은 메일들어가서 stmp 설정해줘야함

		String host = "smtp.naver.com";
		final String user = "s2555228"; // 아이디
		final String password = "youliana12!@"; // 비밀번호
		
		int port = 465;

	

		// Get the session object
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

			String un = user;
			String pw = password;

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(un, pw);
			}
		});

		session.setDebug(true);// for debug

		// Compose the message

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("s2555228@naver.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		message.setSubject(subject);// 메일보내기 제목
		message.setSentDate(new Date());

		// 파일 첨부시에는 body를 사용함

		// 메일내용
	//	message.setText("메일보내는중.");

		if (!filename.equals("")) {

			// 파일첨부를 위한 Multipart
			Multipart multipart = new MimeMultipart();

			// BodyPart를 생성
			BodyPart bodyPart = new MimeBodyPart();

			// 1. Multipart에 BodyPart를 붙인다.
			multipart.addBodyPart(bodyPart);

			// 2. 파일을 첨부한다.

			DataSource source = new FileDataSource(filename);
			bodyPart.setDataHandler(new DataHandler(source));
			bodyPart.setFileName(filename);

			// 이메일 메시지의 내용에 Multipart를 붙인다.
			message.setContent(multipart);
			Transport.send(message);
		} else {
			message.setText(body);
			Transport.send(message);
		}

	}

	public void setrecipient(String recipient) {
		this.recipient = recipient;

	}

	public void setsubject(String subject) {
		this.subject = subject;

	}

	public void setbody(String body) {
		this.body = body;

	}

	public void setFile(String filename) {
		this.filename = filename;
	}

	

}
