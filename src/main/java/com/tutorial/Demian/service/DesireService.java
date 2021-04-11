package com.tutorial.Demian.service;

import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.dto.DesireWithDecadeJobDTO;
import com.tutorial.Demian.model.DecadeJob;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesireService {
//    @Autowired
    private DesireRepository desireRepository;
//    @Autowired
    private UserRepository userRepository;

    public DesireService(
            DesireRepository desireRepository,
            UserRepository userRepository){
        this.desireRepository = desireRepository;
        this.userRepository = userRepository;
    }

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
        desire.setDecades(new ArrayList<DecadeJob>());

        return desireRepository.save(desire);
    }

    public DesireDTO save(DesireDTO dto) {
        Desire newDesire = new Desire(dto.getTitle(), dto.getContent(), 0l);

        // added temporary user
        System.out.println("In Real : " + userRepository.hashCode());
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
