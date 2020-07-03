package ca.angios.tableagricole.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlimentMapperTest {
    private AlimentMapper alimentMapper;

    @BeforeEach
    public void setUp() {
        alimentMapper = new AlimentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(alimentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(alimentMapper.fromId(null)).isNull();
    }
}
