package addressbook.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import addressbook.springframework.commands.AddressForm;
import addressbook.springframework.domain.Address;

/**
 * Created on 01/02/2018.
 */
@Component
public class AddressToAddressForm implements Converter<Address, AddressForm> {
    @Override
    public AddressForm convert(Address address) {
        AddressForm addressForm = new AddressForm();
        addressForm.setId(address.getId());
        addressForm.setName(address.getName());
        addressForm.setPhone(address.getPhone());
        return addressForm;
    }
}
