package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.board.service.BoardService;

@Controller
public class BoardController {

	private BoardService boardService;
	
	@GetMapping(value = "board/write.do")
	public String openBoardWrite(Model model) {
		
		String title = "제목";
		String content = "내용";
		String writer = "홍길동";
		
		model.addAttribute("t", title);
		model.addAttribute("c", content);
		model.addAttribute("w", writer);
		
		return "board/write";
	}
}
