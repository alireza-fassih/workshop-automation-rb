package ir.fassih.workshop.usermanagement.rest;

import com.auth0.jwt.JWT;
import ir.fassih.workshop.core.jwt.JwtAlgorithm;
import ir.fassih.workshop.core.model.AuthModel;
import ir.fassih.workshop.usermanagement.manager.UserManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/rest/auth")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {

    private final UserManager  userManager;
    private final JwtAlgorithm algorithm;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginModel dto) {
        AuthModel token = userManager.createToken(dto.getUsername(), dto.getPassword());
        String t = JWT.create()
                .withClaim("userId", token.getUserId())
                .withClaim("appId", token.getAppId())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                .sign(algorithm.getAlgorithm());
        return new LoginResponse(t);
    }


    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginResponse {
        private String token;
    }


    @Getter @Setter
    public static class LoginModel {
        private String username;
        private String password;
    }

}
