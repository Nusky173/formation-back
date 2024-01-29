package group.fortil.model;

import group.fortil.model.MessageModel;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tag")
@NamedQuery(name="TagModel.findAllTagsForAMessage", query="select m from MessageModel m left join TagModel t on m.messageIndex = t.tagIndex")
public class TagModel {

    @Id
    @UuidGenerator
    @Column(name="t_index", length=50, nullable = false)
    private UUID tagIndex;

    @Column(name="content", length=50, nullable = false)
    private String name;

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

    @Override
    public String toString() {
        return "TagModel{" +
            "tagIndex=" + tagIndex +
            ", name='" + name + '\'' +
            '}';
    }
}