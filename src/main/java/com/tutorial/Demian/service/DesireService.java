package com.tutorial.Demian.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.repository.DesireRepository;

@Service
public class DesireService {
    private DesireRepository desireRepository;

    public DesireService(
            DesireRepository desireRepository) {
        this.desireRepository = desireRepository;
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
        }

        return dto;
    }

    public List<Desire> getCurrentUserDesires(Long userId) {
        return desireRepository.findByUserId(userId);
    }

    public DesireDTO update(DesireDTO dto, Long id) {
        Optional<Desire> maybeEntity = desireRepository.findById(id);

        if (maybeEntity.isPresent()) {
            Desire entity = maybeEntity.get();

            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            entity.setSortNum(dto.getSortNum());

            desireRepository.save(entity);
            dto.setId(id);
        } else {
            dto = new DesireDTO();
        }

        return dto;
    }

    public DesireDTO save(DesireDTO dto) {
        Desire entity = desireRepository.save(dto.getEntity());
        dto.setId(entity.getId());

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