package ir.fassih.workshop.core.manager;

import ir.fassih.workshop.core.repository.RsaKeyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RsaKeyManager {

    protected RsaKeyRepository repository;

}
