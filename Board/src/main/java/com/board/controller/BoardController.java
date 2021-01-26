package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardDTO;
import com.board.service.BoardService;

@Controller
public class BoardController {

	private BoardService boardService;
	
/*	@GetMapping(value = "board/write.do")
	public String openBoardWrite(Model model) {
		
		String title = "제목"; 
		String content = "내용"; 
		String writer = "홍길동";
		 
		model.addAttribute("t", title); 
		model.addAttribute("c", content);
		model.addAttribute("w", writer);
		
		return "board/write";
	}
	*/
	
	@GetMapping(value = "board/write.do")
	public String openBoardWrite(@RequestParam(value="idx", required=false) Long idx, Model model) {
	
		if(idx == null) { //새로 등록
			model.addAttribute("board", new BoardDTO());
		} else { //기존글 수정
			BoardDTO board = boardService.getBoard(idx);
			//if (board == null) {
			//	return "redirect:/board/list.do";
			//}
		}
		
		return "board/write";
	}
		
}
