package ir.fassih.workshop.usermanagement.manager;

import ir.fassih.workshop.core.exceptions.RestException;
import ir.fassih.workshop.core.holder.AppHolder;
import ir.fassih.workshop.core.model.AuthModel;
import ir.fassih.workshop.usermanagement.entity.UserEntity;
import ir.fassih.workshop.usermanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserManager {

    private UserRepository repository;
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public AuthModel createToken(String username, String password) {
        return repository.findOneByIdAppIdAndUsername(AppHolder.getAppId(), username)
                .flatMap(u -> checkPasswordAndCreateAuth(u, password))
                .orElseThrow(() -> new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "auth_invalid_credentials"));
    }

    private Optional<AuthModel> checkPasswordAndCreateAuth(UserEntity u, String password) {
        if (passwordEncoder.matches(password, u.getPassword())) {
            return Optional.of(AuthModel.builder()
                    .appId(u.getId().getAppId())
                    .userId(u.getId().getId())
                    .build());
        }
        return Optional.empty();
    }
}
