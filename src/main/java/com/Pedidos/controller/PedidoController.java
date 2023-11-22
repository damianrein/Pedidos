package com.Pedidos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pedidos.Dto.PedidoDto;
import com.Pedidos.Dto.ProductoPedidosDto;
import com.Pedidos.model.Pedido;
import com.Pedidos.model.ProductoPedidos;
import com.Pedidos.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	public PedidoController(@Autowired PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@PostMapping("/add/{idCliente}")
	@Transactional
	@Operation(summary="Agrega un pedido al Cliente correspondiente" ,
	description="Recibe una lista de productos(de tipo ProductoPedidosDto) y el id(de tipo Long) del cliente que realiza tal pedido.")
	public ResponseEntity<?> addPedido(@RequestBody List<ProductoPedidosDto> pro, @PathVariable Long idCliente){
		List<ProductoPedidos> prod = pro.stream().map(dto-> dto.dtoAProductoPedidos(dto)).collect(Collectors.toList());
		pedidoService.addPedido(prod, idCliente);
		pedidoService.descontarCantidadDeProductos(prod);
		return ResponseEntity.created(null).build();
	}
	
	@GetMapping("/all")
	@Operation(description="Devuelve una lista de los pedidos recibidos y el cliente que los realizo")
	public ResponseEntity<List<PedidoDto>> findAllPedidos (){
		List<Pedido> ped = pedidoService.allPedidos();
		List<PedidoDto> pedDto = ped.stream().map(p->p.pedidoADto(p)).toList();
		return ResponseEntity.ok(pedDto);
	}
	
	@GetMapping("/allPage")
	@Operation(description="Devuelve los pedidos Dto paginados")
	public Page<PedidoDto> pageOfPedidos(Pageable page){
		Page <Pedido> p = pedidoService.allPedidosPag(page);
		Page <PedidoDto> dto = p.map(pa->pa.pedidoADto(pa));
		return dto;
	}
}
