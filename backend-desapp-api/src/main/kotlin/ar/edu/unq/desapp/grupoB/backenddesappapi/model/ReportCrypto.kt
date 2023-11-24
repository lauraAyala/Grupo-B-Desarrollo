package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import java.time.LocalDateTime

class ReportCrypto (val operativeDate:LocalDateTime, val amountDolar: Double, val amountArg: Double, val crypto: TypeCrypto, val cantNominal: Int, val currentQuoteArg: Double, val quoteArg: Double ){

}
