package com.example.nfqtask.Repository

import com.example.nfqtask.Entity.ActiveVisits
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IActiveVisitRepository: CrudRepository<ActiveVisits, Long> {

}