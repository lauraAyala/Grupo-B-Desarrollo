package modelTest

import ar.edu.unq.desapp.grupoB.backenddesappapi.builder.CryptoBuilder
import ar.edu.unq.desapp.grupoB.backenddesappapi.builder.UserBuilder
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

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

    @Test
    fun userCreatedOperationOfTypeSale() {

        var user: UserBuilder = UserBuilder().builder()
        var crypto: CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.ALICEUSDT)
        var operationType= SaleOperation()
        var operation = Operation(user, 2,crypto,30.0,operationType)
        user.saleCrypto(operation)
        operation = user.operations.get(0)

        Assertions.assertEquals(user.operations.size,1)
        Assertions.assertEquals(operation.crypto, crypto)
        Assertions.assertEquals(operation.userCreated, user)
    }

    @Test
    fun userCreatedOperationOfTypeBuy() {

        var user: UserBuilder = UserBuilder().builder()
        user.withDirectionWallet("xxx")
        var crypto: CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.ALICEUSDT)
        var operationType= BuyOperation()
        var operation = Operation(user, 2,crypto,30.0,operationType)
        user.buyCrypto(operation)
        operation = user.operations.get(0)
        //var userUpdate = operation.userCreated

        Assertions.assertEquals(user.operations.size,1)
        Assertions.assertEquals(operation.crypto, crypto)
        Assertions.assertEquals(operation.userCreated, user)
    }

    @Test
    fun userCreatedOperationOfTypeCanceled() {

        var user: UserBuilder = UserBuilder().builder()
        var crypto: CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.ALICEUSDT)
        var operationType= CancelOperation()
        var operation = Operation(user, 2,crypto,30.0,operationType)
        operation = user.canceledCrypto(operation)!!
        var userUpdate = operation.userCreated

        Assertions.assertEquals(userUpdate!!.operations.size,0)
        Assertions.assertEquals(operation.crypto, crypto)
        Assertions.assertEquals(operation.userCreated, user)
    }

   @Test
    fun userMakesThePurchase0fACrypto(){

        var pepe = User("pepe","Gonzalez","pepe@hotmail.com","chile456","123456","290394949949202","directionWallet")
        var operativeDate = LocalDateTime.now()
        var ALICEUSDT = TypeCrypto.ALICEUSDT
        var crypto = CryptoQuote(ALICEUSDT,20.0)
        var operationType= BuyOperation()
        var operation = Operation(pepe, 2,crypto,30.0,operationType)
        var operationUpdate = pepe.buyCrypto(operation)


        Assertions.assertEquals(pepe.directionWallet,operationUpdate!!.direction)
        Assertions.assertEquals(pepe.reception,true)
        Assertions.assertEquals(pepe.operations.size,1)

    }

    @Test
    fun userMakesThesaleOfCrypto(){
        var pepe = User("pepe","Gonzalez","pepe@hotmail.com","chile456","123456","290394949949202","directionWallet")
        var jose = User("jose","Muñoz","jmuñoz@hotmail.com","florida 124","1234","290432253549202","wallet")
        var ALICEUSDT = TypeCrypto.ALICEUSDT
        var crypto = CryptoQuote(ALICEUSDT,20.0)
        var operationType= SaleOperation()
        var operation = Operation(pepe, 2,crypto,30.0,operationType)
        operation = operation.updateUserInterested(jose)!!
        var operationUpdate = pepe.saleCrypto(operation)
       // var operationUpdate: Operation = pepeUpdate.operations.get(0)

       Assertions.assertEquals(pepe.cvu,operation.userInterested!!.directionForTransfer)



    }

    @Test
    fun verifyThatTheUserIfTheyNeverMadeAOperationsHas0ReputationPoints(){

        var pepe = User("pepe","Gonzalez","pepe@hotmail.com","chile456","123456","290394949949202","directionWallet")

        Assertions.assertEquals(pepe.operations.size,0)
        Assertions.assertEquals(pepe.countOperations,0)
        Assertions.assertEquals(pepe.point, 0)
    }

    @Test
    fun theUserPepeMakesASaleWithin30Minutes(){

        var pepe = User("pepe","Gonzalez","pepe@hotmail.com","chile456","123456","290394949949202","directionWallet")
        var jose = User("jose","Muñoz","jmuñoz@hotmail.com","florida 124","1234","290432253549202","wallet")
        var crypto = CryptoQuote(TypeCrypto.ALICEUSDT,20.0)
        var operationType= SaleOperation()
        var operation = Operation(pepe, 2,crypto,30.0,operationType)
        operation = operation.updateUserInterested(jose)!!
        var operationUpdate = pepe.saleCrypto(operation)

        Assertions.assertEquals(pepe.operations.size,1)
        Assertions.assertEquals(pepe.countOperations,1)
        Assertions.assertEquals(pepe.point, 10)
    }

    @Test
    fun theUserPepeMakesASaleAndExceeds30Minutes(){

        var pepe = User("pepe","Gonzalez","pepe@hotmail.com","chile456","123456","290394949949202","directionWallet")
        var jose = User("jose","Muñoz","jmuñoz@hotmail.com","florida 124","1234","290432253549202","wallet")
        var fecha = LocalDate.of(2023, 11, 30)
        var hora = LocalTime.of( 15,20, 30)
        var operativeDate = LocalDateTime.of(fecha, hora)
        var crypto = CryptoQuote(TypeCrypto.ALICEUSDT,20.0)
        var operationType= SaleOperation()
        var operation = Operation(pepe, 2,crypto,30.0,operationType)
        operation = operation.updateUserInterested(jose)!!
        operation.updateOperativeDate(operativeDate)
        var operationUpdate = pepe.saleCrypto(operation)

        Assertions.assertEquals(pepe.operations.size,1)
        Assertions.assertEquals(pepe.countOperations,1)
        Assertions.assertEquals(pepe.point, 5)
    }

    @Test
    fun theUserPepeMakesAPurchaseWithin30Minutes(){

        var pepe = User("pepe","Gonzalez","pepe@hotmail.com","chile456","123456","290394949949202","directionWallet")
        var jose = User("jose","Muñoz","jmuñoz@hotmail.com","florida 124","1234","290432253549202","wallet")
        var crypto = CryptoQuote(TypeCrypto.ALICEUSDT,20.0)
        var operationType= BuyOperation()
        var operation = Operation(pepe, 2,crypto,30.0,operationType)
        operation = operation.updateUserInterested(jose)!!
        var operationUpdate = pepe.buyCrypto(operation)

        Assertions.assertEquals(pepe.operations.size,1)
        Assertions.assertEquals(pepe.countOperations,1)
        Assertions.assertEquals(pepe.point, 10)
    }

    @Test
    fun theUserPepeMakesAPurchaseAndExceeds30Minutes(){

        var pepe = User("pepe","Gonzalez","pepe@hotmail.com","chile456","123456","290394949949202","directionWallet")
        var jose = User("jose","Muñoz","jmuñoz@hotmail.com","florida 124","1234","290432253549202","wallet")
        var fecha = LocalDate.of(2023, 11, 30)
        var hora = LocalTime.of( 15,20, 30)
        var operativeDate = LocalDateTime.of(fecha, hora)
        var crypto = CryptoQuote(TypeCrypto.ALICEUSDT,20.0)
        var operationType= BuyOperation()
        var operation = Operation(pepe, 2,crypto,30.0,operationType)
        operation = operation.updateUserInterested(jose)!!
        operation.updateOperativeDate(operativeDate)
        var operationUpdate = pepe.buyCrypto(operation)

        Assertions.assertEquals(pepe.operations.size,1)
        Assertions.assertEquals(pepe.countOperations,1)
        Assertions.assertEquals(pepe.point, 5)
    }

    @Test
    fun theUserPepeCanceledTheOperation(){

        var pepe = User("pepe","Gonzalez","pepe@hotmail.com","chile456","123456","290394949949202","directionWallet")
        var jose = User("jose","Muñoz","jmuñoz@hotmail.com","florida 124","1234","290432253549202","wallet")
        var fecha = LocalDate.of(2023, 11, 30)
        var hora = LocalTime.of( 15,20, 30)
        var operativeDate = LocalDateTime.of(fecha, hora)
        var crypto = CryptoQuote(TypeCrypto.ALICEUSDT,20.0)
        var operationType= CancelOperation()
        var operation = Operation(pepe, 2,crypto,30.0,operationType)
        operation = operation.updateUserInterested(jose)!!
        operation.updateOperativeDate(operativeDate)
        var operationUpdate = pepe.canceledCrypto(operation)

        Assertions.assertEquals(pepe.operations.size,0)
        Assertions.assertEquals(pepe.countOperations,0)
        Assertions.assertEquals(pepe.point, 0)
    }

}

