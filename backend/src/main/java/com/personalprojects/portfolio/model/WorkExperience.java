package com.personalprojects.portfolio.model;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
@Entity
@Table(name = "WorkExperience")
public class WorkExperience {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
 
    @Column(name = "Company", nullable = false, length = 150)
    private String company;
 
    @Column(name = "Location", length = 150)
    private String location;
 
    @Column(name = "Title", nullable = false, length = 150)
    private String title;
 
    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;
 
    @Column(name = "EndDate")
    private LocalDate endDate;  // null = "present"
 
    @OneToMany(
        mappedBy = "workExperience",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @OrderBy("sortOrder ASC")
    private List<WorkExperienceTask> tasks = new ArrayList<>();

    @OneToMany(
        mappedBy = "workExperience",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @BatchSize(size = 25)
    private Set<WorkExperienceTechnology> technologies = new HashSet<>();
 
    // --- Constructors ---
 
    public WorkExperience() {}
 
    public WorkExperience(String company, String location, String title,
                          LocalDate startDate, LocalDate endDate) {
        this.company = company;
        this.location = location;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
 
    // --- Helper methods ---
 
    public void addTask(WorkExperienceTask task) {
        tasks.add(task);
        task.setWorkExperience(this);
    }
 
    public void removeTask(WorkExperienceTask task) {
        tasks.remove(task);
        task.setWorkExperience(null);
    }
 
    // --- Getters & Setters ---
 
    public Integer getId() { return id; }
 
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
 
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
 
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
 
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
 
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
 
    public List<WorkExperienceTask> getTasks() { return tasks; }
    public void setTasks(List<WorkExperienceTask> tasks) { this.tasks = tasks; }

    public Set<WorkExperienceTechnology> getTechnologies() { return technologies; }
    public void setTechnologies(Set<WorkExperienceTechnology> technologies) { this.technologies = technologies; }
}
