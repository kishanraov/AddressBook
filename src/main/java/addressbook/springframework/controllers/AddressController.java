package addressbook.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import addressbook.springframework.commands.AddressForm;
import addressbook.springframework.converters.AddressToAddressForm;
import addressbook.springframework.domain.Address;
import addressbook.springframework.services.AddressService;

import javax.validation.Valid;

/**
 * Created on 01/02/2018.
 */
@Controller
public class AddressController {
    private AddressService addressService;

    private AddressToAddressForm addressToAddressForm;

    @Autowired
    public void setAddressToAddressForm(AddressToAddressForm addressToAddressForm) {
        this.addressToAddressForm = addressToAddressForm;
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/address/list";
    }

    @RequestMapping({"/address/list", "/address"})
    public String listAddresses(Model model){
        model.addAttribute("addresses", addressService.listAll());
        return "address/list";
    }

    @RequestMapping("/address/show/{id}")
    public String getAddress(@PathVariable String id, Model model){
        model.addAttribute("address", addressService.getById(Long.valueOf(id)));
        return "address/show";
    }

    @RequestMapping("address/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Address address = addressService.getById(Long.valueOf(id));
        AddressForm addressForm = addressToAddressForm.convert(address);

        model.addAttribute("addressForm", addressForm);
        return "address/addressform";
    }

    @RequestMapping("/address/new")
    public String newAddress(Model model){
        model.addAttribute("addressForm", new AddressForm());
        return "address/addressform";
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public String saveOrUpdateAddress(@Valid AddressForm addressForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "address/addressform";
        }

        Address savedAddress = addressService.saveOrUpdateAddressForm(addressForm);

        return "redirect:/address/show/" + savedAddress.getId();
    }

    @RequestMapping("/address/delete/{id}")
    public String delete(@PathVariable String id){
        addressService.delete(Long.valueOf(id));
        return "redirect:/address/list";
    }
}
