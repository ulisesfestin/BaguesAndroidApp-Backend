package um.edu.bagues.Auth;

import um.edu.bagues.Jwt.JwtService;
import um.edu.bagues.User.Role;
import um.edu.bagues.User.User;
import um.edu.bagues.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        Long userId = userRepository.findByUsername(request.getUsername()).get().getId();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .id(userId)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(Role.CLIENT)
                .build();

        userRepository.save(user);

        Long userId = userRepository.findByUsername(request.getUsername()).get().getId();

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .id(userId)
                .build();
    }
}
