package han.gyoul.shop.member.service;

import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Service;

import han.gyoul.shop.member.dao.MemberDAO;
import han.gyoul.shop.member.dto.MemberDTO;

@Service
public class MemberServicelmpl implements MemberService{
	
	@Inject
	private MemberDAO memberDAO;
	

	@Override
	public void updateMember(MemberDTO memberDTO) throws Exception {
		memberDAO.updateMember(memberDTO);
		
	}

	@Override
	public int removeMember(int mb_no) throws Exception {
		return memberDAO.removeMember(mb_no);
	}

	@Override
	public MemberDTO memberRead(MemberDTO memberDTO) throws Exception {
		return memberDAO.memberRead(memberDTO);
	}

	@Override
	public String idUnique(String mb_id) throws Exception {
		return memberDAO.idUnique(mb_id);
		
	}

	@Override
	public String nickNameUnique(String mb_nickname) throws Exception {
		return memberDAO.nickNameUnique(mb_nickname);
		
	}

	@Override
	public void sendEmail(String email, String authNum) {
		String host = "smtp.gmail.com";
		String subject = "한결단결 인증번호 전달";
		String fromName = "한결단결 관리자";
		String from = "bobgoo69@gmail.com"; // 보내는 메일?		
		String to1 = email;
		
		String content = "인증번호 [" + authNum + "]";
		
		try {
			Properties props = new Properties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.auth", "true");
			Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication("bobgoo69@gmail.com","c1580718");
				}
			});
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName, "UTF-8", "B")));
			InternetAddress[] address1 = {new InternetAddress(to1)};
			msg.setRecipients(Message.RecipientType.TO, address1);
			msg.setSubject(subject);
			msg.setSentDate(new java.util.Date());
			msg.setContent(content, "text/html;charset=euc-kr");
			Transport.send(msg);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String RandomNum() {
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i <= 6; i++) {
			int n = (int)(Math.random()*10);
			buffer.append(n);
		}
		return buffer.toString();
	}




}
