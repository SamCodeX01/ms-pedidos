package br.com.techtaste.ms_pedidos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//Configura a geração automática do valor do ID.
    private UUID id; //UUID = tipo de dado que representa um identificador único universal (Universally Unique Identifier) de 128 bits, gerado aleatoriamente ou a partir de informações como timestamp e endereço MAC. Exemplo: 550e8400-e29b-41d4-a716-446655440000, evita conflitos em sistemas distribuídos, pois a chance de dois UUIDs serem iguais é praticamente nula, muito usado em bancos de dados, chaves primárias de tabelas, sessões, mensageria, etc.
    private String cpf;
    private LocalDate data;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)//mappedBy = "Oi JPA, não invente tabela nova. Olha ali no outro lado (na classe Item, no campo pedido), pega a chave estrangeira que já tem lá."
    private List<ItemPedido> itens = new ArrayList<>();
    private BigDecimal valorTotal;//O tipo BigDecimal é utilizado para evitar problemas de precisão em cálculos financeiros.
    @Enumerated(EnumType.STRING)//armazena no banco com o nome da constante do enum como string (por exemplo, PENDENTE, FINALIZADO), em vez de um índice ordinal.
    private Status status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        itens.forEach(i -> i.setPedido(this));
        this.itens = itens;
    }



    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
/*Para que serve mappedBy?

mappedBy serve para avisar o JPA:

"Ei, não crie uma tabela extra!
A relação já está mapeada no outro lado,
no atributo chamado pedido dentro da classe Item.
Use a chave estrangeira que está lá."

Sem mappedBy, o JPA cria uma 3° tabela de ligação desnecessária.

O JPA precisa saber onde está a chave estrangeira (a informação de quem é filho de quem).
Se você não colocar mappedBy, o JPA pensa:
"Ah, não me disseram nada, então vou criar uma tabela separada só para guardar o relacionamento (tabela MAE_FILHO com duas colunas: mãe_id e filho_id)."

Regra de ouro para lembrar:
Lado @OneToMany → sempre coloque mappedBy + o nome do atributo que está na classe do @ManyToOne.

Lado @ManyToOne → não coloque mappedBy (ele não existe para esse lado).

*/