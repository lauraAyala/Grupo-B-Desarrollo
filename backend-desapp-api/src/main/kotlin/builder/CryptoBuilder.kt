package builder

import model.Crypto
import model.TypeCrypto
import model.User
import java.time.LocalDateTime

class CryptoBuilder : Crypto() {

    fun withUserCreated(user: User): Crypto{

        this.userCreate = user
        return this
    }

    fun withQuote(amount:Double):Crypto{

        this.quote = amount
        return this
    }

    fun withDate(date: LocalDateTime):Crypto{

        this.operativeDate = date
        return this
    }

    fun withTypeCrypto(typeCrypto: TypeCrypto):Crypto{

        this.typeCrypto = typeCrypto
        return this
    }

    fun builder():CryptoBuilder{

        return CryptoBuilder()
    }
}