package ca.angios.tableagricole.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ca.angios.tableagricole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class TypeAlimentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeAliment.class);
        TypeAliment typeAliment1 = new TypeAliment();
        typeAliment1.setId(1L);
        TypeAliment typeAliment2 = new TypeAliment();
        typeAliment2.setId(typeAliment1.getId());
        assertThat(typeAliment1).isEqualTo(typeAliment2);
        typeAliment2.setId(2L);
        assertThat(typeAliment1).isNotEqualTo(typeAliment2);
        typeAliment1.setId(null);
        assertThat(typeAliment1).isNotEqualTo(typeAliment2);
    }
}
