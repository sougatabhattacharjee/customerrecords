## Customer Records ##
We have some customer records in a text file -- one customer per line, JSON-encoded. 
An example is, ```{"latitude4": "52.986375", "user_id": 12, "name": "Christina McArdle", "longitude": "-6.043701"}```


We want to invite any customer within 100km of Dublin office location ```53.339428, -6.257664``` for some food and drinks on us. 
The solution will read the full list of customers 
and output the names and user ids of matching customers (within 100km), sorted by User ID (ascending).

### Technical Stack ###
To implement this solution, the following libraries / tools were used:
  * Editor/Tool - Intellij IDEA  
  * Java 8 - Programming language   
  * Maven 3.5.2 - Used for dependency management and as a build tool.  
  * jUnit - unit test framework.
  * Git - Version control system.


#### How to build ####
Before running the project, JDK 1.8 and Maven 3.x.x have to be installed in the system. 
```
mvn clean package 
```  
#### How to run #### 
```
mvn exec:java 
```  
#### How to execute test cases #### 
```
mvn test
```  
