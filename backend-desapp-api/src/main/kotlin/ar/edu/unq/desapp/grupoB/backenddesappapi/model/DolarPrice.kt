package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
class DolarPrice (var date : LocalDateTime,val value: Double){
}