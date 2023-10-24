package ar.edu.unq.desapp.grupoB.backenddesappapi.service

import ar.edu.unq.desapp.grupoB.backenddesappapi.builder.UserBuilder
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.Crypto
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

        var user = UserBuilder().builder()
        return user //esto se tienen ue manejar con una excepcion
    }


    fun recoverUser(id : Long) : User{

        return repo.getOne(id)
    }

    fun buyOperation(user: User, crypto: Crypto, count:Int, quote:Double): User {

        return user.buyCrypto(crypto,count,quote)

    }

    fun saleCrypto(user: User, crypto: Crypto, countNominal: Int, quote: Double): User {

        return user.saleCrypto(crypto,countNominal,quote)

    }
}