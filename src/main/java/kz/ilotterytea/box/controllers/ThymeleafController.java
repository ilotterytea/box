package kz.ilotterytea.box.controllers;

import kz.ilotterytea.box.BoxProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * A controller for Thymeleaf web templates.
 * @author ilotterytea
 * @since 1.0
 */
@Controller
public class ThymeleafController {
    private BoxProperties properties;

    @Autowired
    public ThymeleafController(BoxProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/")
    public String serveIndex(Model model, HttpServletRequest request) {
        model.addAttribute("title", properties.getBoxName());
        model.addAttribute("web_url", request.getRequestURL().toString() + "web");

        return "index";
    }
}
