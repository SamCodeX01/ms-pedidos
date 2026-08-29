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
    public PedidoService(PedidoRepository repository) {
        this.pedidoRepository = repository;
    }

    //Metodo para cadastrar produto
    public PedidoResponseDto cadastrarPedido(PedidoRequestDto pedidoDto){
        Pedido pedido = new Pedido(); // 1. Cria um pedido vazio
        BeanUtils.copyProperties(pedidoDto, pedido); // 2. Copia os dados do DTO para o pedido
        Status status = Status.AGUARDANDO_PAGAMENTO; // 3. Define que o status inicial é aguardando pagamento
        pedido.setStatus(status);
        pedido.setData(LocalDate.now()); // 4. Coloca a data de hoje no pedido
        pedido.calcularTotal(); // 5. Roda a lógica para somar o valor total
        pedidoRepository.save(pedido); // 6. Salva tudo no banco de dados

        return new PedidoResponseDto(
                pedido.getId(), pedido.getStatus(),
                pedido.getCpf(), pedido.getItens(),
                pedido.getValorTotal(), pedido.getData());
    }

    //Metodo para visualizar todos os pedidos, buscando todos os itens no banco e convertendo-os para PedidoResponseDto.
    public List<PedidoResponseDto> obterTodos(){
        return pedidoRepository.findAll().stream() // 1. Busca tudo no banco e abre uma "esteira de produção" (Stream)
                .map(pedido -> new PedidoResponseDto( // 2. Transforma cada pedido do banco em um PedidoResponseDto
                        pedido.getId(),
                        pedido.getStatus(),
                        pedido.getCpf(),
                        pedido.getItens(),
                        pedido.getValorTotal(),
                        pedido.getData()))
                .collect(Collectors.toList()); // 3. Junta tudo de volta em uma Lista comum e retorna
    }

}
