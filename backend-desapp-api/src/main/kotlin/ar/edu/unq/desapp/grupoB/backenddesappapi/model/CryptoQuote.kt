package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import javax.persistence.*

@Entity
class CryptoQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null
    @Column
    var typeCrypto : TypeCrypto? = null
    @Column
    var quote: Double? =null

    constructor(type: TypeCrypto, quote:Double){

        this.typeCrypto = type
        this.quote = quote
    }

}
