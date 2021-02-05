package com.tutorial.ohDiaraySpringBoot.controller;

import com.tutorial.ohDiaraySpringBoot.model.Board;
import com.tutorial.ohDiaraySpringBoot.repository.BoardRepository;
import com.tutorial.ohDiaraySpringBoot.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model,@PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
        //Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);

        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.max(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);

        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){
        if(id == null)
        {
            model.addAttribute("board", new Board());
        }
        else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String postForm(@Valid Board board, BindingResult bindingResult, Authentication authentication){
        boardValidator.validate(board, bindingResult);

       if(bindingResult.hasErrors()) {
           return "board/form";
       }

       String username = authentication.getName();

        // [210205 hsoh] Tutorial Link https://www.youtube.com/watch?v=wM7P-6_CHFM&list=PLPtc9qD1979DG675XufGs0-gBeb2mrona&index=10
        // 10:20 까지 봤음

       boardRepository.save(board);

       return "redirect:/board/list";
    }

}















