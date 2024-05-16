package todo.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

public class JWTService{
    private final String SECRET_KEY = "DcU1tVsBjbXoXxigk4u6Ng==";
    public String generateToken(User user){
        String token = Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()*24*60*60*1000))
                .signWith(getSigninKey())
                .compact();
        return token;
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaims(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractUserName(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user){
        String username = extractUserName(token);
        return (username.equals(user.getUsername()) && isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token)
                .before(new Date());
    }

    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }
}

