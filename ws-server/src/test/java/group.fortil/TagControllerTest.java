package group.fortil;

import group.fortil.controller.TagControllerImpl;
import group.fortil.dto.TagDtoImpl;
import group.fortil.service.TagServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TagControllerTest {

    @InjectMocks
    private TagControllerImpl controller;

    @Mock
    private TagServiceImpl<TagDtoImpl> tagService;
}
