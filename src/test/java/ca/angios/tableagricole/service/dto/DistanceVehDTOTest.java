package ca.angios.tableagricole.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class DistanceVehDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DistanceVehDTO.class);
        DistanceVehDTO distanceVehDTO1 = new DistanceVehDTO();
        distanceVehDTO1.setId(1L);
        DistanceVehDTO distanceVehDTO2 = new DistanceVehDTO();
        assertThat(distanceVehDTO1).isNotEqualTo(distanceVehDTO2);
        distanceVehDTO2.setId(distanceVehDTO1.getId());
        assertThat(distanceVehDTO1).isEqualTo(distanceVehDTO2);
        distanceVehDTO2.setId(2L);
        assertThat(distanceVehDTO1).isNotEqualTo(distanceVehDTO2);
        distanceVehDTO1.setId(null);
        assertThat(distanceVehDTO1).isNotEqualTo(distanceVehDTO2);
    }
}
