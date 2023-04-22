package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.EmailDTO;
import co.edu.uniquindio.homebliss.services.interfaces.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    @Override
    public void sendEmail(EmailDTO emailDTO) throws Exception{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject(emailDTO.getSubject());
        helper.setText(emailDTO.getBody(), true);
        helper.setTo(emailDTO.getAddressee());
        helper.setFrom("no_reply@dominio.com");

        javaMailSender.send(message);
    }
}
