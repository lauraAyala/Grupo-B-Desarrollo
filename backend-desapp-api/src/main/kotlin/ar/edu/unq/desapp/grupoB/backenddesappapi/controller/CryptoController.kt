package ar.edu.unq.desapp.grupoB.backenddesappapi.controller

import ar.edu.unq.desapp.grupoB.backenddesappapi.dto.CryptoDTO
import ar.edu.unq.desapp.grupoB.backenddesappapi.dto.CryptoQuoteDTO
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.Crypto
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.CryptoQuote
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.TypeCrypto
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.CryptoQuoteRequest
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.CryptoRequest
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.TypeCryptoRequest
import ar.edu.unq.desapp.grupoB.backenddesappapi.service.BinanceProxyService
import ar.edu.unq.desapp.grupoB.backenddesappapi.service.CryptoService
import ar.edu.unq.desapp.grupoB.backenddesappapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class CryptoController {

    @Autowired
    lateinit var service : CryptoService
    @Autowired
    lateinit var serviceUser : UserService
    @Autowired
    lateinit var binance : BinanceProxyService




    @GetMapping("/cryptos")
    fun getCryptos() : List<Crypto>{
        return service.allCryptos()
    }


    @PostMapping("/addCrypto")
    fun saveCrypto(@RequestBody crypto: CryptoRequest) : ResponseEntity<CryptoDTO> {
        var typeCrypto = TypeCrypto.valueOf(crypto.typeCrypto)
        var user = serviceUser.findBy(crypto.user)
        println(user)
        println(typeCrypto)
        var operativeDate = LocalDateTime.now()
        var cryptoUpdate = Crypto(crypto.quote,typeCrypto)
        println(cryptoUpdate)
        cryptoUpdate = service.createCrypto(cryptoUpdate)
        var cryptoDTO = CryptoDTO(cryptoUpdate.typeCrypto!!.name, cryptoUpdate.quote!!)
        return ResponseEntity(cryptoDTO, HttpStatus.CREATED)
    }

    @GetMapping("/cryptoOfType")
    fun cryptoOfType( @RequestBody typeCrypto : TypeCryptoRequest) : ResponseEntity<Crypto>{

        var typeCrypto1 = service.findOfType(typeCrypto.type)
        var crypto = service.findCryptoOF(typeCrypto1)
        return ResponseEntity(crypto,HttpStatus.ACCEPTED)

    }


    //.findOfType
}