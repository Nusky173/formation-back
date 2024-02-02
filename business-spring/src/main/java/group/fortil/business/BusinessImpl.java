package group.fortil.business;

public abstract class BusinessImpl implements IBusiness<Long> {

    private Long index;

    public BusinessImpl() {

    }

    public BusinessImpl(Long index) {
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
        return "" ;
    }
}
