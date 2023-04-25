package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.SessionDTO;
import co.edu.uniquindio.homebliss.dto.TokenDTO;
import co.edu.uniquindio.homebliss.security.UserDetailsImpl;
import co.edu.uniquindio.homebliss.security.services.JwtService;
import co.edu.uniquindio.homebliss.services.interfaces.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    @Autowired
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public TokenDTO login(SessionDTO sessionDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        sessionDTO.getEmail(),
                        sessionDTO.getPassword())

        );
        UserDetails user = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(user);
        return new TokenDTO(jwtToken);
    }

    @Override
    public void logout(int userCode) {

    }

}
