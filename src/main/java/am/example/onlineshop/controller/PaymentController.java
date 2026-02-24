package am.example.onlineshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentController {

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public String pay(){
        return "pay";
    }
}
