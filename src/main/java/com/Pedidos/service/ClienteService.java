package com.Pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Pedidos.model.Cliente;
import com.Pedidos.repository.IClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private IClienteRepository clienteRepo;

	public ClienteService(@Autowired IClienteRepository clienteRepo) {
		this.clienteRepo = clienteRepo;
	}
	
	public List<Cliente> findAll(){
		return clienteRepo.findAll();
	}
	
	public Cliente clienteById(Long id) {
		return clienteRepo.findById(id).get();
	}
	
	public void addCliente (Cliente c) {
		clienteRepo.save(c);
	}
	
	public void deleteClientById(Long id) {
		clienteRepo.deleteById(id);
	}
}
