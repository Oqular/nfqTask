package com.example.nfqtask.Controllers

import com.example.nfqtask.Entity.Customer
import com.example.nfqtask.Service.CustomerService
import com.example.nfqtask.Service.SpecialistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping


@Controller
class CustomerController {

    @Autowired
    lateinit var customerService: CustomerService
    @Autowired
    lateinit var specialistService: SpecialistService

    @PostMapping("bookAppointment")
    fun createAppointment(customer: Customer, model: Model): String{
        customerService.addCustomer(customer)
        model["customer"] = customer
        return "registeredAppointment"
    }

    @PostMapping("/checkReservation")
    fun checkTime(reservationCode: String, validationCode: String, model: Model): String{
        val customer = customerService.findByCodes(validationCode, reservationCode) ?: return "appointmentNotFound"
        val time = specialistService.countTimeLeft(customer)
        if (time == 0){
            model["next"] = "You first in line"
        }
        else {
            model["hours"] = time / 60
            model["mins"] = time % 60
        }
        model["customerId"] = customer.id!!
        return "showTime"
    }

    @PostMapping("/showTime")
    fun cancelVisit(visitId: Long, model: Model): String{
        val customer = customerService.getById(visitId) ?: return "checkReservation"
        customerService.deleteCustomer(customer)
        return "checkReservation"
    }

    @Scheduled(fixedDelay=5000)//should refresh every 5 sec
    @PostMapping("/departmentScreen")
    fun departmentScreen(reservationCode: String, model: Model): String{
        val customer = customerService.findByReservation(reservationCode) ?: return "appointmentNotFound"
        val specialist = specialistService.findSpecialistByUsername(customer.specialist!!.userName)
        if (specialist.appointments.size > 5){
            model["list"] = specialist.appointments.subList(0, 5)
        }
        else
            model["list"] = specialist.appointments
        if (specialist.activeVisit != null)
            model["current"]  = specialist.activeVisit!!
        return "visitList"
    }

}