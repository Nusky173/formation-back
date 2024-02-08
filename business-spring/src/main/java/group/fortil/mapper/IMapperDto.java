package group.fortil.mapper;

/**
 * @param <T> business Object
 * @param <U> data transfer object
 */
public interface IMapperDto<T, U> {

    T dtoToBusiness(U dto);

    U businessToDto(T business);
}
