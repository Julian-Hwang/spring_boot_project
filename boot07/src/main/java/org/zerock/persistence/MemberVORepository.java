package org.zerock.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.domain.MemberVO;

public interface MemberVORepository extends JpaRepository<MemberVO, Long> {

}
