package ca.angios.tableagricole.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class TypeAlimentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeAlimentDTO.class);
        TypeAlimentDTO typeAlimentDTO1 = new TypeAlimentDTO();
        typeAlimentDTO1.setId(1L);
        TypeAlimentDTO typeAlimentDTO2 = new TypeAlimentDTO();
        assertThat(typeAlimentDTO1).isNotEqualTo(typeAlimentDTO2);
        typeAlimentDTO2.setId(typeAlimentDTO1.getId());
        assertThat(typeAlimentDTO1).isEqualTo(typeAlimentDTO2);
        typeAlimentDTO2.setId(2L);
        assertThat(typeAlimentDTO1).isNotEqualTo(typeAlimentDTO2);
        typeAlimentDTO1.setId(null);
        assertThat(typeAlimentDTO1).isNotEqualTo(typeAlimentDTO2);
    }
}
