package com.profileAnalysisTables.ContributToDepartment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionToDepartmentRepo extends JpaRepository<ContributionToDepartmentTable,Long> {
	    List<ContributionToDepartmentTable> findByUserId(Long userId);
	    List<ContributionToDepartmentTable> findByUser_Usergmail(String email);
	    List<ContributionToDepartmentTable> findByUserIdOrderByCreatedAtAsc(Long userId);
	    
	    List<ContributionToDepartmentTable> findByUser_UsergmailOrderByCreatedAtAsc(String email);
}
