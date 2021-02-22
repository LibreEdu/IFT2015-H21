The executable file, lindenmayer, is located in the test2 directory. 
Sample commands are in the lindenmayer.sh file.

There are two versions of the program. A PostScript version :
java -jar lindenmayer.jar buisson.json 5 > bush5.eps

And a screen version :
java -jar lindenmayer.jar buissonScreen.json 5 screen

The screen version opens a window of zero size while the drawing is being made, 
then the drawing is displayed for 6 seconds. Unless there is new drawing (case 
of random sequence, as is the case with the herbeScreen.json file).

Since the specifics of the screen (x-axis down) are different from PostScript, 
the JSON files have been adapted.

The directory src/lindenmayer contains all the code. The directory 
lindenmayerTest contains test classes.