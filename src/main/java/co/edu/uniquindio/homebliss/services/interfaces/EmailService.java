package co.edu.uniquindio.homebliss.services.interfaces;

import co.edu.uniquindio.homebliss.dto.EmailDTO;

public interface EmailService {

    void sendEmail(EmailDTO emailDTO) throws Exception;
}
