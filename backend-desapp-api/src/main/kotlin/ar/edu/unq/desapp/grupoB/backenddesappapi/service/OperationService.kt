package ar.edu.unq.desapp.grupoB.backenddesappapi.service

import ar.edu.unq.desapp.grupoB.backenddesappapi.model.*
import ar.edu.unq.desapp.grupoB.backenddesappapi.repository.OperationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OperationService {

    @Autowired
    lateinit var repo : OperationRepository

    fun createOperation(operation : Operation) : Operation?{
        repo.save(operation)
        return operation
    }

    fun allOperations(): List<Operation>{
        return repo.findAll()
    }

    fun findOfType(typeOperation: String): OperationType? {

        var operationsType : ArrayList<OperationType> = ArrayList()
        operationsType.add(BuyOperation())
        operationsType.add(SaleOperation())
        operationsType.add(CancelOperation())
        for (i in operationsType){
            if (i.type == typeOperation){

                return i

            }

        }


        return BuyOperation()

    }

    fun getOperationOf(user: User): ArrayList<Operation> {

        var operations = this.allOperations()
        var operationsUpdate : ArrayList<Operation> =  ArrayList()
        for (operation in operations){

            if (operation.userCreated == user){

                operationsUpdate.add(operation)
            }
        }

        return operationsUpdate

    }


}
