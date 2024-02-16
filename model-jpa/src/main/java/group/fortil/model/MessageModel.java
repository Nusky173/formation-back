package group.fortil.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "message")
@NamedQueries({
    @NamedQuery(name = "MessageModel.findMessagesByUser", query = "select m from MessageModel m where m.user = ?1")
})
public class MessageModel extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_index")
    private Long messageIndex;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tag_message",
        joinColumns = @JoinColumn(name = "message_index", referencedColumnName = "m_index", table = "message"),
        inverseJoinColumns = @JoinColumn(name = "tag_index", referencedColumnName = "t_index", table = "tag")
    )
    private List<TagModel> tags;

    public MessageModel() {
    }

    public MessageModel(
        String content,
        UserModel user
    ) {
        this.content = content;
        this.user = user;
    }

    private MessageModel(
        String content,
        UserModel user,
        Long index
    ) {
        this.content = content;
        this.user = user;
        this.messageIndex = index;
    }

    public Long getMessageIndex() {
        return messageIndex;
    }

    public void setMessageIndex(Long messageIndex) {
        this.messageIndex = messageIndex;
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


    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }


    public UserModel getUser() {
        return user;
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
