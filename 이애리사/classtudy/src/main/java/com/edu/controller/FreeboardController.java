package com.edu.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.domain.ClassboardDTO;
import com.edu.domain.FreeboardDTO;
import com.edu.domain.MemberDTO;
import com.edu.service.FreeboardService;

@Controller
@RequestMapping("/community/*")
public class FreeboardController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FreeboardController.class);

	@Inject
	FreeboardService freeboardService;

	//게시글 목록
	@RequestMapping(value = "/freeboard" )
	public String freeboard(Model model,HttpSession session)throws Exception {
		LOGGER.info("FreeboardController Freeboard().....");
		//MemberDTO member = (MemberDTO)session.getAttribute("member");
		model.addAttribute("list", freeboardService.boardList());
		return "/freeboard/list";		
	}

	// 게시글 작성 GET
	@RequestMapping(value="/writer", method=RequestMethod.GET)
	public String getWriter(HttpSession session, RedirectAttributes rttr) throws Exception {
		LOGGER.info("FreeboardController writer GET....");
		// 로그인을 하지 않았으면 로그인 화면으로 보낸다.
		if (session.getAttribute("member") == null) {
			rttr.addFlashAttribute("msgLogin", false);
			return "redirect:/member/login";
		}
		return "/freeboard/writer";
	}	
	// 게시글 작성 POST
	@RequestMapping(value = "/writer", method = RequestMethod.POST)
	public String postWriter(FreeboardDTO boardDTO, HttpSession session, Model model )throws Exception {
		LOGGER.info("FreeboardController writer POST....." + boardDTO);		
		freeboardService.writer(boardDTO);
		model.addAttribute("게시글 등록이 완료되었습니다.");	
				
		return "redirect:/community/freeboard";		
	}	
	/*
	//게시글 목록
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String boardList( HttpSession session, Model model) throws Exception {
		LOGGER.info("FreeboardController boardList before.....");
		//게시글 목록 보여주기 화면으로 가기 전에 보여줄 데이터를 가져와서 model에 담는다.
		model.addAttribute("list", freeboardService.boardList());
		LOGGER.info("FreeboardController boardList after.....");
		return "/freeboard/list";		
	}
	*/
	
	// 게시판 상세보기
	@RequestMapping(value = "/detail/{boardNo}")
	public String boardDetail(@PathVariable int boardNo, Model model,HttpSession session) throws Exception{
		LOGGER.info("FreeboardController detailBoard()....");
		LOGGER.info("detailBoard() : " + freeboardService.boardDetail(boardNo));
		// boardNO에 해당하는 자료의 조회수를 1 증가 시킨다.
		freeboardService.addViews(boardNo);
		// boardNO에 해당하는 자료를 model에 담는다.
		model.addAttribute("detail", freeboardService.boardDetail(boardNo));
		return "/freeboard/detail";
	}
	
	// 게시글 수정 GET
	@RequestMapping(value="/update/{boardNo}", method=RequestMethod.GET)
	public String getUpdate(@PathVariable int boardNo, HttpSession session, RedirectAttributes rttr, Model model) throws Exception {
		LOGGER.info("FreeboardController getUpdate()....");
		//로그인을 하지 않았으면 로그인 화면으로 보낸다.
/*		if (session.getAttribute("member") == null) {
			rttr.addFlashAttribute("msgLogin", false);
			return "redirect:/member/login";
		} */
		LOGGER.info("BoardDTO : " + freeboardService.boardDetail(boardNo));
		model.addAttribute("detail", freeboardService.boardDetail(boardNo));
		return "/freeboard/update";
	}
		
	// 게시글 수정 POST
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String postUpdate(FreeboardDTO boardDTO, HttpSession session) throws Exception {
		LOGGER.info("FreeboardController postUpdate()....");
		freeboardService.update(boardDTO);
		return "redirect:/community/freeboard";	
	}
		
	// 게시글 삭제
	@RequestMapping(value="/delete/{boardNo}")
	public String delete(@PathVariable int boardNo) throws Exception {
		LOGGER.info("ClassboardController delete()....");
		freeboardService.delete(boardNo);
		return "redirect:/class/classroom";
	}
	
}
