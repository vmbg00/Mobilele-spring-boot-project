package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.services.OfferServiceModel;
import bg.softuni.mobilele.model.view.OfferDetailsViewModel;
import bg.softuni.mobilele.model.view.OfferSummaryViewModel;
import java.util.List;
import java.util.Optional;

public interface OfferService {

  void createOffer(OfferServiceModel offerServiceModel);

  List<OfferSummaryViewModel> getAllOffers();

  Optional<OfferDetailsViewModel> getOfferDetails(long offerId);

  void removeOffer(long offerId);
}
