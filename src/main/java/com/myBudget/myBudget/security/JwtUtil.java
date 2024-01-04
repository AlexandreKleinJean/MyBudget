package com.myBudget.myBudget.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // Je génère la clé secrète
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // J'implémente la durée de vie du JWT (1 heure)
    private static final long EXPIRATION_TIME = 3600000;

    /*---------Method pour générer le Jwt en back-end-----------*/
    public static String generateJwtToken(String email) {
        String jwtToken = Jwts.builder()
                // date de création du token
                .setIssuedAt(new Date())
                // date d'expiration du token
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // je signe le Jwt (le rendant unique)
                .signWith(SECRET_KEY)
                // la configuration du token est close
                .compact();
        return jwtToken;
    }

    /*---------Method pour valider le Jwt venant du front-end---------*/
    public static boolean validateToken(String jwt) {
        try {
            // je construit l'interprète du Jwt à checker
            Claims claims = Jwts.parserBuilder()
                    // je génére la clé secrète (identique au token à checker)
                    .setSigningKey(SECRET_KEY)
                    // j'initialise l'interprète
                    .build()
                    // l'interprète analyse le Jwt
                    .parseClaimsJws(jwt)
                    // je récupère les infos du Jwt
                    .getBody();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
