package bg.softuni.mobilele.model.entities;


import bg.softuni.mobilele.model.entities.enums.EngineEnum;
import bg.softuni.mobilele.model.entities.enums.TransmissionEnum;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="offers")
public class OfferEntity extends BaseEntity {

  @Enumerated(EnumType.STRING)
  private EngineEnum engine;
  private String imageUrl;
  private int mileage;
  private BigDecimal price;
  private int year;
  private String description;
  @Enumerated(EnumType.STRING)
  private TransmissionEnum transmission;
  @ManyToOne
  private ModelEntity model;

//  @ManyToOne
//  private UserEntity user;

  public EngineEnum getEngine() {
    return engine;
  }

  public OfferEntity setEngine(EngineEnum engine) {
    this.engine = engine;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public OfferEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public OfferEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public int getMileage() {
    return mileage;
  }

  public OfferEntity setMileage(int mileage) {
    this.mileage = mileage;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public OfferEntity setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public int getYear() {
    return year;
  }

  public OfferEntity setYear(int year) {
    this.year = year;
    return this;
  }

  public TransmissionEnum getTransmission() {
    return transmission;
  }

  public OfferEntity setTransmission(
      TransmissionEnum transmission) {
    this.transmission = transmission;
    return this;
  }

  public ModelEntity getModel() {
    return model;
  }

  public OfferEntity setModel(ModelEntity model) {
    this.model = model;
    return this;
  }

  //TODO: uncomment when users come into the game.
//  public UserEntity getUser() {
//    return user;
//  }
//
//  public OfferEntity setUser(UserEntity user) {
//    this.user = user;
//    return this;
//  }

  @Override
  public String toString() {
    return "OfferEntity{" +
        "engine=" + engine +
        ", imageUrl='" + imageUrl + '\'' +
        ", mileage=" + mileage +
        ", price=" + price +
        ", year=" + year +
        ", transmission=" + transmission +
        ", model=" + model +
        //", user=" + user +
        ", id=" + id +
        ", created=" + created +
        ", updated=" + updated +
        '}';
  }
}
