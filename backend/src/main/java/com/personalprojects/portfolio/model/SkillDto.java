package com.personalprojects.portfolio.model;

public class SkillDto {

    private Integer id;
    private String category;
    private String name;
    private IconDto icon;
    private Integer displayOrder;

    // --- Nested DTO ---

    public static class IconDto {
        private String cssClass;

        public static IconDto from(Icon icon) {
            IconDto dto = new IconDto();
            dto.cssClass = icon.getCssClass();
            return dto;
        }

        public String getCssClass() { return cssClass; }
    }

    // --- Factory method ---

    public static SkillDto from(Skill entity) {
        SkillDto dto = new SkillDto();
        dto.id = entity.getId();
        dto.category = entity.getCategory();
        dto.name = entity.getName();
        dto.icon = IconDto.from(entity.getIcon());
        dto.displayOrder = entity.getDisplayOrder();
        return dto;
    }

    // --- Getters ---
    public Integer getId() { return id; }
    public String getCategory() { return category; }
    public String getName() { return name; }
    public IconDto getIcon() { return icon; }
    public Integer getDisplayOrder() { return displayOrder; }
}
