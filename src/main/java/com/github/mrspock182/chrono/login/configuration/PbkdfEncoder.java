package com.github.mrspock182.chrono.login.configuration;

import com.github.mrspock182.chrono.login.exception.InternalServerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
public class PbkdfEncoder implements PasswordEncoder {

    @Value("${spring.password.encoder.secret}")
    private String secret;

    @Value("${spring.password.encoder.secretkey}")
    private String secretkey;

    @Value("${spring.password.encoder.iteration}")
    private Integer iteration;

    @Value("${spring.password.encoder.keylength}")
    private Integer keylength;

    @Override
    public String encode(CharSequence cs) {
        try {
            byte[] result = SecretKeyFactory.getInstance(secretkey)
                    .generateSecret(new PBEKeySpec(cs.toString().toCharArray(), secret.getBytes(), iteration, keylength))
                    .getEncoded();
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new InternalServerService(ex);
        }
    }

    @Override
    public boolean matches(CharSequence cs, String string) {
        return encode(cs).equals(string);
    }
}
