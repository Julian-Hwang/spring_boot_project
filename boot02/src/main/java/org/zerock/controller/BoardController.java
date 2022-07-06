package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Board;
import org.zerock.persistence.BoardRepository;

@RestController
public class BoardController {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/insert")
	public void insert() {
		Board board = new Board();
		board.setTitle("게시글 제목");
		board.setContent("게시글 내용 넣기");
		board.setWriter("user01");
		
		boardRepo.save(board);
	}

}
