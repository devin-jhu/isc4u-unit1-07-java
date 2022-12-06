/*
* This program prints out grades with a median
* of 75 to a .csv file.
*
* By:      Devin Jhu
* Version: 1.0
* Since:   2022-11-05
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

/**
* This is the marks program.
*/
final class Marks {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Marks() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The generateMarks() function.
    *
    * @param studentsArray the collection of students
    * @param unitsArray the collection of assignments
    */

    public static void generateMarks(final ArrayList<String> studentsArray,
                                       final ArrayList<String> unitsArray) {
        // Declares constants for the function.
        final Random random = new Random();
        final int studentLength = studentsArray.size();
        final int unitLength = unitsArray.size();
        final ArrayList<ArrayList<String>> table =
                new ArrayList<ArrayList<String>>();

        // Creates top row.
        final ArrayList<String> topRow = new ArrayList<String>();
        topRow.add(" ");
        for (int count = 0; count < unitLength; count++) {
            topRow.add(unitsArray.get(count));
        }
        table.add(topRow);

        // Generates random marks to be added to the table.
        for (int count = 0; count < studentLength; count++) {
            final ArrayList<String> studentRow = new ArrayList<String>();
            studentRow.add(studentsArray.get(count));
            for (int count2 = 0; count2 < unitLength; count2++) {
                final int mark = (int) Math.floor(random.nextGaussian()
                                  * 10 + 75);
                studentRow.add(String.valueOf(mark));
            }
            table.add(studentRow);
        }

        // Create csv file.
        final String csvName = "marks.csv";
        final File file = new File(csvName);

        // Formatting for the table.
        try {
            final FileWriter fileWriter = new FileWriter(csvName, false);

            for (int count = 0; count < table.size(); count++) {
                final String line = String.join(",", table.get(count)) + ",\n";
                System.out.println(line);
                fileWriter.write(line);
            }
            fileWriter.close();
        } catch (IOException error) {
            System.out.println("Something went wrong.");
            error.printStackTrace();
        }
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        final ArrayList<String> students1 = new ArrayList<String>();
        final ArrayList<String> unit2 = new ArrayList<String>();
        final Path studentFilePath = Paths.get(args[0]);
        final Path unitFilePath = Paths.get(args[1]);
        final Charset charset = Charset.forName("UTF-8");

        // read students.txt
        try (BufferedReader readerStudent = Files.newBufferedReader(
                                     studentFilePath, charset)) {
            String lineStudent = null;
            while ((lineStudent = readerStudent.readLine()) != null) {
                studentsList.add(lineStudent);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        // reads units.txt
        try (BufferedReader readerAssignment = Files.newBufferedReader(
                                     unitFilePath, charset)) {
            String lineAssignment = null;
            while ((lineAssignment = readerAssignment.readLine()) != null) {
                unitsList.add(lineAssignment);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        // outputs onto the file
        generateMarks(studentsList, unitsList);

        // end of program
        System.out.println("\nDone.");
    }
}
