package ca.angios.tableagricole.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TypeAlimentMapperTest {
    private TypeAlimentMapper typeAlimentMapper;

    @BeforeEach
    public void setUp() {
        typeAlimentMapper = new TypeAlimentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeAlimentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeAlimentMapper.fromId(null)).isNull();
    }
}
