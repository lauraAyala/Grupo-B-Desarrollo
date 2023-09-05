package modelTest

import builder.UserBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.security.core.userdetails.User

class UserTest {

    @Test
    fun userCreatedWithName() {

        var user1: UserBuilder = UserBuilder().builder()
        user1.withName("Laura")
        Assertions.assertEquals(user1.name, "Laura")
    }

    @Test
    fun userCreatedWithLastName() {

        var user1: UserBuilder = UserBuilder().builder()
        user1.withLastName("Ayala")
        Assertions.assertEquals(user1.lastName, "Ayala")
    }

    @Test
    fun userCreatedWithADirection() {

        var user1: UserBuilder = UserBuilder().builder()
        user1.withDirection("chacabuco 123")
        Assertions.assertEquals(user1.direction, "chacabuco 123")
    }

    @Test
    fun userCreatedWithAEmail() {

        var user1: UserBuilder = UserBuilder().builder()
        user1.withEmail("layala")
        Assertions.assertEquals(user1.email, "layala")
    }

    @Test
    fun userCreatedWithAPassword() {

        var user1: UserBuilder = UserBuilder().builder()
        user1.withPassword("123")
        Assertions.assertEquals(user1.password, "123")
    }

    @Test
    fun userCreatedWithADirectionWallet() {

        var user1: UserBuilder = UserBuilder().builder()
        user1.withDirectionWallet("layala12")
        Assertions.assertEquals(user1.directionWallet, "layala12")
    }


}