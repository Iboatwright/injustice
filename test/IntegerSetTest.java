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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(Parameterized.class)
class IntegerSetTest {
  private IntegerSet integerSet;
  private int[] first_list_of_numbers;
  private int[] unique_numbers_in_the_first_list;
  private int[] second_list_of_numbers;
  private int[] unique_numbers_in_the_second_list;
  private int[] intersection_of_set1_and_set2;
  private int[] union_of_set1_and_set2;

  // Constructor for assigning current test set to instance variables
  public IntegerSetTest(int[] first, int[] first_unique, int[] second,
                        int[] second_unique, int[] intersection, int[] union){
    this.first_list_of_numbers = first;
    this.unique_numbers_in_the_first_list = first_unique;
    this.second_list_of_numbers = second;
    this.unique_numbers_in_the_second_list = second_unique;
    this.intersection_of_set1_and_set2 = intersection;
    this.union_of_set1_and_set2 = union;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    List<String> test_input = new ArrayList<>();
    ArrayList<Object[]> testData = new ArrayList<>();

    try {
      test_input = Files.readAllLines(Paths.get("test_input.txt"));
    } catch (IOException|SecurityException e) {
      e.printStackTrace();
    }

    // loop through the test_input 7 lines at a time
    int line = 0;
    Object[] test_set = new Object[6];
    for (int i=0; i < test_input.size()/test_set.length; i++){
      // copy the 6 lists of test data to test_set j as int arrays
      for (int j=0; j<6; j++) {
        test_set[j] = Stream.of(test_input.get(line++).split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();
      }
      // TEST - shows the output is an int[]
      //System.out.println(Stream.of("1,2,3,4".split(",")).mapToInt(Integer::parseInt).toArray()[i]);
      testData.add(test_set);
      line++; // skip the test set blank line delimiter
    }

    return testData;
  }

  @Before
  void setUp() {
    integerSet = new IntegerSet();
  }

  @After
  void tearDown() {
    integerSet = null;
  }

  // tests null inputs for intersection
  @Test
  void testIntersectionWithNullInput(){

  }

  // tests if  a value exists in a set
  @Test
  void testExists(){
    System.out.println("intersection: " + intersection_of_set1_and_set2[0]);
    assertNull(null);
  }


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
