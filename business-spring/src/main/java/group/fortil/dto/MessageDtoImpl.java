package group.fortil.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Validated
public class MessageDtoImpl extends DtoImpl<Long> implements IMessageDto {

    @NotBlank(message = "Message's content must not be nul")
    private String text;

    @PastOrPresent
    private Date publishDate;

    @PastOrPresent
    private Date changeDate;

    @NotNull
    @Valid
    private UserDtoImpl owner;

    private List<@Valid @NotNull TagDtoImpl> tagList;

    public MessageDtoImpl() {
        super();
    }

    public MessageDtoImpl(
        String text,
        UserDtoImpl owner
    ) {
        super();
        this.text = text;
        this.owner = owner;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public UserDtoImpl getOwner() {
        return owner;
    }

    public void setOwner(UserDtoImpl owner) {
        this.owner = owner;
    }

    public List<TagDtoImpl> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagDtoImpl> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "MessageDtoImpl{" +
            "text='" + text + '\'' +
            ", publicationDate=" + publishDate +
            ", modificationDate=" + changeDate +
            ", owner=" + owner.toString() +
            ", tagList=" + tagList.stream().map(TagDtoImpl::toString) +
            '}';
    }
}
