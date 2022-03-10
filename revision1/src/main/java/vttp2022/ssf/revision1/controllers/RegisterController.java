package vttp2022.ssf.revision1.controllers;

import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

// FQCN
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2022.ssf.revision1.Revision1Application;
import vttp2022.ssf.revision1.models.Registrations;
import vttp2022.ssf.revision1.services.RegistrationService;

@Controller
@RequestMapping(path="/register")
public class RegisterController {

    Logger logger = Logger.getLogger(Revision1Application.class.getName());
    
    @Autowired
    private RegistrationService regSvc;

    @Autowired
    private ApplicationArguments args;

    // @Value reads from application.properties
    @Value("${app.version}")
    String appVersion;

    @PostConstruct
    public void init() {
        logger.entering("init", "");
        logger.info(">>>>> value of --data: %s\n".formatted(args.getOptionValues("data")));
        logger.exiting("init", "");
    }

    @GetMapping("/version")
    public String getApplicationVersion(Model model) {
        // get version
        String version = "v1beta1";
        model.addAttribute("appVersion", appVersion);
        return "version";
    }

    @GetMapping("/name/{username}")
    public String getRegistrationByName(@PathVariable String username, Model model) {

        Registrations r = regSvc.getUserByName(username);
        if (null != r) {
            // 200 OK
            model.addAttribute("newReg", r);
            return "registered";
        } 

        // 404 Not Found
        model.addAttribute("username", username);
        return "not_found";
    }

    @PostMapping(path={"", "/"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    //@PostMapping(path={"", "/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postRegister(@RequestBody MultiValueMap<String, String> form,
        Model model) {
        String name = form.getFirst("name");
        String email = form.getFirst("email");
        String phone = form.getFirst("phone");

        System.out.println(">>> name: %s, email: %s, phone: %s\n".formatted(name, email, phone));

        final Registrations reg = new Registrations();
        reg.setEmail(email);
        reg.setName(name);
        reg.setPhone(phone);
        model.addAttribute("newReg", reg);

        regSvc.newRegistration(reg);

        return "registered";
    }

    @GetMapping(path={"", "/"})
    public String getRegistration(Model model) {
        Collection<Registrations> regs = regSvc.getAllRegistrations();
        model.addAttribute("regs", regs);
        return "register_list";
    }

}