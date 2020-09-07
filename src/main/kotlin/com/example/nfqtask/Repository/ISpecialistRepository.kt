package com.example.nfqtask.Repository

import com.example.nfqtask.Entity.Specialist
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ISpecialistRepository: CrudRepository<Specialist, Long> {
    fun findOneByUserName(userName: String): Specialist?
}