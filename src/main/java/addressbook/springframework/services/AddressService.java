package addressbook.springframework.services;

import java.util.List;

import addressbook.springframework.commands.AddressForm;
import addressbook.springframework.domain.Address;

/**
 * Created on 01/02/2018.
 */
public interface AddressService {

    List<Address> listAll();

    Address getById(Long id);

    Address saveOrUpdate(Address address);

    void delete(Long id);

    Address saveOrUpdateAddressForm(AddressForm addressForm);
}
