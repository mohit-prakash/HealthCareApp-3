package com.mps.controller;

import com.mps.entity.Appointment;
import com.mps.entity.Doctor;
import com.mps.exception.AppointmentNotFoundException;
import com.mps.service.IAppointmentService;
import com.mps.service.IDoctorService;
import com.mps.service.ISpecialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppointmentController {
    @Autowired
    private IAppointmentService service;
    @Autowired
    private IDoctorService docService;
    @Autowired
    private ISpecialization specializationService;

    private void dropDownForDoctor(Model model){
        model.addAttribute("doctors",docService.getDocIdAndDocName());
    }
    @GetMapping("/add")
    public String registerAppointment(Model model, @RequestParam(name = "message",required = false)String message){
        model.addAttribute("message",message);
        dropDownForDoctor(model);
        return "AppointmentRegister";
    }
    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute Appointment appointment, RedirectAttributes attributes)
    {
        Long appId = service.addAppointment(appointment);
        attributes.addAttribute("message","Appointment "+appId+" saved successfully!!");
        return "redirect:add";
    }
    @GetMapping("/all")
    public String showAllAppointments(Model model,@RequestParam(name="message",required = false)String message)
    {
        List<Appointment> appointments = service.getAllAppointments();
        model.addAttribute("appointments",appointments);
        model.addAttribute("message",message);
        return "AppointmentData";
    }
    @GetMapping("/edit")
    public String editAppointment(@RequestParam("appId")Long appId,Model model,RedirectAttributes attributes)
    {
        try {
            Appointment appointment = service.getAppointmentById(appId);
            model.addAttribute("appointment",appointment);
            dropDownForDoctor(model);
            return "AppointmentEdit";
        }
        catch (AppointmentNotFoundException anfe)
        {
            attributes.addAttribute("message",anfe.getMessage());
            return "redirect:all";
        }
    }
    @PostMapping("/update")
    public String updateAppointment(@ModelAttribute Appointment appointment,RedirectAttributes attributes)
    {
        Long appId = service.updateAppointment(appointment);
        attributes.addAttribute("message","Appointment "+appId+" updated successfully!!");
        return "redirect:all";
    }
    @GetMapping("/delete")
    public String removeAppointment(@RequestParam("appId")Long appId,RedirectAttributes attributes)
    {
        try {
            service.deleteAppointmentById(appId);
            attributes.addAttribute("message","Appointment "+appId+" deleted successfully!!");
        }
        catch (AppointmentNotFoundException anfe)
        {
            attributes.addAttribute("message",anfe.getMessage());
        }
        return "redirect:all";
    }
    @GetMapping("/search")
    public String searchAppointment(@RequestParam(required = false,defaultValue = "0") Long specId,Model model){
        Map<Long, String> specMap = specializationService.getSpecIdAndSpecName();
        model.addAttribute("specializations",specMap);
        String message=null;
        if (specId==0){
            List<Doctor> allDoctors = docService.getAllDoctors();
            model.addAttribute("doctors",allDoctors);
            message="Reults: All Doctors";
            model.addAttribute("message",message);
        } else {
            List<Doctor> doctors = docService.getDocBySpecId(specId);
            model.addAttribute("doctors",doctors);
            message="Result: "+specializationService.getSpecializationById(specId).getSpecName()+" Doctors";
            model.addAttribute("message",message);
        }
        return "AppointmentSearch";
    }
}
