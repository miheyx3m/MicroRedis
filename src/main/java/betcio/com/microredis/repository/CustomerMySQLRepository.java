package betcio.com.microredis.repository;

import betcio.com.microredis.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerMySQLRepository extends JpaRepository<CustomerEntity,Integer> {
    List<CustomerEntity> findAllByCreatedAfter(LocalDateTime localDateTime);
 }
