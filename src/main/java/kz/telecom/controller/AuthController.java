package kz.telecom.controller;


import kz.telecom.model.dto.SignInRequestDTO;
import kz.telecom.model.dto.TokenResponseDTO;
import kz.telecom.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponseDTO> signIn(@RequestBody SignInRequestDTO signInRequestDTO) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequestDTO));
    }

    @PostMapping("/access-token")
    public ResponseEntity<TokenResponseDTO> accessToken(@CookieValue(name = "refresh-token") String refreshToken) {
        //log.info("IN accessToken - get access token with refresh token");
        return ResponseEntity.ok(authenticationService.accessToken(refreshToken));
    }

//    @PostMapping("/signup")
//    public ResponseEntity<HttpStatus> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
//        authenticationService.createUser(signUpRequestDTO);
//        return ResponseEntity.ok(HttpStatus.CREATED);
//    }
//
//    @PostMapping("/password")
//    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO){
//        authenticationService.resetPassword(resetPasswordDTO);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

}
