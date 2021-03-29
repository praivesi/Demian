package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.dto.DesireDTO;
import com.tutorial.ohDiaraySpringBoot.dto.DesireWithDecadeJobDTO;
import com.tutorial.ohDiaraySpringBoot.dto.JobDTO;
import com.tutorial.ohDiaraySpringBoot.model.DecadeJob;
import com.tutorial.ohDiaraySpringBoot.model.Desire;
import com.tutorial.ohDiaraySpringBoot.model.User;
import com.tutorial.ohDiaraySpringBoot.model.YearJob;
import com.tutorial.ohDiaraySpringBoot.repository.DesireRepository;
import com.tutorial.ohDiaraySpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesireService {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private UserRepository userRepository;

    public List<DesireWithDecadeJobDTO> GetAllDesires() {
        return null;
//        List<Desire> desires = desireRepository.findAll();
//        List<DesireWithDecadeJobDTO> response = new ArrayList<>();
//
//        for(Desire desire : desires){
//            response.add(DesireWithDecadeJobDTO.of(desire, desire.getDecadeJobs()));
//        }
//
//        return response;
    }

    public Desire savePrev(String username, Desire desire) {
        User user = userRepository.findByUsername(username);
        desire.setUser(user);

        // [210228] Dummy data for test
        desire.setSortNum(10l);
        desire.setDecadeJobs(new ArrayList<DecadeJob>());
        desire.setFromTime(Timestamp.valueOf("2007-09-23 10:10:10.0"));
        desire.setToTime(Timestamp.valueOf("2007-09-23 10:10:10.0"));

        return desireRepository.save(desire);
    }

    public DesireDTO save(DesireDTO dto) {
        Desire newDesire = new Desire(dto.getTitle(), dto.getContent(), 0l, dto.getFromTime(), dto.getToTime());

        // added temporary user
        User user = userRepository.findByUsername("hsoh");
        newDesire.setUser(user);

        Desire entity = desireRepository.save(newDesire);
        dto.setId(entity.getId());

        return dto;
    }

    public DesireDTO update(DesireDTO dto, Long id) {
        Optional<Desire> maybeEntity = desireRepository.findById(id);

        if (maybeEntity.isPresent()) {
            Desire entity = maybeEntity.get();

            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            entity.setSortNum(dto.getSortNum());
            entity.setFromTime(dto.getFromTime());
            entity.setToTime(dto.getToTime());

            desireRepository.save(entity);
            dto.setId(id);
        } else {
            dto = new DesireDTO();
        }

        return dto;
    }

    public DesireDTO get(Long id) {
        DesireDTO dto = new DesireDTO();

        Optional<Desire> maybeDesire = desireRepository.findById(id);

        if (maybeDesire.isPresent()) {
            Desire entity = maybeDesire.get();

            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dto.setSortNum(entity.getSortNum());
            dto.setFromTime(entity.getFromTime());
            dto.setToTime(entity.getToTime());
        }

        return dto;
    }

    public Long delete(Long id) {
        try {
            desireRepository.deleteById(id);
        } catch (Exception e) {
            id = -1l;
        }

        return id;
    }
}
