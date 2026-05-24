package com.personalprojects.portfolio.model;

import java.util.List;
import java.util.Set;

public class ProjectDto {

    private Integer id;
    private String title;
    private String category;
    private String description;
    private String imageUrl;
    private List<TechnologyDto> technologies;
    private Set<LinkDto> links;

    // --- Nested DTOs ---

    public static class IconDto {
        private String cssClass;

        public static IconDto from(Icon icon) {
            IconDto dto = new IconDto();
            dto.cssClass = icon.getCssClass();
            return dto;
        }

        public String getCssClass() { return cssClass; }
    }

    public static class TechnologyDto {
        private Integer id;
        private String name;
        private IconDto icon;
        private String url;

        public static TechnologyDto from(Technology technology) {
            TechnologyDto dto = new TechnologyDto();
            dto.id = technology.getId();
            dto.name = technology.getName();
            dto.icon = IconDto.from(technology.getIcon());
            dto.url = technology.getUrl();
            return dto;
        }

        public Integer getId() { return id; }
        public String getName() { return name; }
        public IconDto getIcon() { return icon; }
        public String getUrl() { return url; }
    }

    public static class LinkDto {
        private Integer id;
        private String name;
        private IconDto icon;
        private String url;

        public static LinkDto from(Link link) {
            LinkDto dto = new LinkDto();
            dto.id = link.getId();
            dto.name = link.getName();
            dto.icon = IconDto.from(link.getIcon());
            dto.url = link.getUrl();
            return dto;
        }

        public Integer getId() { return id; }
        public String getName() { return name; }
        public IconDto getIcon() { return icon; }
        public String getUrl() { return url; }
    }

    // --- Factory method ---

    public static ProjectDto from(Project entity) {
        ProjectDto dto = new ProjectDto();
        dto.id = entity.getId();
        dto.title = entity.getTitle();
        dto.category = entity.getCategory();
        dto.description = entity.getDescription();
        dto.imageUrl = entity.getImageUrl();
        dto.technologies = entity.getTechnologies().stream()
                .map(TechnologyDto::from)
                .toList();
        dto.links = entity.getLinks().stream()
                .map(LinkDto::from)
                .collect(java.util.stream.Collectors.toSet());
        return dto;
    }

    // --- Getters ---

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public List<TechnologyDto> getTechnologies() { return technologies; }
    public Set<LinkDto> getLinks() { return links; }
}
