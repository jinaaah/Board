package com.board.service;

import java.util.List;

import com.board.domain.BoardDTO;

public interface BoardService {
	
	//등록
	public boolean registerBoard(BoardDTO params);
	
	//상세조회
	public BoardDTO getBoardDetail(long idx);
	
	//삭제
	public boolean deleteBoard(long idx);
	
	//리스트 조회
	public List<BoardDTO> getBoardList();
	

}
