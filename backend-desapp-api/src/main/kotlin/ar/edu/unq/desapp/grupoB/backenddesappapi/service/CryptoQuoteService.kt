package ar.edu.unq.desapp.grupoB.backenddesappapi.service

import ar.edu.unq.desapp.grupoB.backenddesappapi.model.CryptoQuote
import ar.edu.unq.desapp.grupoB.backenddesappapi.repository.CryptoQuoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CryptoQuoteService {

    @Autowired
    lateinit var repo : CryptoQuoteRepository

    fun createCryptoQuote(cryptoQuote: CryptoQuote ) : CryptoQuote {
        repo.save(cryptoQuote)
        return cryptoQuote
    }

    fun allCryptoQuote(): List<CryptoQuote>{
        return repo.findAll()
    }
}
