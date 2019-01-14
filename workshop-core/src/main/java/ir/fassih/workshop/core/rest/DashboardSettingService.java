package ir.fassih.workshop.core.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/rest/dashboard-setting")
public class DashboardSettingService {


    @GetMapping
    public Map<String, Object> getAppSetting() {
        return Collections.emptyMap();
    }

}
