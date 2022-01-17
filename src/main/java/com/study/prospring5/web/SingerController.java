package com.study.prospring5.web;

import com.study.prospring5.domain.Singer;
import com.study.prospring5.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value="/singers")
public class SingerController {
    private final Logger logger = LoggerFactory.getLogger(SingerController.class);

    @Autowired
    SingerService singerService;

    @GetMapping
    public String list(Model model){
        logger.info("Listing singers");
        List<Singer> singers = singerService.findAll();
        model.addAttribute("singers", singers);
        logger.info("No. of singers: "+singers.size());
        return "singers";
    }

    @GetMapping(value = "/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        Singer singer = singerService.findById(id);
        model.addAttribute("singer",singer);
        return "show";
    }

    @GetMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        model.addAttribute("singer", singerService.findById(id));
        return "update";
    }

    @GetMapping(value = "/new")
    public String createForm(Model model){
        Singer singer = new Singer();
        model.addAttribute("singer",singer);
        return "update";
    }

    @PostMapping(value = "/save")
    public String saveSinger(@Valid Singer singer){
        singerService.save(singer);
        return "redirect:/singers/"+singer.getId();
    }
}
