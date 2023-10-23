package ar.edu.unq.desapp.grupoB.backenddesappapi.repository

import ar.edu.unq.desapp.grupoB.backenddesappapi.model.Operation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OperationRepository : JpaRepository<Operation, Long> {

}
