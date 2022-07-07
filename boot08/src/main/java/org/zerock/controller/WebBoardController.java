package org.zerock.controller;

import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.WebBoard;
import org.zerock.persistence.WebBoardRepository;
import org.zerock.vo.PageMaker;
import org.zerock.vo.PageVO;


@Controller
@RequestMapping("/boards")
public class WebBoardController {

	@Autowired
	private WebBoardRepository repo;
	
	@PostConstruct
	public void init() {
		IntStream.range(0, 300).forEach(i->{
			
			WebBoard board = new WebBoard();
			
			board.setTitle("Sample Board Title "+i);
			board.setContent("Content Sample ..."+i+" of Board");
			board.setWriter("user0"+(i%10));
			
			repo.save(board);
		});
	}
	
	@GetMapping("/list") //list.html 보여주기
	public String list(PageVO vo, Model model) {
		
		Pageable page = vo.makePageable(0, "bno");
		
		Page<WebBoard> result = repo.findAll(repo.makePredicate(vo.getType(), vo.getKeyword()), page);
		
		model.addAttribute("result", new PageMaker<>(result));
		return "boards/list";
	}
	
	@GetMapping("/register") //게시물 등록
	public String registerGET(@ModelAttribute("vo")WebBoard vo) {
		return "/boards/register";
	}
	
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo")WebBoard vo, RedirectAttributes rttr){
		repo.save(vo); //repository에 내용 저장
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/boards/list";
	}
	
	@GetMapping("/view") //게시글 내용 보기
	public String view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		repo.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
		return "boards/view";
	}
	
	@GetMapping("/modify")
	public String modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		repo.findById(bno).ifPresent(board->model.addAttribute("vo", board));
		return "boards/modify";
	}
	
	@PostMapping("/modify") // 게시글 내용 수정
	public String modifyPost(WebBoard board, PageVO vo, RedirectAttributes rttr) {
		repo.findById(board.getBno()).ifPresent(origin->{
			origin.setTitle(board.getTitle());
			origin.setContent(board.getContent());
			
			repo.save(origin);
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("bno", origin.getBno());
		});
		
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		
		return "redirect:/boards/view";
	}
	
	@PostMapping("/delete") //게시글 삭제
	public String delete(Long bno, PageVO vo, RedirectAttributes rttr) {
		repo.deleteById(bno);
		rttr.addFlashAttribute("msg", "success");
		
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		
		return "redirect:/boards/list";
	}
}