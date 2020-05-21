package betcio.com.microredis.service;

import betcio.com.microredis.entity.CustomerEntity;
import betcio.com.microredis.redishash.CustomerHash;
import betcio.com.microredis.repository.CustomerMySQLRepository;
import betcio.com.microredis.repository.CustomerRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServices {

    final CustomerMySQLRepository mySQLRepository;
    final CustomerRedisRepository redisRepository;

    @Autowired
    public CustomerServices(CustomerMySQLRepository mySQLRepository, CustomerRedisRepository redisRepository) {
        this.mySQLRepository = mySQLRepository;
        this.redisRepository = redisRepository;
    }

    public boolean findCustomerInRedis(String first_name, String last_name) {
        return !(redisRepository.findByFirstNameAndLastName(first_name,last_name)==null);
    }

    public boolean writerToDBRedis(CustomerHash customer) {
        redisRepository.save(customer);
        return true;
    }

    public List<CustomerEntity> readCustomersFromDBMysql(LocalDateTime localDateTime) {
        List<CustomerEntity> customers = new ArrayList<>(mySQLRepository.findAllByCreatedAfter(localDateTime));
        return customers;
    }

    public List<CustomerEntity> readAllCustomersFromDBMysql() {
        List<CustomerEntity> customers = new ArrayList<>(mySQLRepository.findAll());
        return customers;
    }
}
