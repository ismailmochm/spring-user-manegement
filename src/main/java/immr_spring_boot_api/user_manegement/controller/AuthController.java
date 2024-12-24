package immr_spring_boot_api.user_manegement.controller;

import immr_spring_boot_api.user_manegement.model.LoginUserRequest;
import immr_spring_boot_api.user_manegement.model.RegisterUserRequest;
import immr_spring_boot_api.user_manegement.model.TokenResponse;
import immr_spring_boot_api.user_manegement.model.WebResponse;
import immr_spring_boot_api.user_manegement.services.AuthService;
import immr_spring_boot_api.user_manegement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/api/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<TokenResponse> login(@RequestBody LoginUserRequest request){
        TokenResponse tokenResponse = authService.login(request);
        return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
    }

}
