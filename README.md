# Analyze and translate reviews from CSV file with predefined structure

Note: For ability to run analyzing on multiple machines these functionality should be rewritten using some Big Data solution. For example using Hadoop.

To run the program you should do the following:
1. Run command mvn clean install from the current folder.
2. Execute command java -Dtranslate=true -Dpath={pathToCSVFile} -jar target/test-1.0.jar
