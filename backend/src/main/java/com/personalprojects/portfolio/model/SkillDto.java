package com.personalprojects.portfolio.model;


public class SkillDto {

    private Integer id;
    private String category;
    private String name;
    private Icon icon;
    private Integer displayOrder;

    // --- Factory method ---
 
    public static SkillDto from(Skill entity) {
        SkillDto dto = new SkillDto();
        dto.id = entity.getId();
        dto.category = entity.getCategory();
        dto.name = entity.getName();
        dto.icon = entity.getIcon();
        dto.displayOrder = entity.getDisplayOrder();

        return dto;
    }

    // --- Getters ---
    public Integer getId() { return id; }
    public String getCategory() { return category; }
    public String getName() { return name; }
    public Icon getIcon() { return icon; }
    public Integer getDisplayOrder() { return displayOrder; }

}
