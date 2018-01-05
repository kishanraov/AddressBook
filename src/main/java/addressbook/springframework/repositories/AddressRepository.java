package addressbook.springframework.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import addressbook.springframework.domain.Address;

/**
 * Created on 01/02/2018.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
	public List<Address> findAllByOrderByName();
}
