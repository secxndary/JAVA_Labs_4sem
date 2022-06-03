import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GreetingTargetTest {
    @Test
    public void testGreet() {
        assertThat(GreetingTarget.getTarget(), is("Modules"));
    }
}