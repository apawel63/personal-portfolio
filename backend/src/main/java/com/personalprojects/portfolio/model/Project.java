package com.personalprojects.portfolio.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Project", schema = "dbo")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(nullable = false, length = 500)
    private String imageUrl;

    @Column(name = "SortOrder", nullable = false)
    private int sortOrder;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @OrderBy("sortOrder ASC")
    private List<ProjectTechnology> technologies = new ArrayList<>();

    @OneToMany(
        mappedBy = "project",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private Set<Link> links = new HashSet<>();

    // --- Constructors ---

    public Project() {}

    public Project(String title, String category, String description, String imageUrl) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // --- Helper methods ---

    public void addLink(Link link) {
        links.add(link);
        link.setProject(this);
    }

    public void removeLink(Link link) {
        links.remove(link);
        link.setProject(null);
    }

    // --- Getters & Setters ---

    public Integer getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getSortOrder() { return sortOrder; }
    public void setSortOrder(int sortOrder) { this.sortOrder = sortOrder; }

    public List<ProjectTechnology> getTechnologies() { return technologies; }
    public void setTechnologies(List<ProjectTechnology> technologies) { this.technologies = technologies; }

    public Set<Link> getLinks() { return links; }
    public void setLinks(Set<Link> links) { this.links = links; }
}
