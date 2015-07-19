

package net.nczonline.web.props2js;

import java.util.Properties;

import net.nczonline.web.props2js.PropertyConverter;
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
    private Properties propertiesHtml;
    private final String resultJson = "{\"price\":12.3,\"count\":10,\"found\":true,\"under.score\":\"under_score\",\"baz\":\"boom\",\"foo\":\"bar\"}";
	private final String resultPretty = "{\n"+
		"  \"price\": 12.3,\n"+
		"  \"count\": 10,\n"+
		"  \"found\": true,\n"+
        "  \"under.score\": \"under_score\",\n"+
		"  \"baz\": \"boom\",\n"+
		"  \"foo\": \"bar\"\n"+
		"}";

    private final String resultFlattenKeys = "{\n"+
        "  \"price\": 12.3,\n"+
        "  \"count\": 10,\n"+
        "  \"found\": true,\n"+
        "  \"under_score\": \"under_score\",\n"+
        "  \"baz\": \"boom\",\n"+
        "  \"foo\": \"bar\"\n"+
        "}";

    private final String resultNoEscape = "{\n"+
		"  \"html\": \"This is <b>Bold text</b>\",\n"+
		"  \"price\": 12.3,\n"+
		"  \"count\": 10,\n"+
		"  \"found\": true,\n"+
        "  \"under.score\": \"under_score\",\n"+
        "  \"baz\": \"boom\"\n"+
		"}";

    private final String resultEscape = "{\n"+
            "  \"html\": \"This is \\u003cb\\u003eBold text\\u003c/b\\u003e\",\n"+
            "  \"price\": 12.3,\n"+
            "  \"count\": 10,\n"+
            "  \"found\": true,\n"+
            "  \"under.score\": \"under_score\",\n"+
            "  \"baz\": \"boom\"\n"+
            "}";

    @Before
    public void setUp(){
        properties = new Properties();
        properties.setProperty("baz", "boom");
        properties.setProperty("count", "10");
        properties.setProperty("foo", "bar");
        properties.setProperty("found", "true");
        properties.setProperty("price", "12.3");
        properties.setProperty("under.score", "under_score");

        propertiesHtml = new Properties();
        propertiesHtml.setProperty("baz", "boom");
        propertiesHtml.setProperty("count", "10");
        propertiesHtml.setProperty("html", "This is <b>Bold text</b>");
        propertiesHtml.setProperty("found", "true");
        propertiesHtml.setProperty("price", "12.3");
        propertiesHtml.setProperty("under.score", "under_score");
    }

    @After
    public void tearDown(){
        properties = null;
    }

    @Test
    public void testConvertToJSON(){
        String result = PropertyConverter.convertToJson(properties, false, true, false);
        assertEquals(resultJson, result);
    }

    @Test
    public void testConvertToJSONP(){
        String result = PropertyConverter.convertToJsonP(properties, "func", false, true, false);
        assertEquals("func(" + resultJson + ");", result);
    }

    @Test
    public void testConvertToJavaScript(){
        String result = PropertyConverter.convertToJavaScript(properties, "foo", false, true, false);
        assertEquals("var foo=" + resultJson + ";", result);
    }

    @Test
    public void testConvertToJavaScriptProperty(){
        String result = PropertyConverter.convertToJavaScript(properties, "foo.bar", false, true, false);
        assertEquals("foo.bar=" + resultJson + ";", result);
    }
    
	@Test
    public void testConvertToJavaScriptPretty(){
        String result = PropertyConverter.convertToJavaScript(properties, "foo", true, true, false);
        assertEquals("var foo=" + resultPretty + ";", result);
    }

	@Test
    public void testConvertToJavaScriptNoEscapePretty(){
        String result = PropertyConverter.convertToJavaScript(propertiesHtml, "foo", true, false, false);
        assertEquals("var foo=" + resultNoEscape + ";", result);
    }

	@Test
    public void testConvertToJavaScriptEscapePretty(){
        String result = PropertyConverter.convertToJavaScript(propertiesHtml, "foo", true, true, false);
        assertEquals("var foo=" + resultEscape + ";", result);
    }

    @Test
    public void testConvertToJsonFlattenKeys(){
        String result = PropertyConverter.convertToJson(properties, true, true, true);
        assertEquals(resultFlattenKeys, result);
    }
}
