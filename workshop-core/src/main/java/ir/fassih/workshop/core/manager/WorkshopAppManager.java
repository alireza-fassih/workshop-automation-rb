package ir.fassih.workshop.core.manager;

import ir.fassih.workshop.core.entity.WorkshopAppEntity;
import ir.fassih.workshop.core.repository.WorkshopAppRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WorkshopAppManager {

    private WorkshopAppRepository repository;


    @Transactional(readOnly = true)
    public WorkshopAppEntity findByTitle(String title) {
        return repository.findOneByTitle(title);
    }
}
