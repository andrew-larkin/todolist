package main.controller;

import main.service.ServiceDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    private final ServiceDefault serviceDefault;

    public DefaultController(ServiceDefault serviceDefault) {
        this.serviceDefault = serviceDefault;
    }

    @RequestMapping("/")
    public String index(Model model) {
        return serviceDefault.indexing(model);
    }
}