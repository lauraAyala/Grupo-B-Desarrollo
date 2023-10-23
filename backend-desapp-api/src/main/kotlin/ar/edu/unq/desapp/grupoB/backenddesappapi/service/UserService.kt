package ar.edu.unq.desapp.grupoB.backenddesappapi.service

import ar.edu.unq.desapp.grupoB.backenddesappapi.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ar.edu.unq.desapp.grupoB.backenddesappapi.repository.UserRepository

@Service
class UserService {

    @Autowired
    lateinit var repo : UserRepository

    fun createUser(user : User) : User {
        repo.save(user)
        return user
    }

    fun allUsers(): List<User>{
        return repo.findAll()
    }

    fun findBy(user: Int): User {

        for (i in allUsers()){

            if( i.id.hashCode() == user){

                return i
            }
        }

        return User() //esto se tienen ue manejar con una excepcion
    }
}