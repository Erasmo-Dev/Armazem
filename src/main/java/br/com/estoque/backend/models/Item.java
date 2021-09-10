package br.com.estoque.backend.models;

import br.com.estoque.backend.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Audited
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false, length = 3)
    private float quantidade;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Armazem armazem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria tipo;

}
