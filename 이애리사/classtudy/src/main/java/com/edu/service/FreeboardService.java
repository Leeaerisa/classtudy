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

import net.bytebuddy.build.Plugin.Engine.Dispatcher.Materializable.ForRetainedElement;

@Service("com.edu.service.FreeboardService")
public class FreeboardService {
	// 로깅을 위한 변수 LOGGER를 선언한다.
	private static final Logger LOGGER
		= LoggerFactory.getLogger(FreeboardController.class);
	
	@Resource(name = "com.edu.mapper.FreeboardMapper")
	FreeboardMapper freeboardMapper;	
	
	//게시글 작성
	public int write(FreeboardDTO boardDTO) throws Exception{
		LOGGER.info("Service write(freeboard) : " + boardDTO);
		return freeboardMapper.write(boardDTO);		
	}
	//게시글 상세보기
	public FreeboardDTO boardDetail(int boardNo) throws Exception {
		return freeboardMapper.boardDetail(boardNo);
	}
	//게시글 수정
	public int boardUpdate(FreeboardDTO boardDTO) throws Exception {
		return freeboardMapper.boardUpdate(boardDTO);		
	}
	//게시글 삭제
	public int boardDelete(int boardNo) throws Exception {
		return freeboardMapper.boardDelete(boardNo);	
	}
	//게시글 조회수 증가
	public int addViews(int boardNo) throws Exception{
		return freeboardMapper.addViews(boardNo);
	}
	// 게시글 좋아요수 증가
	public int addLikes(int boardNo) throws Exception  {
		return freeboardMapper.addLikes(boardNo);
	}
	//게시글 목록
	public List<FreeboardDTO> boardList() throws Exception{
		return freeboardMapper.boardList();		
	}
	// 게시판 목록 보기 - 정해진 말머리만
	public List<FreeboardDTO> boardList2( String viewCategory, int maxNo) throws Exception {
		return freeboardMapper.boardList2(viewCategory, maxNo);
	}
	
	// 화면에 보여줄 게시글 개수 추출
	public int getBoardCount(String viewCategory) throws Exception {
		return freeboardMapper.getBoardCount();
	}
	// 화면에 보여줄 게시글 개수 추출 - 정해진 말머리만
	public int getBoardCount2(String viewCategory) throws Exception {
		return freeboardMapper.getBoardCount2(viewCategory);
	}
	//마지막 게시글 번호 추출
	public int getNextNum() throws Exception {
		return freeboardMapper.getNextNum() + 1;
	}
	//마지막 게시글 번호 추출 - 정해진 말머리만
	public int getNextNum2(String viewCategory) throws Exception {
		return freeboardMapper.getNextNum2(viewCategory) + 1;
	}
	//게시글 검색
	public List<FreeboardDTO> search(String keyword) throws Exception {		
		return freeboardMapper.search(keyword);
	}
	//게시글 검색(정해진 말머리)
	public List<FreeboardDTO> search2(String keyword, String viewCategory) throws Exception {
		return freeboardMapper.search2(keyword, viewCategory);
	}

		
		
}
