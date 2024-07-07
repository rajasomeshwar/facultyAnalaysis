package com.profileAnalysisTables.contributionToSociety;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface contributionToSocietyRepo extends JpaRepository<ContributionToSocietyTable,Long> {
	    List<ContributionToSocietyTable> findByUserId(Long userId);
	    List<ContributionToSocietyTable> findByUser_Usergmail(String email);
	    List<ContributionToSocietyTable> findByUserIdOrderByCreatedAtAsc(Long userId);
	    
	    List<ContributionToSocietyTable> findByUser_UsergmailOrderByCreatedAtAsc(String email);
}
