package sem3.customerdemo.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleSanitizerTest {

    @Test
    void sanitize() {
        String result = SimpleSanitizer.sanitize("<script>Uhhhhh</script>");
        assertEquals("scriptUhhhhhscript",result);
    }
}