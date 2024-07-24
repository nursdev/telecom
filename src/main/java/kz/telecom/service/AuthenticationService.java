package kz.telecom.service;


import kz.telecom.model.dto.SignInRequestDTO;
import kz.telecom.model.dto.TokenResponseDTO;
import kz.telecom.model.entity.User;
import kz.telecom.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationService(UserRepository userRepository, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenResponseDTO signIn(SignInRequestDTO signInRequestDTO) {
        User user = userRepository.findByUsername(signInRequestDTO.username())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("user with username %s not found", signInRequestDTO.username())));

        var access = jwtService.generateToken(user);
        var refresh = jwtService.generateRefreshToken(new HashMap<>(), user);
        if(!passwordEncoder.matches(signInRequestDTO.password(), user.getPassword())){
            throw new IllegalArgumentException("password incorrect");
        }
        return new TokenResponseDTO(access, refresh);
    }

    public TokenResponseDTO accessToken(String refreshToken) {
        Long userId = Long.parseLong(jwtService.extractID(refreshToken));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("employee with userId %s not found", userId)));
        if (!jwtService.isTokenExpired(refreshToken)) {
            var accessToken = jwtService.generateToken(user);
            TokenResponseDTO tokens = new TokenResponseDTO(accessToken, refreshToken);
            return tokens;
        }
        return null;
    }

}
