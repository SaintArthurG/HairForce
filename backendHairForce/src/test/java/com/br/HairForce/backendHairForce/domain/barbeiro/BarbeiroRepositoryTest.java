package com.br.HairForce.backendHairForce.domain.barbeiro;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class BarbeiroRepositoryTest {

    @BeforeAll
    static void carregarDotenv() {
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private EntityManager em;


    @Test
    @DisplayName("Deverar retornar not null, barbeiro que nao tem horario marcado com nenhum usuario")
    void encontrarBarbeiroLivre() {
        var dados = new DadosCadastroBarbeiro("Pedro");
        var barbeiro = new Barbeiro(dados);
        em.persist(barbeiro);

        var barbeiroLivre = barbeiroRepository.encontrarBarbeiroLivre("11:00");
        assertThat(barbeiroLivre).isNotNull();
    }

}