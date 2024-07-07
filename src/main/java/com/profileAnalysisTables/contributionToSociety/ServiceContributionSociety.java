package com.profileAnalysisTables.contributionToSociety;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.spring.spring_security_learn.repository.UserRepository;

import jakarta.transaction.Transactional;

import com.profileAnalysisTables.exception.UnauthorizedActionException;
import com.spring.spring_security_learn.model.ApplicationUser;;

@Service
@Transactional
public class ServiceContributionSociety {
	 @Autowired
	    private contributionToSocietyRepo repository;

	    @Autowired
	    private UserRepository userRepository;

	    public List<ContributionToSocietyTable> getAllContributions() {
	        return repository.findAll();
	    }

	    public ContributionToSocietyTable getContributionById(long indx) {
	        return repository.findById(indx).orElse(null);
	    }
	    public List<ContributionToSocietyTable> getContributionByUsergmail(String email) {
	        return repository.findByUser_Usergmail(email);
	    }
	    public List<ContributionToSocietyTable> updateContributions(List<ContributionToSocietyTable> contributions, String email) {
	        List<ContributionToSocietyTable> updatedContributions = new ArrayList<>();

	        for (ContributionToSocietyTable contribution : contributions) {
	            Optional<ContributionToSocietyTable> existingContributionOpt = repository.findById(contribution.getIndx());

	            if (existingContributionOpt.isPresent()) {
	            	ContributionToSocietyTable existingContribution = existingContributionOpt.get();

	                if (!existingContribution.getUser().getUsergmail().equals(email)) {
	                    throw new RuntimeException("Unauthorized to update contribution with indx: " + contribution.getIndx());
	                }

	                // Update existing contribution
	                existingContribution.setResponsibility(contribution.getResponsibility());
	                existingContribution.setContribution(contribution.getContribution());
	                existingContribution.setScore(contribution.getScore());
	                updatedContributions.add(repository.save(existingContribution));
	            } else {
	                // Save new contribution
	                ApplicationUser user = userRepository.findByUsergmail(email)
	                        .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
	                contribution.setUser(user);
	                updatedContributions.add(repository.save(contribution));
	            }
	        }

	        return updatedContributions;
	    }
	    public List<ContributionToSocietyTable> getContributionsByUserId(Long userId) {
	        return repository.findByUserIdOrderByCreatedAtAsc(userId);
	    }

	    public List<ContributionToSocietyTable> getContributionsByUserEmail(String email) {
	        return repository.findByUser_UsergmailOrderByCreatedAtAsc(email);
	    }

	    public ContributionToSocietyTable saveContribution(ContributionToSocietyTable contribution) {
	        return repository.save(contribution);
	    }

	    public void deleteContribution(long indx) {
	        repository.deleteById(indx);
	    }

	    public ContributionToSocietyTable createContribution(Long userId, ContributionToSocietyTable contribution) {
	    
	        Optional<ApplicationUser> userOptional = userRepository.findByUserId(userId);
	        if (userOptional.isPresent()) {
	            ApplicationUser user = userOptional.get();
	            contribution.setUser(user);
	            return repository.save(contribution);
	        }
	        return null;
	    }

	    public ContributionToSocietyTable createContributionByEmail(String email, ContributionToSocietyTable contribution) {
	        Optional<ApplicationUser> userOptional = userRepository.findByUsergmail(email);
	      //  System.out.println(userOptional);
	        if (userOptional.isPresent()) {
	            ApplicationUser user = userOptional.get();
	            contribution.setUser(user);
	            return repository.save(contribution);
	        }
	        return null;
	    }
	    public ContributionToSocietyTable updateContribution(Long indx, ContributionToSocietyTable contribution) {
	        Optional<ContributionToSocietyTable> existingContributionOptional = repository.findById(indx);
	        if (existingContributionOptional.isPresent()) {
	        	ContributionToSocietyTable existingContribution = existingContributionOptional.get();
	            existingContribution.setResponsibility(contribution.getResponsibility());
	            existingContribution.setContribution(contribution.getContribution());
	            existingContribution.setScore(contribution.getScore());
	            return repository.save(existingContribution);
	        }
	        return null;
	    }
	    public boolean CanWeDelete(String email,long indx) {
	    	  ApplicationUser user = userRepository.findByUsergmail(email)
	    	            .orElseThrow(() -> new ResourceAccessException("User not found with email: " + email));
	    	    
	    	    // Check if the contribution belongs to the user
	    	  ContributionToSocietyTable contribution = getContributionById(indx);
	    	    if (contribution == null) {
	    	        throw new UnauthorizedActionException("Contribution not found with index: " + indx);
	    	    }
	    	    if (!contribution.getUser().getUserId().equals(user.getUserId())) {
	    	        throw new UnauthorizedActionException("You are not authorized to delete this contribution");
	    	    }
	    	    return true;
	    	    
	    }
	    

		
}
