package com.tutorial.Demian.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.model.DesireGrowth;
import com.tutorial.Demian.repository.DesireRepository;

@Service
public class DesireService {
    private DesireRepository desireRepository;

    public DesireService(
            DesireRepository desireRepository) {
        this.desireRepository = desireRepository;
    }

    public DesireDTO get(Long id) {
        Optional<DesireGrowth> maybeDesire = desireRepository.findById(id);

        return maybeDesire.isPresent() ? new DesireDTO(maybeDesire.get()) : new DesireDTO();
    }

    public Optional<DesireGrowth> getEntity(long desireId) {
        return desireRepository.findById(desireId);
    }

    public List<DesireGrowth> getCurrentUserDesires(Long userId) {
        return desireRepository.findByUserId(userId);
    }

    public DesireDTO update(DesireDTO dto, Long id) {
        Optional<DesireGrowth> maybeEntity = desireRepository.findById(id);

        if (!maybeEntity.isPresent()) {
            return new DesireDTO();
        }

        DesireGrowth updateEntity = dto.getEntity();
        updateEntity.setId(id);

        desireRepository.save(updateEntity);

        return DesireDTO.of(updateEntity);
    }

    public DesireDTO save(DesireDTO dto) {
        DesireGrowth entity = desireRepository.save(dto.getEntity());
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