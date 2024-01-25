package group.fortil.repository;

import group.fortil.model.TagModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TagRepository extends CrudRepository<TagModel, UUID> {

}
