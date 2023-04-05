package co.edu.uniquindio.homebliss.services.interfaces;

import co.edu.uniquindio.homebliss.dto.EmailDTO;

public interface EmailService {
    String enviarEmail(EmailDTO emailDTO);
}
