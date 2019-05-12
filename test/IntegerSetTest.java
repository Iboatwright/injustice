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
  Notes:
    1) IntegerSet.java line 130 in the union method will never reach the false
        branch condition. IntegerSets cannot contain duplicate elements. The if
        statement on line 130 should be removed.
    2) IntegerSet.java line 155 states it is checking for null pointers, but
        in fact it will throw an unhandled NullPointerException. Either the
        comments should be corrected or the if statement needs to be rewritten
        to properly handle null arguments.
    3) There is a disparity between null and empty that this assignment fails to
        properly address. As such, exception handling will only be tested
        where exceptions are specifically thrown. In all other cases the term
        null will be treated/tested as equivalent to list.isEmpty() == true.
    4) I intentionally did not repeat traditional test methods for all tests. Since
        this is an exercise to demonstrate my comprehension of testing and
        ability to write tests. Some tests, such as testInsertAll, I
        would not have used in production.
    5) deleteElement does not remove an integer because this implementation
        relies on LinkedList.remove(int x) where x is the index.
        https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html#remove-int-
        The obvious intent was deleteElement to perform a search and remove for
        the int argument. The deleteElement should be rewritten to work as
        designed.
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
import java.util.Random;
import java.util.stream.IntStream;
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

  // tests if  a value exists in a set
  @Test
  public void testExists(){
    assertTrue(integerSet1.exists(first_list_of_numbers[0]));

    // generate an integer than is not in integerSet1
    Random rando = new Random();
    int randomInteger = rando.nextInt();
    while (integerSet1.exists(randomInteger)) {
      randomInteger = rando.nextInt();
    }

    // insert it into integerSet1 and check that it exists
    integerSet1.insertElement(randomInteger);
    assertTrue(integerSet1.exists(randomInteger));

    // insert an existing value and check that it still exists
    integerSet1.insertElement(first_list_of_numbers[1]);
    assertTrue(integerSet1.exists(first_list_of_numbers[1]));
  }

  // tests if an IntegerSet is empty
  @Test
  public void testIsEmpty(){
    assertTrue((new IntegerSet()).isEmpty());
    assertTrue(!integerSet1.isEmpty());
    assertEquals((new IntegerSet()).toString(),"[]");
  }


  // tests the creation of a set from  null
  @Test(expected = NullPointerException.class)
  public void testCreateSetFromNull(){
    new IntegerSet(null);
  }


  // tests the creation of a set from an array
  @Test
  public void testCreateSetFromArray(){
    IntegerSet newSetFromArray = new IntegerSet(first_list_of_numbers);

    // check that all values in the expected set are in the new set.
    assertEquals(newSetFromArray.size(),unique_numbers_in_the_first_list.length);
    for (int el : unique_numbers_in_the_first_list){
      assertTrue(newSetFromArray.exists(el));
    }
  }


  @Test
  public void testInsertAll(){
    IntegerSet newSet = new IntegerSet();

    // insertAll from a known set and check the result against the expected.
    newSet.insertAll(first_list_of_numbers);
    assertEquals(newSet.toString(),integerSet1.toString());
  }


  @Test(expected = NullPointerException.class)
  public void testInsertAllNull(){
    new IntegerSet().insertAll(null);
  }


  @Test
  public void testDeleteElement(){
    int testNumber = integerSet1.toArray()[0];  // first element of the set
    integerSet1.deleteElement(0);  // remove the first element **(see Note 5)
    assertTrue(!integerSet1.exists(testNumber));
  }


  @Test
  public void testDeleteAll(){
    integerSet1.deleteAll();
    assertTrue(integerSet1.isEmpty());
  }


  // tests the union of two sets
  @Test
  public void testUnion(){
    IntegerSet unionTest;
    Integer[] unionArray;

    // test union with two non-empty IntegerSets.
    unionTest = IntegerSet.union(integerSet1, integerSet2);
    unionArray = unionTest.toArray();

    // compare each element between the sorted arrays.
    assertEquals(unionArray.length,union_of_set1_and_set2.length);
    for (int i=0; i < unionArray.length; i++){
      assertEquals(unionArray[i],union_of_set1_and_set2[i]);
    }
  }


  @Test
  public void testUnionWithNullInput(){
    IntegerSet unionTest;
    Integer[] unionArray;

    // test union with an empty array for set1 and a empty array for set2.
    unionTest = IntegerSet.union(new IntegerSet(), new IntegerSet());

    // the resulting union set is expected to be empty.
    assertTrue(unionTest.isEmpty());

    // test union with an empty array for set1 and a non-empty array for set2.
    unionTest = IntegerSet.union(new IntegerSet(), integerSet1);
    unionArray = unionTest.toArray();

    // compare each element between the sorted arrays.
    assertEquals(unionTest.size(),unique_numbers_in_the_first_list.length);
    for (int i=0; i < unionArray.length; i++){
      assertEquals(unionArray[i],unique_numbers_in_the_first_list[i]);
    }

    // test union with an non-empty array for set1 and a empty array for set2.
    unionTest = IntegerSet.union(integerSet1, new IntegerSet());
    unionArray = unionTest.toArray();

    // compare each element between the sorted arrays.
    assertEquals(unionTest.size(),unique_numbers_in_the_first_list.length);
    for (int i=0; i < unionArray.length; i++){
      assertEquals(unionArray[i],unique_numbers_in_the_first_list[i]);
    }
  }


  @Test
  public void testIntersection(){
    IntegerSet intersectionTest;
    Integer[] intersectionArray;

    // test intersection with two non-empty IntegerSets.
    intersectionTest = IntegerSet.intersection(integerSet1, integerSet2);
    intersectionArray = intersectionTest.toArray();

    // compare each element between the sorted arrays.
    assertEquals(intersectionTest.size(),intersection_of_set1_and_set2.length);
    for (int i=0; i < intersectionArray.length; i++){
      assertEquals(intersectionArray[i],intersection_of_set1_and_set2[i]);
    }
  }


  // tests null inputs for intersection **(see Notes 2 and 3).
  @Test
  public void testIntersectionWithNullInput(){
    IntegerSet intersectionTest;

    // test intersection with an empty array for set1 and a empty array for set2.
    intersectionTest = IntegerSet.intersection(new IntegerSet(), new IntegerSet());

    // the resulting intersection set is expected to be empty.
    assertTrue(intersectionTest.isEmpty());

    // test intersection with an empty array for set1 and a non-empty array for set2.
    intersectionTest = IntegerSet.intersection(new IntegerSet(), integerSet1);

    // intersection with an empty set should be and empty set
    assertTrue(intersectionTest.isEmpty());

    // test intersection with an non-empty array for set1 and a empty array for set2.
    intersectionTest = IntegerSet.intersection(integerSet2, new IntegerSet());

    // intersection with an empty set should be and empty set
    assertTrue(intersectionTest.isEmpty());
  }

}
