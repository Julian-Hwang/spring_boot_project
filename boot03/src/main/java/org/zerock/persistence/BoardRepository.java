package org.zerock.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	public List<Board> findBoardByTitle(String title); 
	
}