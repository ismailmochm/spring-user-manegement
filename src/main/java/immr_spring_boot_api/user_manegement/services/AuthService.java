package immr_spring_boot_api.user_manegement.services;

import immr_spring_boot_api.user_manegement.entity.User;
import immr_spring_boot_api.user_manegement.model.LoginUserRequest;
import immr_spring_boot_api.user_manegement.model.TokenResponse;
import immr_spring_boot_api.user_manegement.repository.UserRepository;
import immr_spring_boot_api.user_manegement.scurity.BCrypt;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationServices validationServices;

    @Transactional
    public TokenResponse login(LoginUserRequest request){
        validationServices.validate(request);

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong"));

        if(BCrypt.checkpw(request.getPassword(), user.getPassword())){
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30day());
            userRepository.save(user);

           return TokenResponse.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();

        }else {
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }
    }

    private Long next30day(){
        return System.currentTimeMillis() + (1000 * 16 * 24 * 30);
    }

}
