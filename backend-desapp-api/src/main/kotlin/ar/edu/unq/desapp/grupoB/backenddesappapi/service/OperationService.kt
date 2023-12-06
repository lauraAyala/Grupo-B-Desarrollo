package ar.edu.unq.desapp.grupoB.backenddesappapi.service

import ar.edu.unq.desapp.grupoB.backenddesappapi.dto.OperationDTO
import ar.edu.unq.desapp.grupoB.backenddesappapi.model.*
import ar.edu.unq.desapp.grupoB.backenddesappapi.repository.OperationRepository
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.OperationRequest
import ar.edu.unq.desapp.grupoB.backenddesappapi.request.operationDatesRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.io.Serializable
import java.time.LocalDateTime

@Service
class OperationService {

    @Autowired
    lateinit var repo: OperationRepository

    @Autowired
    lateinit var serviceUser: UserService

    @Autowired
    lateinit var serviceCrypto: CryptoQuoteService

    fun createOperation(operation: Operation): Operation {

        return repo.save(operation)
    }

    fun createOperationBuy(operationRequest: OperationRequest): OperationDTO {

        var operationType = BuyOperation()
        var operativeDate: LocalDateTime = LocalDateTime.now()
        var user: User = serviceUser.recoverUser(operationRequest.user.toLong())
        var cryptoOfType: TypeCrypto = serviceCrypto.findOfType(operationRequest.typeCrypto)

        var crypto = CryptoQuote( cryptoOfType,operationRequest.quote)
        var operation = Operation(user, operationRequest.countNominal, crypto, operationRequest.quote, operationType)

        var operationUpdate = serviceUser.buyOperation(user, operation)

        this.createOperation(operationUpdate!!)

        var operationDTO = OperationDTO(operationType.type!!, operationRequest.typeCrypto, operationRequest.user)

        return operationDTO
    }

    fun allOperations(): List<Operation> {
        return repo.findAll()
    }

    fun findOfType(typeOperation: String): OperationType? {

        var operationsType: ArrayList<OperationType> = ArrayList()
        operationsType.add(BuyOperation())
        operationsType.add(SaleOperation())
        operationsType.add(CancelOperation())
        for (i in operationsType) {
            if (i.type == typeOperation) {

                return i

            }

        }


        return BuyOperation()

    }

    fun getOperationOf(user: User): ArrayList<Operation> {

        var operations = this.allOperations()
        var operationsUpdate: ArrayList<Operation> = ArrayList()
        for (operation in operations) {

            if (operation.userCreated == user) {

                operationsUpdate.add(operation)
            }
        }

        return operationsUpdate

    }

    fun intentionsActives(): List<OperationDTO> {

        var operationsDTO: ArrayList<OperationDTO> = ArrayList()
        for (i in this.allOperations()) {
            var user: Int = i.userCreated!!.id!!.toInt()
            var operationDTO: OperationDTO = OperationDTO(i.nameOperation!!, i.nameOperation!!, user)
            operationsDTO.add(operationDTO)

        }
        return operationsDTO
    }

    fun createOperationSale(operationRequest: OperationRequest): OperationDTO {

        var operationType: OperationType = SaleOperation()
        var user: User = serviceUser.recoverUser(operationRequest.user.toLong())
        var operativeDate: LocalDateTime = LocalDateTime.now()
        var cryptoOfType: TypeCrypto = serviceCrypto.findOfType(operationRequest.typeCrypto)

        var crypto = serviceCrypto.createCryptoQuote(CryptoQuote( cryptoOfType, operationRequest.quote))
        var operation = Operation(user, operationRequest.countNominal, crypto, operationRequest.quote, operationType)
        var operationUpdate =
            serviceUser.saleCrypto(user, operation) //(user,crypto,operationRequest.countNominal,operationRequest.quote)

        this.createOperation(operationUpdate!!)

        return OperationDTO(operationType.type!!, operationRequest.typeCrypto, operationRequest.user)
    }

    fun operationsDates(@RequestBody operationDatesRequest: operationDatesRequest): ReportCrypto {
        var user = serviceUser.recoverUser(operationDatesRequest.user.toLong())
        var crypto = serviceCrypto.findCryptoOF(operationDatesRequest.crypto)
        var operations = this.getOperationOf(user)
        user.operations.addAll(operations)

        return user.reportVolumOperatorOfCrypto(crypto,operationDatesRequest.firstDate,operationDatesRequest.secondDate)
    }

}
