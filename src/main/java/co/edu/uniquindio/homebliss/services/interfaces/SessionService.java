package co.edu.uniquindio.homebliss.services.interfaces;

import co.edu.uniquindio.homebliss.dto.SessionDTO;
import co.edu.uniquindio.homebliss.dto.TokenDTO;
import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.model.User;

public interface SessionService {

    TokenDTO login(SessionDTO sessionDTO);

    void logout(int userCode);

    User getCurrentUser();

}
