package com.profileAnalysisTables.contributionToSociety;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.spring_security_learn.model.ApplicationUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contribution_to_society_table") // Explicitly specify the table name
public class ContributionToSocietyTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long indx;

    private String responsibility;
    private String contribution;
    private long score;
    
    @JsonIgnore
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")// Foreign key column
    @JsonIgnore
    private ApplicationUser user;

    public ContributionToSocietyTable() {
        super();
        this.createdAt = LocalDateTime.now();

    }

    public ContributionToSocietyTable(long indx, String responsibility, String contribution, long score, ApplicationUser user) {
        super();
        this.indx = indx;
        this.responsibility = responsibility;
        this.contribution = contribution;
        this.score = score;
        this.user = user;
        this.createdAt = LocalDateTime.now();

    }

    public long getIndx() {
        return indx;
    }

    public void setIndx(long indx) {
        this.indx = indx;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return "ContributionToSocietyTable [indx=" + indx + ", responsibility=" + responsibility + ", contribution="
                + contribution + ", score=" + score + ", user="  + "]";
    }
}
