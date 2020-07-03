package ca.angios.tableagricole.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class DistanceVehTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DistanceVeh.class);
        DistanceVeh distanceVeh1 = new DistanceVeh();
        distanceVeh1.setId(1L);
        DistanceVeh distanceVeh2 = new DistanceVeh();
        distanceVeh2.setId(distanceVeh1.getId());
        assertThat(distanceVeh1).isEqualTo(distanceVeh2);
        distanceVeh2.setId(2L);
        assertThat(distanceVeh1).isNotEqualTo(distanceVeh2);
        distanceVeh1.setId(null);
        assertThat(distanceVeh1).isNotEqualTo(distanceVeh2);
    }
}
