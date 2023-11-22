package com.Pedidos.Dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDto {

	private String nombre;
	private String apellido;
	private Integer edad;
	@Email
	private String email;
}
