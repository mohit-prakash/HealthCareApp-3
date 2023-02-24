package com.mps.controller;

import com.mps.entity.Specialization;
import com.mps.exception.SpecializationNotFoundException;
import com.mps.service.ISpecialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
    @Autowired
    private ISpecialization specialization;

    @GetMapping("/add")
    public String registerSpecialization(Model model, @RequestParam(name = "message",required = false) String message){
        model.addAttribute("message",message);
        return "SpecializationRegister";
    }
    @PostMapping("/save")
    public String saveSpecialization(@ModelAttribute Specialization specialization, RedirectAttributes attributes)
    {
        Long specId = this.specialization.addSpecialization(specialization);
        attributes.addAttribute("message","Specid "+specId+" saved successfully!!");
        return "redirect:add";
    }

    @GetMapping("/all")
    public String showAllSpecializations(Model model,@RequestParam(name = "message",required = false)String message)
    {
        List<Specialization> specializations = this.specialization.getAllSpecializations();
        model.addAttribute("specializations",specializations);
        model.addAttribute("message",message);
        return "SpecializationData";
    }
    @GetMapping("/edit")
    public String editSpecialization(@RequestParam("specId") Long specId, RedirectAttributes attributes,Model model)
    {
        try {
            Specialization specialization = this.specialization.getSpecializationById(specId);
            model.addAttribute("specialization",specialization);
            return "SpecializationEdit";
        }
        catch (SpecializationNotFoundException snfe)
        {
            attributes.addAttribute("message","Specid "+specId+" not found!!");
            return "redirect:all";
        }
    }
    @PostMapping("/update")
    public String updateSpecialization(@ModelAttribute Specialization specialization, RedirectAttributes attributes){
        Long specId = this.specialization.updateSpecialization(specialization);
        attributes.addAttribute("message","Specid "+specId+" updated successfully!!");
        return "redirect:all";
    }
    @GetMapping("/delete")
    public String removeSpecialization(@RequestParam("specId") Long specId,RedirectAttributes attributes)
    {
        String message;
        try{
            specialization.deleteSpecializationById(specId);
            message="Specid "+specId+" deleted successfully!!";
            attributes.addAttribute("message",message);
        }
        catch (SpecializationNotFoundException snfe)
        {
            message="Specid "+specId+" not found!!";
            attributes.addAttribute("message",message);
        }
        return "redirect:all";
    }
}

