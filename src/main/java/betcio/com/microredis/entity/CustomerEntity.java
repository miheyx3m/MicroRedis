package betcio.com.microredis.entity;


import betcio.com.microredis.redishash.CustomerHash;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "created")
    private LocalDateTime created;

    public CustomerEntity(CustomerHash customerHash){
        this.first_name=customerHash.getFirstName();
        this.last_name=customerHash.getLastName();
        this.created=customerHash.getCreated();
    }

}
