package br.com.villaca.arte.dto.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderMapper {

    private final PasswordEncoder encoder;

    public PasswordEncoderMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encode(String senha) {
        return encoder.encode(senha);
    }
}