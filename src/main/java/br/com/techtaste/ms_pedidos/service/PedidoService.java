package br.com.techtaste.ms_pedidos.service;

import br.com.techtaste.ms_pedidos.dto.PedidoRequestDto;
import br.com.techtaste.ms_pedidos.dto.PedidoResponseDto;
import br.com.techtaste.ms_pedidos.model.Pedido;
import br.com.techtaste.ms_pedidos.model.Status;
import br.com.techtaste.ms_pedidos.repository.PedidoRepository;
import jakarta.persistence.metamodel.ListAttribute;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

//✅ Correto em parte: O Service contém as regras de negócio. Quanto ao DTO:

@Service//@Service, o Spring registra essa classe como um bean gerenciado no contexto da aplicação.Isso significa que o Spring criará uma instância (singleton, por padrão) da classe e irá gerenciá-la automaticamente. singleton garante a existência de apenas uma única instância
public class PedidoService {//Aqui no service é onde vamos implementar os métodos para criação e consulta de um pedido.
    private PedidoRepository pedidoRepository;

    //Injeção de dependência, para ter acesso as informações de PedidoRepository. Esse processo pode ser feito utilizando a anotação @Autowired ou então via construtor.
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }


    //Metodo para cadastrar produto cadastrar o produto
    public PedidoResponseDto cadastrarPedido(PedidoRequestDto pedidoDto){
        Pedido pedido = new Pedido();
        BeanUtils.copyProperties(pedidoDto, pedido);
        Status status = Status.AGUARDANDO_PAGAMENTO;
        pedido.setStatus(status);
        pedido.setData(LocalDate.now());
        pedido.calcularTotal();
        repository.save(pedido);
        return new PedidoResponseDto(pedido.getId(), pedido.getStatus(),
                pedido.getCpf(), pedido.getItens(), pedido.getValorTotal(), pedido.getData());
    }

    public void calcularTotal(){
        this.valorTotal = this.itens.stream()
                .map(i -> i.getValorUnitario()
                .multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //Metodo para visualizar todos os pedidos, buscando todos os itens no banco e convertendo-os para PedidoResponseDto.
    public List<PedidoResponseDto> obterTodos(){
        return pedidoRepository.findAll().stream()
                .map(pedido -> new PedidoResponseDto(
                        pedido.getId(),
                        pedido.getStatus(),
                        pedido.getCpf(),
                        pedido.getItens(),
                        pedido.getValorTotal(),
                        pedido.getData()))
                .collect(Collectors.toList());
    }

}
