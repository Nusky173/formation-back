package group.fortil.business;

import group.fortil.validation.pastofanotherdate.PastOfAnotherDate;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
@PastOfAnotherDate
public interface IMessageBusiness extends IBusiness<Long> {

    Date getPublishDate();

    Date getChangeDate();

}
