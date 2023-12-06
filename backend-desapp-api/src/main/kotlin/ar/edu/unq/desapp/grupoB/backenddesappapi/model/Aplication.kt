package ar.edu.unq.desapp.grupoB.backenddesappapi.model

class Aplication {

    var users : ArrayList<User> = ArrayList()
    var cryptosQuote: ArrayList<CryptoQuote> = ArrayList()

    fun registerUser(user: User){

        this.users.add(user)
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

    fun cotitationCryptoActive() : ArrayList<CryptoQuote>{

        return cryptosQuote

    }

    fun intentionsActiveOfSaleAndBuyCryptos() : ArrayList<Operation>{

        var intentionsActives : ArrayList<Operation> = ArrayList()
        for (i in this.users){

            intentionsActives.addAll(i.operations)

        }
        return intentionsActives
    }

    fun cancelatedOperation(operation: Operation){

        var cancelOperation = Operation(null,operation.cantNominal!!,operation.crypto!!,operation.amount!!, CancelOperation())
        cancelOperation.processAction()
    }
}
