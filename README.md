# OrderParser
This is an implementation of a test assignment for candidates on a Java developer position.

### Implementation
OrderParser is a console application that parses orders from text files, either in JSON or in CSV formats, and prints
them out to a console. To make testers' life easier, the app can also *generate* files with random orders, and even deliberately make
mistakes in parameters. OrderParser is an entirely SpringBoot-based console app, without support of REST API or DB levels. 

## Main classes:
  * **Application** - an entry point of an application. Parses CLI parameters, run either a parsing flow, or generating flow.
  * **ValidationService** - an interface that performs validation of input files in the parsing flow; implemented by the Spring service ValidationServiceImpl;
  * **OrderGenerator** - an interface that parses orders from a text file. Is implemented by Spring service classes JsonFileParsingServiceImpl and CsvFileParsingServiceImpl;
  * **OrderParserContainer** (one should come up with a better name :) ) - an interface that actually triggers parsing of several files. Its implementation, the OrderParserContainerIml Spring service class, contains a few OrderGenerator classes, each responsible for a certain file extension; 
  * **ApplicationConfig** - a Spring configuration cless. Constructs OrderGenerator heirs and builds an OrderParserContainerImpl instance from them;

## Application Flows
### Parsing Flow
1. A few paths to text files are passed to the app as CLI parameters.
2. **ValidationService** validates those file - whether they exit, or whether theis extensions are supported. If there's at least 1 error, the program prints out them to the console, and termintes.
3. **OrderParsingContainer** transforms paths to text files into a Java Set to avoid duplicates, and for each of them calls a corresponding OrderGenerator instance for parsing & logging to the system output.
4. **OrderGenerator** opens a file as a list of Strings (via Apache Commons library), and iterates through them using parallel streams to transform each line to a JSON log entry and print it out.
### Generation Flow
The app can tell this flow from the parsing one by the '-g' (or '--generate') CLI parameter. Besides that, the following params are necessary:
  * '-s' ('--size') - number of orders to generate, 100 by default;
  * '-f' ('--format') - file format. By now, only 'csv' and 'json' are supported.
**OrderGenerator** creates randomly **size** orders and stores them to a file orders${System.currentTimeInMillies()}.${format}, i.e., 'orders1608287871331.csv'.
 
### Build & Run
The OrderParser project structure is a Maven one (https://maven.apache.org/). The command ```mvn clean package```, run from the console of via IDE (i.e. Intellij IDEA) builds and generates the Jar in the '/target' folder.
#### Run parsing flow:
`java -jar target/orderparser-1.0-SNAPSHOT.jar data/orders1.csv data/orders2.json`

After that, all orders from all files will be flushed to the output.
#### Run generation flow:
`$ java -jar target/orderparser-1.0-SNAPSHOT.jar -g -s 200 -f json`

After that, a file with a new like 'orders1608284238063.json' with exactly 200 lines will appear in a local directory.  