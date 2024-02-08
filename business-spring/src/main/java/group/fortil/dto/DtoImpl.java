package group.fortil.dto;

public abstract class DtoImpl<T extends Long> implements IDto<Long> {

    private Long index;

    public DtoImpl() {}

    public DtoImpl(Long index) {
        this.index = index;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "DtoImpl{" +
            "index=" + index +
            '}';
    }
}
