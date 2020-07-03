package ca.angios.tableagricole.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BesoinMapperTest {
    private BesoinMapper besoinMapper;

    @BeforeEach
    public void setUp() {
        besoinMapper = new BesoinMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(besoinMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(besoinMapper.fromId(null)).isNull();
    }
}
