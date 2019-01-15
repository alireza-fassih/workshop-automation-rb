package ir.fassih.workshop.usermanagement.rest;

import com.auth0.jwt.JWT;
import ir.fassih.workshop.core.jwt.JwtAlgorithm;
import ir.fassih.workshop.core.localeutil.LocaleUtil;
import ir.fassih.workshop.core.model.AuthModel;
import ir.fassih.workshop.core.rest.model.CommonsResponse;
import ir.fassih.workshop.usermanagement.manager.UserManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Date;

@RestController
@RequestMapping("/rest/auth")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {

    private final SecureRandom random = new SecureRandom();

    private final UserManager  userManager;
    private final JwtAlgorithm algorithm;
    private final LocaleUtil   localeUtil;


    @PostMapping("/login")
    public ResponseEntity<CommonsResponse> login(@RequestBody LoginModel dto, HttpServletResponse response, HttpServletRequest request) {
        AuthModel token = userManager.createToken(dto.getUsername(), dto.getPassword());
        Cookie cookie = createCookie("token", createJwt(token), request);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        response.addCookie(createCookie("scrf-token", createRandomString(20), request));
        return ResponseEntity.ok(new CommonsResponse(localeUtil.getString("logged_in_successFull")));
    }


    private Cookie createCookie(String name, String value,HttpServletRequest request) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/rest");
        cookie.setDomain(request.getServerName());
        return cookie;
    }


    private String createRandomString(int length) {
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return  new Base32().encodeAsString(bytes);
    }

    private String createJwt(AuthModel token) {
        return JWT.create()
            .withClaim("userId", token.getUserId())
            .withClaim("appId", token.getAppId())
            .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
            .sign(algorithm.getAlgorithm());
    }



    @Getter @Setter
    private static class LoginModel {
        private String username;
        private String password;
    }

}
