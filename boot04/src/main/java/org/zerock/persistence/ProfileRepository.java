package org.zerock.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
