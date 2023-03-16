package co.edu.uniquindio.homebliss.services;

import co.edu.uniquindio.homebliss.dto.SessionDTO;
import co.edu.uniquindio.homebliss.dto.TokenDTO;

public interface SessionService {

    TokenDTO login(SessionDTO sessionDTO);

    void logout(int userCode);
}
