package com.example.nfqtask.Service

import com.example.nfqtask.Entity.Customer
import com.example.nfqtask.Entity.Specialist
import com.example.nfqtask.Repository.ICustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class CustomerService {
    @Autowired
    lateinit var repository: ICustomerRepository

    fun addCustomer(customer: Customer): Customer {
        repository.save(customer)
        return customer
    }

    fun getById(id: Long): Customer? {
        return repository.findByIdOrNull(id)
    }

    fun deleteCustomer(customer: Customer){
        repository.delete(customer)
    }

    fun findByCodes(validationCode: String, reservationCode: String): Customer?{
        val customers = repository.findAll()
        for (c in customers) {
            if (c.validationCode == validationCode && c.reservationCode == reservationCode)
                return c
        }
        return null
    }

    fun findByReservation(reservationCode: String): Customer?{
        val customers = repository.findAll()
        for (c in customers) {
            if (c.reservationCode == reservationCode)
                return c
        }
        return null
    }

    //delete customer?
}