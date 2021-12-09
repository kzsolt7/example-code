package com.cavityeye.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {
            "/about",
            "/device-list",
            "/user-list",
            "/add-new-user",
            "/edit-user/**",
            "/notification",
            "/add-new-notification-group",
            "/edit-notification-group/**",
            "/permission",
            "/add-new-permission-group",
            "/edit-permission-group/**",
            "/export-import",
            "/realtime-monitoring",
            "/clamp-analysis",
            "/network-management",
            "/system-update",
    })
    public String aboutForward() {
        return "forward:/";
    }

}
