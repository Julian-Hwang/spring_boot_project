package org.zerock.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.domain.PDSBoard;

public interface PDSBoardRepository extends JpaRepository<PDSBoard, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE FROM PDSFile f set f.pdsfile = ?2 WHERE f.fno = ?1 ")
	public int updatePDSFile(Long fno, String newFileName);
	
	//@Query("DELETE FROM PDSFile f WHERE f.fno = ?1")
	//public int deletePDSFile(Long fno);
}
