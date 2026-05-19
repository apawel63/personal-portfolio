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
 
    // --- Nested DTO ---
 
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

}
