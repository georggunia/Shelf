package ShelfPckg;

import org.junit.*;

import static org.junit.Assert.*;

public class ToolTest {
    @Test
    public void testTool() {
        Tool tool = new Tool("worked");
        assertEquals("worked", tool.getName());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testToolNull() {
        Tool tool = new Tool(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testToolEmpty() {
        Tool tool = new Tool("");
    }

}
