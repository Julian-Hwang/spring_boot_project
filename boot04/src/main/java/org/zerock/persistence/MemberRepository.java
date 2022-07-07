package org.zerock.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
