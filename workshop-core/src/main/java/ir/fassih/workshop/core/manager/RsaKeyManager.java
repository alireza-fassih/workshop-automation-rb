package ir.fassih.workshop.core.manager;

import ir.fassih.workshop.core.entity.RsaKeyEntity;
import ir.fassih.workshop.core.repository.RsaKeyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RsaKeyManager {

    protected RsaKeyRepository repository;



    @Transactional(readOnly = true)
    public RsaKeyEntity loadLastRsaKey() {
        return repository.findOneByOrderByIdDesc();
    }
}
