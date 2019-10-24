#!/bin/bash

mkdir piccolo2d
mkdir piccolo2d/library
cp COPYING piccolo2d
cp README.md piccolo2d
cp library.properties piccolo2d
cp lib/piccolo2d-core-3.0.1-license.txt piccolo2d/piccolo2d-license.txt
cp lib/piccolo2d-core-3.0.1.jar piccolo2d/library
cp -R src piccolo2d
cp -R examples piccolo2d
cd src
javac -source 1.8 -target 1.8 -classpath "../lib/processing-core-3.5.3.jar:../lib/piccolo2d-core-3.0.1.jar" org/piccolo2d/processing/Piccolo2D.java
jar cvf ../piccolo2d/library/piccolo2d.jar org/piccolo2d/processing/Piccolo2D.class
cd ..
