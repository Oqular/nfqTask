package com.example.nfqtask.Entity

import javax.persistence.*

@Entity
class ActiveVisits() {
    @Id
    @GeneratedValue
    var id: Long? = null
    var name: String = ""
    var surname: String = ""
    var reservationCode: String = ""

    constructor(visit: Customer): this(){
        this.name = visit.name
        this.surname = visit.surname
        this.reservationCode = visit.reservationCode
    }
}