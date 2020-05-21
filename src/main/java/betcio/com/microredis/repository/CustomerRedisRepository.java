package betcio.com.microredis.repository;

import betcio.com.microredis.redishash.CustomerHash;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;

public interface CustomerRedisRepository extends CrudRepository<CustomerHash, Integer> {
    CustomerHash findByFirstNameAndLastName(String firstName, String lastName);
}
