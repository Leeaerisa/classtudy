package com.edu.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edu.domain.FreeboardDTO;

@Repository("com.edu.mapper.FreeboardMapper")
public interface FreeboardMapper {

	
	//게시글 작성
	public int writer(FreeboardDTO boardDTO) throws Exception;
	
	//게시글 목록
	public List<FreeboardDTO> boardList() throws Exception;
	
	//게시글 상세보기
	public FreeboardDTO boardDetail(int boardNo) throws Exception;
	
	//게시글 조회수 증가
	public int addViews(int boardNo) throws Exception;

	//게시글 수정
	public int update(FreeboardDTO boardDTO) throws Exception;	
	
	//게시글 삭제

}
