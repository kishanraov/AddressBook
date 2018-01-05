package addressbook.springframework.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import addressbook.springframework.domain.Address;
import addressbook.springframework.repositories.AddressRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddressRepositoryTest {

    private static final String NAME= "Vipul";
    private static final String PHONE = "(000)123-456-7890";

    @Autowired
    private AddressRepository addressRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        Address address = new Address();
        address.setId(1L);
        address.setName(NAME);
        address.setPhone(PHONE);

        //when
        addressRepository.save(address);
        
        //then
        Assert.assertNotNull(address.getId());
        Address newAddress = addressRepository.findById(address.getId()).orElse(null);
        Assert.assertEquals((Long) 1L, newAddress.getId());
        Assert.assertEquals(NAME, newAddress.getName());
        Assert.assertEquals(PHONE, newAddress.getPhone());
    }
}