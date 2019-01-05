package ir.fassih.workshop.usermanagement;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "ir.fassih.workshop.usermanagement.entity")
@EnableJpaRepositories(basePackages = "ir.fassih.workshop.usermanagement.repository")
@ComponentScan(basePackages = { "ir.fassih.workshop.usermanagement.manager", "ir.fassih.workshop.usermanagement.filter" })
public class UserManagementAutoConfiguration {

}
