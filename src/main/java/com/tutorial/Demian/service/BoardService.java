package com.tutorial.Demian.service;

import com.tutorial.Demian.model.Board;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.repository.BoardRepository;
import com.tutorial.Demian.repository.UserRepository;
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
