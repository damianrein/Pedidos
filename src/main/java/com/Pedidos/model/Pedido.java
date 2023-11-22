package com.Pedidos.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.Pedidos.Dto.PedidoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="pedidos")
@Data
public class Pedido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy="pedido" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonProperty("productoPedido")
	private List<ProductoPedidos> productoPedido;
	@ManyToOne(targetEntity = Cliente.class)
	//@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	@JsonIgnore
	private Cliente cliente;
	private LocalDate fecha = LocalDate.now();
	
	private Double montoTotal;
	
	public Double montoAPagar(List<ProductoPedidos> productos) {
		double total = 0;
		for(ProductoPedidos pro : productos) {
			
			total = total + (pro.getPrecio()*pro.getCantidad());
		}
		return total;
	}
	
	public Pedido(){}

	public Pedido(Long id, List<ProductoPedidos> productoPedido, Cliente cliente, LocalDate fecha, Double montoTotal) {
		this.id = id;
		this.productoPedido = productoPedido;
		this.cliente = cliente;
		this.fecha = fecha;
		this.montoTotal = montoAPagar(productoPedido);
	}
	
	public PedidoDto pedidoADto (Pedido pedido) {
		PedidoDto dto = new PedidoDto();
		dto.setId(pedido.getId());
		dto.setCliente(pedido.getCliente());
		dto.setFecha(pedido.getFecha());
		dto.setMontoTotal(pedido.getMontoTotal());
		return dto;
	}
	
/*
 * 	public List<ProductoPedidos> dtoToEntityPedido (ProductoPedidosDto dto) {
		List<ProductoPedidos> entity = dto.productos();
		return entity;
	}
 * */
	
}