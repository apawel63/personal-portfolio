package com.personalprojects.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Link", schema = "dbo")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(nullable = false, length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id", nullable = false)
    private Icon icon;

    @Column(nullable = false, length = 500)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false, insertable = false, updatable = false)
    private Project project;

    // --- Constructors ---

    public Link() {}

    public Link(String name, Icon icon, String url) {
        this.name = name;
        this.icon = icon;
        this.url = url;
    }

    // --- Getters & Setters ---

    public Integer getId() { return id; }

    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Icon getIcon() { return icon; }
    public void setIcon(Icon icon) { this.icon = icon; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}
