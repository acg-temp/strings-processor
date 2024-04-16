# Strings Processor
### String cleaners:
- **Brackets cleaner**. We say that a string is *brackets-consistent* if every left bracket has a uniquely determined matching right bracket. At the page **/strings-processor/brackets** you can find a service to remove outer irrelevant brackets from a brackets-consistent string. Brackets-inconsistent strings are not modified by the service.
- **En-Pairs cleaner.** A service to remove external matching letter pairs of the english alphabeth (like the pair (a,z)). The pairs are considered to be ordered (for example the pair (a,z) is considered a matching pair, while (z,a) is not). This service can be found at the page  **/strings-processor/pairs-en**.
### String printer:
For each strings cleaner service you can download a pdf file containing a spiral composed by cleaned strings (unfortunately in a synchronous way); 
### Run the application
Inside the folder containing Dockerfile run the following commands to build Docker image and run 
```
docker build -t strings-processor-image .
docker run --rm -it -p 8081:8080 strings-processor-image
```
Navigate to the following urls
- Brackets cleaner: http://localhost:8081/strings-processor/brackets
- Pairs cleaner: http://localhost:8081/strings-processor/pairs-en
### Project Description
Project is developed following the mvc pattern, implemented by use of Java Servlet Pages. 
Views can be found at \strings-processor\src\main\webapp\jsp, while controllers, models and services are stored in \strings-processor\src\main\java\pntc. 
The docker image already contains a Tomcat Server to run the application.
