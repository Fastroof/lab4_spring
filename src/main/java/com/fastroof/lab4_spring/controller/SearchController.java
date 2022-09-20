package com.fastroof.lab4_spring.controller;

import com.fastroof.lab4_spring.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchServiceImpl) {
        this.searchService = searchServiceImpl;
    }

    @GetMapping("/search")
    @ResponseBody
    public String search(@RequestParam(required = false) Double area , @RequestParam(required = false) Integer bedroomCount, @RequestParam(required = false) Integer price) {
        return searchService.findAllByAreaAndBedroomCountAndPrice(area, bedroomCount, price).toString();
    }
}
