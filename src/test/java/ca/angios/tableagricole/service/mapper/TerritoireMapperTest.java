package ca.angios.tableagricole.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TerritoireMapperTest {
    private TerritoireMapper territoireMapper;

    @BeforeEach
    public void setUp() {
        territoireMapper = new TerritoireMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(territoireMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(territoireMapper.fromId(null)).isNull();
    }
}
