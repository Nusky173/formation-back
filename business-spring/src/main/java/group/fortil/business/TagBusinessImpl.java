package group.fortil.business;

public class TagBusinessImpl extends BusinessImpl implements ITagBusiness {

    private String value;

    public TagBusinessImpl() {
        super();
    }

    public TagBusinessImpl(String value) {
        super();
        this.value = value;
    }

    public TagBusinessImpl(
        Long index,
        String value
    ) {
        super(index);
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TagBusinessImpl{" +
            "value='" + value + '\'' +
            '}';
    }
}
