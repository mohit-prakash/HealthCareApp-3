package com.mps.controller;

import com.mps.entity.Patient;
import com.mps.exception.PatientNotFoundException;
import com.mps.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pat")
public class PatientController {
    @Autowired
    private IPatientService service;

    @GetMapping("/add")
    public String registerPatient(Model model, @RequestParam(name = "message",required = false)String message)
    {
        model.addAttribute("message",message);
        return "PatientRegister";
    }
    @PostMapping("/save")
    public String savePatient(@ModelAttribute Patient patient, RedirectAttributes attributes)
    {
        Long patId = service.addPatient(patient);
        attributes.addAttribute("message","Patient "+patId+" registered successfully!!");
        return "redirect:add";
    }
    @GetMapping("/all")
    public String showAllPatients(Model model,@RequestParam(name = "message",required = false)String message)
    {
        List<Patient> patients = service.getAllPatients();
        model.addAttribute("patients",patients);
        model.addAttribute("message",message);
        return "PatientData";
    }
    @GetMapping("/edit")
    public String editPatient(@RequestParam("patId")Long patId,Model model,RedirectAttributes attributes)
    {
        try {
            Patient patient = service.getPatientById(patId);
            model.addAttribute("patient",patient);
            return "PatientEdit";
        }
        catch (PatientNotFoundException pnfe)
        {
            attributes.addAttribute("message",pnfe.getMessage());
            return "redirect:all";
        }
    }
    @PostMapping("/update")
    public String updatePatient(@ModelAttribute Patient patient,RedirectAttributes attributes)
    {
        Long patId = service.updatePatient(patient);
        attributes.addAttribute("message","Patient "+patId+" updated successfully!!");
        return "redirect:all";
    }
    @GetMapping("/delete")
    public String removePatient(@RequestParam("patId")Long patId,RedirectAttributes attributes)
    {
        try{
            service.deletePatientById(patId);
            attributes.addAttribute("message","Patient "+patId+" removed successfully!!");
        }
        catch (PatientNotFoundException pnfe)
        {
            attributes.addAttribute("message",pnfe.getMessage());
        }
        return "redirect:all";
    }
}
