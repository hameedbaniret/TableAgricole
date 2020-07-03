package ca.angios.tableagricole.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class OrganismeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganismeDTO.class);
        OrganismeDTO organismeDTO1 = new OrganismeDTO();
        organismeDTO1.setId(1L);
        OrganismeDTO organismeDTO2 = new OrganismeDTO();
        assertThat(organismeDTO1).isNotEqualTo(organismeDTO2);
        organismeDTO2.setId(organismeDTO1.getId());
        assertThat(organismeDTO1).isEqualTo(organismeDTO2);
        organismeDTO2.setId(2L);
        assertThat(organismeDTO1).isNotEqualTo(organismeDTO2);
        organismeDTO1.setId(null);
        assertThat(organismeDTO1).isNotEqualTo(organismeDTO2);
    }
}
