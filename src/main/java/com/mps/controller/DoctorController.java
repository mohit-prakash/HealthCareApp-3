package com.mps.controller;

import com.mps.entity.Doctor;
import com.mps.exception.DoctorNotFoundException;
import com.mps.service.IDoctorService;
import com.mps.service.ISpecialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/doc")
public class DoctorController {
    @Autowired
    private IDoctorService service;
    @Autowired
    private ISpecialization specService;
    private void dropDownForSpecialization(Model model){
        model.addAttribute("specializations",specService.getSpecIdAndSpecName());
    }
    @GetMapping("/add")
    public String registerDoctor(Model model, @RequestParam(name = "message",required = false)String message){
        model.addAttribute("message",message);
        dropDownForSpecialization(model);
        return "DoctorRegister";
    }
    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute Doctor doctor, RedirectAttributes attributes)
    {
        Long docId = service.addDoctor(doctor);
        attributes.addAttribute("message","DocId "+docId+" registered successfully!!");
        return "redirect:add";
    }
    @GetMapping("/all")
    public String showAllDoctors(Model model,@RequestParam(name="message",required = false)String message)
    {
        List<Doctor> doctors = service.getAllDoctors();
        model.addAttribute("doctors",doctors);
        model.addAttribute("message",message);
        return "DoctorData";
    }
    @GetMapping("/edit")
    public String editDoctor(@RequestParam("docId")Long docId,Model model,RedirectAttributes attributes)
    {
        try {
            Doctor doctor = service.getDoctorById(docId);
            model.addAttribute("doctor",doctor);
            dropDownForSpecialization(model);
            return "DoctorEdit";
        }
        catch (DoctorNotFoundException dnfe)
        {
            attributes.addAttribute("message",dnfe.getMessage());
            return "redirect:all";
        }
    }
    @PostMapping("/update")
    public String updateDoctor(@ModelAttribute Doctor doctor,RedirectAttributes attributes)
    {
        Long docId = service.updateDoctor(doctor);
        attributes.addAttribute("message","DocId "+docId+" updated successfully!!");
        return "redirect:all";
    }
    @GetMapping("/delete")
    public String removeDoctor(@RequestParam("docId")Long docId,RedirectAttributes attributes)
    {
        try {
            service.deleteDoctorById(docId);
            attributes.addAttribute("message","DocId "+docId+" deleted successfully!!");
        }
        catch (DoctorNotFoundException dnfe)
        {
            attributes.addAttribute("message",dnfe.getMessage());
        }
        return "redirect:all";
    }
}
