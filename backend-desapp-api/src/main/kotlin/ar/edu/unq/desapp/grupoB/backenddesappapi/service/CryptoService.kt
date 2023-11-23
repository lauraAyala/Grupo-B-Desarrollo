package ar.edu.unq.desapp.grupoB.backenddesappapi.service

import ar.edu.unq.desapp.grupoB.backenddesappapi.builder.CryptoBuilder
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.Crypto
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.TypeCrypto
import ar.edu.unq.desapp.grupoB.backenddesappapi.repository.CryptoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CryptoService {

    @Autowired
    lateinit var repo : CryptoRepository


    fun createCrypto(crypto : Crypto) : Crypto {
        //gruporepo.save(crypto)
        return  repo.save(crypto)
    }

    fun allCryptos(): List<Crypto>{
        return repo.findAll()
    }

    fun findBy(cryptoId: Int): Crypto? {

        for (i in allCryptos()){

            if( i.id.hashCode() == cryptoId){

                return i
            }
        }

        return null
    }

    fun findOfType(typeCrypto: String): TypeCrypto {

        for (i in TypeCrypto.values()) {

            if (i.name == typeCrypto) {

                return i
            }
        }
        return TypeCrypto.ALICEUSDT
    }

    fun findCryptoOF(cryptoOfType: TypeCrypto): Crypto {

        for (c in repo.findAll()){

            if (c.typeCrypto == cryptoOfType){

                return c

            }

        }

        var crypto = CryptoBuilder().builder()
        return crypto

    }

}
