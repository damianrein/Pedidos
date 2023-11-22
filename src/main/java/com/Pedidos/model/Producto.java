package com.Pedidos.model;

import java.io.Serializable;

import com.Pedidos.Dto.ProductoPedidosDto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Data
@Table(name="productos")
public class Producto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nombre;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	private String marca;
	@PositiveOrZero
	private Integer cantidad;
	@PositiveOrZero
	private Double precio;
	
	public ProductoPedidosDto productoToDto(Producto p) {
		ProductoPedidosDto dto = new ProductoPedidosDto();
		dto.setNombre(p.getNombre());
		dto.setCategoria((p.getCategoria().name()));
		dto.setMarca(p.getMarca());
		dto.setCantidad(p.getCantidad());
		dto.setPrecio(p.getPrecio());
		return dto;
	}

}
//@ManyToMany(fetch = FetchType.LAZY, mappedBy = "productoPedido")
//private List<Pedido> pedidos;