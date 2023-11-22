package com.Pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pedidos.Dto.ProductoPedidosDto;
import com.Pedidos.model.Producto;
import com.Pedidos.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	public ProductoController(@Autowired ProductoService productoService) {
		this.productoService = productoService;
	}
	
	@PostMapping("/add")
	@Operation(description="Agrega un producto a lo que seria el inventario")
	public ResponseEntity<?> addProducto(@RequestBody Producto pro){
		productoService.createProducto(pro);
		return ResponseEntity.created(null).build();
	}
	
	@GetMapping("/all")
	@Operation(description="Devuelve todos los productos que existen en el inventario")
	public ResponseEntity<List<ProductoPedidosDto>> getAllProductos(){
		List<Producto> pro = productoService.allProductos();
		List<ProductoPedidosDto> dto = pro.stream().map(p->p.productoToDto(p)).toList();
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/{id}")
	@Operation(description="Devuelve un producto por id")
	public ResponseEntity<Producto> findProductoId(@PathVariable Long id){
		Producto p = productoService.findProductoById(id);
		return ResponseEntity.ok(p);
	}
	
	@DeleteMapping("/{id}")
	@Operation(description="Elimina un producto del inventario por id")
	public ResponseEntity<?> deleteProductoId(@PathVariable Long id){
		productoService.deleteProductoById(id);
		return ResponseEntity.noContent().build();
	}
}
