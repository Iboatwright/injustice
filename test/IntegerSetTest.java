/**
 * File: IntegerSetTest.java
 * Author: 5153
 * Course: CEN 4072
 * Assignment: 6
 * Date: 5.7.19
 *
 * JUnit5.4.2 IntegerSet Parameterized Unit Tests
 */
/*
TODO: JUnit5 implementation will result in output that doesn't match the examples
      given. Rather than risking stupidiy on top of injustice, I will explore
      a JUnit4 solution.
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class IntegerSetTest {
  List<List<Integer>> testData;

  IntegerSet{
    Scanner test_input;

/*    // open the test_input file
    try {
      test_input = new Scanner(new File("test_input.txt"));
    } catch(FileNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }*/

    try {
      test_input = Files.readAllLines("test_input.txt")
    } catch (IOException|SecurityException e) {

    }

    // read each line and split it into an Integer list
    while (test_input.hasNextLine()) {
      testData.add(
          Stream
              .of(test_input.nextLine().split(","))
              .map(Integer::valueOf)
              .collect(Collectors.toList())
      );
    }

    // TODO: split testData into the 6 lists

  }

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  // tests null inputs for intersection
  @Test
  void testIntersectionWithNullInput(){

  }

  // tests if  a value exists in a set
  @ParameterizedTest
  void testExists(){}


  // tests if an IntegerSet is empty
  @Test
  void testIsEmpty(){}


  // tests the union of two sets
  @Test
  void testUnion(){}


  // tests the creation of a set from an array
  @Test
  void testCreateSetFromArray(){}


  // tests the creation of a set from  null
  @Test
  void testCreateSetFromNull(){}


  @Test
  void testDeleteAll(){}


  @Test
  void testDeleteElement(){}


  @Test
  void testInsertAll(){}


  @Test
  void testAllNull(){}


  @Test
  void testIntersection(){}


  @Test
  void testUnionWithNullInput(){}

}
