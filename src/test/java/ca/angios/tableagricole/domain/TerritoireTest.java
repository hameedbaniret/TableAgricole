package ca.angios.tableagricole.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class TerritoireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Territoire.class);
        Territoire territoire1 = new Territoire();
        territoire1.setId(1L);
        Territoire territoire2 = new Territoire();
        territoire2.setId(territoire1.getId());
        assertThat(territoire1).isEqualTo(territoire2);
        territoire2.setId(2L);
        assertThat(territoire1).isNotEqualTo(territoire2);
        territoire1.setId(null);
        assertThat(territoire1).isNotEqualTo(territoire2);
    }
}
