package ar.edu.unq.desapp.grupoB.backenddesappapi.service

import ar.edu.unq.desapp.grupoB.backenddesappapi.builder.CryptoBuilder
import ar.edu.unq.desapp.grupoB.backenddesappapi.dto.CryptoQuoteDTO
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.CryptoQuote
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.TypeCrypto
import ar.edu.unq.desapp.grupoB.backenddesappapi.repository.CryptoQuoteRepository
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.CryptoQuoteRequest
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.QuotitationRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

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

    fun findOfType(typeCrypto: String): TypeCrypto {

        for (i in TypeCrypto.values()) {

            if (i.name == typeCrypto) {

                return i
            }
        }
        return TypeCrypto.ALICEUSDT
    }

    fun findCryptoOF(cryptoOfType: TypeCrypto): CryptoQuote {

        for (c in repo.findAll()){

            if (c.typeCrypto == cryptoOfType){

                return c

            }

        }

        var crypto = CryptoBuilder().builder()
        return crypto

    }

    fun getBinanceCryptos() : ArrayList<TypeCrypto>{

        var cryptos : ArrayList<TypeCrypto> = ArrayList()
        for ( c in TypeCrypto.values()) {

            var crypto: TypeCrypto = binance.getCryptoCurrencyValue(c.name)

            cryptos.add(crypto)
        }

        return cryptos
    }

    @Cacheable("allCryptoQuote")
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

    fun getQuotitationAtDate(quotitationRequest : QuotitationRequest): List<CryptoQuoteDTO> {

        var typeCrypto = TypeCrypto.valueOf(quotitationRequest.crypto)
        //var cryptosDTO = serviceCryptoQuote.getQuotitationAtDate(typeCrypto,quotitationRequest.date)

        var cryptosQuoteDTO : ArrayList<CryptoQuoteDTO> = ArrayList()
        var datePrevious = quotitationRequest.date.plusDays(-1)
        print(datePrevious)
        for (cryptoQuote in this.allCryptoQuote()){

            if (cryptoQuote.typeCrypto == typeCrypto && (cryptoQuote.operativeDate!! >= datePrevious || cryptoQuote.operativeDate!! <= quotitationRequest.date)){

                var cryptoDTO = CryptoQuoteDTO(cryptoQuote.typeCrypto!!.name,cryptoQuote.quote!!)
                cryptosQuoteDTO.add(cryptoDTO)
            }
        }

        return cryptosQuoteDTO
    }

    fun saveCryptoQuote(@RequestBody cryptoQuote: CryptoQuoteRequest) : CryptoQuoteDTO {


        var typeCrypto = TypeCrypto.valueOf(cryptoQuote.typeQuote)
        var cryptoQuoteUpdate = CryptoQuote(typeCrypto, cryptoQuote.quote)
        this.createCryptoQuote(cryptoQuoteUpdate)

        return CryptoQuoteDTO(cryptoQuote.typeQuote,cryptoQuote.quote)

    }

    }
