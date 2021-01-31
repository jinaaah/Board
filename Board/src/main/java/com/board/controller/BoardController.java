package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.constant.Method;
import com.board.domain.BoardDTO;
import com.board.service.BoardService;
import com.board.util.UiUtils;

@Controller
public class BoardController extends UiUtils{

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
	public String registerBoard(final BoardDTO params, Model model) {
	
		try {
			boolean isRegisterd = boardService.registerBoard(params);
			if(isRegisterd == false) {
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 생겼습니다.", "/board/list.do", Method.GET, null, model);

		} catch (Exception e) { 
			return showMessageWithRedirect("시스템 문제가 발생했습니다.", "/board/list.do", Method.GET, null, model);

		}
		
		//return "redirect:/board/list.do";
		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/board/list.do", Method.GET, null, model);
		
	}
	

	@GetMapping(value = "/board/list.do")
	public String openBoardList(Model model) {
		
		List<BoardDTO> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
				
		return "board/list";
		
	}
	
	@GetMapping(value = "/board/view.do")
	public String boardDetail(@RequestParam(value = "idx", required = false) Long idx, Model model) {
		
		if(idx == null) {
			return "redirect:/board/list.do";
		}
		
		BoardDTO board = boardService.getBoardDetail(idx);
		if(board == null || "Y".equals(board.getDeleteYn())) {
			return  "redirect:/board/list.do";
		}
		model.addAttribute("board", board);
		
		return "board/view";
	}
	
	
	@PostMapping(value = "/board/delete.do")
	public String boardDelete(@RequestParam(value = "idx", required = false) Long idx, Model model) {
		
		//System.out.println("/board/delete.do : idx = "+idx);
		
		if(idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);
			//return "redirect:/board/list.do";
		}
		
		try {
			boolean deleted = boardService.deleteBoard(idx);
			if (deleted == false) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/board/list.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 생겼습니다.", "/board/list.do", Method.GET, null, model);

		} catch (Exception e) { 
			return showMessageWithRedirect("시스템 문제가 발생했습니다.", "/board/list.do", Method.GET, null, model);

		}
		//model.addAttribute("board", board);
		
		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/board/list.do", Method.GET, null, model);
		//return "redirect:/board/list.do";
	}



		
}
