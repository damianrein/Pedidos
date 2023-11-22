package com.Pedidos.model;

import java.io.Serializable;

import com.Pedidos.Dto.ProductoPedidosDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Table(name="ProductoPedidos")
@Data
public class ProductoPedidos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nombre;
	
	private String categoria;
	
	private String marca;
	@PositiveOrZero
	private Integer cantidad;
	@PositiveOrZero
	private Double precio;
	@ManyToOne(targetEntity = Pedido.class, fetch = FetchType.LAZY )
	@JsonBackReference
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
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
