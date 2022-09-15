package com.fastroof.lab4_spring.security.oauth;

import com.fastroof.lab4_spring.entity.User;
import com.fastroof.lab4_spring.enums.Provider;
import com.fastroof.lab4_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;

    @Autowired
    public OAuth2LoginSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2UserImpl oAuth2User = (OAuth2UserImpl) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            User user1 = new User();
            user1.setEmail(email);
            user1.setFullName(oAuth2User.getName());
            user1.setProvider(Provider.GOOGLE);
            userRepository.getUsers().add(user1);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
