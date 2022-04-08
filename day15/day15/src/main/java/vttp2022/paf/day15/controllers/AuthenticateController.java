package vttp2022.paf.day15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.paf.day15.services.UsersService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/authenticate")
public class AuthenticateController {

    @Autowired
    private UsersService usersSvc;

    @PostMapping
    public ModelAndView postMethodName(@RequestBody MultiValueMap<String, String> payload) {

        String username = payload.getFirst("username");
        String password = payload.getFirst("password");

        System.out.printf("+++ username: %s, password: %s\n", username, password);

        final ModelAndView mvc = new ModelAndView();

        if (!usersSvc.authenticate(username, password)) {
            mvc.setViewName("error");
            mvc.setStatus(HttpStatus.FORBIDDEN);

        } else {
            mvc.setViewName("hello");
            mvc.setStatus(HttpStatus.OK);
            mvc.addObject("username", username);
        }
        
        return mvc;
    }
    
    
}
