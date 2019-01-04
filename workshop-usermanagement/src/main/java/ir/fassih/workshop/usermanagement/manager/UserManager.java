package ir.fassih.workshop.usermanagement.manager;

import ir.fassih.workshop.usermanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserManager {

    private UserRepository repository;

}
