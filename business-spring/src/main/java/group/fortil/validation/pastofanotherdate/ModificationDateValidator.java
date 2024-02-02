package group.fortil.validation.pastofanotherdate;

import group.fortil.business.MessageBusinessImpl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;

public class ModificationDateValidator implements ConstraintValidator<PastOfAnotherDate, MessageBusinessImpl> {
    @Override
    public boolean isValid(
        MessageBusinessImpl value,
        ConstraintValidatorContext context
    ) {
        Date publicationDate = value.getPublishDate();
        Date dateToControl = value.getChangeDate();

        if (publicationDate.before(dateToControl)) {
            context.buildConstraintViolationWithTemplate("Modification Date is Invalid, must be in the future of Publication Date")
                .addConstraintViolation();
            return false;
        }

        return true;
    }
}
