package com.profileAnalysisTables.contributionToSchool;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface contributionToSchoolRepo extends JpaRepository<ContributionToSchoolTable,Long> {
	    List<ContributionToSchoolTable> findByUserId(Long userId);
	    List<ContributionToSchoolTable> findByUser_Usergmail(String email);
	    List<ContributionToSchoolTable> findByUserIdOrderByCreatedAtAsc(Long userId);
	    
	    List<ContributionToSchoolTable> findByUser_UsergmailOrderByCreatedAtAsc(String email);
}
