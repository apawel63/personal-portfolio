package com.personalprojects.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ProjectTechnology", schema = "dbo")
public class ProjectTechnology {

    @EmbeddedId
    private ProjectTechnologyId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    @JoinColumn(name = "ProjectId")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("technologyId")
    @JoinColumn(name = "TechnologyId")
    private Technology technology;

    @Column(name = "SortOrder", nullable = false)
    private Integer sortOrder;

    public ProjectTechnology() {}

    public ProjectTechnologyId getId() { return id; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public Technology getTechnology() { return technology; }
    public void setTechnology(Technology technology) { this.technology = technology; }

    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}
