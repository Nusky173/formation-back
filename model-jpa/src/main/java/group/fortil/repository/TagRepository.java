package group.fortil.repository;

import group.fortil.IRepository;
import group.fortil.model.TagModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends IRepository<TagModel, Long> {

    List<TagModel> findAllTagsForAMessage();
}
