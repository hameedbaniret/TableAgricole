package ca.angios.tableagricole.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DemandeMapperTest {
    private DemandeMapper demandeMapper;

    @BeforeEach
    public void setUp() {
        demandeMapper = new DemandeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(demandeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(demandeMapper.fromId(null)).isNull();
    }
}
