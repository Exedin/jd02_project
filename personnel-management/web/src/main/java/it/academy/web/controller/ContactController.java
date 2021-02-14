package it.academy.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes
public class ContactController {

    @RequestMapping("/contact")
    public String showContacts(Model m) {
        return "contact";
    }
}