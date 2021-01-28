package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardDTO;
import com.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
/*	@GetMapping(value = "/board/write.do")
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
	
	@GetMapping(value = "/board/write.do")
	public String openBoardWrite(@RequestParam(value="idx", required=false) Long idx, Model model) {
	
		if(idx == null) { //새로 등록
			model.addAttribute("board", new BoardDTO());
		} else { //기존글 수정
			BoardDTO board = boardService.getBoardDetail(idx);
			if (board == null) {
				return "redirect:/board/list.do";
			}
			model.addAttribute("board", board);
		}		
		return "board/write";
	}
	
	
	@PostMapping(value = "/board/register.do")
	public String registerBoard(final BoardDTO params) {
	
		try {
			boolean isRegisterd = boardService.registerBoard(params);
			if(isRegisterd == false) {
				//등록 실패
			}
		} catch (DataAccessException e) {
			// 데이터베이서 처리 문제
		} catch (Exception e) { 
			// 시스템 문제
		}
		
		return "redirect:/board/list.do";
		
	}
	
	@GetMapping(value = "/board/list.do")
	public String openBoardList(Model model) {
		
		List<BoardDTO> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
				
		return "board/list";
		
	}



		
}
