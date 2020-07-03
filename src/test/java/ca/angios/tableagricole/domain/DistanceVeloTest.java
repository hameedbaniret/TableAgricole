package ca.angios.tableagricole.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class DistanceVeloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DistanceVelo.class);
        DistanceVelo distanceVelo1 = new DistanceVelo();
        distanceVelo1.setId(1L);
        DistanceVelo distanceVelo2 = new DistanceVelo();
        distanceVelo2.setId(distanceVelo1.getId());
        assertThat(distanceVelo1).isEqualTo(distanceVelo2);
        distanceVelo2.setId(2L);
        assertThat(distanceVelo1).isNotEqualTo(distanceVelo2);
        distanceVelo1.setId(null);
        assertThat(distanceVelo1).isNotEqualTo(distanceVelo2);
    }
}
