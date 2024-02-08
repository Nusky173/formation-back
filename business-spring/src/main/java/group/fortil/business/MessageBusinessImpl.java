package group.fortil.business;

import java.util.Date;
import java.util.List;

public class MessageBusinessImpl extends BusinessImpl implements IMessageBusiness {

    private String text;

    private Date publishDate;

    private Date changeDate;

    private UserBusinessImpl owner;

    private List<TagBusinessImpl> tagList;

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
