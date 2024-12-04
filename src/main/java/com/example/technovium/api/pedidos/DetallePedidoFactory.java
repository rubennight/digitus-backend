package com.example.technovium.api.pedidos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.technovium.api.domcilios.DomicilioFactory;
import com.example.technovium.api.productos.ProductoFactory;
import com.example.technovium.persistence.model.CategoriaEntity;
import com.example.technovium.persistence.model.DetallePedidoEntity;
import com.example.technovium.persistence.model.DomicilioEntity;
import com.example.technovium.persistence.model.MetodoPagoEntity;
import com.example.technovium.persistence.model.PedidoEntity;
import com.example.technovium.persistence.model.ProductoEntity;
import com.example.technovium.persistence.model.TarjetaEntity;
import com.example.technovium.persistence.model.UsuarioEntity;
import com.example.technovium.persistence.repository.CategoriaRepository;
import com.example.technovium.persistence.repository.DetallePedidoRepository;
import com.example.technovium.persistence.repository.DomicilioRepository;
import com.example.technovium.persistence.repository.MetodoPagoRepository;
import com.example.technovium.persistence.repository.ProductoRepository;
import com.example.technovium.persistence.repository.TarjetaRepository;
import com.example.technovium.persistence.repository.UsuarioRepository;

@Component
public class DetallePedidoFactory {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private DomicilioFactory domicilioFactory;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ProductoFactory productoFactory;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public DetallePedido toObject(PedidoEntity pedidoEntity){
        UsuarioEntity usuarioEntity = usuarioRepository.getReferenceById(pedidoEntity.getIdUsuario());
        DomicilioEntity domicilioEntity = domicilioRepository.getReferenceById(pedidoEntity.getIdDomicilio());
        MetodoPagoEntity metodoPagoEntity = metodoPagoRepository.obtenerPorId(pedidoEntity.getIdMetodoPago());

        List<DetallePedidoEntity> detallePedidoEntities = detallePedidoRepository.encontrarPorPedido(pedidoEntity.getIdPedido());
        List<ProductoDetalle> productos = new ArrayList<>();

        DetallePedido detallePedido = new DetallePedido();

        if(pedidoEntity.getIdTarjeta() != null){
            TarjetaEntity tarjetaEntity = tarjetaRepository.encontrarPorIdTarjeta(pedidoEntity.getIdTarjeta());
            detallePedido.setNumeroTarjeta(tarjetaEntity.getNumeroTarjeta());
        }
        
        if(pedidoEntity.getFolio() != null){
            detallePedido.setFolio(pedidoEntity.getFolio());
        }

        if(metodoPagoEntity != null){
            detallePedido.setMetodoPago(metodoPagoEntity.getDescripcion());
        }

        detallePedido.setReceptorDePedido(usuarioEntity.getNombre());
        detallePedido.setDomicilio(domicilioFactory.toObject(domicilioEntity));
        detallePedido.setTotal(pedidoEntity.getTotal());

        for (DetallePedidoEntity detallePedidoEntity : detallePedidoEntities) {
            ProductoEntity productoEntity = productoRepository.getReferenceById(detallePedidoEntity.getIdProducto());
            
            CategoriaEntity categoriaEntity = categoriaRepository.getReferenceById(productoEntity.getCategoria());
            String categoria = categoriaEntity.getNombreCategoria();

            ProductoDetalle productoDetalle = productoFactory.toObject(productoEntity, categoria, detallePedidoEntity.getCantidad());

            productos.add(productoDetalle);
        }

        detallePedido.setProductos(productos);

        return detallePedido;
    }

    public List<DetallePedido> toObjects(List<PedidoEntity> pedidoEntities){
        List<DetallePedido> detallePedidos = new ArrayList<>();

        for (PedidoEntity pedidoEntity : pedidoEntities) {
            DetallePedido detallePedido = toObject(pedidoEntity);
            detallePedidos.add(detallePedido);
        }

        return detallePedidos;
    }

}
