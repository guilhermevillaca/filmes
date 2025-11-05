package br.com.villaca.arte.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {

    private String login;
    private String senha;

}
