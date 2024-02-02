package group.fortil.business;

import jakarta.validation.constraints.NotBlank;

public class TagBusinessImpl extends BusinessImpl implements ITagBusiness {

    @NotBlank(message = "Tag should not be blank")
    private String value;

    public TagBusinessImpl() {
        super();
    }

    public TagBusinessImpl(String value) {
        super();
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
