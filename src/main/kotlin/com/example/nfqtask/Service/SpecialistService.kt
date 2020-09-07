package com.example.nfqtask.Service

import com.example.nfqtask.Entity.ActiveVisits
import com.example.nfqtask.Entity.Customer
import com.example.nfqtask.Entity.Specialist
import com.example.nfqtask.Repository.ISpecialistRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SpecialistService {
    @Autowired
    lateinit var repository: ISpecialistRepository

    fun findSpecialistByUsername(username: String): Specialist {
        return repository.findOneByUserName(username)!!
    }

    fun addSpecialist(spec: Specialist): Specialist {
        repository.save(spec)
        return spec
    }

    fun getAll(): MutableIterable<Specialist> {
        return repository.findAll()
    }

    fun setActiveVisit(id: Long, activeVisit: ActiveVisits?){
        val currentSpecialist = repository.findByIdOrNull(id)
        if (currentSpecialist != null){
            currentSpecialist.activeVisit = activeVisit
            repository.save(currentSpecialist)
        }
    }

    fun countTimeLeft(customer: Customer): Int{
        val specialist = repository.findOneByUserName(customer.specialist!!.userName) ?: return 0
        for ((time, c) in specialist.appointments.withIndex()){
            if (c == customer){
                return time * 20
            }
        }
        return 0
    }
    //count time left
}