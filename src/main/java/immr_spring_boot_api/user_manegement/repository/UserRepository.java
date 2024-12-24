package immr_spring_boot_api.user_manegement.repository;

import immr_spring_boot_api.user_manegement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
