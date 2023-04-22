package co.edu.uniquindio.homebliss.security.services;

import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.model.Moderator;
import co.edu.uniquindio.homebliss.repositories.ClientRepository;
import co.edu.uniquindio.homebliss.repositories.ModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModeratorRepository moderatorRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Client> client = clientRepository.findByEmail(email);
        if(client.isEmpty()){
            Optional<Moderator> admin = moderatorRepository.findByEmail(email);
            if(admin.isEmpty())
                throw new UsernameNotFoundException("El usuario no existe");
            return UserDetailsImpl.build(admin.get());
        }else{
            return UserDetailsImpl.build(client.get());
        }
    }
}
