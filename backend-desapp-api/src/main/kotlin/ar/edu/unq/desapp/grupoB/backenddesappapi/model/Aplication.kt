package ar.edu.unq.desapp.grupoB.backenddesappapi.model

class Aplication {

    var users : ArrayList<User> = ArrayList()
    var cryptos : ArrayList<Crypto> = ArrayList()
    var cryptosQuote: ArrayList<CryptoQuote> = ArrayList()

    fun registerUser(user: User){

        this.users.add(user)
    }

    fun registerCrypto(crypto: Crypto){

        this.cryptos.add(crypto)
    }

    fun addQuote(quoteCrypto: CryptoQuote) {

        this.cryptosQuote.add(quoteCrypto)

    }

    fun quoteOfTypeCrypto(type: TypeCrypto): Double? {

        for (i in this.cryptosQuote ){

            if (i.typeCrypto == type){

                return i.quote

            }
        }

        return null

    }
}