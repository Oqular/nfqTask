package com.example.nfqtask.Repository

import com.example.nfqtask.Entity.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface ICustomerRepository: CrudRepository<Customer, Long> {
    
}