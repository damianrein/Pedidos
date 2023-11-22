package com.Pedidos.Dto;

import java.time.LocalDate;

import com.Pedidos.model.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

	private Long id;
	private Cliente cliente;
	private LocalDate fecha = LocalDate.now();
	private Double montoTotal;
}
