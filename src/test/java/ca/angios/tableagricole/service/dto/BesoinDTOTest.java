package ca.angios.tableagricole.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BesoinDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BesoinDTO.class);
        BesoinDTO besoinDTO1 = new BesoinDTO();
        besoinDTO1.setId(1L);
        BesoinDTO besoinDTO2 = new BesoinDTO();
        assertThat(besoinDTO1).isNotEqualTo(besoinDTO2);
        besoinDTO2.setId(besoinDTO1.getId());
        assertThat(besoinDTO1).isEqualTo(besoinDTO2);
        besoinDTO2.setId(2L);
        assertThat(besoinDTO1).isNotEqualTo(besoinDTO2);
        besoinDTO1.setId(null);
        assertThat(besoinDTO1).isNotEqualTo(besoinDTO2);
    }
}
