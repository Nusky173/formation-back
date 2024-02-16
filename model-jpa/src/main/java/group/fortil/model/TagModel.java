package group.fortil.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tag")
@NamedQueries({
    @NamedQuery(name = "TagModel.findAllTagsForAMessage", query = "select m from MessageModel m left join TagModel t on m.messageIndex = t.tagIndex")
})
public class TagModel extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_index", nullable = false)
    private Long tagIndex;

    @Column(name = "content", length = 50, nullable = false)
    private String name;

    public TagModel() {
    }

    public TagModel(
        String name
    ) {
        this.name = name;
    }

    private TagModel(
        String name,
        Long index
    ) {
        this.name = name;
        this.tagIndex = index;
    }

    public Long getTagIndex() {
        return tagIndex;
    }

    public void setTagIndex(Long tagIndex) {
        this.tagIndex = tagIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagModel{" + ", name='" + name + '\'' + '}';
    }
}