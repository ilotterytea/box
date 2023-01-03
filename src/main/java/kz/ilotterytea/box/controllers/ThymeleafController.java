package kz.ilotterytea.box.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * A controller for Thymeleaf web templates.
 * @author ilotterytea
 * @since 1.0
 */
@Controller
public class ThymeleafController {
    @GetMapping("/")
    public String serveIndex(Model model) {
        model.addAttribute("test", "test");

        return "index";
    }
}
