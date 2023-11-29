package ar.edu.unq.desapp.grupoB.backenddesappapi.controller

import ar.edu.unq.desapp.grupoB.backenddesappapi.model.User
import ar.edu.unq.desapp.grupoB.backenddesappapi.dto.UserDTO
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.IdRequest
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.UserRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import ar.edu.unq.desapp.grupoB.backenddesappapi.service.UserService
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.*
import javax.persistence.Id

@RestController
class UserController {

    @Autowired
    lateinit var serviceUser : UserService

    @GetMapping("/")
    fun welcome(): String{
        return "Welcome Crypto Exchange"
    }

    @GetMapping("/users")
    //@Cacheable("getUsers")
    fun getUsers() : List<User>{
        return serviceUser.allUsers()
    }

    @PostMapping("/register")
    fun saveUser(@RequestBody userRequest : UserRequest) : ResponseEntity<UserDTO> {
        var user = User(userRequest.name!!,userRequest.lastName!!,userRequest.email!!,userRequest.password!!,userRequest.direction!!,userRequest.cvu!!, userRequest.directionWallet!!)
        serviceUser.createUser(user)
        var userDTO = UserDTO(user.name!!)
        return ResponseEntity(userDTO, HttpStatus.CREATED)
    }

    @GetMapping("/userId")
    fun userOfId(@RequestBody id: IdRequest) : ResponseEntity<User>{

        var user = serviceUser.findBy(id.id)
        return ResponseEntity(user, HttpStatus.ACCEPTED)

    }
    @GetMapping("/user/{id}")
    fun findById(@PathVariable id: Int) = serviceUser.recoverUser(id.toLong())



}