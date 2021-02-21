#!/bin/zsh
java -jar lindenmayer.jar buisson.json 5 > buisson5.eps
ps2pdf -dEPSCrop buisson5.eps
java -jar lindenmayer.jar herbe.json 7 > herbe5.eps
ps2pdf herbe7.eps
java -jar lindenmayer.jar hexamaze.json 6 > hexamaze6.eps
ps2pdf hexamaze6.eps
java -jar lindenmayer.jar sierpinski.json 8 > sierpinski8.eps
ps2pdf sierpinski8.eps
#java -jar lindenmayer.jar buissonScreen.json 5 screen
#java -jar lindenmayer.jar herbeScreen.json 6 screen
#java -jar lindenmayer.jar hexamaze.json 6 screen
#java -jar lindenmayer.jar sierpinskiScreen.json 8 screen