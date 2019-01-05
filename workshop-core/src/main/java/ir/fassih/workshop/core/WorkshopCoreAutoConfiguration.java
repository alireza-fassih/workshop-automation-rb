package ir.fassih.workshop.core;

import ir.fassih.workshop.core.localeutil.LocaleProperties;
import ir.fassih.workshop.core.localeutil.LocaleUtil;
import ir.fassih.workshop.core.rest.CommonsExceptionHandler;
import lombok.AllArgsConstructor;
import org.apache.coyote.http2.Http2Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "ir.fassih.workshop.core.entity")
@EnableJpaRepositories(basePackages = "ir.fassih.workshop.core.repository")
@Import(CommonsExceptionHandler.class)
public class WorkshopCoreAutoConfiguration {

    @ConditionalOnMissingBean(LocaleUtil.class)
    @EnableConfigurationProperties(LocaleProperties.class)
    @AllArgsConstructor(onConstructor = @__(@Autowired))
    protected static class LocalUtilAutoConfiguration {

        private final LocaleProperties properties;

        @Bean
        public LocaleUtil localeUtil() {
            return new LocaleUtil(properties);
        }

    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> sessionManagerCustomizer() {
        return server -> server.addConnectorCustomizers(connector -> connector.addUpgradeProtocol(new Http2Protocol()));
    }

}
