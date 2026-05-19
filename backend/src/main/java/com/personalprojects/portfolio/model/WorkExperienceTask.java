package com.personalprojects.portfolio.model;

import jakarta.persistence.*;
 
@Entity
@Table(name = "WorkExperienceTask")
public class WorkExperienceTask {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WorkExperienceId", nullable = false)
    private WorkExperience workExperience;
 
    @Column(name = "Description", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String description;
 
    @Column(name = "SortOrder", nullable = false)
    private Integer sortOrder = 0;
 
    // --- Constructors ---
 
    public WorkExperienceTask() {}
 
    public WorkExperienceTask(String description, Integer sortOrder) {
        this.description = description;
        this.sortOrder = sortOrder;
    }
 
    // --- Getters & Setters ---
 
    public Integer getId() { return id; }
 
    public WorkExperience getWorkExperience() { return workExperience; }
    public void setWorkExperience(WorkExperience workExperience) {
        this.workExperience = workExperience;
    }
 
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
 
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}
