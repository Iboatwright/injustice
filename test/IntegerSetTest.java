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
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@RunWith(Parameterized.class)
public class IntegerSetTest {
  private IntegerSet integerSet1;
  private IntegerSet integerSet2;
  private Integer[] first_list_of_numbers;
  private Integer[] unique_numbers_in_the_first_list;
  private Integer[] second_list_of_numbers;
  private Integer[] unique_numbers_in_the_second_list;
  private Integer[] intersection_of_set1_and_set2;
  private Integer[] union_of_set1_and_set2;

  // Constructor for assigning current test set to instance variables
  public IntegerSetTest(Integer[] first, Integer[] first_unique,
                        Integer[] second, Integer[] second_unique,
                        Integer[] intersection, Integer[] union){
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
    Object[] testSet;

    // read each line into an ordered List
    try {
      test_input = Files.readAllLines(Paths.get("test_input.txt"));
    } catch (IOException|SecurityException e) {
      e.printStackTrace();
    }

    // helper vars to improve code readability
    int inputsPerSet = 6;
    int inputSets = test_input.size()/(inputsPerSet +1); // +1 delimiter line

    // loop through the test_input and store the inputSets into testData
    int line = 0;
    for (int i=0; i < inputSets; i++){
      testSet = new Object[inputsPerSet];
      // copy the 6 lists of test data to test_set j as boxed Integer Lists
      for (int j=0; j < inputsPerSet; j++) {
        testSet[j] = Stream.of(test_input.get(line++).split(","))
                            .mapToInt(Integer::parseInt)
                            .boxed()
                            .sorted()
                            .toArray( Integer[]::new );
      }
      testData.add(testSet);
      line++; // skip the test set blank line delimiter
    }

    return testData;
  }

  @Before
  public void setUp() {
    this.integerSet1 = new IntegerSet(first_list_of_numbers);
    this.integerSet2 = new IntegerSet(second_list_of_numbers);
  }

  @After
  public void tearDown() {
    this.integerSet1 = null;
    this.integerSet2 = null;
  }

  // tests null inputs for intersection
  @Test
  public void testIntersectionWithNullInput(){
  }

  // tests if  a value exists in a set
  @Test
  public void testExists(){
//    assertEquals(integerSet1.toArray()[0],first_list_of_numbers[0]);
    assertTrue(integerSet1.exists(first_list_of_numbers[0]));
  }

  // tests if an IntegerSet is empty
  @Test
  public void testIsEmpty(){
    assertTrue((new IntegerSet()).isEmpty());
  }


  // tests the union of two sets
  @Test
  public void testUnion(){
    IntegerSet unionTest = IntegerSet.union(integerSet1, integerSet2);
    Integer[] unionArray = unionTest.toArray();


  }


  // tests the creation of a set from an array
  @Test
  public void testCreateSetFromArray(){}


  // tests the creation of a set from  null
  @Test
  public void testCreateSetFromNull(){}


  @Test
  public void testDeleteAll(){}


  @Test
  public void testDeleteElement(){}


  @Test
  public void testInsertAll(){}


  @Test
  public void testAllNull(){}


  @Test
  public void testIntersection(){}


  @Test
  public void testUnionWithNullInput(){}

}
