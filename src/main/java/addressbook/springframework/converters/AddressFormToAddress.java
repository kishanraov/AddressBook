package addressbook.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import addressbook.springframework.commands.AddressForm;
import addressbook.springframework.domain.Address;

/**
 * Created on 01/02/2018.
 */
@Component
public class AddressFormToAddress implements Converter<AddressForm, Address> {

    @Override
    public Address convert(AddressForm addressForm) {
        Address address = new Address();
        if (addressForm.getId() != null  && !StringUtils.isEmpty(addressForm.getId())) {
            address.setId(new Long(addressForm.getId()));
        }
        address.setName(addressForm.getName());
        address.setPhone(addressForm.getPhone());
        return address;
    }
}
