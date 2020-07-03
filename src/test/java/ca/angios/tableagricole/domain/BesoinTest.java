package ca.angios.tableagricole.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BesoinTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Besoin.class);
        Besoin besoin1 = new Besoin();
        besoin1.setId(1L);
        Besoin besoin2 = new Besoin();
        besoin2.setId(besoin1.getId());
        assertThat(besoin1).isEqualTo(besoin2);
        besoin2.setId(2L);
        assertThat(besoin1).isNotEqualTo(besoin2);
        besoin1.setId(null);
        assertThat(besoin1).isNotEqualTo(besoin2);
    }
}
