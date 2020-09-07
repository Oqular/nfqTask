package com.example.nfqtask.Controllers

import com.example.nfqtask.Entity.ActiveVisits
import com.example.nfqtask.Entity.Customer
import com.example.nfqtask.Service.ActiveVisitService
import com.example.nfqtask.Service.CustomerService
import com.example.nfqtask.Service.SpecialistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpServletRequest


@Controller
class SpecialistController {

    @Autowired
    lateinit var specialistService: SpecialistService
    @Autowired
    lateinit var activeVisitService: ActiveVisitService
    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping("specialistAppointments")
    fun specialistAppointmentPage(model: Model, request: HttpServletRequest): String{
        setData(model, request.remoteUser)
        return "specialistAppointments"
    }

    @PostMapping("specialistAppointments")
    fun beginAppointment(appointmentId: Long?, model: Model, request: HttpServletRequest){
        val customer = customerService.getById(appointmentId!!)
        val activeVisit = ActiveVisits(customer!!)

        activeVisitService.addActiveVisit(activeVisit)
        specialistService.setActiveVisit(customer.specialist!!.id!!, activeVisit)
        customerService.deleteCustomer(customer)

        specialistAppointmentPage(model, request)
    }

    @PostMapping("activeVisitEnd")
    fun endActiveVisit(activeId: Long?, model: Model, request: HttpServletRequest): String{
        val specialist = specialistService.findSpecialistByUsername(request.remoteUser)
        specialistService.setActiveVisit(specialist.id!!, null)
        activeVisitService.deleteActiveVisit(activeId!!)

        setData(model, request.remoteUser)

        return "specialistAppointments"
    }

    @PostMapping("specialistCancel")
    fun visitCancel(visitId: Long?, model: Model, request: HttpServletRequest): String{
        val customer = customerService.getById(visitId!!)
        customerService.deleteCustomer(customer!!)

        setData(model, request.remoteUser)

        return "specialistAppointments"
    }

    fun setData(model: Model, username: String){
        val specialist = specialistService.findSpecialistByUsername(username)

        model["beginAppointment"] = Customer()
        model["appointmentArr"] = specialist.appointments

        if (specialist.activeVisit != null)
            model["activeApp"] = specialist.activeVisit!!
    }

}