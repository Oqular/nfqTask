package com.example.nfqtask.Entity

import javax.persistence.*

@Entity
class Customer(var name: String = "",
               var surname: String = "",
               @ManyToOne
               var specialist: Specialist? = null) {
    @Id
    @GeneratedValue
    var id: Long? = null
    var reservationCode: String = ""
    var validationCode: String = ""

    init {
        val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        reservationCode = (1..5)
                .map { _ -> kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
        validationCode = (1..5)
                .map { _ -> kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
    }

}