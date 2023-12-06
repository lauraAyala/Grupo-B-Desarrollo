package ar.edu.unq.desapp.grupoB.backenddesappapi.service

import ar.edu.unq.desapp.grupoB.backenddesappapi.builder.UserBuilder
import ar.edu.unq.desapp.grupoB.backenddesappapi.dto.UserDTO
import ar.edu.unq.desapp.grupoB.backenddesappapi.exception.ExceptionInvalidId
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.Operation
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ar.edu.unq.desapp.grupoB.backenddesappapi.repository.UserRepository
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.UserRequest
import java.io.Serializable

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

        var userId : User? = null
        for (i in allUsers()){

            if( i.id.hashCode() == user){

                userId = i
            }
        }

       if (userId == null){

           throw ExceptionInvalidId("It is not user with id")
       }
        return userId
    }


    fun recoverUser(id : Long) : User {


        return repo.getOne(id)

    }
    fun buyOperation(user:User,operation: Operation): Operation? {

        return user.buyCrypto(operation)

    }

    fun saleCrypto(user: User, operation: Operation): Operation? {

        return user.saleCrypto(operation)

    }

    fun saveUser(userRequest: UserRequest): UserDTO {

        var user = User(userRequest.name!!,userRequest.lastName!!,userRequest.email!!,userRequest.password!!,userRequest.direction!!,userRequest.cvu!!, userRequest.directionWallet!!)
        this.createUser(user)
        return UserDTO(user.name!!)

    }
}