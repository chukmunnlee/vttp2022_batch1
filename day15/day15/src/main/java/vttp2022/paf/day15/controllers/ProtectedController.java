package vttp2022.paf.day15.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/protected/{view}")
public class ProtectedController {

    @GetMapping
    @PostMapping
    public ModelAndView post(@PathVariable String view, HttpSession sess) {

        String username = (String)sess.getAttribute("username");
        System.out.println(">>>> view: " + view);

        ModelAndView mvc = new ModelAndView();
        mvc.setViewName(view);
        mvc.addObject("username", username);
        mvc.setStatus(HttpStatus.OK);
        
        return mvc;
    }
    

    
}
