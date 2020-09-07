package com.example.nfqtask.Service

import com.example.nfqtask.Repository.ISpecialistRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService: UserDetailsService {
    @Autowired
    lateinit var repository: ISpecialistRepository

    override fun loadUserByUsername(username: String): UserDetails {
        var specialist = repository.findOneByUserName(username)
        specialist?.userName ?: throw UsernameNotFoundException("User not authorized $username")
        var authority: GrantedAuthority = SimpleGrantedAuthority(specialist.role)
        //        return CustomUserDetails(repository.findOneByUserName(username)!!)
//        return User.withDefaultPasswordEncoder().username(specialist.userName).password(specialist.passWord).roles("USER").build()
        return User(specialist.userName, specialist.passWord, listOf(authority))
    }
}