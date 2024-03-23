package org.rivero.roommanager.services;

import io.jsonwebtoken.*;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rivero.roommanager.dtos.UserInfo;
import org.rivero.roommanager.entities.User;
import org.rivero.roommanager.repositories.DBConnectionManager;
import org.rivero.roommanager.repositories.UserRepository1;
import org.rivero.roommanager.request.LoginRequest;
import org.rivero.roommanager.request.UserUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    public static final String TOKEN_ISSUER = "room-manager-service";
    public static final String SECRET_KEY = "my-secret-zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";

    final UserRepository1 userRepository;

    final DBConnectionManager dbConnectionManager;

    public String login(LoginRequest request) {
        if (DigestUtils.md5DigestAsHex(request.password().getBytes(StandardCharsets.UTF_8))
                .equals(userRepository.getPasswordHash(request.username()))) {

            User user = userRepository.getByUsername(request.username())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "user not found"));

            return generateToken(user).compact();
        } else
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "username or password doesnt matched");
    }

    protected JwtBuilder generateToken(User user) {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(keyBytes, "HmacSHA256");
        return Jwts.builder()
                .subject(user.getId())
                .claim("username", user.getName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .issuer(TOKEN_ISSUER)
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1_000))
                .signWith(signingKey);
    }

    public User getUserById(String id) {
        return userRepository.getOne(id);
    }

    public List<User> getAllUser() {
        return userRepository.getList();
    }

    public void register(User user) {
        if (userRepository.getPasswordHash(user.getName()).isEmpty()) {
            userRepository.insert(user);
        }
    }

    public void deleteOne(String id) {
        userRepository.deleteOne(id);
    }

    public void updateOne(UserUpdateRequest userUpdateRequest, String id) {
        userRepository.updateOne(userUpdateRequest, id);
    }

    public UserInfo authorize(String token) {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA256");
        Jwt<Header, Claims> jwt = (Jwt<Header, Claims>) Jwts.parser().verifyWith(signingKey).build().parse(token);
        return new UserInfo(
                jwt.getPayload().getSubject(),
                jwt.getPayload().get("username", String.class));
    }
}
