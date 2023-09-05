package modelTest

import builder.CryptoBuilder
import builder.UserBuilder
import model.Aplication
import model.CryptoQuote
import model.TypeCrypto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AplicationTest {

    @Test
    fun createdAplicationDoesNotCrypto(){

        var app: Aplication = Aplication()
        Assertions.assertEquals(app.cryptos.size,  0)
    }

    @Test
    fun createdAplicationDoesNotUsers(){

        var app: Aplication = Aplication()
        Assertions.assertEquals(app.users.size,  0)
    }

    @Test
    fun createdAplicationWithOneCryptoAdd(){

        var app: Aplication = Aplication()
        var crypto: CryptoBuilder= CryptoBuilder().builder()
        app.registerCrypto(crypto)
        Assertions.assertEquals(app.cryptos.size,  1)
        Assertions.assertTrue(app.cryptos.contains(crypto))
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
    fun AplicationShowQuoteOfCryptoWithTypeALICEUSDT(){

        var app: Aplication = Aplication()
        var quoteCrypto: CryptoQuote = CryptoQuote(TypeCrypto.ALICEUSDT, 200.00)
        app.addQuote(quoteCrypto)
        Assertions.assertEquals(app.quoteOfTypeCrypto(TypeCrypto.ALICEUSDT),  200.00)
    }





}