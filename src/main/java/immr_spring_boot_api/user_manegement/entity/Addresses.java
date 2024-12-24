package immr_spring_boot_api.user_manegement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Addresses")
public class Addresses {

    @Id
    @Column(length = 100, nullable = false)
    private String id;

    @Column(length = 100, nullable = false)
    private String contact_id;

    @Column(length = 200)
    private String street;

    @Column(length = 200)
    private String city;

    @Column(length = 200)
    private String province;

    @Column(length = 200, nullable = false)
    private String country;

    @Column(length = 200)
    private String postal_code;

    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Contact contact;

}
