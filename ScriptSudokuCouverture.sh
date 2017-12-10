#!/bin/bash

## SUPPRESSION DES ANCIENS RAPPORTS ET DOSSIERS D'INSTRUMENTATION
rm -rf reports/coverage/* instrumented/* cobertura.ser build/classes/* build/test/classes/*

## COMPILATION DU CODE SOURCE POUR LA CLASSE ELLE-MEME ET SA CLASSE DE TEST

javac -cp lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar src/main/java/grille/Grille.java src/main/java/grille/GrilleImpl.java src/test/java/grille/GrilleTest.java

## INSTRUMENTATION
cobertura/cobertura-instrument.sh --destination instrumented/ src/main/java/grille/Grille.class src/main/java/grille/GrilleImpl.class src/test/java/grille/GrilleTest.class

## EXECUTION DU CODE INSTRUMENTE
cp src/main/java/grille/Grille.class instrumented/grille/
chmod -R 777 *
java -cp cobertura/cobertura-2.1.1.jar:cobertura/lib/slf4j-api-1.7.5.jar:cobertura/lib/logback-core-1.0.13.jar:cobertura/lib/logback-classic-1.0.13.jar:lib/junit-4.12.jar:src/main/java/grille/Grille:./instrumented:. grille.GrilleTest

## GENERATION DU RAPPORT
cobertura/cobertura-report.sh --datafile cobertura.ser --format html --destination reports/coverage/ src/main/java src/test/java

## AFFICHAGE DU RAPPORT
firefox reports/coverage/index.html
