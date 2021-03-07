package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.model.DecadeJob;
import com.tutorial.ohDiaraySpringBoot.model.User;
import com.tutorial.ohDiaraySpringBoot.repository.DecadeJobRepository;
import com.tutorial.ohDiaraySpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecadeJobService {

    @Autowired
    private DecadeJobRepository decadeJobRepository;

    @Autowired
    private UserRepository userRepository;

    public DecadeJob save(String username, DecadeJob decadeJob)
    {
        User user = userRepository.findByUsername(username);

        return decadeJobRepository.save(decadeJob);
    }
}
