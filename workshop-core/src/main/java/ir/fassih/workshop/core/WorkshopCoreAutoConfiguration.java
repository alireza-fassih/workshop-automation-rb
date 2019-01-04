package ir.fassih.workshop.core;

import ir.fassih.workshop.core.localeutil.LocaleProperties;
import ir.fassih.workshop.core.localeutil.LocaleUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
}
