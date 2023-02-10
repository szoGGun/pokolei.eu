package eu.przemyslawrutkowski.pokolei;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Map;

import eu.przemyslawrutkowski.pokolei.controller.AuthController;
import eu.przemyslawrutkowski.pokolei.entity.User;
import eu.przemyslawrutkowski.pokolei.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthController authController;

    private User loginUser;
    private User user;

    @Before
    public void setUp() {
        loginUser = new User();
        loginUser.setUsername("testuser");
        loginUser.setPassword("testpassword");

        user = new User();
        user.setUsername("testuser");
        user.setPassword("$2a$10$837vmejYkMIfmHbwHe.W0e6RReU6Oy4HiA.UczArGK91dvckBcMre");
    }

    @Test
    public void testAuthenticateUserSuccess() {
        when(userRepository.findByUsername("testuser")).thenReturn(user);
        when(passwordEncoder.matches("testpassword", "$2a$10$837vmejYkMIfmHbwHe.W0e6RReU6Oy4HiA.UczArGK91dvckBcMre")).thenReturn(true);

        ResponseEntity<?> response = authController.authenticateUser(loginUser);

        assertEquals(200, response.getStatusCode().value());

        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        String jwt = Jwts.builder()
                .setSubject("testuser")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();

        assert responseBody != null;
        assertEquals(jwt, responseBody.get("token"));
    }

    @Test
    public void testAuthenticateUserUsernameNotFound() {
        when(userRepository.findByUsername("testuser")).thenReturn(null);

        ResponseEntity<?> response = authController.authenticateUser(loginUser);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Invalid username or password", response.getBody());
    }
}
