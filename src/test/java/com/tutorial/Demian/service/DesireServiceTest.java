package com.tutorial.Demian.service;

import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class DesireServiceTest {

    private DesireService desireService;

    @Autowired private DesireRepository desireRepository;

    @BeforeEach
    void setup(){
        this.desireService = new DesireService(this.desireRepository);
    }

    @Test
    void save_succeed(){
        // given
        DesireDTO desireDTO = new DesireDTO();
        desireDTO.setTitle("test title");

        // when
        DesireDTO result = this.desireService.save(desireDTO);

        // then
        Assertions.assertThat(result.getId()).isNotEqualTo(-1L);
    }
}

