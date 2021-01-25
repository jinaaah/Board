package com.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class MapperTests {

	@Autowired
	private BoardMapper boardMapper;

	
/*	@Test
	public void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목");
		params.setContent("1번 게시글 내용");
		params.setWriter("테스터");

		int result = boardMapper.insertBoard(params);
		System.out.println("결과는 " + result + "입니다.");
	} */
	
	
/*	@Test
	public void testOfSelectDetail() {
		BoardDTO board = boardMapper.selectBoardDetail((long)1);
		
		try {
			String boardJson = new ObjectMapper().writeValueAsString(board);

			System.out.println("===========================");
			System.out.println(boardJson);
			System.out.println("===========================");
				
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}		
	} */
	
	
	/*
	@Test
	public void testOfUpdate() {
		
		BoardDTO params = new BoardDTO();
		params.setTitle("2번 게시글 제목(수정)");
		params.setContent("2번 게시글 내용(수정)");
		params.setWriter("테스터222");
		params.setIdx((long) 2);
		
		int result = boardMapper.updateBoard(params);
		if (result == 1) { //update 쿼리실행횟수
			BoardDTO board = boardMapper.selectBoardDetail((long)2);
		
			String boardJson;
			try {
				boardJson = new ObjectMapper().writeValueAsString(board);
			
				System.out.println("============================");
				System.out.println(boardJson);
				System.out.println("============================");
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}		
		}
	} */
	
	
	/*
	@Test
	public void testOfDelete() {

		int result = boardMapper.deleteBoard((long)1);
		
		if(result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long)1);
			
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				
				System.out.println("============================");
				System.out.println(boardJson);
				System.out.println("============================");
							
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
	} */
	
	
	/*
	@Test
	public void testOfMultiInsert() {

		for (int i = 1; i < 10; i++) {
			BoardDTO params = new BoardDTO();
			params.setTitle(i + "번 게시글 제목");
			params.setContent(i + "번 게시글 내용");
			params.setWriter(i + "번 게시글 작성자");
			boardMapper.insertBoard(params);
		}
	}
	*/
	
	@Test
	public void testOfSelectList() {
				
		//List<BoardDTO> selectBoardList
		//int selectBoardTotalCount
		
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		
		if(boardTotalCount > 0) {	
			List<BoardDTO> boardList = boardMapper.selectBoardList();
			
			if(CollectionUtils.isEmpty(boardList) == false) {
				for (BoardDTO board : boardList) {
					System.out.println("===============================");
					System.out.println(board);
					System.out.println(board.getTitle());
					System.out.println(board.getContent());
					System.out.println(board.getWriter());
					System.out.println("===============================");
				
				}
				
			}
			
		}
		
		
	
		
	}
	
	
	@Test
	public void testOfTotalCount() {
		
		//int selectBoardTotalCount
	}
	
}
