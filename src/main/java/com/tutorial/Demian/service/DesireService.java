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
        Optional<Desire> maybeDesire = desireRepository.findById(id);

        return maybeDesire.isPresent() ? new DesireDTO(maybeDesire.get()) : new DesireDTO();
    }

    public Optional<Desire> getEntity(long desireId) {
        return desireRepository.findById(desireId);
    }

    public List<Desire> getCurrentUserDesires(Long userId) {
        return desireRepository.findByUserId(userId);
    }

    public DesireDTO update(DesireDTO dto, Long id) {
        Optional<Desire> maybeEntity = desireRepository.findById(id);

        if (!maybeEntity.isPresent()) {
            return new DesireDTO();
        }

        Desire updateEntity = dto.getEntity();
        updateEntity.setId(id);

        desireRepository.save(updateEntity);

        return DesireDTO.of(updateEntity);
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