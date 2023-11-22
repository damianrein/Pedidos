package com.Pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Pedidos.model.ProductoPedidos;

public interface IItemsPedidoRepository extends JpaRepository< ProductoPedidos, Long >  {

}
