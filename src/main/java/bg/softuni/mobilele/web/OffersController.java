package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.entities.enums.EngineEnum;
import bg.softuni.mobilele.model.entities.enums.TransmissionEnum;
import bg.softuni.mobilele.model.services.OfferServiceModel;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {

  private final OfferService offerService;
  private final BrandService brandService;

  public OffersController(OfferService offerService, BrandService brandService) {
    this.offerService = offerService;
    this.brandService = brandService;
  }

  @GetMapping("/all")
  public String getAllOffers(Model model) {
    model.addAttribute("models", offerService.getAllOffers());
    return "offers";
  }

  @GetMapping("/add")
  public String newOffer(Model model) {
    model.addAttribute("engines", EngineEnum.values());
    model.addAttribute("transmissions", TransmissionEnum.values());
    model.addAttribute("brands", brandService.getAllBrands());
    model.addAttribute("formData", new OfferServiceModel());
    return "offer-add";
  }




}
