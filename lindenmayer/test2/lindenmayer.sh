java -jar lindenmayer.jar buisson.json 5 > buisson5.eps
ps2pdf buisson5.eps
java -jar lindenmayer.jar herbe.json 7 > herbe7.eps
ps2pdf herbe7.eps
java -jar lindenmayer.jar hexamaze.json 6 > hexamaze6.eps
ps2pdf hexamaze6.eps
java -jar lindenmayer.jar sierpinski.json 8 > sierpinski8.eps
ps2pdf sierpinski8.eps
java -jar lindenmayer.jar buisson.json 5 screen
