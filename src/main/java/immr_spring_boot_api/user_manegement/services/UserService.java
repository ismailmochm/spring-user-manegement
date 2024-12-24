package immr_spring_boot_api.user_manegement.services;

import immr_spring_boot_api.user_manegement.entity.User;
import immr_spring_boot_api.user_manegement.model.RegisterUserRequest;
import immr_spring_boot_api.user_manegement.repository.UserRepository;
import immr_spring_boot_api.user_manegement.scurity.BCrypt;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private ValidationServices validationServices;

    @Transactional
    public void register(RegisterUserRequest request){
        validationServices.validate(request);

       if(userRepository.existsById(request.getUsername())){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username already registered");
       }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);
    }

}
