package ca.angios.tableagricole.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class AlimentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlimentDTO.class);
        AlimentDTO alimentDTO1 = new AlimentDTO();
        alimentDTO1.setId(1L);
        AlimentDTO alimentDTO2 = new AlimentDTO();
        assertThat(alimentDTO1).isNotEqualTo(alimentDTO2);
        alimentDTO2.setId(alimentDTO1.getId());
        assertThat(alimentDTO1).isEqualTo(alimentDTO2);
        alimentDTO2.setId(2L);
        assertThat(alimentDTO1).isNotEqualTo(alimentDTO2);
        alimentDTO1.setId(null);
        assertThat(alimentDTO1).isNotEqualTo(alimentDTO2);
    }
}
