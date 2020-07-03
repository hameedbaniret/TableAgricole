package ca.angios.tableagricole.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class EtatFrigoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatFrigoDTO.class);
        EtatFrigoDTO etatFrigoDTO1 = new EtatFrigoDTO();
        etatFrigoDTO1.setId(1L);
        EtatFrigoDTO etatFrigoDTO2 = new EtatFrigoDTO();
        assertThat(etatFrigoDTO1).isNotEqualTo(etatFrigoDTO2);
        etatFrigoDTO2.setId(etatFrigoDTO1.getId());
        assertThat(etatFrigoDTO1).isEqualTo(etatFrigoDTO2);
        etatFrigoDTO2.setId(2L);
        assertThat(etatFrigoDTO1).isNotEqualTo(etatFrigoDTO2);
        etatFrigoDTO1.setId(null);
        assertThat(etatFrigoDTO1).isNotEqualTo(etatFrigoDTO2);
    }
}
