package com.board.service;

import java.util.List;

import com.board.domain.BoardDTO;

public interface BoardService {
	
	//등록, 조회, 삭제, 리스트조회
	public boolean registerBoard(BoardDTO params);
	
	public BoardDTO getBoard(long idx);
	
	public boolean deleteBoard(long idx);
	
	public List<BoardDTO> getBoardList();
	

}
