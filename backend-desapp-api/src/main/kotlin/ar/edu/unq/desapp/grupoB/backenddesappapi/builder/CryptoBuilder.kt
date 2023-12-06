package ar.edu.unq.desapp.grupoB.backenddesappapi.builder

import ar.edu.unq.desapp.grupoB.backenddesappapi.model.CryptoQuote
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.TypeCrypto

class CryptoBuilder : CryptoQuote() {


    fun withQuote(amount:Double): CryptoQuote {

        this.quote = amount
        return this
    }
    fun withTypeCrypto(typeCrypto: TypeCrypto): CryptoQuote {

        this.typeCrypto = typeCrypto
        return this
    }

    fun builder(): CryptoBuilder {

        return CryptoBuilder()
    }
}