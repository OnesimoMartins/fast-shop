package com.fastshop.core.validation

import com.fastshop.domain.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [NameNotExistsInDbValidator::class])
@Retention(AnnotationRetention.RUNTIME)
annotation class NameMustNotExistsInDb(
    val message: String = "Name cannot exists in database",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class NameNotExistsInDbValidator : ConstraintValidator<NameMustNotExistsInDb, String> {
    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    override fun isValid(value: String, context: ConstraintValidatorContext?) =
        !categoryRepository.existsByName(value)

}
