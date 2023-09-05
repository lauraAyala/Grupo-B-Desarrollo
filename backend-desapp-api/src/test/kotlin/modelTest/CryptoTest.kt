package modelTest

import builder.CryptoBuilder
import builder.UserBuilder
import model.TypeCrypto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CryptoTest {

    @Test
    fun cryptoCreatedWithAUser() {

        var user1: UserBuilder = UserBuilder().builder()
        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withUserCreated(user1)
        Assertions.assertEquals(crypto.userCreate, user1)
    }

    @Test
    fun cryptoCreatedWithQuote() {

        var user1: UserBuilder = UserBuilder().builder()
        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withQuote(100.00)
        Assertions.assertEquals(crypto.quote, 100.00)
    }

    @Test
    fun cryptoCreatedWithDate() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        var date : LocalDateTime = LocalDateTime.now()
        crypto.withDate(date)
        Assertions.assertEquals(crypto.operativeDate, date)
    }

    @Test
    fun cryptoCreatedOfTypeALICEUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.ALICEUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.ALICEUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeMATICUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.MATICUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.MATICUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeAXSUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.AXSUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.AXSUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeATOMUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.ATOMUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.ATOMUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeNEOUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.NEOUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.NEOUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeDOTUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.DOTUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.DOTUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeETHUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.ETHUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.ETHUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeCAKEUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.CAKEUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.CAKEUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeBTCUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.BTCUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.BTCUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeBNBUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.BNBUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.BNBUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeADAUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.ADAUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.ADAUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeTRXUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.TRXUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.TRXUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeAUDIOUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.AUDIOUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.AUDIOUSDT)
    }

    @Test
    fun cryptoCreatedOfTypeAAVEUSDT() {

        var crypto : CryptoBuilder = CryptoBuilder().builder()
        crypto.withTypeCrypto(TypeCrypto.AAVEUSDT)
        Assertions.assertEquals(crypto.typeCrypto, TypeCrypto.AAVEUSDT)
    }


}