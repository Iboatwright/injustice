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
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


@RunWith(Parameterized.class)
class IntegerSetTest {
  private IntegerSet integerSet;
  private List<Integer> first_list_of_numbers;
  private List<Integer> unique_numbers_in_the_first_list;
  private List<Integer> second_list_of_numbers;
  private List<Integer> unique_numbers_in_the_second_list;
  private List<Integer> intersection_of_set1_and_set2;
  private List<Integer> union_of_set1_and_set2;

  // Constructor for assigning currect test set to instance variables
  public IntegerSet(String first, String first_unique, String second,
                    String second_unique, String intersection, String union){
    this.first_list_of_numbers = Arrays.asList(first.split(","))
                                       .forEach(Integer::valueOf);
    this.unique_numbers_in_the_first_list = Arrays.asList(first_unique.split(","))
                                                  .forEach(Integer::valueOf);
    this.second_list_of_numbers = Arrays.asList(second.split(","))
                                        .forEach(Integer::valueOf);
    this.unique_numbers_in_the_second_list = Arrays.asList(second_unique.split(","))
                                                   .forEach(Integer::valueOf);
    this.intersection_of_set1_and_set2 = Arrays.asList(intersection.split(","))
                                               .forEach(Integer::valueOf);
    this.union_of_set1_and_set2 = Arrays.asList(union.split(","))
                                        .forEach(Integer::valueOf);
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    List<String> test_input;
    List<List<String>> testData;

    try {
      test_input = Files.readAllLines("test_input.txt")
    } catch (IOException|SecurityException e) {

    }

    // loop through the test_input 7 lines at a time
    int line = 0;
    for (int i=0; i < test_input.size()/7; i++){
      // copy the 6 lists of test data to test set i
      for (int j=0; j=5; j++) {
        testData[i][j] = test_input.get(line++);
      }
      line++; // skip the test set blank line delimeter
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
    System.out.println("input: ");
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
