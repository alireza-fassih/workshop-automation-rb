package ir.fassih.workshop.core.rest;

import ir.fassih.workshop.core.holder.AppHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/rest/guest/app-setting")
public class GuestAppSettingController {

    @GetMapping("/login")
    public Map<String, Object> getLoginSetting() {
        return Collections.singletonMap("app", AppHolder.getAppModel());
    }

}
