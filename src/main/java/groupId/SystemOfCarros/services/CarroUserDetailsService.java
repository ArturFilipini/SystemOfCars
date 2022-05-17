package groupId.SystemOfCarros.services;

import groupId.SystemOfCarros.Carro.CarUser;
import groupId.SystemOfCarros.repository.CarUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarroUserDetailsService implements UserDetailsService {
    private final CarUserRepository carUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        CarUser CarUser = Optional.ofNullable(carUserRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Carro User not found"));
        return CarUser;
    }

}
