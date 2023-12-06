package ar.edu.unq.desapp.grupoB.backenddesappapi.controller

import ar.edu.unq.desapp.grupoB.backenddesappapi.dto.OperationDTO
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.*
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.OperationRequest
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.operationDatesRequest
import ar.edu.unq.desapp.grupoB.backenddesappapi.service.OperationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OperationController {

    @Autowired
    lateinit var service: OperationService

    @GetMapping("/intentionsActives")
    fun getIntentionsActives() : List<OperationDTO>{
        return service.intentionsActives()
    }

    @PostMapping("/buyCrypto")
    fun saveOperation(@RequestBody operationRequest: OperationRequest) : ResponseEntity<OperationDTO> {

        var operationDTO = service.createOperationBuy(operationRequest)
            return ResponseEntity(operationDTO, HttpStatus.CREATED)
    }

    @PostMapping("/saleCrypto")
    fun saveOperationSale(@RequestBody operationRequest: OperationRequest) : ResponseEntity<OperationDTO> {

        var operationDTO = service.createOperationSale(operationRequest)
            return ResponseEntity(operationDTO, HttpStatus.CREATED)
    }

    @PostMapping("/getOperationsDates")
    fun getOperationsDates(@RequestBody operationDatesRequest : operationDatesRequest) : ResponseEntity<ReportCrypto> {

        var report = service.operationsDates(operationDatesRequest)
        return ResponseEntity(report, HttpStatus.OK)
    }


}