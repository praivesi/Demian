package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.model.Board;
import com.tutorial.ohDiaraySpringBoot.model.User;
import com.tutorial.ohDiaraySpringBoot.repository.BoardRepository;
import com.tutorial.ohDiaraySpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(String username, Board board)
    {
        User user = userRepository.findByUsername(username);
        board.setUser(user);

        return boardRepository.save(board);
    }
}
