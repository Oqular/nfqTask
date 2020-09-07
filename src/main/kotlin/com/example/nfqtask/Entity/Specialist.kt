package com.example.nfqtask.Entity

import javax.persistence.*

@Entity
data class Specialist(
        var name: String = "",
        var surname: String = "",
        var userName: String = "",
        var passWord:  String = ""
){
    @Id
    @GeneratedValue
    var id: Long? = null
    var role: String = "USER"
    @OneToMany(mappedBy = "specialist")
    var appointments: List<Customer> = ArrayList()
    @OneToOne
    var activeVisit: ActiveVisits? = null
}