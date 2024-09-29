package at.spengergasse.fhirstarter.validators;

import at.spengergasse.fhirstarter.entity.Patient;
import at.spengergasse.fhirstarter.model.Coding;
import at.spengergasse.fhirstarter.model.Identifier;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

public class PatientValidator implements ConstraintValidator<PatientValid, Patient> {
private final WebClient client =WebClient.create();
    @Override
    public void initialize(PatientValid constraintAnnotation) {
    }
    @Override
    public boolean isValid(Patient patient, ConstraintValidatorContext context) {
        if (patient.getDeceasedBoolean() != null && patient.getDeceasedDateTime() != null) {
            return false;
        }

        for (Identifier identifier : patient.getIdentifier()) {
            for (Coding coding : identifier.getType().getCoding()) {
                if (coding.getDisplay().equals("Sozialversicherungsnummer")) {
                    String nr = identifier.getValue().substring(0, 4);
                    String dob = identifier.getValue().substring(4, 10);


                    WebClient.ResponseSpec responseSpec = client.get()
                            .uri(String.format("http://www2.argedaten.at/php/svnummer.php?str[1]=%s&str[2]=%s", nr, dob))
                            .retrieve();
                    String responseBody = responseSpec.bodyToMono(String.class).block();
                    if (responseBody == null){
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate("Response body is null")
                                .addConstraintViolation();
                        return false;
                    }
                    if(responseBody.contains("OK!")){
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate("Sozialversicherungsnummer OK")
                                .addConstraintViolation();
                        return true;
                    }
                }
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Invalid Sozialversicherungsnummer")
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }

    private String getSVNRFromPatient(Patient patient) {
        // Assuming the SVNR is stored in the identifier field of the Patient entity
        // This method should extract the SVNR from the identifier
        // Replace this with the actual logic to get the SVNR from the patient
        return patient.getIdentifier().stream()
                .filter(identifier -> "SVNR".equals(identifier.getSystem()))
                .map(identifier -> identifier.getValue())
                .findFirst()
                .orElse(null);
    }

    private boolean isValidSVNR(String svnr) {
        if (svnr == null || svnr.length() != 10) {
            return false;
        }

        int[] weights = {3, 7, 9, 0, 5, 8, 4, 2, 1, 6};
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(svnr.charAt(i));
            sum += digit * weights[i];
        }

        return sum % 11 == 0;
    }
}