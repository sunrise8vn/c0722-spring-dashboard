package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cp")
public class HomeCPController {

    @GetMapping
    public String showIndexCPPage() {
        return "cp/index";
    }

    @GetMapping("/temp")
    public String showCPTempPage() {
        return "cp/temp";
    }
}
