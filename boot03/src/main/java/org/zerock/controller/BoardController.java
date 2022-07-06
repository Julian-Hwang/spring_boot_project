package org.zerock.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Board;
import org.zerock.persistence.BoardRepository;

@RestController
public class BoardController {

	@Autowired
	private BoardRepository boardRepo;
	
	@PostConstruct
	public void init() {
		for (int i = 1; i <= 200; i++) {
			Board board = new Board();
			board.setTitle("게시글 제목 "+i);
			board.setContent("게시글 내용 넣기 "+i);
			board.setWriter("user0"+(i%10));
			
			boardRepo.save(board);
		}
	}
	
	@GetMapping("/insert")
	public void insert() {
		boardRepo.findBoardByTitle("게시글 제목1").stream().forEach(System.out::println);
	}
}
