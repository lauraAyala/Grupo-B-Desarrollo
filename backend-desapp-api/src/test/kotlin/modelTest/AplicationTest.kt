package modelTest

import ar.edu.unq.desapp.grupoB.backenddesappapi.builder.CryptoBuilder
import ar.edu.unq.desapp.grupoB.backenddesappapi.builder.UserBuilder
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class AplicationTest {

    @Test
    fun createdAplicationDoesNotCrypto(){

        var app: Aplication = Aplication()
        Assertions.assertEquals(app.cryptosQuote.size,  0)
    }

    @Test
    fun createdAplicationDoesNotUsers(){

        var app: Aplication = Aplication()
        Assertions.assertEquals(app.users.size,  0)
    }

    @Test
    fun createdAplicationWithOneCryptoAdd(){

        var app: Aplication = Aplication()
        var crypto: CryptoBuilder = CryptoBuilder().builder()
        app.addQuote(crypto)
        Assertions.assertEquals(app.cryptosQuote.size,  1)
        Assertions.assertTrue(app.cryptosQuote.contains(crypto))
    }

    @Test
    fun createdAplicationWithOneUserAdd(){

        var app: Aplication = Aplication()
        var user: UserBuilder = UserBuilder().builder()
        app.registerUser(user)
        Assertions.assertEquals(app.users.size,  1)
        Assertions.assertTrue(app.users.contains(user))
    }

    @Test
    fun aplicationShowQuoteOfCryptoWithTypeALICEUSDT(){

        var app: Aplication = Aplication()
        var quoteCrypto: CryptoQuote = CryptoQuote(TypeCrypto.ALICEUSDT, 200.00)
        app.addQuote(quoteCrypto)
        Assertions.assertEquals(app.quoteOfTypeCrypto(TypeCrypto.ALICEUSDT),  200.00)
    }
    @Test
    fun aplicationShowCotitationOfCryptos(){

        var app: Aplication = Aplication()
        var quoteCrypto: CryptoQuote = CryptoQuote(TypeCrypto.ALICEUSDT, 200.00)
        var quoteCrypto2: CryptoQuote = CryptoQuote(TypeCrypto.MATICUSDT, 300.00)
        var quoteCrypto3: CryptoQuote = CryptoQuote(TypeCrypto.AXSUSDT, 400.00)
        var quoteCrypto4: CryptoQuote = CryptoQuote(TypeCrypto.AAVEUSDT, 100.00)
        var quoteCrypto5: CryptoQuote = CryptoQuote(TypeCrypto.ATOMUSDT, 150.00)
        var quoteCrypto6: CryptoQuote = CryptoQuote(TypeCrypto.NEOUSDT, 450.00)
        var quoteCrypto7: CryptoQuote = CryptoQuote(TypeCrypto.DOTUSDT, 500.00)
        var quoteCrypto8: CryptoQuote = CryptoQuote(TypeCrypto.ETHUSDT, 600.00)
        var quoteCrypto9: CryptoQuote = CryptoQuote(TypeCrypto.CAKEUSDT, 700.00)
        var quoteCrypto10: CryptoQuote = CryptoQuote(TypeCrypto.BTCUSDT, 600.00)
        var quoteCrypto11: CryptoQuote = CryptoQuote(TypeCrypto.BNBUSDT, 500.00)
        var quoteCrypto12: CryptoQuote = CryptoQuote(TypeCrypto.ADAUSDT, 650.00)
        var quoteCrypto13: CryptoQuote = CryptoQuote(TypeCrypto.TRXUSDT, 750.00)
        var quoteCrypto14: CryptoQuote = CryptoQuote(TypeCrypto.AUDIOUSDT, 1000.00)

        app.addQuote(quoteCrypto)
        app.addQuote(quoteCrypto2)
        app.addQuote(quoteCrypto3)
        app.addQuote(quoteCrypto4)
        app.addQuote(quoteCrypto5)
        app.addQuote(quoteCrypto6)
        app.addQuote(quoteCrypto7)
        app.addQuote(quoteCrypto8)
        app.addQuote(quoteCrypto9)
        app.addQuote(quoteCrypto10)
        app.addQuote(quoteCrypto11)
        app.addQuote(quoteCrypto12)
        app.addQuote(quoteCrypto13)
        app.addQuote(quoteCrypto14)

        Assertions.assertEquals(app.cotitationCryptoActive().size,  14)
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto2))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto3))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto4))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto5))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto6))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto7))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto8))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto9))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto10))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto11))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto12))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto13))
        Assertions.assertTrue(app.cotitationCryptoActive().contains(quoteCrypto14))

    }

    @Test
    fun aplicationShowIntentionsOfSaleAndBuyTheCryptos() {

        var user: UserBuilder = UserBuilder().builder()
        var crypto: CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.ALICEUSDT)
        var operationType = SaleOperation()
        var operation = Operation(user,2,crypto,30.0,operationType)
        operation = user.saleCrypto(operation)!!
        var userUpdate = operation.userCreated
        var user2: UserBuilder = UserBuilder().builder()
        user2.withDirectionWallet("xxx")
        var crypto2: CryptoBuilder = CryptoBuilder().builder()
        crypto2.withTypeCrypto(TypeCrypto.ALICEUSDT)
        var operationType2= BuyOperation()
        var operation2 = Operation(user, 2,crypto,30.0,operationType2)
        operation2 = user2.buyCrypto(operation2)!!
        var userUpdate2 = operation2.userCreated

        var operations :ArrayList<Operation> = ArrayList()
        operations.addAll(userUpdate!!.operations)
        operations.addAll(userUpdate2!!.operations)

        var app: Aplication = Aplication()
        app.registerUser(userUpdate)
        app.registerUser(userUpdate2)

        Assertions.assertEquals(app.intentionsActiveOfSaleAndBuyCryptos().size,  2)
        Assertions.assertTrue(app.intentionsActiveOfSaleAndBuyCryptos().containsAll(operations))
    }

    @Test
    fun theAplicattionCanceledTheSale(){
        var app: Aplication = Aplication()
        var pepe = User("pepe","Gonzalez","pepe@hotmail.com","chile456","123456","290394949949202","directionWallet")
        pepe.updatePoints(50)
        var crypto = CryptoQuote(TypeCrypto.ALICEUSDT,20.0)
        var operationType= SaleOperation()
        var operation = Operation(pepe, 2,crypto,30.0,operationType)
        app.cancelatedOperation(operation)

        Assertions.assertEquals(pepe.operations.size,0)
        Assertions.assertEquals(pepe.countOperations,0)
        Assertions.assertEquals(pepe.point, 50)
    }

    @Test
    fun theAplicattionCanceledThePurchase(){
        var app: Aplication = Aplication()
        var pepe = User("pepe","Gonzalez","pepe@hotmail.com","chile456","123456","290394949949202","directionWallet")
        pepe.updatePoints(50)
        var crypto = CryptoQuote(TypeCrypto.ALICEUSDT,20.0)
        var operationType= BuyOperation()
        var operation = Operation(pepe, 2,crypto,30.0,operationType)
        app.cancelatedOperation(operation)

        Assertions.assertEquals(pepe.operations.size,0)
        Assertions.assertEquals(pepe.countOperations,0)
        Assertions.assertEquals(pepe.point, 50)
    }
}
