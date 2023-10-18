package ar.edu.unq.desapp.grupoB.backenddesappapi.model

class CryptoQuote {

    var typeCrypto : TypeCrypto? = null
    var quote: Double? =null

    constructor(type: TypeCrypto, quote:Double){

        this.typeCrypto = type
        this.quote = quote
    }

}
