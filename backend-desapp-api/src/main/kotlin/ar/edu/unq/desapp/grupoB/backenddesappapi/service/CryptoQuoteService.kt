package ar.edu.unq.desapp.grupoB.backenddesappapi.service

import ar.edu.unq.desapp.grupoB.backenddesappapi.model.CryptoQuote
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.TypeCrypto
import ar.edu.unq.desapp.grupoB.backenddesappapi.repository.CryptoQuoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CryptoQuoteService {

    @Autowired
    lateinit var repo : CryptoQuoteRepository
    @Autowired
    lateinit var binance : BinanceProxyService
    //lateinit var dolarPrice : DolarService

    fun createCryptoQuote(cryptoQuote: CryptoQuote ) : CryptoQuote {
        repo.save(cryptoQuote)
        return cryptoQuote
    }

    fun getBinanceCryptos() : ArrayList<TypeCrypto>{

        var cryptos : ArrayList<TypeCrypto> = ArrayList()
        for ( c in TypeCrypto.values()) {

            var crypto: TypeCrypto = binance.getCryptoCurrencyValue(c.name)

            cryptos.add(crypto)
        }

        return cryptos
    }
    fun allCryptoQuote(): List<CryptoQuote>{

        /*var cryptos : ArrayList<TypeCrypto> = this.getBinanceCryptos()
        var cryptosQuote : ArrayList<CryptoQuote> = ArrayList()

            for ( cryptoquote in repo.findAll()){

            if (cryptos.contains(cryptoquote.typeCrypto)){

                cryptosQuote.add(cryptoquote)
            }

        }*/

        return repo.findAll()
    }


}
