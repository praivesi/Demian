package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.dto.DesireDTO;
import com.tutorial.ohDiaraySpringBoot.model.Desire;
import com.tutorial.ohDiaraySpringBoot.model.User;
import com.tutorial.ohDiaraySpringBoot.repository.DesireRepository;
import com.tutorial.ohDiaraySpringBoot.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

// TODO: Need inject necessary mock objects into individual methoods
// TODO: Fix update_succeed_whenEntityNOTExistsInDB()

@SpringBootTest
@Transactional
public class DesireServiceTest {
    @Autowired
    DesireService desireService;

    @MockBean
    UserRepository userRepository;

    @Mock
    DesireRepository desireRepository;

    @Test
    @DisplayName("Desire 저장하기")
    public void save_succeed() {
        // given
        User user = new User();
        user.setId(1l);
        user.setUsername("test");

        DesireDTO dto = new DesireDTO();
        dto.setTitle("Test title");
        dto.setContent("Test content");

        Mockito.when(userRepository.findByUsername(any())).thenReturn(user);

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
        Desire alreadySavedDesire = new Desire();
        alreadySavedDesire.setId(1l);
        alreadySavedDesire.setTitle("already saved entity");
        Optional<Desire> maybeEntity = Optional.of(alreadySavedDesire);
        Mockito.when(desireRepository.findById(any())).thenReturn(maybeEntity);

        // when
        DesireDTO updateDTO = DesireDTO.of(alreadySavedDesire);
        updateDTO.setTitle("updated entity");

        DesireDTO resDTO = desireService.update(updateDTO, updateDTO.getId());

        // then
        Assertions.assertEquals(resDTO.getId(), alreadySavedDesire.getId());
        Assertions.assertNotEquals(resDTO.getTitle(), alreadySavedDesire.getTitle());
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





















