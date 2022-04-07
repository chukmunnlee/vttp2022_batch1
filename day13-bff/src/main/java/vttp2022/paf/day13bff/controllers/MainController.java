package vttp2022.paf.day13bff.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.paf.day13bff.models.BFF;
import vttp2022.paf.day13bff.services.BFFService;

@Controller
@RequestMapping(path={"/", "/index.html"})
public class MainController {

    @Autowired
    private BFFService bffSvc;

    @GetMapping
    public ModelAndView getIndex() {

        ModelAndView mvc = new ModelAndView();

        mvc.setViewName("index");
        List<BFF> bffs = bffSvc.getAllBffs();
        mvc.addObject("bffs", bffs);

        return mvc;
    }
}
