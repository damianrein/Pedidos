package com.Pedidos.model;

import java.io.Serializable;
import java.util.List;

import com.Pedidos.Dto.ClienteDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="clientes")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private Integer edad;
	@Column(unique = true)
	private String email;
	@OneToMany(mappedBy="cliente" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JsonIgnoreProperties
	private List<Pedido> pedido;
	
	public ClienteDto clienteADto(Cliente c) {
		ClienteDto dto = new ClienteDto();
		dto.setNombre(c.getNombre());
		dto.setApellido(c.getApellido());
		dto.setEdad(c.getEdad());
		dto.setEmail(c.getEmail());
		return dto;
	}
}
