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