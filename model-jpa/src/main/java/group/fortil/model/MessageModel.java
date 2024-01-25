package group.fortil.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "message")
public class MessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "message_index")
    private UUID messageIndex;

    @Column(name = "message_content", length = 50, nullable = false)
    private String content;

    @Column(name = "message_publication_date", nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    @Column(name = "message_modification_date")
    @Temporal(TemporalType.DATE)
    private Date modificationDate;

    @ManyToOne
    @JoinColumn(name = "message_user_index")
    private UserModel userIndex;

    //findMessageByTagIndex
    public MessageModel(UUID messageIndex, String content, Date publicationDate, UserModel userIndex) {
        this.messageIndex = messageIndex;
        this.content = content;
        this.publicationDate = publicationDate;
        this.userIndex = userIndex;
    }

    public UUID getMessageIndex() {
        return messageIndex;
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

    public UserModel getUserIndex() {
        return userIndex;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
            "messageIndex=" + messageIndex +
            ", content='" + content + '\'' +
            ", publicationDate=" + publicationDate +
            ", modificationDate=" + modificationDate +
            ", userIndex=" + userIndex +
            '}';
    }
}
