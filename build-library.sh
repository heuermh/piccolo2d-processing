#!/bin/bash

mkdir piccolo2d
mkdir piccolo2d/library
cp COPYING piccolo2d
cp README piccolo2d
cp lib/piccolo2d-core-2.0-SNAPSHOT-license.txt piccolo2d/piccolo2d-license.txt
cp lib/piccolo2d-core-2.0-SNAPSHOT.jar lib/piccolo2d-jdk16-2.0-SNAPSHOT.jar piccolo2d/library
cp -R src piccolo2d
cp -R examples piccolo2d
cd src
javac -source 1.6 -target 1.6 -classpath "../lib/processing-core-1.5.1.jar:../lib/piccolo2d-core-2.0-SNAPSHOT.jar:../lib/piccolo2d-jdk16-2.0-SNAPSHOT.jar" org/piccolo2d/processing/Piccolo2D.java
jar cvf ../piccolo2d/library/piccolo2d.jar org/piccolo2d/processing/Piccolo2D.class
cd ..