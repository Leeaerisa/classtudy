package com.edu.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edu.domain.FreeboardDTO;

@Repository("com.edu.mapper.FreeboardMapper")
public interface FreeboardMapper {

	
	//게시글 작성
	public int write(FreeboardDTO boardDTO) throws Exception;
		
	//게시글 상세보기
	public FreeboardDTO boardDetail(int boardNo) throws Exception;
	
	//게시글 수정
	public int boardUpdate(FreeboardDTO boarDTO) throws Exception;
	
	//게시글 삭제
	public int boardDelete(int boardNo) throws Exception;

	//게시글 조회수 증가
	public int addViews(int boardNo) throws Exception;
	
	//게시글 좋아요수 증가
	public int addLikes(int boardNo) throws Exception ;
	
	//게시글 목록
	public List<FreeboardDTO> boardList() throws Exception;
	
	//게시글 목록(정해진 말머리)
	public List<FreeboardDTO> boardList2(String viewCategory, int maxNo) throws Exception;
	
	//화면에 보여줄 개시글 개수 추출
	public int getBoardCount() throws Exception;
	
	//화면에 보여줄 개시글 개수 추출(정해진 말머리)
	public int getBoardCount2(String viewCategory) throws Exception;

	//마지막 게시글 번호 추출
	public int getNextNum() throws Exception;

	//마지막 게시글 번호 추출 (정해진 말머리만)
	public int getNextNum2(String viewCategory) throws Exception;

	//게시글 검색
	public List<FreeboardDTO> search(String keyword) throws Exception;

	//게시글 검색(정해진 말머리)
	public List<FreeboardDTO> search2(String keyword, String viewCategory) throws Exception;
	
	
	


}
