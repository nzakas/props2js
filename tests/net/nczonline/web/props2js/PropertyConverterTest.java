

package net.nczonline.web.props2js;

import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicholas
 */
public class PropertyConverterTest {

    private Properties properties;
    private final String resultJson = "{\"price\":12.3,\"count\":10,\"found\":true,\"baz\":\"boom\",\"foo\":\"bar\"}";

    @Before
    public void setUp(){
        properties = new Properties();
        properties.setProperty("baz", "boom");
        properties.setProperty("count", "10");
        properties.setProperty("foo", "bar");
        properties.setProperty("found", "true");
        properties.setProperty("price", "12.3");
    }

    @After
    public void tearDown(){
        properties = null;
    }

    @Test
    public void testConvertToJSON(){
        String result = PropertyConverter.convertToJson(properties);
        assertEquals(resultJson, result);
    }

    @Test
    public void testConvertToJSONP(){
        String result = PropertyConverter.convertToJsonP(properties, "func");
        assertEquals("func(" + resultJson + ");", result);
    }

    @Test
    public void testConvertToJavaScript(){
        String result = PropertyConverter.convertToJavaScript(properties, "foo");
        assertEquals("var foo=" + resultJson + ";", result);
    }

    @Test
    public void testConvertToJavaScriptProperty(){
        String result = PropertyConverter.convertToJavaScript(properties, "foo.bar");
        assertEquals("foo.bar=" + resultJson + ";", result);
    }
}
