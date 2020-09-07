package com.example.nfqtask

import com.example.nfqtask.Entity.ActiveVisits
import com.example.nfqtask.Entity.Customer
import com.example.nfqtask.Entity.Specialist
import com.example.nfqtask.Service.ActiveVisitService
import com.example.nfqtask.Service.CustomerService
import com.example.nfqtask.Service.SpecialistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@Controller
@RequestMapping("/")
class MainController {

    @Autowired
    lateinit var specialistService: SpecialistService

    @GetMapping("")
    fun mainPage(model: Model): String{
        model["title"] = "Web site"

        return "home"
    }

    @GetMapping("/departmentScreen")
    fun departmentPage(model: Model): String{
        return "departmentScreen"
    }

    @GetMapping("/checkReservation")
    fun timePage(model: Model): String{
        return "checkReservation"
    }



    @GetMapping("bookAppointment")
    fun addAppointmentPage(model: Model): String{
        model["customer"] = Customer()
        model["specArr"] = specialistService.getAll()

        return "bookAppointment"
    }

    @GetMapping("add")
    fun addPage(model: Model): String{
        model["title"] = "Add site"
        var specialist = Specialist("Jhon", "jjhan", "jjhan")
        specialistService.addSpecialist(specialist)
        specialist = Specialist("Simon", "sshan", "sshan")
        specialistService.addSpecialist(specialist)
//        var specialist = specialistService.findSpecialistByUsername("sshan")
//        var appointment = Appointment("Wii", specialist)
//        appointmentService.addSpecialist(appointment)
        return "add"
    }
}