package group.fortil.validation.pastofanotherdate;

import group.fortil.business.IMessageBusiness;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;

public class PastOfAnotherDateValidator implements ConstraintValidator<PastOfAnotherDate, IMessageBusiness> {
    @Override
    public boolean isValid(
        IMessageBusiness value,
        ConstraintValidatorContext context
    ) {
        Date publicationDate = value.getPublishDate();
        Date dateToControl = value.getChangeDate();

        if (publicationDate.after(dateToControl)) {
            context.buildConstraintViolationWithTemplate("Modification Date is Invalid, must be in the future of Publication Date")
                .addConstraintViolation();
            return false;
        }

        return true;
    }
}
