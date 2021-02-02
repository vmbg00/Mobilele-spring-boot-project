package bg.softuni.mobilele.model.view;

import bg.softuni.mobilele.model.entities.enums.ModelCategoryEnum;

public class ModelViewModel {

  private Long id;
  private String name;
  private ModelCategoryEnum category;
  private String imageUrl;
  private int startYear;
  private Integer endYear;

  public String getName() {
    return name;
  }

  public ModelViewModel setName(String name) {
    this.name = name;
    return this;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ModelCategoryEnum getCategory() {
    return category;
  }

  public ModelViewModel setCategory(
      ModelCategoryEnum category) {
    this.category = category;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public ModelViewModel setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public int getStartYear() {
    return startYear;
  }

  public ModelViewModel setStartYear(int startYear) {
    this.startYear = startYear;
    return this;
  }

  public Integer getEndYear() {
    return endYear;
  }

  public ModelViewModel setEndYear(Integer endYear) {
    this.endYear = endYear;
    return this;
  }

  @Override
  public String toString() {
    return "ModelViewModel{" +
        "name='" + name + '\'' +
        ", category=" + category +
        ", imageUrl='" + imageUrl + '\'' +
        ", startYear=" + startYear +
        ", endYear=" + endYear +
        '}';
  }
}
