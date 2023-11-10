package architectureTest

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import org.springframework.beans.factory.annotation.Autowired


@AnalyzeClasses(packages = arrayOf("ar.edu.unq.desapp.grupoB.backenddesappapi"))
class ArchitectureTest {

    @ArchTest
    val services_should_not_access_controllers: ArchRule = ArchRuleDefinition.noClasses().that().resideInAPackage("..service..")
        .should().accessClassesThat().resideInAPackage("..service..")

    @ArchTest
    val repository_only_accesed_by_service: ArchRule = ArchRuleDefinition.classes()
        .that().resideInAPackage("..repository..")
        .should().onlyBeAccessed().byAnyPackage("..service", "..repository..")

    @ArchTest
    val should_package_model_independent_of_other_packages: ArchRule = ArchRuleDefinition.noClasses()
        .that().resideInAPackage("..model..")
        .should().dependOnClassesThat().resideInAnyPackage("..repository..", "..service..")

    @ArchTest
    val code_units_in_service_layer_should_be_autowired: ArchRule = ArchRuleDefinition.noCodeUnits()
        .that().areDeclaredInClassesThat()
        .resideInAPackage("..service..")
        .should().beMetaAnnotatedWith(Autowired::class.java)

}

