package com.example.technovium.api.pedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.technovium.api.tarjetas.TarjetaService;
import com.example.technovium.persistence.model.DetallePedidoEntity;
import com.example.technovium.persistence.model.MetodoPagoEntity;
import com.example.technovium.persistence.model.PedidoEntity;
import com.example.technovium.persistence.model.TarjetaEntity;
import com.example.technovium.persistence.repository.DetallePedidoRepository;
import com.example.technovium.persistence.repository.MetodoPagoRepository;
import com.example.technovium.persistence.repository.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private DetallePedidoFactory detallePedidoFactory;

    @Transactional
    public EstadoNuevoPedido nuevoPedido(NuevoPedido nuevoPedido) {
        //Se inicia un nuevo estado pedido donde almacenaremos los estados de distrintos procesos.
        EstadoNuevoPedido estadoNuevoPedido = new EstadoNuevoPedido();

        //Se inicializan los estados que agregaremos.
        Boolean pedidoAgregado = false;
        String estadoPedido = "";

        Boolean tarjetaExiste = false;
        String estadoTarjeta = "";

        Boolean detallesAgregados = false;
        String estadoDetallesPedido = "";

        //Se obtiene el folio y otros objetos necesarios para la persistencia y lógica del código.
        String folio = generarFolio();

        Pedido pedido = nuevoPedido.getPedido();
        List<DetalleProducto> detallesProductos = nuevoPedido.getDetalleProducto();

        MetodoPagoEntity metodoPagoEntity = metodoPagoRepository.obtenerPorId(pedido.getIdMetodoPago());
        TarjetaEntity tarjetaEntity = new TarjetaEntity();

        /*
         * Se guarda pedido dependiendo de si existe o no tarjeta en el objeto.
         */

        if(pedido.getTarjeta() != null){
            Tarjeta tarjeta = pedido.getTarjeta();
            tarjetaEntity = tarjetaService.guardarTarjeta(tarjeta);

            if(tarjetaEntity != null){
                pedidoRepository.registrarPedido(
                    pedido.getIdUsuario(),
                    pedido.getIdDireccion(),
                    metodoPagoEntity.getIdMetodoPago(),
                    tarjetaEntity.getIdTarjeta(),
                    pedido.getTotal(),
                    folio,
                    EstadoPedidoConst.PREPARACION_ENVIO);          
                
                //Se obtiene el estado del pedido
                tarjetaExiste = true;
                estadoTarjeta = EstadoNuevoPedidoConst.GUARDADO_CORRECTAMENTE;    

                //Se agrega nuevo estado de TARJETA
                estadoNuevoPedido.setSeAgregoTarjeta(tarjetaExiste);
                estadoNuevoPedido.setEstadoTerjeta(estadoTarjeta);            
            }else{
                //Se obtiene el estado del pedido
                tarjetaExiste = false;
                estadoTarjeta = EstadoNuevoPedidoConst.ERROR_AL_GUARDAR_TARJETA;

                //Se agrega nuevo estado de TARJETA
                estadoNuevoPedido.setSeAgregoTarjeta(tarjetaExiste);
                estadoNuevoPedido.setEstadoTerjeta(estadoTarjeta);    
            }
      
        }else{

            pedidoRepository.registrarPedido(
                    pedido.getIdUsuario(),
                    pedido.getIdDireccion(),
                    metodoPagoEntity.getIdMetodoPago(),
                    null,
                    pedido.getTotal(),
                    folio,
                    EstadoPedidoConst.PREPARACION_ENVIO);            
            
            //Se obtiene el estado del pedido
            tarjetaExiste = false;
            estadoTarjeta = EstadoNuevoPedidoConst.NO_ES_PAGO_CON_TARJETA;
            
            //Se agrega nuevo estado de TARJETA
            estadoNuevoPedido.setSeAgregoTarjeta(tarjetaExiste);
            estadoNuevoPedido.setEstadoTerjeta(estadoTarjeta);  
        }

        //Nos aseguramos de haber guardado correctamente el pedido. 
        PedidoEntity pedidoEntity = pedidoRepository.encontrarPorFolio(folio);

        //Si se guardó correctamente y no es null entonces iniciará el proceso de agregar los insert en la tabla detalle_pedidos
        if(pedidoEntity != null){
            pedidoAgregado = true;
            estadoPedido = EstadoNuevoPedidoConst.PEDIDO_GUARDADO;

            estadoNuevoPedido.setSeAgregoPedido(pedidoAgregado);
            estadoNuevoPedido.setEstadoPedido(estadoPedido);

            List<DetallePedidoEntity> detallePedidosEntities = new ArrayList<>();

            for (DetalleProducto detalleProducto : detallesProductos) {
                String folioDetallePedido = generarFolio();

                detallePedidoRepository.agregarDetallePedido(pedidoEntity.getIdPedido(), detalleProducto.getIdProducto(), detalleProducto.getCantidad(), folioDetallePedido);
                DetallePedidoEntity detallePedidoEntity = detallePedidoRepository.encontrarPorFolio(folioDetallePedido);

                detallePedidosEntities.add(detallePedidoEntity);
            }

            if(!detallePedidosEntities.isEmpty()){
                detallesAgregados = true;
                estadoDetallesPedido = EstadoNuevoPedidoConst.DETALLES_AGREGADOS_CORRECTAMENTE;

                estadoNuevoPedido.setSeAgreganDetallesPedidos(detallesAgregados);
                estadoNuevoPedido.setEstadoDetallePedido(estadoDetallesPedido);
            }else{
                detallesAgregados = false;
                estadoDetallesPedido = EstadoNuevoPedidoConst.ERROR_AL_AGREGAR_DETALLES;

                estadoNuevoPedido.setSeAgreganDetallesPedidos(detallesAgregados);
                estadoNuevoPedido.setEstadoDetallePedido(estadoDetallesPedido);
            }

        }else{
            pedidoAgregado = false;
            estadoPedido = EstadoNuevoPedidoConst.PEDIDO_GUARDADO_INCORRECTAMENTE;
            detallesAgregados = false;
            estadoDetallesPedido = EstadoNuevoPedidoConst.ERROR_AL_AGREGAR_DETALLES_SIN_PEDIDO;

            estadoNuevoPedido.setSeAgregoPedido(pedidoAgregado);
            estadoNuevoPedido.setEstadoPedido(estadoPedido);
            estadoNuevoPedido.setSeAgreganDetallesPedidos(detallesAgregados);
            estadoNuevoPedido.setEstadoDetallePedido(estadoDetallesPedido);
        }

        //Y después de todo, se regresa el objeto EstadoNuevoPedido.
        estadoNuevoPedido.setSeAgregoPedidoCorrectamente(detallesAgregados);
        return estadoNuevoPedido;
    }

    public String generarFolio(){
        UUID uuid = UUID.randomUUID();

        String folio = uuid.toString().substring(0, 7);

        return folio;
    }

    public List<DetallePedido> misPedidos(Integer idUsuario) {
        List<PedidoEntity> pedidos = pedidoRepository.encontrarEntitiesPorUsuario(idUsuario);

        List<DetallePedido> consultaPedidos = detallePedidoFactory.toObjects(pedidos);

        return consultaPedidos;
    }

    public List<DetallePedido> todosLosPedidos() {
        List<PedidoEntity> pedidos = pedidoRepository.findAll();

        List<DetallePedido> consultaPedidos = detallePedidoFactory.toObjects(pedidos);

        return consultaPedidos;
    }

}
