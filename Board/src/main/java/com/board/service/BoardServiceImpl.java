package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean registerBoard(BoardDTO params) { //등록,수정
		
		int queryResult = 0;

		if (params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		} else {
			queryResult = boardMapper.updateBoard(params);
		}

		return (queryResult == 1) ? true : false;
		
	}

	@Override
	public BoardDTO getBoardDetail(long idx) { //상세화면 조회
		
		
		return boardMapper.selectBoardDetail(idx);
	}

	@Override
	public boolean deleteBoard(long idx) { //삭제
		
		int deleteResult = 0;
		
		BoardDTO board = boardMapper.selectBoardDetail(idx);

		//조회된 글이 null이 아니고, 삭제된 상태가 아닐 때 실행
		if(board != null && "N".equals(board.getDeleteYn())) {

			deleteResult = boardMapper.deleteBoard(idx);
		}
		
		return (deleteResult == 1) ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList() { //리스트 조회
		
		List<BoardDTO> boardList = Collections.emptyList();
		
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		
		if(boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList();
		}
		
		return boardList;
	}
	

}
