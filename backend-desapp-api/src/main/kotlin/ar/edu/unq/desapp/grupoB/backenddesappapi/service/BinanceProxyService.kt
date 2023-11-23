package ar.edu.unq.desapp.grupoB.backenddesappapi.service

import ar.edu.unq.desapp.grupoB.backenddesappapi.model.TypeCrypto
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.client.RestTemplate


@Service
class BinanceProxyService {

    var restTemplate : RestTemplate = RestTemplate()
    //@Value("integration.binance.api.url:None")
   var binanceApiURL : String = "https://api.binance.com/api/v2/"

    fun getCryptoCurrencyValue(symbol: String) : TypeCrypto{

        var entity : TypeCrypto? = restTemplate.getForObject(binanceApiURL + "ticker/price?symbol=" + symbol, TypeCrypto::class.java)

        return entity!!
    }
}