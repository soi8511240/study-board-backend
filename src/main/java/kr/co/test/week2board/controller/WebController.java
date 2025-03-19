package kr.co.test.week2board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("/")
public class WebController {

//    @GetMapping("error")
    public String error() {
        return "error";
    }
}
