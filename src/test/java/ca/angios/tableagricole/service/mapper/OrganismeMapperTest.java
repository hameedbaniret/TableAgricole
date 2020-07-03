package ca.angios.tableagricole.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrganismeMapperTest {
    private OrganismeMapper organismeMapper;

    @BeforeEach
    public void setUp() {
        organismeMapper = new OrganismeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(organismeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(organismeMapper.fromId(null)).isNull();
    }
}
