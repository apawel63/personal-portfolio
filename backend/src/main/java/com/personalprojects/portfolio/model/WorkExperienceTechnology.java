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

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Column(name = "SortOrder", nullable = false)
    private Integer sortOrder;

    @Column(name = "Url", length = 500)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IconId", nullable = false)
    private Icon icon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WorkExperienceId", nullable = false, insertable = false, updatable = false)
    private WorkExperience workExperience;

    public WorkExperienceTechnology() {}

    public Integer getId() { return id; }

    public Integer getWorkExperienceId() { return workExperienceId; }
    public void setWorkExperienceId(Integer workExperienceId) { this.workExperienceId = workExperienceId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Icon getIcon() { return icon; }
    public void setIcon(Icon icon) { this.icon = icon; }

    public WorkExperience getWorkExperience() { return workExperience; }
    public void setWorkExperience(WorkExperience workExperience) { this.workExperience = workExperience; }
}
