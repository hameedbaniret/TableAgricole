package ca.angios.tableagricole.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EtatFrigoMapperTest {
    private EtatFrigoMapper etatFrigoMapper;

    @BeforeEach
    public void setUp() {
        etatFrigoMapper = new EtatFrigoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etatFrigoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etatFrigoMapper.fromId(null)).isNull();
    }
}
