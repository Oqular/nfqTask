package com.example.nfqtask.Service

import com.example.nfqtask.Entity.ActiveVisits
import com.example.nfqtask.Entity.Customer
import com.example.nfqtask.Repository.IActiveVisitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ActiveVisitService {
    @Autowired
    lateinit var repository: IActiveVisitRepository

    fun addActiveVisit(activeVisit: ActiveVisits): ActiveVisits {
        repository.save(activeVisit)
        return activeVisit
    }

    fun deleteActiveVisit(id: Long){
        repository.deleteById(id)
    }
    //???
}