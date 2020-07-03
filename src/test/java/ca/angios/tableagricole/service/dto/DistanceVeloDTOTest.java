package ca.angios.tableagricole.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class DistanceVeloDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DistanceVeloDTO.class);
        DistanceVeloDTO distanceVeloDTO1 = new DistanceVeloDTO();
        distanceVeloDTO1.setId(1L);
        DistanceVeloDTO distanceVeloDTO2 = new DistanceVeloDTO();
        assertThat(distanceVeloDTO1).isNotEqualTo(distanceVeloDTO2);
        distanceVeloDTO2.setId(distanceVeloDTO1.getId());
        assertThat(distanceVeloDTO1).isEqualTo(distanceVeloDTO2);
        distanceVeloDTO2.setId(2L);
        assertThat(distanceVeloDTO1).isNotEqualTo(distanceVeloDTO2);
        distanceVeloDTO1.setId(null);
        assertThat(distanceVeloDTO1).isNotEqualTo(distanceVeloDTO2);
    }
}
