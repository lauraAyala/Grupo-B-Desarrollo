package ar.edu.unq.desapp.grupoB.backenddesappapi.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.Signature
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.sql.Timestamp


@Aspect
@Component
class AuditAspect {
    private var start: Long = 0
    private lateinit var args: Array<Any>
    private var tipoRetornoYNombreMetodo: Signature? = null
    @Pointcut("execution(* ar.edu.unq.desapp.grupoB.backenddesappapi.controller.*.*(..))")
    fun methodsStarterServicePointcut() {
    }

    @Before("methodsStarterServicePointcut()")
    @Throws(Throwable::class)
    fun beforeMethods(joinPoint: JoinPoint) {
        tipoRetornoYNombreMetodo = joinPoint.signature
        args = joinPoint.args
        start = System.currentTimeMillis()
    }

    @After("methodsStarterServicePointcut()")
    @Throws(Throwable::class)
    fun afterMethods() {
        logger.info("*****************************************************")
        logger.info("*▌▌▌▌▌▌▌▌▌▌▌ INFORMACION DE EJECUCION ▌▌▌▌▌▌▌▌▌▌▌")
        logger.info("*▌▌▌▌▌▌▌▌▌▌▌ Fecha de ejecucion del metodo: " + Timestamp(System.currentTimeMillis()) + " ▌▌▌▌▌▌▌▌▌▌▌")
        val timeExcecuted = System.currentTimeMillis() - start
        logger.info("*▌▌▌▌▌▌▌▌▌▌▌El metodo tardo: $timeExcecuted ms ▌▌▌▌▌▌▌▌▌▌▌▌")
        logger.info("*▌▌▌▌▌▌▌▌▌▌▌ retorna ---->" + tipoRetornoYNombreMetodo + "<---- EL nombre del metodo llamado ▌▌▌▌▌▌▌▌▌▌▌")
        logger.info("*↓↓↓↓↓↓↓↓↓↓↓ ARGUMENTOS EN ORDEN DE USO ↓↓↓↓↓↓↓↓↓↓↓")
        if (args.size == 0) {
            logger.info("*▌▌▌▌▌▌▌▌▌▌▌   Este metodo no usa parametros   ▌▌▌▌▌▌▌▌▌▌▌")
        }
        for (argumnet in args) {
            logger.info("*▌▌▌▌▌▌▌▌▌▌▌   $argumnet   ▌▌▌▌▌▌▌▌▌▌▌")
        }
        logger.info("*↑↑↑↑↑↑↑↑↑↑↑ ARGUMENTOS EN ORDEN DE USO ↑↑↑↑↑↑↑↑↑↑↑")
        logger.info("*****************************************************")
    }

    companion object {
        var logger = LoggerFactory.getLogger(AuditAspect::class.java)
    }
}