package com.neueda.challenge.shortenerUrl.com.neueda.challenge.shortenerUrl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UrlController {

    @RequestMapping(value = "/{slug}", method = RequestMethod.GET)
    public String access(@PathVariable(value = "slug") String slug) {
        return null;
    }

    @RequestMapping(value = "/{url}", method = RequestMethod.POST)
    public String convert(@PathVariable(value = "url") String url) {
        return null;
    }
}
