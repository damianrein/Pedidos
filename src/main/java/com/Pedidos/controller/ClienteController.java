package com.Pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pedidos.Dto.ClienteDto;
import com.Pedidos.model.Cliente;
import com.Pedidos.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	public ClienteController(@Autowired ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping("/create")
	@Operation(description="Registra un cliente")
	public ResponseEntity<?> createCliente(@RequestBody ClienteDto cDto){
		Cliente c = new Cliente();
		c.setNombre(cDto.getNombre());
		c.setApellido(cDto.getApellido());
		c.setEdad(cDto.getEdad());
		c.setEmail(cDto.getEmail());
		clienteService.addCliente(c);
		//return ResponseEntity.created(null).build();
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Cliente>> findAllClientes(){
		List<Cliente> clientes = clienteService.findAll();
		return ResponseEntity.ok(clientes);
	}
	
	@GetMapping("/dto")
	@Operation(description="Devuelve una lista de los clientes registrados")
	public ResponseEntity<List<ClienteDto>> findAllClientesSimples(){
		List<Cliente> clientes = clienteService.findAll();
		List<ClienteDto> Dto = clientes.stream().map(cli-> cli.clienteADto(cli)).toList();
		return ResponseEntity.ok(Dto);
	}
	
	@GetMapping("/{id}")
	@Operation(description="Busca un cliente por su id")
	public ResponseEntity<Cliente> findClienteById(@PathVariable Long id){
		Cliente c = clienteService.clienteById(id);
		return ResponseEntity.ok(c);
	}
	
	@DeleteMapping("/{id}")
	@Operation(description="Elimina un cliente por su id")
	public ResponseEntity<?> deleteClienteById(@PathVariable Long id){
		clienteService.deleteClientById(id);
		return ResponseEntity.noContent().build();
	}
}
