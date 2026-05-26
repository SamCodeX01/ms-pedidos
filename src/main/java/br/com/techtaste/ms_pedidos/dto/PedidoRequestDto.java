package br.com.techtaste.ms_pedidos.dto;

import br.com.techtaste.ms_pedidos.model.ItemPedido;

import java.util.List;

public record PedidoRequestDto(String cpf, List<ItemPedido> itens) {
    /*Analisando nossa classe Pedido, por exemplo, ao cadastrar um novo pedido, não é nossa responsabilidade informar id, o status, o valor total e a data.
    Idealmente, só precisamos informar o cpf e a lista de itens que estamos pedindo. */


    /*O record em Java é um tipo especial de classe criado para armazenar dados de forma simples e concisa.
    Ele elimina a necessidade de escrever código repetitivo (boilerplate), gerando automaticamente os métodos essenciais para objetos de valor.

    Ao declarar um record, o compilador Java cria automaticamente para você: Campos (atributos) private final: Tornando os dados obrigatoriamente imutáveis,
        * Construtor canônico: Um construtor que recebe todos os parâmetros.
        * Métodos acessores: Em vez de usar getNome(), você usa nome() diretamente (sem o prefixo get).
        * Métodos utilitários: Implementações prontas e adequadas de equals(), hashCode() e toString().
    Os records são ideais para modelar dados imutáveis e objetos sem comportamentos complexos,
    sendo a escolha perfeita para:
        * DTOs (Data Transfer Objects): Para transitar dados entre camadas de uma aplicação ou consumir/retornar payloads em APIs.
        * Armazenamento estruturado: Guardar resultados de consultas de banco de dados ou parâmetros de configuração.*/
}



/* DTO (Data Transfer Object)
O que é: Um objeto simples (sem lógica) usado para transportar dados entre o Controller e o cliente (ou entre Controller e Service).
Evita expor a entidade Model diretamente na API.*/