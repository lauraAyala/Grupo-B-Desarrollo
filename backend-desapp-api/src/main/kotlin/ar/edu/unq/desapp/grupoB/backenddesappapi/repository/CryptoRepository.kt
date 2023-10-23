package ar.edu.unq.desapp.grupoB.backenddesappapi.repository

import ar.edu.unq.desapp.grupoB.backenddesappapi.model.Crypto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CryptoRepository : JpaRepository<Crypto,Long> {

}
