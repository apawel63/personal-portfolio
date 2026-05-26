package com.personalprojects.portfolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjectTechnologyId implements Serializable {

    @Column(name = "ProjectId")
    private Integer projectId;

    @Column(name = "TechnologyId")
    private Integer technologyId;

    public ProjectTechnologyId() {}

    public ProjectTechnologyId(Integer projectId, Integer technologyId) {
        this.projectId = projectId;
        this.technologyId = technologyId;
    }

    public Integer getProjectId() { return projectId; }
    public Integer getTechnologyId() { return technologyId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectTechnologyId)) return false;
        ProjectTechnologyId that = (ProjectTechnologyId) o;
        return Objects.equals(projectId, that.projectId) && Objects.equals(technologyId, that.technologyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, technologyId);
    }
}
