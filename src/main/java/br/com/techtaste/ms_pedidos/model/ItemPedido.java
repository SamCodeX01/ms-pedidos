package br.com.techtaste.ms_pedidos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.math.BigDecimal;

public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valorUnitario;
    private Integer quantidade;
    @ManyToMany //@ManyToOne define o relacionamento muitos para um, onde muitos itens de pedido serão associados a um pedido.
    @JsonIgnore //serve para ocultar um campo ou propriedade durante a conversão (serialização e desserialização) de objetos para JSON, é primordial incluir a anotação @JsonIgnore pois o Jackson pode entrar em loop infinito ao tentar serializar as entidades.
    private Pedido pedido;
}
