package ca.angios.tableagricole.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class TerritoireDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TerritoireDTO.class);
        TerritoireDTO territoireDTO1 = new TerritoireDTO();
        territoireDTO1.setId(1L);
        TerritoireDTO territoireDTO2 = new TerritoireDTO();
        assertThat(territoireDTO1).isNotEqualTo(territoireDTO2);
        territoireDTO2.setId(territoireDTO1.getId());
        assertThat(territoireDTO1).isEqualTo(territoireDTO2);
        territoireDTO2.setId(2L);
        assertThat(territoireDTO1).isNotEqualTo(territoireDTO2);
        territoireDTO1.setId(null);
        assertThat(territoireDTO1).isNotEqualTo(territoireDTO2);
    }
}
