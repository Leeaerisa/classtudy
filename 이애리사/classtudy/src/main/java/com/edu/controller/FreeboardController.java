package com.edu.controller;

import java.util.List;
import java.util.Optional;

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

import com.edu.domain.FreeboardDTO;
import com.edu.domain.MemberDTO;
import com.edu.service.FreeboardService;

@Controller
@RequestMapping("/community/")
public class FreeboardController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FreeboardController.class);

	@Inject
	FreeboardService freeboardService;

	// 게시글 작성 GET
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String getWrite(HttpSession session, RedirectAttributes rttr) throws Exception {
		LOGGER.info("FreeboardController write GET....");
		// 로그인을 하지 않았으면 로그인 화면으로 보낸다.
		if (session.getAttribute("member") == null) {
			rttr.addFlashAttribute("msgLogin", false);
			return "redirect:/member/login";
		}
		return "/freeboard/write";
	}	
	// 게시글 작성 POST
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(FreeboardDTO boardDTO, HttpSession session, Model model )throws Exception {
		LOGGER.info("FreeboardController write POST....." + boardDTO);		
		freeboardService.write(boardDTO);
		model.addAttribute("게시글 등록이 완료되었습니다.");	
				
		return "redirect:/community/freeboard";		
	}	
	//게시글 목록	
	@RequestMapping(value = "/freeboard" )
	public String freeboard(Model model,HttpSession session)throws Exception {
		LOGGER.info("FreeboardController Freeboard().....");
		//MemberDTO member = (MemberDTO)session.getAttribute("member");
		model.addAttribute("list", freeboardService.boardList());
		
		
		return "/freeboard/list";		
	}
		
	// 게시판 상세보기
	@RequestMapping(value = "/detail/{boardNo}")
	public String boardDetail(@PathVariable int boardNo, Model model,HttpSession session,RedirectAttributes rttr) throws Exception{
		LOGGER.info("FreeboardController detailBoard()....");
		LOGGER.info("detailBoard() : " + freeboardService.boardDetail(boardNo));
		if (session.getAttribute("member") == null) {
			rttr.addFlashAttribute("msgLogin", false);
			return "redirect:/member/login";
		}
		// boardNO에 해당하는 자료의 조회수를 1 증가 시킨다.
		freeboardService.addViews(boardNo);
		// boardNO에 해당하는 자료를 model에 담는다.
		model.addAttribute("detail", freeboardService.boardDetail(boardNo));
		return "/freeboard/detail";
	}
	
	// 게시글 수정 GET
	@RequestMapping(value="/update/{boardNo}", method = RequestMethod.GET)
	public String getUpdate1(@PathVariable int boardNo, HttpSession session, RedirectAttributes rttr, Model model) throws Exception {
		LOGGER.info("FreeboardController getUpdate()....");
		// 로그인을 하지 않았으면 로그인 화면으로 보낸다.
		if (session.getAttribute("member") == null) {
			rttr.addFlashAttribute("msgLogin", false);
			return "redirect:/member/login";
		}
		LOGGER.info("BoardDTO : " + freeboardService.boardDetail(boardNo));
		model.addAttribute("detail", freeboardService.boardDetail(boardNo));
		return "/freeboard/update";
	}
	// 게시글 수정 POST
	@RequestMapping(value="/update/{boardNo}", method=RequestMethod.POST)
	public String postUpdate(FreeboardDTO boardDTO, HttpSession session) throws Exception {
		LOGGER.info("FreeboardController postUpdate()....");
		LOGGER.info("postUpdate() : " + boardDTO);
		freeboardService.boardUpdate(boardDTO);
		return "redirect:/community/detail/" + boardDTO.getBoardNo();
	}
		
	// 게시글 삭제
	@RequestMapping(value="/delete/{boardNo}")
	public String delete(@PathVariable int boardNo) throws Exception {
		LOGGER.info("FreeboardController delete()....");
		freeboardService.boardDelete(boardNo);
		return "redirect:/community/freeboard";
	}
	// 게시글 좋아요
	@RequestMapping(value="/like/{boardNo}", method=RequestMethod.POST)
	public int like(@PathVariable int boardNo) throws Exception {
		LOGGER.info("FreeboardController like()....");
		// 게시글 좋아요를 하기 위해 게시글 번호를 Service에게 넘겨준다.
		int result = freeboardService.addLikes(boardNo);
		return result;
	}
	
	// 게시글 검색
	@RequestMapping(value = "/search/{keyword}")
	public String search(@PathVariable String keyword, HttpSession session, Model model) throws Exception {
		LOGGER.info("FreeboardController search()....");
		LOGGER.info("FreeboardController search() : " + keyword);
		//List<FreeboardDTO> boardList = freeboardService.search("%" + keyword + "%"); 
		// 검색한 키워드를 뷰 페이지로 보내준다.
		model.addAttribute("nowKeyword", keyword);
		model.addAttribute("list", freeboardService.search("%" + keyword + "%"));	
		
		
		return "/freeboard/list";
		
	}

}
