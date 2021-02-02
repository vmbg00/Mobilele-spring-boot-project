package bg.softuni.mobilele.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="brands")
public class BrandEntity extends BaseEntity {

  @Column(unique = true, nullable = false)
  private String name;

  public String getName() {
    return name;
  }

  public BrandEntity setName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public String toString() {
    return "BrandEntity{" +
        "name='" + name + '\'' +
        ", id=" + id +
        ", created=" + created +
        ", updated=" + updated +
        '}';
  }
}
