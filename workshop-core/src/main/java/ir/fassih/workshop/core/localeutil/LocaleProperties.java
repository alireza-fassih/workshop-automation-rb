package ir.fassih.workshop.core.localeutil;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "workshop.locale")
public class LocaleProperties {


    private String propertiesName = "classpath*:/*-locale-msg.properties";

    private String appTimeZone = "Asia/Tehran";

}
