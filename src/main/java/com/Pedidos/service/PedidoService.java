package com.Pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Pedidos.Dto.PedidoDto;
import com.Pedidos.model.Cliente;
import com.Pedidos.model.Pedido;
import com.Pedidos.model.ProductoPedidos;
import com.Pedidos.repository.IClienteRepository;
import com.Pedidos.repository.IPedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private IPedidoRepository pedidoRepo;
	@Autowired
	private IClienteRepository clienteRepo;
	@Autowired
	private ProductoService productoService;

	public PedidoService(@Autowired IPedidoRepository pedidoRepo,@Autowired IClienteRepository clienteRepo,@Autowired ProductoService productoService) {
		this.pedidoRepo = pedidoRepo;
		this.clienteRepo = clienteRepo;
		this.productoService = productoService;
	}
	
	public Page<Pedido> allPedidosPag(Pageable page){
		return pedidoRepo.findAll(page);
	}
	
	public void createPedido(Pedido pedido) {
		pedidoRepo.save(pedido);
	}
	
	public List<Pedido> allPedidos(){
		return pedidoRepo.findAll();
	}
	
	public void addPedido(List<ProductoPedidos> productos, Long idCliente) {
		Cliente cli = clienteRepo.findById(idCliente).get();
		Pedido pedido = new Pedido();
		pedido.setCliente(cli);
		pedido.setProductoPedido(productos);
		pedido.setMontoTotal(pedido.montoAPagar(productos));
		productos.forEach(p-> p.setPedido(pedido));
		pedidoRepo.save(pedido);
	}
	
	public List<PedidoDto> allPedidos2(){
		return pedidoRepo.findAllPedido();
	}
	
	public void descontarCantidadDeProductos(List<ProductoPedidos> pp) {
		
		for(ProductoPedidos p : pp) {
			String nombre = p.getNombre();
			Integer cantidad = p.getCantidad();
			productoService.descontarCantidad(nombre, cantidad);
		}
	}
}
