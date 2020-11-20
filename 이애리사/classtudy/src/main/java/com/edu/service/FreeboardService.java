package com.edu.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.controller.FreeboardController;
import com.edu.domain.ClassboardDTO;
import com.edu.domain.FreeboardDTO;
import com.edu.mapper.FreeboardMapper;

@Service("com.edu.service.FreeboardService")
public class FreeboardService {
	// 로깅을 위한 변수 LOGGER를 선언한다.
	private static final Logger LOGGER
		= LoggerFactory.getLogger(FreeboardController.class);
	
	@Resource(name = "com.edu.mapper.FreeboardMapper")
	FreeboardMapper freeboardMapper;	
	
	//게시글 작성
	public int writer(FreeboardDTO boardDTO) throws Exception{
		LOGGER.info("Service write(freeboard) : " + boardDTO);
		return freeboardMapper.writer(boardDTO);		
	}
	//게시글 목록
	public List<FreeboardDTO> boardList() throws Exception{
		return freeboardMapper.boardList();		
	}
	//게시글 상세보기
	public FreeboardDTO boardDetail(int boardNo) throws Exception {
		return freeboardMapper.boardDetail(boardNo);
	}
	//게시글 조회수 증가
	public int addViews(int boardNo) throws Exception{
		return freeboardMapper.addViews(boardNo);
	}
	//게시글 수정
	public int update(FreeboardDTO boardDTO) throws Exception {
		return freeboardMapper.update(boardDTO);		
	}
	public int delete(int boardNo) {
		return boardNo;		
	}
		
	//게시글 (삭제

}
