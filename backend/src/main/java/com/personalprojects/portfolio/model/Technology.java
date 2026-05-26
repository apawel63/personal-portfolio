package com.personalprojects.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Technology", schema = "dbo")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Column(name = "Url", length = 500)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IconId", nullable = false)
    private Icon icon;

    // --- Constructors ---

    public Technology() {}

    // --- Getters & Setters ---

    public Integer getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Icon getIcon() { return icon; }
    public void setIcon(Icon icon) { this.icon = icon; }
}
