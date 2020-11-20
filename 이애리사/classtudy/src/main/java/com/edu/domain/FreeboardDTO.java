package com.edu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
public class FreeboardDTO {
	@Id
	private	int		boardNo;	//게시글번호
	private	String	writer;		//작성자
	private	String	title;		//글제목
	private String	content;	//글내용
	private	String	category;	//말머리
	private	int		views;		//조회수
	private	int		likes;		//좋아요
	private	Date	writeDate;	//작성일자
	private	String	tags;		//태그
	
	public FreeboardDTO() {}
	/*
	@ManyToOne(targetEntity = MemberDTO.class)
	private MemberDTO member;
	*/
}
