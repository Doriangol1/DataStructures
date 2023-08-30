package app;

import java.util.Scanner;
import java.io.*;

public class RLEconverter {
   private final static int DEFAULT_LEN = 100; // used to create arrays.

   /*
    *  This method reads in an uncompressed ascii image file that contains 
    *  2 characters. It stores each line of the file in an array.
    *  It then calls compressAllLines to get an array that stores the compressed
    *  version of each uncompressed line from the file. The compressed array
    *  is then passed to the getCompressedFileStr method which returns a String
    *  of all compressed lines (the two charcaters are written in the first line)
    *  in CSV format. This String is written to a text file with the prefix "RLE_"
    *  added to the original, uncompressed file name.
    *  Note that dataSize keeps track of the number of lines in the file. The array 
    *  that holds the lines of the file is initialized to the DEFAULT_LEN, which 
    *  is assumed to be << the number of lines in the file.
    */   

  public void compressFile(String fileName) throws IOException{ // compress the file
     
    Scanner scan = new Scanner(new FileReader(fileName));
    String line = null;
    String[] decompressed = new String [DEFAULT_LEN];
    int dataSize = 0;
    while(scan.hasNext()){
      line = scan.next();
      if(line != null && line.length()>0)
        decompressed[dataSize]=line;
        dataSize++;
    }
    scan.close();
    char[] fileChars = discoverAsciiChars(decompressed, dataSize);
    String[] compressed = compressAllLines(decompressed, dataSize, fileChars);
    writeFile(getCompressedFileStr(compressed, fileChars), "RLE_"+fileName);
    
  }
  
   
/*
 * This method implements the RLE compression algorithm. It takes a line of uncompressed data
 * from an ascii file and returns the RLE encoding of that line in CSV format.
 * The two characters that make up the image file are passed in as a char array, where
 * the first cell contains the first character that occurred in the file.
*/

public String compressLine(String line, char[] fileChars){ // compress the line ; iterate through characters in line
   //TODO: Implement this method
      
    int i = 0; // initialize variable i for for-loop
    int countA = 0;  // count for first ascii character
    int countB = 0;   // count for second ascii character
    String compLine = ""; // final string
    boolean notFirstLoop = false; // boolean checking if its the for-loop's first loop
      

      for (i=0;i<=line.length();i++){
        
        countA = 0; // resest countA
        countB=0;   // reset countB

        if (notFirstLoop)
          i--; // reduce i by one to avoid skipping

        notFirstLoop = true; 

          while (i<line.length() && line.charAt(i)== fileChars[0]){ //iterate through characters, checking if equal to first ascii letter
              countA +=1; // add to count of first character
              i++;
          }

          if (i == line.length() && countA>0) // if i is at the end of the line and countA is positive, add count
            compLine += countA;

          else if (countA > 0) // if i is positive and not at the end of line, add count and ','.
            compLine += countA + ",";
          
          while (i<line.length() && line.charAt(i)==fileChars[1]){ // iterate through characters, checking if equal to second ascii character
            countB +=1; // add to count of second character
            i++;
          }
          
          if (i==line.length() && countB > 0) // if i is at the end of line and countB is positive, add the count
            compLine += countB;

          else if (countB>0) // if i is positive but not end of line, add the count and ','.
            compLine += countB + ",";
      }

      if (line.charAt(0) != fileChars[0]) // check if first character in line is not equal to first ascii letter.
          compLine = "0," + compLine; // if they dont match, 0 must be added as the count for first character.
          
      return compLine; // return compressed line
}

  /*
   *  This method discovers the two ascii characters that make up the image. 
   *  It iterates through all of the lines and writes each compressed line
   *  to a String array which is returned. The method compressLine is called on 
   *  each line.
   *  The dataSize is the number of lines in the file, which is likely to be << the length of lines.
   */

  public String[] compressAllLines(String[] lines, int dataSize, char[] fileChars){ // compress all of the lines
      //TODO: Implement this method

      String[] allComLines = new String[dataSize]; // new array of type string in size of number of lines
      int i=0; // intialize i for while loop
      
      while (i < dataSize){ // while i is less than the number of lines 
        
        allComLines[i] = compressLine(lines[i],fileChars); // iterating through array, add compressed lines to equivalent index of array;
        i++;

      }

      return allComLines; // return all compressed lines in array form
}

/*
 *  This method assembles the lines of compressed data for
 *  writing to a file. The first line must be the 2 ascii characters
 *  in comma-separated format. 
 */

public String getCompressedFileStr(String[] compressed, char[] fileChars) { // get the compressed file string 
    //TODO: Implement this method

      String finalString = ""; // initialize final string
      finalString += fileChars[0] + "," + fileChars[1]; // set first line of string to ascii letters
      
      for (int j=0; j<compressed.length;j++){ // iterate through array of compressed lines, adding them to string.
        
        finalString += "\n" + compressed[j];  

      }
      
      return finalString; // return final string ready to be written to file
}
   /*
    *  This method reads in an RLE compressed ascii image file that contains 
    *  2 characters. It stores each line of the file in an array.
    *  It then calls decompressAllLines to get an array that stores the decompressed
    *  version of each compressed line from the file. The first row contains the two 
    *  ascii charcaters used in the original image file. The decompressed array
    *  is then passed to the getDecompressedFileStr method which returns a String
    *  of all decompressed lines, thus restoring the original, uncompressed image.
    *  This String is written to a text file with the prefix "DECOMP_"
    *  added to the original, compressed file name.
    *  Note that dataSize keeps track of the number of lines in the file. The array 
    *  that holds the lines of the file is initialized to the DEFAULT_LEN, which 
    *  is assumed to be << the number of lines in the file.
    */   

  public void decompressFile(String fileName) throws IOException{ // decompress file
    Scanner scan = new Scanner(new FileReader(fileName));
    String line = null;
    String[] compressed = new String [DEFAULT_LEN];
    int dataSize =0;
    while(scan.hasNext()){
      line = scan.next();
      if(line != null && line.length()>0)
        compressed[dataSize]=line;
        dataSize++;
    }
    scan.close();
    String[] decompressed = decompressAllLines(compressed, dataSize);
    writeFile(getDecompressedFileStr(decompressed), "DECOMP_"+fileName);
  }
 
   /*
   * This method decodes lines that were encoded by the RLE compression algorithm. 
   * It takes a line of compressed data and returns the decompressed, or original version
   * of that line. The two characters that make up the image file are passed in as a char array, 
   * where the first cell contains the first character that occurred in the file.
   */

   public String decompressLine(String line, char[] fileChars){ // decompress a line
      //TODO: Implement this method

        String unComLine = ""; // intialize string for uncompressed line
        boolean firstAscii = true; // boolean set to true if count number correlates to first ascii letter
        Integer value = 0; // Integer to store value of number from string
        String number = ""; // string to store the number from a string
        
        for (int i=0;i<line.length(); i++){ // iterate through characters in line

          number = ""; // reset number string

          while (i<line.length() && line.charAt(i) != ','){ // iterate through characters, not stopping until a comma is reached.
           
            number += line.charAt(i); //add the characters from ',' to the next ,' , creating a string equivalent to number value
            i++;

          }      

          value = Integer.parseInt(number); // convert the number from string to integer type, stored in new variable

          if (firstAscii){ //check boolean, determining which ascii letter the number corresponds to

            for (int j=0;j<value;j++){ // if it corresponds to the first Ascii letter, the letter will be added "value" times to the string.
              unComLine += fileChars[0]; // add to string of uncompressed line 
            }

          }

          if (!firstAscii){ //check boolean, determining which ascii letter the number corresponds to

            for (int k=0;k<value;k++){ // if it corresponds to the second Ascii letter, the letter will be added "value" times to the string.
              unComLine += fileChars[1]; // add to string of uncompressed line
            }
                
          }

          if (firstAscii) // if boolean is true
              firstAscii = false; // set it to false

          else // if false
              firstAscii = true; // set to true
        }

        return unComLine; // return uncompressed line.
   }

    /*
   *  This method iterates through all of the compressed lines and writes 
   *  each decompressed line to a String array which is returned. 
   *  The method decompressLine is called on each line. The first line in
   *  the compressed array passed in are the 2 ascii characters used to make
   *  up the image. 
   *  The dataSize is the number of lines in the file, which is likely to be << the length of lines.
   *  The array returned contains only the decompressed lines to be written to the decompressed file.
   */

  public String[] decompressAllLines(String[] lines, int dataSize){ // decompress all of the lines
     //TODO: Implement this method

      String[] allUncomLines = new String[dataSize]; // create new string array to store uncompressed lines
      int i=1; // intialize i for loop, starting at 1
      char[] Chars = {lines[0].charAt(0), lines[0].charAt(2)}; // get array consisting of the ascii letters to find, found on first line
      
      while (i < dataSize){ // iterate through each line

        allUncomLines[i] = decompressLine(lines[i], Chars); // set each uncompressed line to array with matching index to line
        i++;
      }

      return allUncomLines; // return array consisting of the lines with matching index
  }
  
  /*
   *  This method assembles the lines of decompressed data for
   *  writing to a file. 
   */

  public String getDecompressedFileStr(String[] decompressed){ // get the decompressed file string 
     String data = "";
     //TODO: Implement this method

     for (int i=0;i<decompressed.length;i++){ // go through each item in array
        data += "\n" + decompressed[i]; // add each string to a final string in a new line
     }
   
      return data; // return final decompressed lines, ready to be written to file 
  }

  // assume the file contains only 2 different ascii characters.
  public char[] discoverAsciiChars(String[] decompressed, int dataSize){ // find the ascii characters
//TODO: Implement this method

char[] asciiChars = new char[2]; // create characters array to store the Ascii characters
char firstAsciiChar = decompressed[0].charAt(0); // set first ascii letter to the first character in first line
int j=0; // intialize j and i for loops
int i=0;

while (j<decompressed.length && decompressed[j].charAt(i)==decompressed[j].charAt(i+1)){ // iterate through characters and lines, and checking if they are equal to the next character
  i++; // if so, go forward a character
    
  if (i==decompressed[j].length() - 1){ // check if last character is reached
      j+=1; // go do a line
      i=0; // set back to first character 
    }
}

char secondChar = decompressed[j].charAt(i+1); // set second ascii character to first new character

asciiChars[0] = firstAsciiChar; // first index of array matces with first character 
asciiChars[1] = secondChar; // second index of array matches with second ascii character

  return asciiChars; // return array of ascii characters
}



   
   public void writeFile(String data, String fileName) throws IOException{ // write to file
		PrintWriter pw = new PrintWriter(fileName);
      pw.print(data);
      pw.close();
   }
}