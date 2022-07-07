package org.zerock.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.WebBoard;
import org.zerock.domain.WebReply;
import org.zerock.persistence.WebReplyRepository;

@RestController
@RequestMapping("/replies/")
public class WebReplyController {

	@Autowired
	WebReplyRepository repo;
	
	@PostConstruct
	public void init() {
		Long arr[] = {304L, 303L, 300L};
		
		Arrays.stream(arr).forEach(num->{
			
			WebBoard board = new WebBoard();
			board.setBno(num);
			
			IntStream.range(0, 10).forEach(i->{
				
				WebReply reply = new WebReply();
				reply.setReplyText("REPLY ..."+i);
				reply.setReplyer("replyer"+i);
				reply.setBoard(board);
				
				repo.save(reply);
			});
		});
	}
	
	@Transactional
	@PostMapping("/{bno}")
	public ResponseEntity<List<WebReply>> addReply(@PathVariable("bno")Long bno, @RequestBody WebReply reply){
		
		WebBoard board = new WebBoard();
		board.setBno(bno);
		
		reply.setBoard(board);
		
		repo.save(reply);
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
		
		
	}
	
	private List<WebReply> getListByBoard(WebBoard board)throws RuntimeException {
		return repo.getRepliesOfBoard(board);
	}
	
	@Transactional
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<List<WebReply>> remove(@PathVariable("bno")Long bno, @PathVariable("rno")Long rno){
		
		repo.deleteById(rno);
		
		WebBoard board = new WebBoard();
		board.setBno(bno);
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
	}
    
	@Transactional
	@PutMapping("/{bno}")
	public ResponseEntity<List<WebReply>> modify(@PathVariable("bno")Long bno, @RequestBody WebReply reply) {
		
		repo.findById(reply.getRno()).ifPresent(origin -> {
			origin.setReplyText(reply.getReplyText());
			repo.save(origin);
		});
		
		WebBoard board = new WebBoard();
		board.setBno(bno);
		
		return new ResponseEntity<List<WebReply>>(getListByBoard(board), HttpStatus.CREATED);
	}
	
}