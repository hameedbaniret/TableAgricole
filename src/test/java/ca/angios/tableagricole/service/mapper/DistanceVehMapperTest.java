package ca.angios.tableagricole.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DistanceVehMapperTest {
    private DistanceVehMapper distanceVehMapper;

    @BeforeEach
    public void setUp() {
        distanceVehMapper = new DistanceVehMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(distanceVehMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(distanceVehMapper.fromId(null)).isNull();
    }
}
