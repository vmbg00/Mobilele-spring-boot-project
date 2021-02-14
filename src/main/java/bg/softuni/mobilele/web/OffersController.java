package bg.softuni.mobilele.web;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import bg.softuni.mobilele.model.entities.enums.EngineEnum;
import bg.softuni.mobilele.model.entities.enums.TransmissionEnum;
import bg.softuni.mobilele.model.services.OfferServiceModel;
import bg.softuni.mobilele.model.view.OfferDetailsViewModel;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/offers")
public class OffersController {

  private final OfferService offerService;
  private final BrandService brandService;

  public OffersController(OfferService offerService,
                          BrandService brandService) {
    this.offerService = offerService;
    this.brandService = brandService;
  }

  @GetMapping("/all")
  public String getAllOffers(Model model) {
    model.addAttribute("offers",
            offerService.getAllOffers());

    return "offers";
  }

  @ModelAttribute("offerModel")
  public OfferServiceModel offerModel(){
    return new OfferServiceModel();
  }

  @GetMapping("/add")
  public String newOffer(Model model) {
    model.addAttribute("engines", EngineEnum.values());
    model.addAttribute("transmissions", TransmissionEnum.values());
    model.addAttribute("brands", brandService.getAllBrands());
    return "offer-add";
  }

  @DeleteMapping("/delete/{id}")
  public String removeOffer(@PathVariable("id") long id) {

    offerService.removeOffer(id);

    return "redirect:/offers/all";
  }

  @PostMapping("/add")
  public String addOffer(@Valid @ModelAttribute OfferServiceModel offerModel,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("offerModel", offerModel);
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

      return "redirect:/offers/add";
    }

    offerService.createOffer(offerModel);
    return "redirect:/offers/all";
  }

  @GetMapping("/update/{id}")
  public String updateOffer(Model model, @PathVariable int id){
    model.addAttribute("engines", EngineEnum.values());
    model.addAttribute("transmissions", TransmissionEnum.values());
    model.addAttribute("brands", brandService.getAllBrands());

    OfferDetailsViewModel offerDetailsModel = offerService.
            getOfferDetails(id).
            orElseThrow();

    model.addAttribute("offer", offerDetailsModel);

    return "update";
  }

  @PostMapping("/update/{id}")
  public String updateOfferConfirm(@Valid @ModelAttribute OfferServiceModel offerModel,
                                   @PathVariable int id,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){

    if (bindingResult.hasErrors()){
      redirectAttributes.addFlashAttribute("offerModel", offerModel);
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

      return "redirect:/offers/all";
    }

    this.offerService.updateOffer(offerModel, id);

    return "redirect:http://localhost:8080/";
  }


  @GetMapping("/{id}")
  public String offerDetails(Model model,
                             @PathVariable int id) {

    OfferDetailsViewModel offerDetailsModel = offerService.
            getOfferDetails(id).
            orElseThrow();

    model.addAttribute("offer", offerDetailsModel);

    return "details";
  }
}