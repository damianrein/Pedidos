package com.Pedidos.Dto;

import com.Pedidos.model.ProductoPedidos;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoPedidosDto {

	private String nombre;
	
	private String categoria;
	
	private String marca;
	@PositiveOrZero
	private Integer cantidad;
	@PositiveOrZero
	private Double precio;
	
	public ProductoPedidos dtoAProductoPedidos(ProductoPedidosDto dto) {
		ProductoPedidos p = new ProductoPedidos();
		p.setNombre(dto.getNombre());
		p.setCategoria(dto.getCategoria());
		p.setMarca(dto.getMarca());
		p.setCantidad(dto.getCantidad());
		p.setPrecio(dto.getPrecio());
		return p;
	}
}
