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
@Table(name = "contacts")
public class Contact {

    @Id
    @Column(length = 100, nullable = false)
    private String id;

    @Column(length = 100, nullable = false)
    private String username;

    @Column(length = 100, nullable = false, name = "first_name")
    private String firstName;

    @Column(length = 100, name = "last_name")
    private String lastName;

    @Column(length = 100)
    private String phone;

    @Column(length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable=false, updatable=false)
    private User user;

    @OneToMany(mappedBy = "contact")
    private List<Addresses> addresses;

}
