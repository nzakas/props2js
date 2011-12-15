/*
 * Copyright (c) 2011 Nicholas C. Zakas. All rights reserved.
 * http://www.nczonline.net/
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
 
package net.nczonline.web.props2js;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nicholas C. Zakas
 */
public class PropertyConverter {

    private static Pattern floatPattern = Pattern.compile( "^[0-9]+(\\.[0-9]+)?$" );
    private static Pattern intPattern = Pattern.compile( "^[0-9]+$" );

    /**
     * Converts the properties object into a JSON string.
     * @param properties The properties object to convert.
     * @return A JSON string representing the object.
     */
    public static String convertToJson(Properties properties){
        JsonObject json = new JsonObject();
        for (Object key: properties.keySet()){
            String value = properties.getProperty(key.toString());
            if (value.equals("true") || value.equals("false")){
                json.addProperty(key.toString(), Boolean.parseBoolean(value));
            } else if (intPattern.matcher(value).matches()){
                json.addProperty(key.toString(), Integer.parseInt(value));
            } else if (floatPattern.matcher(value).matches()){
                json.addProperty(key.toString(), Float.parseFloat(value));
            } else {
                json.addProperty(key.toString(), value);
            }
        }

        return new Gson().toJson(json);
    }

    /**
     * Converts the properties object into a JSONP string, of the form
     * callback(json);
     * @param properties The properties object to convert.
     * @param callback The name of the callback to call.
     * @return A JSONP string representing the object.
     */
    public static String convertToJsonP(Properties properties, String callback){
        return callback + "(" + convertToJson(properties) + ");";
    }

    /**
     * Converts the properties object into a JavaScript string, of the form
     * var variable = json; or object.property = json;
     * @param properties The properties object to convert.
     * @param variable The name of the variable to store the object.
     * @return A JavaScript string representing the object.
     */
    public static String convertToJavaScript(Properties properties, String variable){
        String result = variable + "=" + convertToJson(properties) + ";";
        if (!variable.contains(".")){
            result = "var " + result;
        }
        return result;
    }

}
