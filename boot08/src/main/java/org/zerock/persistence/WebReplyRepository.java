package org.zerock.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.domain.WebBoard;
import org.zerock.domain.WebReply;

public interface WebReplyRepository extends JpaRepository<WebReply, Long>{

	@Query("SELECT r FROM WebReply r WHERE r.board = ?1 "+" AND r.rno > 0 ORDER BY r.rno ASC")
	public List<WebReply> getRepliesOfBoard(WebBoard webBoard);
}