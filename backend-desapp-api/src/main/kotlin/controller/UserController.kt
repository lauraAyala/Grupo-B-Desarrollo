package controller

import model.User
import dto.UserDTO
import request.UserRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import service.UserService

@RestController
class UserController {

    @Autowired
    lateinit var service : UserService

    @GetMapping("/")
    fun welcome(): String{
        return "Welcome Crypto Exchange"
    }

    @GetMapping("/users")
    fun getUsers() : List<User>{
        return service.allUsers()
    }

    @PostMapping("/register")
    fun saveUser(@RequestBody userRequest : UserRequest) : ResponseEntity<UserDTO> {
        var user = User(userRequest.name!!,userRequest.lastName!!,userRequest.email!!,userRequest.password!!,userRequest.direction!!,userRequest.cvu!!, userRequest.directionWallet!!)
        service.createUser(user)
        var userDTO = UserDTO(user.name!!)
        return ResponseEntity(userDTO, HttpStatus.CREATED)
    }

}