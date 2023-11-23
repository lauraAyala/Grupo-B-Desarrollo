package ar.edu.unq.desapp.grupoB.backenddesappapi.service

import ar.edu.unq.desapp.grupoB.backenddesappapi.model.DolarPrice
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.http.*
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import javax.annotation.PostConstruct


@Configuration
@EnableScheduling
@Service
class DolarService {

    /*
var template : RestTemplate = RestTemplate()
    //@Value("http://api.estadisticas.bcra.com/usd")
    var bcraApi : String = "http://api.estadisticas.bcra.com/usd"

lateinit var lastPrice : DolarPrice

@Scheduled(fixedDelay = 60000)
@PostConstruct
fun getDolarPrice(){

    var headers : HttpHeaders = HttpHeaders()
    headers.set("Authorization","BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3Mjk3MDIzNjEsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJsdWNhc0Bob3RtYWlsLmNvbSJ9.fZ04yaP3xE7WkWr3lbmovn0HxqLROVE20tV5csuZUdpk7V1ikqNXpM_4fW7v5lBHjaYi7SMFsSfe_jREl3r44w")
    var entity: HttpEntity<Void> = HttpEntity<Void>(headers)
    var response : ResponseEntity<Array<DolarPrice>> = template.exchange(bcraApi,HttpMethod.GET,entity,Array<DolarPrice>::class.java)
    var prices : Array<DolarPrice> = response.body!!

    this.lastPrice = prices.last()
}

fun getLastPrice(): Double{

    return lastPrice.value
}

fun getPriceInArs(price: Double): Double{

    return price * getLastPrice()
}*/

}