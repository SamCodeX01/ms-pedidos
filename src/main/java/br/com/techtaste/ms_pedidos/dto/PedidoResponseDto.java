package br.com.techtaste.ms_pedidos.dto;

import br.com.techtaste.ms_pedidos.model.ItemPedido;
import br.com.techtaste.ms_pedidos.model.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDto(UUID id,
                                Status status,
                                String cpf,
                                List<ItemPedido> itens,
                                BigDecimal valorTotaL,
                                LocalDate data) {

    /*Já a resposta, ou confirmação do pedido,
    ou até mesmo a consulta por uma lista grande de pedidos,
    queremos as informações completas. */
}


/* DTO (Data Transfer Object)
O que é: Um objeto simples (sem lógica) usado para transportar dados entre o Controller e o cliente (ou entre Controller e Service).
Evita expor a entidade Model diretamente na API.*/



/*No padrão DTO (Data Transfer Object) em Java, o request é o dado enviado pelo cliente (como o Front-end, aplicativo ou Postman) para o servidor (Back-end),
enquanto o response é o dado enviado de volta pelo servidor para o cliente.*/


//https://share.google/aimode/vYdXJwVbF7fSRC3tN

//https://www.linkedin.com/pulse/dtos-em-java-diferentes-abordagens-e-minha-vis%C3%A3o-pessoal-lucas-aita-fwgef/