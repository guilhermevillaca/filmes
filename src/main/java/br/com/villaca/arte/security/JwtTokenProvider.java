package br.com.villaca.arte.security;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

/**
 * JwtTokenProvider — gera JWTs assinados com RSA (RS256) usando Nimbus.
 *
 * Uso:
 *   @Autowired JwtTokenProvider jwtProvider;
 *   String token = jwtProvider.generateToken("username", Map.of("role","USER"));
 */
@Component
public class JwtTokenProvider {

    @Value("${jwt.private-key}")
    private Resource privateKeyResource;

    /**
     * Gera um token JWT RS256 para o subject informado e claims adicionais.
     *
     * @param subject identidade (ex: username)
     * @param extraClaims claims extras a colocar no token (roles, etc.)
     * @param ttlSeconds validade em segundos (ex: 3600 para 1h)
     * @return token JWT assinado (compact)
     * @throws Exception se falhar ao ler/parsear a chave ou ao assinar
     */
    public String generateToken(String subject, Map<String, Object> extraClaims, long ttlSeconds) throws Exception {
        RSAPrivateKey privateKey = (RSAPrivateKey) loadPrivateKey();

        // Header
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(JOSEObjectType.JWT)
                .build();

        // Claims
        Instant now = Instant.now();
        JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder()
                .subject(subject)
                .issuer("villaca-app") // ajuste conforme precisar
                .issueTime(Date.from(now))
                .expirationTime(Date.from(now.plusSeconds(ttlSeconds)));

        if (extraClaims != null) {
            extraClaims.forEach(claimsBuilder::claim);
        }

        JWTClaimsSet claims = claimsBuilder.build();

        // Signed JWT
        SignedJWT signedJWT = new SignedJWT(header, claims);

        // Signer
        RSASSASigner signer = new RSASSASigner(privateKey);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    /** Overload prático com ttl padrão de 1 hora */
    public String generateToken(String subject, Map<String, Object> extraClaims) throws Exception {
        return generateToken(subject, extraClaims, 3600);
    }

    /** Overload minimal (somente subject, 1 hora) */
    public String generateToken(String subject) throws Exception {
        return generateToken(subject, null, 3600);
    }

    /** Lê e retorna a PrivateKey (PKCS#8) apontada por jwt.private-key */
    private PrivateKey loadPrivateKey() throws Exception {
        String key = new String(privateKeyResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        // remove cabeçalhos PEM e quebras de linha
        key = key.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] bytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }
}
