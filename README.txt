README

Hanan Ali- Wrote the file reading method and created the report class. Did the first draft of the minimum counters needed method. Wrote the main() method. Wrote the method for testing the code.

Jason Pichette- Project lead, designed the final solution. Wrote the Accident Queue class and tweaked the counters needed method. 
 

Program4: The .java file which contains the main method. This file uses the other classes and solves the minimum counter problem for the given counties. 
AccidentQueue: A java class which implements the Queue interface to create a Queue containing Report Objects. 
Report: A class which adds Report objects, designed to contain the information read from the provided accidents.csv file. Each Report object represents an accident report. 

Important Fields:
Program4-
reports: Array of Report objects, generated from the given csv file.
reportList: ArrayList of Report objects generated from the reports array. reportList is sorted by the daysSinceCE of the Report objects

Report-
daysSinceCE: An integer which represents the approximate amount of days that have past since the start of the common era. While this is confusing for humans to understand, it makes the computer quickly recognize when two reports are from the same day.

Important methods:
Program4- 
constructQueue(String state, String county): Creates an AccidentQueue which contains all Report objects which occurred in the provided state and county. Grabs Report objects from the reportList, so the queue made is sorted by date.
getMinCountersNeeded(accidentQueue reports): Calculates the minimum amount of counters needed to successfully process the accident reports for the given accidentQueue. accidentQueues provided should contain only one county, and be sorted. 
