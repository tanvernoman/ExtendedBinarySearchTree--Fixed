/*
 *@author (B00316640)
 */
package part1solution;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 *
 * @author thnom
 */
public class BST {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Please choose your option");
        Scanner sc = new Scanner(System.in);
        switch (sc.nextInt()) {
            case 1:
                part1_test();
                break;
            case 2:
                test2();
                test3();test3();test3();
                test4();
                test5();test5();test5();
                test6();
                test7(); test7(); test7();
                test8();
                test9();test9();test9();
                break;
            case 3:
                test10();
                test11();test11();test11();
                test12();
                test13();test13();test13();
                test14();
                test15();test15();test15();
                test16();
                test17();test17();test17();
            case 4:
                testSuccessfulSearch();
                break;
            case 5: 
                testUnSuccessfulSearch();
                break;
            case 6:
                testSearchItemAfterInsertAndDelete();
                break;
            case 7: 
                testSerializatoin();
                break;
            default:
                break;
        }

    }

    /**
     * Serializing the object in the file
     *
     * @param data
     * @param filename
     */
    public static void serialize(BinarySearchTree data, String filename) {
        try {
            // System.out.println(data);
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            System.out.println("Serialization complete");
        } catch (IOException ex) {
            System.out.println("Exception " + ex.toString());
        }
    }

    /**
     * Deserializing the object from the file
     *
     * @param fileName
     */
    public static void deserialize(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            BinarySearchTree data = (BinarySearchTree) ois.readObject();
            ois.close();
            data.BFT();
            System.out.println("Deserialization complete");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception " + e.toString());
        }

    }

    private static void part1_test() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        List<Integer> dataSet = new LinkedList<>();
        Random ran = new Random();
        System.out.print("Please enter the size of the data set: ");
        try (Scanner keyboard = new Scanner(System.in)) {
            int size = keyboard.nextInt();
            final int N = size * 2; // to ensure duplicates generated on most runs
            System.out.printf("The size of the dataSet for this run is %d \n",
                    size);
            int count = 0;
            while (bst.size() < size) {
                int candidate = ran.nextInt(N) * 2 + 1;  // add odd values only
                count++;
                if (bst.add(candidate)) {
                    dataSet.add(candidate);
                }
            }
            System.out.printf("Added %d elements from %d random numbers.\n",
                    size, count);
            int errorCount = 0;
            // System.out.println(bst.size);
            //System.out.println(bst.height());
            System.out.println("Testing contains() method:");
            for (int d : dataSet) {
                if (!bst.contains(d)) {
                    errorCount++;
                    System.out.printf(
                            "Error %d: contains(%d) returns false.\n",
                            errorCount, d);
                }
                if (bst.contains((d / 2) * 2)) { // even number tests for absence
                    errorCount++;
                    System.out.printf(
                            "Error %d: contains(%d) returns true.\n",
                            errorCount, (d / 2) * 2);
                }
            }
            System.out.printf("Testing contents complete with %d errors ",
                    errorCount);
            System.out.println();
            System.out.println("Testing enumerator throws an exception "
                    + "if tree is modified while in use.");
            try {
                for (int i : bst) {
                    bst.add(-1);
                }
                errorCount++;
            } catch (ConcurrentModificationException e) {
            }
            System.out.println("Testing exception throwing complete.");
            System.out.printf("Errors now %d.\n", errorCount);
            System.out.println();
            //bst.BFT();
            bst.remove(-1);
            bst.BFT();
            if (bst.size() <= 25) {
                int counter = 1;
                System.out.println("Tree contents are:");
                for (int i : bst) {
                    System.out.printf("Item number %d is: %d\n", counter++, i);
                }
            }
            System.out.println("Removing tree contents:");
            count = bst.size();
            errorCount = 0;
            for (int d : dataSet) {
                if (bst.remove(d)) {
                    count--;
                    if (count != bst.size()) {
                        errorCount++;  // tree size was not updated
                    }
                } else { // d was not removed
                    errorCount++;
                }
            }
            System.out.printf("%d items removed with %d errors.\n",
                    size, errorCount);
            System.out.printf("Tree size = %d\n", bst.size());
            System.out.println("Any remaining contents listed below:");
            for (int i : bst) {
                System.out.println(i);
            }
            System.out.println("Testing complete....");

        }

    }

    private static void test2() {
        //Adding 1 .. 100 items in the tree in that order 

        System.out.println("--------------------- Te sting 2 -----------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        for (int i = 1; i <= 100; i++) {
            bst.add(new Item(i));
        }
        bst.BFT();
        Item.resetCompCount();

        for (int i = 0; i <= 100; i++) {
            bst.contains(new Item(i));
        }
        System.out.println("\n");
        System.out.println("Tree Height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of comaprison " + Item.getCompCount());

        bst.removeAll();
        System.out.println("Size " + bst.size());
        bst.BFT();
        System.out.println("\n");

    }

    private static void test3() {
        // Inserting item in tree between 1 and 100 in random

        System.out.println("-------------Test 3 ----------------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        Random rnd = new Random();
        while (bst.size() != 100) {
            bst.add(new Item(rnd.nextInt(100 - 1 + 1)));
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 0; i <= 100; i++) {
            bst.contains(new Item(i));
        }
        System.out.println("\n");
        System.out.println("Tree height " + bst.height());
        System.out.println("Size " + bst.size);
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of Comparisons " + Item.getCompCount());
    }

    private static void test4() {
        //Adding 1 .. 1000 items in the tree in that order 

        System.out.println("--------------------- Testing 4 -----------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        for (int i = 1; i <= 1000; i++) {
            bst.add(new Item(i));
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 0; i <= 1000; i++) {
            bst.contains(new Item(i));
        }
        System.out.println("\n");
        System.out.println("Tree Height " + bst.height());
        System.out.println("Size " + bst.size);
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of comaprison " + Item.getCompCount());
    }

    private static void test5() {
        // Inserting item in tree between 1 and 1000 in random
        System.out.println("-------------Test 5 ----------------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        Random rnd = new Random();
        while (bst.size() != 1000) {
            bst.add(new Item(rnd.nextInt(1000 - 1 + 1)));
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 0; i <= 1000; i++) {
            bst.contains(new Item(i));
        }
        System.out.println("\n");
        System.out.println("Tree height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of Comparisons " + Item.getCompCount());
    }

    private static void test6() {
        // Adding item between 1 and 10000 in order

        System.out.println("--------------------- Testing 6 -----------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        for (int i = 1; i <= 10000; i++) {
            bst.add(new Item(i));
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 0; i <= 10000; i++) {
            bst.contains(new Item(i));
        }
        System.out.println("\n");
        System.out.println("Tree Height " + bst.height());
        System.out.println(" Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println(" Number of comaprison " + Item.getCompCount());
    }

    private static void test7() {
        // Adding item between 1 and 10000 in random order

        System.out.println("-------------Test 7 ----------------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        Random rnd = new Random();
        while (bst.size() != 10000) {
            bst.add(new Item(rnd.nextInt(10000 - 1 + 1)));
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 0; i <= 10000; i++) {
            bst.contains(new Item(i));
        }
        System.out.println("\n");
        System.out.println("Tree height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of Comparisons " + Item.getCompCount());

    }

    private static void test8() {
        // Adding item between 1 and 100000 in order
        System.out.println("--------------------- Testing 8 -----------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        for (int i = 1; i <= 100000; i++) {
            bst.add(new Item(i));
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 0; i <= 100000; i++) {
            bst.contains(new Item(i));
        }
        System.out.println("\n");
        // System.out.println("Tree Height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of comaprison " + Item.getCompCount());
    }

    private static void test9() {
        // Adding item between 1 and 100000 in random order

        System.out.println("-------------Test 9 ----------------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        Random rnd = new Random();
        while (bst.size() != 100000) {
            bst.add(new Item(rnd.nextInt(100000 - 1 + 1)));
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 0; i <= 100000; i++) {
            bst.contains(new Item(i));
        }
        System.out.println("\n");
        System.out.println("Tree height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of Comparisons " + Item.getCompCount());

    }

    private static void test10() {
        //Adding odd item in the tree between 1 and 200 in order 

        System.out.println("--------------------- Testing 10 -----------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        for (int i = 1; i <= 200; i++) {
            if (i % 2 == 1) {
                bst.add(new Item(i));
            }
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 1; i <= 200; i++) {
            if (i % 2 == 0) {
                bst.contains(new Item(i));
            }
        }
        System.out.println("\n");
        System.out.println("Tree Height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of comaprison " + Item.getCompCount());

    }

    private static void test11() {
        // Adding odd item in the tree between 1 and 200 in random order
        System.out.println("-------------Test 11 ----------------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        Random rnd = new Random();
        final int N = 100; // to ensure duplicates generated on most runs

        while (bst.size() != 100) {
            int candidate = rnd.nextInt(N) * 2 + 1;  // add odd values only
            bst.add(new Item(candidate));
        }
        bst.BFT();

        Item.resetCompCount();
        for (int i = 1; i <= 200; i++) {
            if (i % 2 == 0) {
                bst.contains(new Item(i));
            }
        }

        System.out.println("\n");
        System.out.println("Tree height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of Comparisons " + Item.getCompCount());
    }

    private static void test12() {
        //Adding odd item in the tree between 1 and 2000 in order
        System.out.println("--------------Testing 12 -----------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        for (int i = 1; i <= 2000; i++) {
            if (i % 2 == 1) {
                bst.add(new Item(i));
            }
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 1; i <= 2000; i++) {
            if (i % 2 == 0) {
                bst.contains(new Item(i));
            }
        }
        System.out.println("\n");
        System.out.println("Tree Height " + bst.height());
        System.out.println(" Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println(" Number of comaprison " + Item.getCompCount());
    }

    private static void test13() {
        // Inserting odd item in the tree between 1 and 2000 in random order
        System.out.println("-------------Test 13 ----------------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        Random rnd = new Random();
        final int N = 1000; // to ensure duplicates generated on most runs

        while (bst.size() != 1000) {
            int candidate = rnd.nextInt(N) * 2 + 1;  // add odd values only
            bst.add(new Item(candidate));
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 1; i <= 2000; i++) {
            if (i % 2 == 0) {
                bst.contains(new Item(i));
            }
        }
        System.out.println("\n");
        System.out.println("Tree height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of Comparisons " + Item.getCompCount());
    }

    private static void test14() {
        //Adding odd item in the tree between 1 and 20000 in order
        System.out.println("--------------Testing 14 -----------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        for (int i = 1; i <= 20000; i++) {
            if (i % 2 == 1) {
                bst.add(new Item(i));
            }
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 1; i <= 20000; i++) {
            if (i % 2 == 0) {
                bst.contains(new Item(i));
            }
        }
        System.out.println("\n");
        System.out.println("Tree Height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of comaprison " + Item.getCompCount());
    }

    private static void test15() {
        // Inserting odd item in the tree between 1 and 2000 in random order

        System.out.println("-------------Test 15 ----------------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        Random rnd = new Random();
        final int N = 10000;

        while (bst.size() != 10000) {
            int candidate = rnd.nextInt(N) * 2 + 1;
            bst.add(new Item(candidate));
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 1; i <= 20000; i++) {
            if (i % 2 == 0) {
                bst.contains(new Item(i));
            }
        }
        System.out.println("\n");
        System.out.println("Tree height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of Comparisons " + Item.getCompCount());
    }

    private static void test16() {
        //Adding odd item in the tree between 1 and 20000 in order
        System.out.println("--------------Testing 16 -----------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        for (int i = 1; i <= 200000; i++) {
            if (i % 2 == 1) {
                bst.add(new Item(i));
            }
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 1; i <= 200000; i++) {
            if (i % 2 == 0) {
                bst.contains(new Item(i));
            }
        }
        System.out.println("\n");
        // System.out.println("Tree Height " + bst.height());
        System.out.println(" Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println(" Number of comaprison " + Item.getCompCount());
    }

    private static void test17() {
        // Inserting odd item in the tree between 1 and 2000 in random order
        System.out.println("-------------Test 17 ----------------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        Random rnd = new Random();
        final int N = 100000; // to ensure duplicates generated on most runs

        while (bst.size() != 100000) {
            int candidate = rnd.nextInt(N) * 2 + 1;  // add odd values only
            bst.add(new Item(candidate));
        }
        bst.BFT();
        Item.resetCompCount();
        for (int i = 1; i <= 200000; i++) {
            if (i % 2 == 0) {
                bst.contains(new Item(i));
            }
        }
        System.out.println("\n");
        System.out.println("Tree height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of Comparisons " + Item.getCompCount());
    }

    /**
     * successful search in large collection counting the average number of
     * comparisons for 10 successful search
     */
    private static void testSuccessfulSearch() {

        System.out.println("-------------Test successfully Finding an item in large collection ----------------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        Random rnd = new Random();
        while (bst.size() != 100000) {
            bst.add(new Item(rnd.nextInt(100000 - 1 + 1)));
        }
        bst.BFT();
        Item.resetCompCount();
        System.out.println("\n");
        System.out.println("Comparions :" + Item.getCompCount());
        int average = 0;
        int c = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 50000; i < 50010; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < 10; i++) {
            int number = list.get(i);
            //int number=rnd.nextInt()*100;  //Unsuccessfull search
            bst.contains(new Item(number));
            System.out.println(" Pass : " + i + " Number : " + number + " Comparisons :" + Item.getCompCount());
            average = (int) (average + Item.getCompCount());
            c++;
        }
        System.out.println("Average number of comparisons : " + average / c);
        System.out.println("\n");
        System.out.println("Tree height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of Comparisons " + Item.getCompCount());

    }

    
     private static void testUnSuccessfulSearch() {

        System.out.println("-------------Test Unsuccessfully Finding an item in large collection ----------------");
        BinarySearchTree<Item> bst = new BinarySearchTree<>();
        Random rnd = new Random();
        while (bst.size() != 100000) {
            bst.add(new Item(rnd.nextInt(100000 - 1 + 1)));
        }
        bst.BFT();
        Item.resetCompCount();
        System.out.println("\n");
        System.out.println("Comparions :" + Item.getCompCount());
        int average = 0;
        int c = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 200000; i < 200010; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < list.size(); i++) {
            int number = list.get(i);
            //int number=rnd.nextInt()*100;  //Unsuccessfull search
            bst.contains(new Item(number));
            System.out.println(" Pass : " + i + " Number : " + number + " Comparisons :" + Item.getCompCount());
            average = (int) (average + Item.getCompCount());
            c++;
            //Item.resetCompCount();
        }
        System.out.println("Average number of comparisons : " + average / c);
        System.out.println("\n");
        System.out.println("Tree height " + bst.height());
        System.out.println("Size " + bst.size());
        System.out.println("The Number of leaves " + bst.leaves());
        System.out.println("Number of Comparisons " + Item.getCompCount());

    }

    /**
     * testing the thee tree after large number of insertion and delation . tree
     * size is constant 100000 insertion and delation has been done 10 times
     */
    private static void testSearchItemAfterInsertAndDelete() {
        int averageHeight = 0;
        int averageSize = 0;
        System.out.println("-------------Test successfully Finding an item after large insertion and deletion----------------");
        BinarySearchTree<Item> bs = new BinarySearchTree<>();
        Random rnd = new Random();
        while (bs.size() != 100000) {
            bs.add(new Item(rnd.nextInt(100000 - 1 + 1)));
        }

        System.out.println("Height of the tree : " + bs.height());
        System.out.println(" Size of the set : " + bs.size());
        bs.BFT();

        for (int i = 0; i < 10; i++) {
            System.out.println(" \n\n--------- Removing one fifth of the item from the tree------------ ");
            for (int j = 1; j < 20000; j++) {
                //hs.remove(new Item(j));
                bs.remove(new Item(rnd.nextInt(100000 - 1 + 1)));
            }

            System.out.println(" \n\n After deleteion ");
            System.out.println(" Size of the tree after removals : " + bs.size());
            // print(hs);
            bs.BFT();
            System.out.println(" \n\n ------Inserting data again after deletion--------- ");
            while (bs.size() != 100000) {
                //  bs.add(new Item(j));
                bs.add(new Item(rnd.nextInt(100000 - 1 + 1)));
            }

            System.out.println(" Size of the tree after insertion : " + bs.size());
            System.out.println("Height of the tree : " + bs.height());

            bs.BFT();
            System.out.println("\n pass " + i + " complete ");

            averageHeight = averageHeight + bs.height();
            averageSize = averageSize + bs.size();

        }

        System.out.println("average height of the tree" + averageHeight / 10);
        System.out.println("Average size of the tree : " + averageSize / 10);
        Item.resetCompCount();
        System.out.println("\n");
        System.out.println("Comparions :" + Item.getCompCount());
        double total = 0;
        int c = 0;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 30000; i < 30010; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < 10; i++) {
            int number = list.get(i);
            bs.contains(new Item(number));
            System.out.println(" Pass : " + i + " Number : " + number + " Comparisons :" + Item.getCompCount());
            total = (int) (total + Item.getCompCount());
            c++;
            //Item.resetCompCount();
        }
        System.out.println("Average number of comparisons : " + total / c);
        System.out.println("\n");
        //System.out.println("Tree height " + height(hs));
        System.out.println("Size " + bs.size());
        System.out.println("Number of Comparisons " + Item.getCompCount());

    }

    private static void testSerializatoin() {
      BinarySearchTree<Integer> bst= new BinarySearchTree<>();
      Random rnd= new Random();
      
      while(bst.size != 100){
          bst.add(rnd.nextInt(100-1+1));
      }
        serialize(bst, "data.txt");
        deserialize("data.txt");
    }
}
