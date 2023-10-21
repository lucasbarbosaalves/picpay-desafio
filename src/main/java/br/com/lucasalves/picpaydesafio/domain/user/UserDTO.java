package br.com.lucasalves.picpaydesafio.domain.user;

import br.com.lucasalves.picpaydesafio.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(
        String name,
        String document,
        BigDecimal balance,
        String email,
        String password,
        UserType userType
) {
}
