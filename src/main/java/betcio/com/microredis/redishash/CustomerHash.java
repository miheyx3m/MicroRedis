package betcio.com.microredis.redishash;


import betcio.com.microredis.entity.CustomerEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor

@RedisHash("Customers")
public class CustomerHash implements Serializable {
    @Id
    private Integer id;
    @Indexed
    private String firstName;
    @Indexed
    private String lastName;
    private LocalDateTime created;

    public CustomerHash(CustomerEntity customer){
        this.id=customer.getId();
        this.firstName=customer.getFirst_name();
        this.lastName=customer.getLast_name();
        this.created=customer.getCreated();
    }
}
