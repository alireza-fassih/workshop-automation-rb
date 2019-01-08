package ir.fassih.workshop.usermanagement.rest;

import ir.fassih.workshop.usermanagement.manager.UserManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/auth")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {

    private final UserManager userManager;


    @PostMapping("/login")
    public void login(@RequestBody LoginModel dto) {

        userManager.createToken(dto.getUsername(), dto.getPassword());
    }




    @Getter @Setter
    public static class LoginModel {
        private String username;
        private String password;
    }

}
