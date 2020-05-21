package betcio.com.microredis.service;

import betcio.com.microredis.entity.CustomerEntity;
import betcio.com.microredis.redishash.CustomerHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FetchService {
    int count_exist_customers = 0;
    int count_new_customers = 0;
    int count_all_customers = 0;
    int count_all_service_iteration = 0;
    boolean first_request_from_dbmysql = true;

    final CustomerServices fetchService;

    @Autowired
    public FetchService(CustomerServices fetchService) {
        this.fetchService = fetchService;
    }

    @Scheduled(initialDelay = 0, fixedDelay = 5000)
    public void ReadFromDBMySQLCustomer() {
        List<CustomerEntity> customerEntities;
        LocalDateTime time_check = LocalDateTime.now().minusSeconds(5);
        if (first_request_from_dbmysql) {
            customerEntities = fetchService.readAllCustomersFromDBMysql();
            first_request_from_dbmysql = false;
        } else {
            customerEntities = fetchService.readCustomersFromDBMysql(time_check);
        }
        count_all_service_iteration++;
        if (!customerEntities.isEmpty()) {
            for (CustomerEntity customer : customerEntities) {
                CustomerHash customerHash = new CustomerHash(customer);
//                System.out.println(customerHash);
                if (fetchService.findCustomerInRedis(customerHash.getFirstName(), customerHash.getLastName()))
                    count_exist_customers++;
                else {
                    fetchService.writerToDBRedis(customerHash);
                    count_new_customers++;
                }
                count_all_customers++;
            }
            System.out.println("count_exist_customers: " + count_exist_customers +
                    " , count_new_customers: " + count_new_customers +
                    " , count_all_customers: " + count_all_customers +
                    " , count_all_service_iteration: " + count_all_service_iteration);
//            System.out.println("read customers from DB MySQL");
        }
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Customers were found in DB Redis while fetching data from DB MySQL : " + count_exist_customers);
        System.out.println("Customers were not found in DB Redis while fetching data from DB MySQL: " + count_new_customers);
        System.out.println("Customers were added in DB MySQL: " + count_all_customers);
        System.out.println("Count of service iterations: " + count_all_service_iteration);
        System.out.println("Application closed...");
    }

}
