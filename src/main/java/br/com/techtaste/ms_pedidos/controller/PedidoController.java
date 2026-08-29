package br.com.techtaste.ms_pedidos.controller;

import br.com.techtaste.ms_pedidos.dto.PedidoRequestDto;
import br.com.techtaste.ms_pedidos.dto.PedidoResponseDto;
import br.com.techtaste.ms_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//✅ O Controller é o ponto de entrada HTTP e delega a lógica para o Service.

@RestController
@RequestMapping("/pedidos")//
public class PedidoController {

    @Autowired
    private PedidoService service;//Para essa classe, injetaremos a classe de serviço através da anotação @Autowired

    @PostMapping
    public ResponseEntity<PedidoResponseDto> cadastrarPedido(@RequestBody PedidoRequestDto pedidoDto){//Anotação Post Mapping, método público que retorna uma Response Entity do tipo Pedido Response Dto, chamado cadastrar Pedido, que recebe no corpo da requisição um Pedido Request Dto chamado pedido Dto.
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarPedido(pedidoDto));
    }

    @GetMapping
    public List<PedidoResponseDto> obterTodos(){
        return service.obterTodos();
    }
}


/*{
    "cpf": "123.456.789-10",
    "itens": [
        {"quantidade": 4, "descricao": "coca-cola", "valorUnitario": 8.99},
        {"quantidade": 1, "descricao": "combo sushi", "valorUnitario": 65}
    ]
}
*/