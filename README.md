# Props2Js

**Notice:** This utility has been end-of-lifed and is no longer supported. It is kept here for legacy purposes. If you're interested in taking over ownership of this project, please [contact me](http://nczonline.net/contact).

Props2Js is a command line utility that converts Java properties files into one
of three different formats:

1. JSON
1. JSONP
1. JavaScript

The goal is to allow you to easily store configuration data that is needed for
JavaScript in a simple format and then transform it into a format you need in
your application.

## Formats

### JSON Format

To convert a properties file into JSON, use the following

    java -jar props2js-x.y.z.jar source.properties -t json -o output.json

This reads in the properties file `source.properties` and outputs the JSON
equivalent into `output.json`.

### JSONP Format

To convert a properties file into JSON, use the following

    java -jar props2js-x.y.z.jar source.properties -t jsonp --name myfunc -o output.jsonp

This reads in the properties file `source.properties` and outputs the JSONP
equivalent into `output.jsonp`. This is the same as outputting as JSON except
`myfunc()` is wrapped around the data.

### JavaScript Format

To convert a properties file into JavaScript, use the following

    java -jar props2js-x.y.z.jar source.properties -t js --name myvar -o output.js

This reads in the properties file `source.properties` and outputs the JavaScript
equivalent into `output.js`. This is the same as outputting as JSON except
`var myvar=` precedes the JSON and a semicolon follows it.
