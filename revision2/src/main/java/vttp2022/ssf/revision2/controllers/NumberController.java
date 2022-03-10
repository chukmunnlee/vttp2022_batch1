package vttp2022.ssf.revision2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/number")
public class NumberController {

    // GET /number/<a number>
    @GetMapping(path="/{num}")
    public String getNumber(@PathVariable String num, Model model) {

        Integer toDisplay = 0;

        // not a number
        try {
            toDisplay = Integer.parseInt(num);
        } catch (NumberFormatException ex) {
            // handle it later
            model.addAttribute("errMsg", "Not a number: " + num);
            return "error";
        }

        if ((toDisplay < 0) || (toDisplay > 30)) {
            model.addAttribute("errMsg", "Number out of range: " + num);
            return "error";
        }

        StringBuilder sb = new StringBuilder(); // ""
        sb.append("/numbers/number"); // "/numbers/number"
        sb.append(num); // "/numbers/number4"
        sb.append(".jpg"); // "/numbers/number4.jpg"
        String numUrl = sb.toString();
        model.addAttribute("numUrl", numUrl);

        return "display";
    }
    
}
