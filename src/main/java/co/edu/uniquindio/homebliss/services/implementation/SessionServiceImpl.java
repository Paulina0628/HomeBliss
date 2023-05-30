package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.SessionDTO;
import co.edu.uniquindio.homebliss.dto.TokenDTO;
import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.model.Moderator;
import co.edu.uniquindio.homebliss.model.User;
import co.edu.uniquindio.homebliss.repositories.ClientRepository;
import co.edu.uniquindio.homebliss.repositories.ModeratorRepository;
import co.edu.uniquindio.homebliss.security.UserDetailsImpl;
import co.edu.uniquindio.homebliss.security.services.JwtService;
import co.edu.uniquindio.homebliss.services.interfaces.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    @Autowired
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModeratorRepository moderatorRepository;

    @Override
    public User getCurrentUser() {

        try {
            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !auth.isAuthenticated()) {
                throw new UsernameNotFoundException("El usuario no existe.");
            }

            String email = auth.getName();
            Optional<Client> client = clientRepository.findByEmail(email);
            if(client.isEmpty()){
                Optional<Moderator> admin = moderatorRepository.findByEmail(email);
                if(admin.isEmpty())
                    throw new UsernameNotFoundException("El usuario no existe.");
                return admin.get();
            }else{
                return client.get();
            }
        } catch (Throwable ex) {
            return null;
        }

    }

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
