package ttajiri.example.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import ttajiri.example.service.ReactService;

import java.util.Map;

@Controller
public class IndexController {

    private ReactService service;

    public IndexController(ReactService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("test", service.render().asString());
        return "index";
    }
}
