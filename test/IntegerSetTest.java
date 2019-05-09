/**
 * File: IntegerSetTest.java
 * Author: 5153
 * Course: CEN 4072
 * Assignment: 6
 * Date: 5.7.19
 *
 * JUnit4.12 IntegerSet Parameterized Unit Tests
 */

/*
TODO: JUnit4 implementation
 */

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@RunWith(Parameterized.class)
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

  @Before
  void setUp() {
  }

  @After
  void tearDown() {
  }

  // tests null inputs for intersection
  @Test
  void testIntersectionWithNullInput(){

  }

  // tests if  a value exists in a set
  @Test
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
