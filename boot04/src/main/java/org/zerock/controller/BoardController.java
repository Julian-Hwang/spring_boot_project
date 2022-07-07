package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Member;
import org.zerock.domain.PDSBoard;
import org.zerock.domain.PDSFile;
import org.zerock.domain.Profile;
import org.zerock.persistence.MemberRepository;
import org.zerock.persistence.PDSBoardRepository;
import org.zerock.persistence.ProfileRepository;

@RestController
public class BoardController {
	
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	ProfileRepository profileRepo;
	
	@Autowired
	PDSBoardRepository pdsBoardRepo;
	
	@Transactional
	@PostConstruct
	public void init() {
		IntStream.range(1, 101).forEach(i->{
			Member member = new Member();
			member.setUid(Long.valueOf(i));
			member.setUpw("pw"+i);
			member.setUname("사용자"+i);
			
			memberRepo.save(member);
		});
		
		Member member = new Member();
		member.setUid(Long.valueOf(1L));
		for (int i = 1; i < 5; i++) {
			Profile profile1 = new Profile();
			profile1.setFname("face"+i+".jpg");
			
			if (i==1) {
				profile1.setCurrent(true);
			}
			profile1.setMember(member);
			profileRepo.save(profile1);
		}
		
		PDSBoard pdsBoard = new PDSBoard();
		pdsBoard.setPname("Document");
		
		PDSFile file1 = new PDSFile();
		file1.setPdsfile("file1.doc");
		
		PDSFile file2 = new PDSFile();
		file2.setPdsfile("file2.doc");
		
		pdsBoard.setFiles(Arrays.asList(file1,file2));
		pdsBoardRepo.save(pdsBoard);
		
		List<PDSBoard> list = new ArrayList<>();
		IntStream.range(1, 100).forEach(i->{
			PDSBoard pds = new PDSBoard();
			pds.setPname("자료"+i);
			
			PDSFile file_1 = new PDSFile();
			file_1.setPdsfile("file_1.doc");
			
			PDSFile file_2 = new PDSFile();
			file_2.setPdsfile("file_2.doc");
			
			pds.setFiles(Arrays.asList(file_1,file_2));
			list.add(pds);
		});
		pdsBoardRepo.saveAll(list);
	}

}
