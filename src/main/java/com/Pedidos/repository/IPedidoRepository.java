package com.Pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Pedidos.Dto.PedidoDto;
import com.Pedidos.model.Pedido;
@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

/*
 * 	@Query("SELECT pedidos.id, pedidos.fecha , pedidos.monto_total FROM pedidos "
			+ "JOIN clientes ON pedidos.cliente_id=clientes.id"
			+ "JOIN producto_pedidos ON pedidos.id = producto_pedidos.pedido_id")
	List<Pedido> findAllPedido();
 * 
 * */
	
	@Query("SELECT new com.Pedidos.Dto.PedidoDto(pedidos.id, pedidos.fecha, pedidos.montoTotal) FROM Pedido pedidos "
			+ "JOIN pedidos.cliente cliente "
			+ "JOIN pedidos.productoPedido productoPedidos")
List<PedidoDto> findAllPedido();

}
