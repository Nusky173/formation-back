package group.fortil.dto;

import jakarta.validation.constraints.NotBlank;

public class TagDtoImpl extends DtoImpl<Long> implements ITagDto {

    @NotBlank(message = "Tag should not be blank")
    private String value;

    public TagDtoImpl() {
        super();
    }

    public TagDtoImpl(String value) {
        super();
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TagDtoImpl{" +
            "value='" + value + '\'' +
            '}';
    }
}
