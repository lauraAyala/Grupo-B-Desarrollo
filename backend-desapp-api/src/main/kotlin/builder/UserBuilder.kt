package builder

import model.User

class UserBuilder : User() {

    fun withName(nameU:String) :User{

        this.name= nameU
        return this
    }

    fun withLastName(lastName: String): User{

        this.lastName = lastName
        return this
    }

    fun withEmail(emailU : String) : User {

        this.email= emailU
        return this
    }

    fun withCvu(cvu:String) : User{

        this.cvu = cvu
        return this
    }

    fun withDirection(directionU:String): User{

        this.direction = directionU
        return this
    }

    fun withDirectionWallet(directionWalletU:String):User{

        this.directionWallet = directionWalletU
        return this
    }

    fun builder():UserBuilder{

        return UserBuilder()
    }

    fun withPassword(password: String) : User{

        this.password = password
        return this
    }

}