package ma.learning.springsecurity6.resources.auth;

import lombok.RequiredArgsConstructor;
import ma.learning.springsecurity6.config.JwtService;
import ma.learning.springsecurity6.entities.Role;
import ma.learning.springsecurity6.entities.User;
import ma.learning.springsecurity6.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthRes register(RegisterReq request) {
        var user = User.builder()
                        .email(request.getEmail())
                        .firstname(request.getFirstname())
                        .lastname(request.getLastname())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.USER)
                        .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthRes.builder()
                .token(jwtToken)
                .build();
    }

    public AuthRes authenticate(AuthReq request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthRes.builder()
                .token(jwtToken)
                .build();
    }
}
