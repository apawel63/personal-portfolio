package com.personalprojects.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Category", length = 100, nullable = false)
    private String category;

    @Column(name = "Name", length = 100, nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "IconId", referencedColumnName = "id")
    private Icon icon;

    @Column(name = "DisplayOrder", nullable = false)
    private Integer displayOrder = 0;

    // --- Constructors ---
    public Skill() {}

    public Skill(Integer id, String category, String name, Icon icon, Integer displayOrder) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.icon = icon;
        this.displayOrder = displayOrder;
    }

    // --- Getters & Setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Icon getIcon() { return icon; }
    public void setIcon(Icon icon) { this.icon = icon; }

    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }

}
