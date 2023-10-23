package ar.edu.unq.desapp.grupoB.backenddesappapi.controller

import ar.edu.unq.desapp.grupoB.backenddesappapi.dto.OperationDTO
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.*
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.OperationRequest
import ar.edu.unq.desapp.grupoB.backenddesappapi.service.CryptoService
import ar.edu.unq.desapp.grupoB.backenddesappapi.service.OperationService
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
class OperationController {

    @Autowired
    lateinit var service: OperationService
    @Autowired
    lateinit var serviceUser : UserService
    @Autowired
    lateinit var serviceCrypto: CryptoService

    @GetMapping("/intentionsActives")
    fun getIntentionsActives() : List<OperationDTO>{

        var operationsDTO : ArrayList<OperationDTO> = ArrayList()
        for(i in service.allOperations()){
            var typeCrypto : String = (serviceCrypto.findBy(i.crypto!!.id.hashCode()))!!.typeCrypto!!.name
            var user  : Int = i.userCreated.hashCode()
            var operationDTO : OperationDTO = OperationDTO(i.nameOperation!!,typeCrypto,user)
            operationsDTO.add(operationDTO)

        }
        return operationsDTO
    }

    @PostMapping("/buyCrypto")
    fun saveOperation(@RequestBody operationRequest: OperationRequest) : ResponseEntity<OperationDTO> {

        var operationType : OperationType = BuyOperation()
        var user: User = serviceUser.findBy(operationRequest.user)
        var operativeDate : LocalDateTime = LocalDateTime.now()
        var cryptoOfType : TypeCrypto = serviceCrypto.findOfType(operationRequest.typeCrypto)
        var crypto : Crypto = serviceCrypto.createCrypto(Crypto(user,operationRequest.quote,operativeDate,cryptoOfType))
        service.createOperation(Operation(user, operationRequest.countNominal,crypto,operationRequest.quote, operationType))
        var operationDTO = OperationDTO(operationType.type!!,operationRequest.typeCrypto, operationRequest.user)
        return ResponseEntity(operationDTO, HttpStatus.CREATED)
    }

    @PostMapping("/saleCrypto")
    fun saveOperationSale(@RequestBody operationRequest: OperationRequest) : ResponseEntity<OperationDTO> {

        var operationType : OperationType = SaleOperation()
        var user: User = serviceUser.findBy(operationRequest.user)
        var operativeDate : LocalDateTime = LocalDateTime.now()
        var cryptoOfType : TypeCrypto = serviceCrypto.findOfType(operationRequest.typeCrypto)
        var crypto : Crypto = serviceCrypto.createCrypto(Crypto(user,operationRequest.quote,operativeDate,cryptoOfType))
        service.createOperation(Operation(user, operationRequest.countNominal,crypto,operationRequest.quote, operationType))
        var operationDTO = OperationDTO(operationType.type!!,operationRequest.typeCrypto, operationRequest.user)
        return ResponseEntity(operationDTO, HttpStatus.CREATED)
    }

}