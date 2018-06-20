# credit-log
Console Log event CRUD

•	This is a Maven project and used spring mvc and hibernate. 
•	FilePath.jsp file takes in the path of a console  log file
•	FinalResult.jsp file displays the total number of events logged in the database
•	ConsoleLog.txt file has been created with the example logs provided made few changes so that the content becomes JSON complaint. Kindly use this file.
•	Used JSONParser to convert each log entry (which is similar to JSON object) into model class.
•	Further business logic is applied where finally an ArrayList of model objects is created which have processing time over 4ms.
•	These model objects are persisted into the hsqldb in memory database, for this I had installed hsqldb-2.4.1 in computer.
•	I have tried initally to use hibernate for persistence, unfortunately ran into issues, the issue was mainly the creation of table. I do have this property set <property name="hbm2ddl.auto">create</property>, which ideally should be able to create the table if not existing but unfortunately it didn't. I think it could be a configuration issue.
•	Further just to show the persistence of the model objects opted the very crude way, which can be found in LogEventDAOImpl class.


