package group.fortil.model;

import group.fortil.model.MessageModel;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tag")
public class TagModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="tag_index", length=50, nullable = false)
    private UUID tagIndex;

    @Column(name="tag_content", length=50, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "tags_on_messages",
        joinColumns= @JoinColumn(name="tag_index"),
        inverseJoinColumns=@JoinColumn(name="message_index")
    )
    private List<MessageModel> messages;

    public TagModel() {
    }

    public TagModel(UUID tagIndex, String name) {
        this.tagIndex = tagIndex;
        this.name = name;
    }
    public UUID getTagIndex() {
        return tagIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "TagModel{" +
            "tagIndex=" + tagIndex +
            ", name='" + name + '\'' +
            ", messages=" + messages.stream().map(MessageModel::toString) +
            '}';
    }
}