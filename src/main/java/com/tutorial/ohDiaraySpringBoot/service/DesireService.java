package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.dto.DesireDTO;
import com.tutorial.ohDiaraySpringBoot.model.DecadeJob;
import com.tutorial.ohDiaraySpringBoot.model.Desire;
import com.tutorial.ohDiaraySpringBoot.model.User;
import com.tutorial.ohDiaraySpringBoot.repository.DesireRepository;
import com.tutorial.ohDiaraySpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class DesireService {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private UserRepository userRepository;

    public Desire savePrev(String username, Desire desire)
    {
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
        desireRepository.save(newDesire);

        return dto;
    }

    public DesireDTO get(Long id) {
        Desire desire = desireRepository.findById(id).get();
        DesireDTO dto = new DesireDTO();

        dto.setId(desire.getId());
        dto.setTitle(desire.getTitle());
        dto.setContent(desire.getContent());
        dto.setSortNum(desire.getSortNum());
        dto.setFromTime(desire.getFromTime());
        dto.setToTime(desire.getToTime());

        return dto;
    }

    public void delete(Long id) {
        desireRepository.deleteById(id);
    }
}
