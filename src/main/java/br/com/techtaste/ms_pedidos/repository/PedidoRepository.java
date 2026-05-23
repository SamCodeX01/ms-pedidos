package br.com.techtaste.ms_pedidos.repository;

import br.com.techtaste.ms_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    /*A interface JpaRepository herda funcionalidades de várias interfaces do Spring Data JPA.
    Isso inclui métodos prontos para realizar operações no banco de dados,
    como consultas básicas, inserção, atualização e remoção de dados.
    Para isso, incluímos na declaração a classe que será manipulada pelo repositório,
    geralmente é que representa a entidade (anotada com @Entity), que no nosso caso é a Pedido,
    e também o tipo chave primária da mesma (o UUID).*/
}
