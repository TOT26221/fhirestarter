package at.spengergasse.fhirstarter.validators;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;
    @Target({ ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = {PatientValidator.class})
    @Documented
    public @interface PatientValid {
        String message() default "Patient.deceasedBoolean and Patient.decesaedDateTime must not both have a value";
        Class<?>[] groups() default { };
        Class<? extends Payload>[] payload() default { };
    }

