package ca.angios.tableagricole.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class OrganismeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Organisme.class);
        Organisme organisme1 = new Organisme();
        organisme1.setId(1L);
        Organisme organisme2 = new Organisme();
        organisme2.setId(organisme1.getId());
        assertThat(organisme1).isEqualTo(organisme2);
        organisme2.setId(2L);
        assertThat(organisme1).isNotEqualTo(organisme2);
        organisme1.setId(null);
        assertThat(organisme1).isNotEqualTo(organisme2);
    }
}
