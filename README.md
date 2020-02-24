# CSX42: Assignment 2
## Name: Kamleshwar Ragava

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in numberPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile numberPlay/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile numberPlay/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant run -buildfile numberPlay/src/build.xml \
-DinputNumStream="<input file path>" \
-DrunAvgWindowSize="<size of the window for running average calculations>" \
-DrunAvgOutFile="<output file path to which running averages are written>" \
-Dk="<max size of the list containing the top K numbers>" \
-DtopKNumOutFile="<path of output file to which the top K numbers are written>" \
-DnumPeaksOutFile="<path of output file to which the peaks in the number stream are written>"
```

-----------------------------------------------------------------------
## Description:
<h3>Flow of Control:</h3>
<p>
	Command line validation is done in driver and an Instance of Validator is created in Driver which will validate input File.<br>
	The driver has instances Observers and Subject which is NumberProcessor.<br>
	The driver also holds the instance of FileProcesssor to get the input line by line and send it a class called CheckNumber.<br>
	This class will generate the events accordingly and will send the number and event to the Subject.<br>
	The subject will have a HashMap of filters and List of Observers and accordingly notifies the observers.<br>
	There are three observers which implements ObserverI interface for running Average, top K Numbers and Peak Value Calculation.<br>
	These observers will send data to store and write to their respective utilites.<br>
	After a process complete event is triggered data will be flushed to the files.<br>
</p>
<h3>Sourcess/ References</h3>
<ul>
<li>Piazza for looping through hash map as TA explained in class</li>
<li>Usage of enum: <a href="https://www.geeksforgeeks.org/enum-in-java/">https://www.geeksforgeeks.org/enum-in-java/</a></li>
</ul>

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 02/23/2020


