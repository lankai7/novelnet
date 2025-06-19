package com.novelnet.demo.controller;

import com.novelnet.demo.service.PythonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class CrawlerController {

    private final PythonService pythonService;

    public CrawlerController(PythonService pythonService) {
        this.pythonService = pythonService;
    }

    @PostMapping("/scrape")
    public String scrapeBook(@RequestParam String bookId, @RequestParam String bookType) {
        return pythonService.scrapeBook(bookId, bookType);
    }
}
