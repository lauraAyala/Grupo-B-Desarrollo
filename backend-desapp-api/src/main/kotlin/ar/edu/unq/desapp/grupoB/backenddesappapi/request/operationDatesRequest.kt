package ar.edu.unq.desapp.grupoB.backenddesappapi.request

import ar.edu.unq.desapp.grupoB.backenddesappapi.model.TypeCrypto
import java.time.LocalDate

class operationDatesRequest (val crypto : TypeCrypto, val firstDate: LocalDate, val secondDate:LocalDate, val user: Int){

}
