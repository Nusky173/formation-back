package group.fortil.mapper;

/**
 * T: BusinessImpl object
 * U: Model object
 */
public interface IMapperModel<T, U> {

    T modelToBusiness(U model);

    U businessToModel(T business);


}
