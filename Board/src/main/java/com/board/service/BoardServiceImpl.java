package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean registerBoard(BoardDTO params) {
		
		int queryResult = 0;

		if (params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		} else {
			queryResult = boardMapper.updateBoard(params);
		}

		return (queryResult == 1) ? true : false;
		
	}

	@Override
	public BoardDTO getBoard(long idx) {
		
		return boardMapper.selectBoardDetail(idx);
	}

	@Override
	public boolean deleteBoard(long idx) {
		
		
		return false;
	}

	@Override
	public List<BoardDTO> getBoardList() {
		
		
		
		return null;
	}
	

}
