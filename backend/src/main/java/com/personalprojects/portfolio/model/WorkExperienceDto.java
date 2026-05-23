package com.personalprojects.portfolio.model;

import java.time.LocalDate;
import java.util.List;

public class WorkExperienceDto {

    private Integer id;
    private String company;
    private String location;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TaskDto> tasks;
    private List<TechnologyDto> technologies;

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

        public static TechnologyDto from(WorkExperienceTechnology tech) {
            TechnologyDto dto = new TechnologyDto();
            dto.id = tech.getId();
            dto.name = tech.getName();
            dto.icon = IconDto.from(tech.getIcon());
            return dto;
        }

        public Integer getId() { return id; }
        public String getName() { return name; }
        public IconDto getIcon() { return icon; }
    }

    public static class TaskDto {
        private Integer id;
        private String description;
        private Integer sortOrder;
 
        public static TaskDto from(WorkExperienceTask task) {
            TaskDto dto = new TaskDto();
            dto.id = task.getId();
            dto.description = task.getDescription();
            dto.sortOrder = task.getSortOrder();
            return dto;
        }
 
        public Integer getId() { return id; }
        public String getDescription() { return description; }
        public Integer getSortOrder() { return sortOrder; }
    }
 
    // --- Factory method ---
 
    public static WorkExperienceDto from(WorkExperience entity) {
        WorkExperienceDto dto = new WorkExperienceDto();
        dto.id = entity.getId();
        dto.company = entity.getCompany();
        dto.location = entity.getLocation();
        dto.title = entity.getTitle();
        dto.startDate = entity.getStartDate();
        dto.endDate = entity.getEndDate();
        dto.tasks = entity.getTasks().stream()
                .map(TaskDto::from)
                .toList();
        dto.technologies = entity.getTechnologies().stream()
                .map(TechnologyDto::from)
                .toList();
        return dto;
    }
 
    // --- Getters ---
 
    public Integer getId() { return id; }
    public String getCompany() { return company; }
    public String getLocation() { return location; }
    public String getTitle() { return title; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public List<TaskDto> getTasks() { return tasks; }
    public List<TechnologyDto> getTechnologies() { return technologies; }

}
