package com.Pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Pedidos.model.Producto;
import com.Pedidos.model.ProductoPedidos;
import com.Pedidos.repository.IProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private IProductoRepository productoRepo;
	
	public ProductoService(@Autowired IProductoRepository productoRepo) {
		this.productoRepo = productoRepo;
	}

	public void createProducto(Producto producto) {
		productoRepo.save(producto);
	}
	
	public List<Producto> allProductos(){
		return productoRepo.findAll();
	}
	
	public Producto findProductoById(Long id) {
		return productoRepo.findById(id).get();
	}
	
	public void deleteProductoById(Long id) {
		productoRepo.deleteById(id);
	}
	
	public void descontarCantidad(String nombre, Integer cantidad) {
		productoRepo.subtractProductoCantidad(nombre, cantidad);
	}
	
	public void descontarCantidadImpl(List<ProductoPedidos> pp) {

	}
}
