package com.tutorial.Demian.service;

import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
public class DesireServiceTest {
//    @Autowired
    DesireService desireService;

    @Mock
    UserRepository userRepository;

    @Mock
    DesireRepository desireRepository;

    @BeforeEach
    void setup(){
        this.desireService = new DesireService(desireRepository, userRepository);
    }

    @Test
    @DisplayName("Desire 저장하기")
    public void save_succeed() {
        // given
        User user = new User();
        user.setId(1l);
        user.setUsername("test");

        Desire savedDesire = new Desire();
        savedDesire.setId(1l);
        savedDesire.setTitle("Test title");
        savedDesire.setContent("Test content");

        DesireDTO dto = DesireDTO.of(savedDesire);

        Mockito.when(userRepository.findByUsername(any())).thenReturn(user);
        Mockito.when(desireRepository.save(any())).thenReturn(savedDesire);

        // when
        DesireDTO idAddedDTO = desireService.save(dto);

        // then
        Assertions.assertEquals(idAddedDTO.getTitle(), dto.getTitle());
        Assertions.assertEquals(idAddedDTO.getContent(), dto.getContent());
        Assertions.assertNotEquals(idAddedDTO.getId(), -1l);
    }

    @Test
    @DisplayName("DB에 Entity가 있을 때 Desire 업데이트하기")
    public void update_succeed_whenEntityExistsInDB() {
        // given
        String oldTitle = "already saved entity";
        Desire alreadySavedDesire = new Desire();
        alreadySavedDesire.setId(1l);
        alreadySavedDesire.setTitle(oldTitle);
        Optional<Desire> maybeEntity = Optional.of(alreadySavedDesire);
        Mockito.when(desireRepository.findById(any())).thenReturn(maybeEntity);

        // when
        DesireDTO updateDTO = DesireDTO.of(alreadySavedDesire);
        updateDTO.setTitle("updated entity");

        DesireDTO resDTO = desireService.update(updateDTO, updateDTO.getId());

        // then
        Assertions.assertEquals(resDTO.getId(), alreadySavedDesire.getId());
        Assertions.assertNotEquals(resDTO.getTitle(), oldTitle);
    }

    @Test
    @DisplayName("DB에 Entity가 없을 때 Desire 업데이트하기")
    public void update_succeed_whenEntityNOTExistsInDB() {
        // given
        Mockito.when(desireRepository.findById(1l))
                .thenReturn(Optional.ofNullable(null));

        // when
        DesireDTO updateDTO = new DesireDTO();
        updateDTO.setId(1l);
        updateDTO.setTitle("updated entity");

        DesireDTO resDTO = desireService.update(updateDTO, updateDTO.getId());

        // then
        Assertions.assertNull(resDTO.getId());
        Assertions.assertEquals(resDTO.getTitle(), "");
    }
}





















