package ir.fassih.workshop.core.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
public class JwtAlgorithm {

    @Getter
    private final Algorithm algorithm;

    public JwtAlgorithm(byte[] publicKeyBytes, byte[] privateKeyBytes) {
        try {
            RSAPublicKey publicKey = getPublicKey(publicKeyBytes);
            RSAPrivateKey privateKey = getPrivateKey(privateKeyBytes);
            this.algorithm = Algorithm.RSA256(publicKey, privateKey);
            log.info("Jwt algorithm create successful");
        } catch (Exception e) {
            log.error("cannot create JwtAlgorithm bean ", e);
            throw new IllegalStateException("cannot create jwt bean", e);
        }
    }


    private static RSAPublicKey getPublicKey(byte[] keyBytes) throws Exception {
        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(spec);
    }


    private RSAPrivateKey getPrivateKey(byte[] keyBytes) throws Exception {
        PKCS8EncodedKeySpec spec =
                new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) kf.generatePrivate(spec);

    }

}
