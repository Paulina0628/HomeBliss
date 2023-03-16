package co.edu.uniquindio.homebliss.services;


import co.edu.uniquindio.homebliss.dto.UserDTO;
import co.edu.uniquindio.homebliss.model.User;

public interface UserService {

    int createUser(UserDTO userDTO);

    int updateUser(int userCode, UserDTO userDTO);

    int deleteUser(int userCode);

    User getUser(int userCode);

}
