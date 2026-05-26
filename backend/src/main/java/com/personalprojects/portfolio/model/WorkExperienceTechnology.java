package com.personalprojects.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "WorkExperienceTechnology")
public class WorkExperienceTechnology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "WorkExperienceId", nullable = false)
    private Integer workExperienceId;

    @Column(name = "TechnologyId", nullable = false)
    private Integer technologyId;

    @Column(name = "SortOrder", nullable = false)
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WorkExperienceId", nullable = false, insertable = false, updatable = false)
    private WorkExperience workExperience;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TechnologyId", nullable = false, insertable = false, updatable = false)
    private Technology technology;

    public WorkExperienceTechnology() {}

    public Integer getId() { return id; }

    public Integer getWorkExperienceId() { return workExperienceId; }
    public void setWorkExperienceId(Integer workExperienceId) { this.workExperienceId = workExperienceId; }

    public Integer getTechnologyId() { return technologyId; }
    public void setTechnologyId(Integer technologyId) { this.technologyId = technologyId; }

    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }

    public WorkExperience getWorkExperience() { return workExperience; }
    public void setWorkExperience(WorkExperience workExperience) { this.workExperience = workExperience; }

    public Technology getTechnology() { return technology; }
    public void setTechnology(Technology technology) { this.technology = technology; }
}
