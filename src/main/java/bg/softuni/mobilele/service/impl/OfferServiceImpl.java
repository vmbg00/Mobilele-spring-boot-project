package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.entities.BaseEntity;
import bg.softuni.mobilele.model.entities.ModelEntity;
import bg.softuni.mobilele.model.entities.OfferEntity;
import bg.softuni.mobilele.model.services.OfferServiceModel;
import bg.softuni.mobilele.model.view.OfferDetailsViewModel;
import bg.softuni.mobilele.model.view.OfferSummaryViewModel;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.security.CurrentUser;
import bg.softuni.mobilele.service.OfferService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;
  private final ModelRepository modelRepository;
  private final CurrentUser currentUser;
  private final UserRepository userRepository;

  public OfferServiceImpl(OfferRepository offerRepository,
                          ModelRepository modelRepository,
                          CurrentUser currentUser,
                          UserRepository userRepository) {
    this.offerRepository = offerRepository;
    this.modelRepository = modelRepository;
    this.currentUser = currentUser;
    this.userRepository = userRepository;
  }

  @Override
  public void createOffer(OfferServiceModel offerServiceModel) {

    OfferEntity newOffer = asNewOffer(offerServiceModel);

    ModelEntity model = modelRepository.findById(offerServiceModel.getModelId()).
            orElse(null);

    newOffer.setModel(model);
    setCurrentTimestamps(newOffer);
    offerRepository.saveAndFlush(newOffer);
  }

  public static void setCurrentTimestamps(BaseEntity baseEntity) {
    baseEntity.
            setCreated(Instant.now()).
            setUpdated(Instant.now());
  }

  private OfferEntity asNewOffer(OfferServiceModel offerServiceModel) {
    ModelMapper modelMapper = new ModelMapper();

    OfferEntity newOffer = new OfferEntity();

    modelMapper.map(offerServiceModel, newOffer);

    newOffer.setSeller(userRepository.findByUsername(currentUser.getName()).orElseThrow());
    newOffer.setId(null);

    return newOffer;
  }

  @Override
  public List<OfferSummaryViewModel> getAllOffers() {

    List<OfferEntity> offerEntities = offerRepository.findAll();

    return offerEntities.
            stream().
            map(OfferServiceImpl::mapToSummary).
            collect(Collectors.toList());
  }

  @Override
  public Optional<OfferDetailsViewModel> getOfferDetails(long offerId) {
    return offerRepository.
            findById(offerId).
            map(this::mapToDetails);
  }

  @Override
  public void removeOffer(long offerId) {
    offerRepository.deleteById(offerId);
  }

  @Override
  public void updateOffer(OfferServiceModel offerModel, int id) {
    OfferEntity offer = this.offerRepository.findById((long) id
    ).orElse(null);

    ModelEntity model = modelRepository.findById(offerModel.getModelId()).
            orElse(null);

    offer
            .setModel(model)
            .setDescription(offerModel.getDescription())
            .setEngine(offerModel.getEngine())
            .setImageUrl(offerModel.getImageUrl())
            .setMileage(offerModel.getMileage())
            .setPrice(offerModel.getPrice())
            .setTransmission(offerModel.getTransmission())
            .setYear(offerModel.getYear());

    offer.setUpdated(Instant.now());

    offerRepository.save(offer);

  }

  private static OfferSummaryViewModel mapToSummary(OfferEntity offerEntity) {
    OfferSummaryViewModel offerModel = new OfferSummaryViewModel();
    mapToSummary(offerEntity, offerModel);
    return offerModel;
  }

  private static void mapToSummary(OfferEntity offerEntity, OfferSummaryViewModel offerModel) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.map(offerEntity, offerModel);
    offerModel.
            setModel(offerEntity.getModel().getName()).
            setBrand(offerEntity.getModel().getBrand().getName());
  }

  private OfferDetailsViewModel mapToDetails(OfferEntity offerEntity) {
    OfferDetailsViewModel offerModel = new OfferDetailsViewModel();
    mapToSummary(offerEntity, offerModel);
    offerModel.
            setSellerFirstName(offerEntity.getSeller().getFirstName()).
            setSellerLastName(offerEntity.getSeller().getLastName()).
            setEditable(currentUser.isAdmin() || offerEntity.getSeller().getUsername().equals(currentUser.getName()));

    return offerModel;
  }
}

