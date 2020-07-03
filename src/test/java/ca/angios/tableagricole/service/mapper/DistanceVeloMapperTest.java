package ca.angios.tableagricole.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DistanceVeloMapperTest {
    private DistanceVeloMapper distanceVeloMapper;

    @BeforeEach
    public void setUp() {
        distanceVeloMapper = new DistanceVeloMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(distanceVeloMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(distanceVeloMapper.fromId(null)).isNull();
    }
}
