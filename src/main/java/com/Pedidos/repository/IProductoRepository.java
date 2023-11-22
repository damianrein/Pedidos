package com.Pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Pedidos.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

	@Modifying
	@Query("UPDATE Producto p SET p.cantidad = p.cantidad - :cantidad WHERE p.nombre = :nombre")
	public void subtractProductoCantidad(@Param("nombre") String nombre,@Param("cantidad") Integer cantidad);
}
