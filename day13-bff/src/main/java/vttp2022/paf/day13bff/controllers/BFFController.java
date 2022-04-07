package vttp2022.paf.day13bff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.paf.day13bff.models.BFF;
import vttp2022.paf.day13bff.services.BFFException;
import vttp2022.paf.day13bff.services.BFFService;

import static vttp2022.paf.day13bff.models.ConversionUtils.*;

@Controller
@RequestMapping(path="/bff")
public class BFFController {

    @Autowired
    private BFFService bffSvc;
    
    @PostMapping
    public ModelAndView postBff(@RequestBody MultiValueMap<String, String> form) {

        BFF bff = convert(form);

        System.out.println(">>>>>> bff: " + bff);

        ModelAndView mvc = new ModelAndView();

        try {
            bffSvc.addNewBff(bff);
            mvc.addObject("message", "%s has been added as one of your bff".formatted(bff.getName()));
            mvc.addObject("bffs", bffSvc.getAllBffs());
        } catch (BFFException ex) {
            mvc.addObject("message", "Error: %s".formatted(ex.getReason()));
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            ex.printStackTrace();
        }

        mvc.setViewName("index");

        return mvc;
    }
}
