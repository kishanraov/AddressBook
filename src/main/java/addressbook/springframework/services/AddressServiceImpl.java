package addressbook.springframework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import addressbook.springframework.commands.AddressForm;
import addressbook.springframework.converters.AddressFormToAddress;
import addressbook.springframework.domain.Address;
import addressbook.springframework.repositories.AddressRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 01/02/2018.
 */
@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private AddressFormToAddress addressFormToAddress;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, AddressFormToAddress addressFormToAddress) {
        this.addressRepository = addressRepository;
        this.addressFormToAddress = addressFormToAddress;
    }


    @Override
    public List<Address> listAll() {
        List<Address> addresses = new ArrayList<>();
        
       // addressRepository.findAll().forEach(addresses::add); 
        
        addressRepository.findAllByOrderByName().forEach(addresses::add);
        
        return addresses;
    }

    @Override
    public Address getById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address saveOrUpdate(Address address) {
        addressRepository.save(address);
        return address;
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);

    }

    @Override
    public Address saveOrUpdateAddressForm(AddressForm addressForm) {
        Address savedAddress = saveOrUpdate(addressFormToAddress.convert(addressForm));

        System.out.println("Saved Product Id: " + savedAddress.getId());
        return savedAddress;
    }
}
