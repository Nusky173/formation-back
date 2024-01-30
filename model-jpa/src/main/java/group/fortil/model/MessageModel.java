package group.fortil.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "message")
@NamedQueries({
    @NamedQuery(name = "MessageModel.findMessagesByUserIndex", query = "select m from MessageModel m where m.user = ?1")
})
public class MessageModel {

    @Id
    @UuidGenerator
    @Column(name = "m_index")
    private UUID messageIndex;

    @Column(name = "content", length = 50, nullable = false)
    private String content;

    @Column(name = "publication_date", nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date publicationDate;

    @Column(name = "modification_date")
    @Temporal(TemporalType.DATE)
    private Date modificationDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_index")
    private UserModel user;

    @ManyToMany
    @JoinTable(name = "tag_message",
        joinColumns = @JoinColumn(name = "message_index", referencedColumnName = "m_index", table = "message"),
        inverseJoinColumns = @JoinColumn(name = "tag_index", referencedColumnName = "t_index", table = "tag")
    )
    private List<TagModel> tags;

    public MessageModel() {
    }

    public MessageModel(
        UUID messageIndex,
        String content,
        Date publicationDate,
        UserModel user
    ) {
        this.messageIndex = messageIndex;
        this.content = content;
        this.publicationDate = publicationDate;
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
            ", content='" + content + '\'' +
            ", publicationDate=" + publicationDate +
            ", modificationDate=" + modificationDate +
            ", user=" + user +
            '}';
    }
}
