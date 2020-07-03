package ca.angios.tableagricole.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class EtatFrigoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatFrigo.class);
        EtatFrigo etatFrigo1 = new EtatFrigo();
        etatFrigo1.setId(1L);
        EtatFrigo etatFrigo2 = new EtatFrigo();
        etatFrigo2.setId(etatFrigo1.getId());
        assertThat(etatFrigo1).isEqualTo(etatFrigo2);
        etatFrigo2.setId(2L);
        assertThat(etatFrigo1).isNotEqualTo(etatFrigo2);
        etatFrigo1.setId(null);
        assertThat(etatFrigo1).isNotEqualTo(etatFrigo2);
    }
}
