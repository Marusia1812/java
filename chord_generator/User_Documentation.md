# Chord Generator

## Overview
Chord Generator is a command-line Java application that generates chords based on user input. Users can input a root note, chord type, and inversion to receive the corresponding chord with its notes. The application also supports generating random chords with the 'random' command.

## Getting Started
1. Compile the Java classes by navigating to the project directory and running the following command:
2. bash
    ```shell
    javac cz/cuni/mff/java/chinkovm/*.java
    ```
3. Run the compiled Java application:
    ```shell
    java cz.cuni.mff.java.chinkovm.Main
    ```

## Using the Application
When the application is launched, you'll be prompted to enter the root note, chord type, and inversion separated by spaces. Here's an example of input:

```shell
C major 1
```

Supported root notes are: C, C#, D, D#, E, F, F#, G, G#, A, A#, B
Supported chord types are: major, minor, diminished, augmented, major7, minor7, dominant7, sus2, sus4
Inversion is an integer value (0, 1, 2, etc.).
You can also generate a random chord by typing 'random'.
To exit the application, type 'exit' and press Enter.

# Developer Documentation:

```shell
javadoc -d doc -sourcepath src/main/java -subpackages cz.cuni.mff.java.chinkovm
```
This will generate an HTML version of the Javadoc documentation in a folder named 'doc'. Open the 'index.html' file in the 'doc' folder to view the generated documentation.