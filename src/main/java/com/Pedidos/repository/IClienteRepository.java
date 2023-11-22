package com.Pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Pedidos.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long>  {

}
