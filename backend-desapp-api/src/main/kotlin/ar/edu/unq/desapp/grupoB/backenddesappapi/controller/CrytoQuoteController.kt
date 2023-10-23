package ar.edu.unq.desapp.grupoB.backenddesappapi.controller

import ar.edu.unq.desapp.grupoB.backenddesappapi.dto.CryptoQuoteDTO
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.CryptoQuote
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.TypeCrypto
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.CryptoQuoteRequest
import ar.edu.unq.desapp.grupoB.backenddesappapi.service.CryptoQuoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CrytoQuoteController {

    @Autowired
    lateinit var serviceCryptoQuote: CryptoQuoteService

    @GetMapping("/quotationOfCryptoActives")
    fun getQuotationOfCryptoActives() : List<CryptoQuote>{
        return serviceCryptoQuote.allCryptoQuote()
    }

    @PostMapping("/addCryptoQuote")
    fun saveCryptoQuote(@RequestBody cryptoQuote: CryptoQuoteRequest) : ResponseEntity<CryptoQuoteDTO> {
        var typeCrypto = TypeCrypto.valueOf(cryptoQuote.typeQuote)
        var cryptoQuoteUpdate = CryptoQuote(typeCrypto, cryptoQuote.quote)
        serviceCryptoQuote.createCryptoQuote(cryptoQuoteUpdate)
        var cryptoQuoteDTO = CryptoQuoteDTO(cryptoQuote.typeQuote,cryptoQuote.quote)
        return ResponseEntity(cryptoQuoteDTO, HttpStatus.CREATED)
    }

}


