package com.personalprojects.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Technology", schema = "dbo")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id", nullable = false)
    private Icon icon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false, insertable = false, updatable = false)
    private Project project;

    // --- Constructors ---

    public Technology() {}

    public Technology(String name, Icon icon) {
        this.name = name;
        this.icon = icon;
    }

    // --- Getters & Setters ---

    public Integer getId() { return id; }

    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Icon getIcon() { return icon; }
    public void setIcon(Icon icon) { this.icon = icon; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}
