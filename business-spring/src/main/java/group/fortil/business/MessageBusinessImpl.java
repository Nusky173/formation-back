package group.fortil.business;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;
import java.util.List;

public class MessageBusinessImpl extends BusinessImpl implements IMessageBusiness {

    @NotBlank(message = "Message's content must not be nul")
    private String text;

    @PastOrPresent
    private Date publishDate;

    @PastOrPresent
    private Date changeDate;

    @NotNull
    @Valid
    private UserBusinessImpl owner;

    private List<@Valid @NotNull TagBusinessImpl> tagList;

    public MessageBusinessImpl() {
        super();
    }

    public MessageBusinessImpl(
        String text,
        UserBusinessImpl owner
    ) {
        super();
        this.text = text;
        this.owner = owner;

    }

    public String getText() {
        return this.text;
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

    public UserBusinessImpl getOwner() {
        return this.owner;
    }

    public void setOwner(UserBusinessImpl owner) {
        this.owner = owner;
    }

    public List<TagBusinessImpl> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagBusinessImpl> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "MessageBusinessImpl{" +
            "text='" + text + '\'' +
            ", publishDate=" + publishDate +
            ", changeDate=" + changeDate +
            ", owner=" + owner.toString() +
            ", tagList=" + tagList.stream().map(TagBusinessImpl::toString) +
            '}';
    }
}
