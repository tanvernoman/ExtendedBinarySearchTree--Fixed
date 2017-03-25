/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part1solution;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thnom
 */
public class BinarySearchTreeTest {
    
   public BinarySearchTreeTest() {
    }
    BinarySearchTree<Integer> bst;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        bst = new BinarySearchTree<>();
    }

    @After
    public void tearDown() {
        bst.removeAll();
    }

    @Test
    public void testDefaultConstructor() {
        System.out.println("--Test default constructor--");
        assertEquals(0, bst.size());
        //assertEquals("[]", bst.toString());

    } // method testDefaultConstructor

    @Test
    public void testCopyConstructor() {
        System.out.println("---Test copy constructor----");
        for (int i = 1; i < 100; i++) {
            bst.add(i);
        }
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(bst);
        assertEquals(bst.size(), tree.size());
        for (int i = 1; i < 100; i++) {
            assertTrue(tree.contains(i));
        }
        //assertTrue(tree.remove(50));               
    } // method testCopyConstructor1  

    /**
     * test equals method when the object is null.
     */
    @Test
    public void testEqualsNull() {
        assertFalse(bst.equals(null));
    } // method testEqualsNull

    /**
     * Test object is not equal
     */
    @Test
    public void testObjectIsNotSame() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertNotSame(bst, tree);
    }

    /**
     * Test of equals method, of class BinarySearchTree.
     */
    @Test
    public void testEquals_Object() {
        System.out.println("equals");
        BinarySearchTree<String> tree = new BinarySearchTree<>();
       // tree.add("kjdnh");
        BinarySearchTree<String> newTree = new BinarySearchTree<>();
        assertEquals(true, newTree.equals(tree));

        //assertTrue(result);
    }
        @Test
    public void testEquals_ObjectNotEqual() {
        System.out.println("equals");
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.add("kjdnh");
        BinarySearchTree<String> newTree = new BinarySearchTree<>();
        assertFalse( newTree.equals(tree));

        //assertTrue(result);
    }
    

    /**
     * Test of equals method, of class BinarySearchTree.
     */
    @Test
    public void testEquals_BinarySearchTreeEntry() {
        System.out.println("----Test equals -------");
        BinarySearchTree<Item> tree = new BinarySearchTree<>();
        tree.add(new Item(10));
        tree.add(new Item(20));
        tree.add(new Item(30));

        BinarySearchTree<Item> instance = new BinarySearchTree<>();
        instance.add(new Item(10));
        instance.add(new Item(20));
        instance.add(new Item(30));
        boolean expResult = true;
        boolean result = instance.equals(tree);
        assertEquals(expResult, result);


    }

    /**
     * Test of size method, of class BinarySearchTree.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        for (int i = 1; i <= 100; i++) {
            bst.add(i);
        }
        int expResult = 100;
        int result = bst.size();
        assertEquals(expResult, result);

    }

    /**
     * Test of isEmpty method, of class BinarySearchTree.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        boolean result = bst.isEmpty();
        assertTrue(result);

    }

    /**
     * Test of isEmpty method of BinarySearchTree when tree is not empty
     */
    @Test
    public void testIsEmpty_NotEmptyTree() {
        bst.add(55);
        bst.add(10);
        assertFalse(bst.isEmpty());
    }

    /**
     * Test of contains method, of class BinarySearchTree. if the element filed
     * is null it should throw nullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testContainsNull() {
        bst.contains(null);
    }

    @Test(expected = ClassCastException.class)
    public void testContainsMismatch() {
        Object obj = new Integer(7);
        bst.contains((String) obj);
    } // method testContainsMismatch  

    /**
     * Test of contains method, of class BinarySearchTree.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        bst.add(50);
        bst.add(60);
        bst.add(40);
        assertTrue(bst.contains(60));
        assertFalse(bst.contains(70));

    }

    /**
     * test the add method by passing null arguments
     */
    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        bst.add(null);
    }

    @Test(expected = ClassCastException.class)
    public void testAddMismatch() {
        Object obj = new Integer(7);
        bst.contains((String) obj);
    } // method testContainsMismatch  

    /**
     * Test of add method, of class BinarySearchTree.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        bst.add(50);
        bst.add(60);
        bst.add(40);
        bst.add(30);
        assertEquals(4, bst.size());
        assertEquals(2, bst.height());
        assertTrue(bst.contains(30));
    }

    /**
     * test add method by passing duplicate elements
     */
    @Test
    public void testAddDuplicates() {
        System.out.println(" Add duplicates ");
        bst.add(50);
        assertFalse(bst.add(50));
    }

    /**
     * Testing Empty trees
     */
    @Test
    public void testEmptyTrees() {
        System.out.println("Test Empty Trees");
        assertEquals(0, bst.size());
        assertFalse(bst.remove(50));
        assertEquals(-1, bst.height());
        assertFalse(bst.contains(900));
    }

    /**
     * Test the trees with on element
     */
    @Test
    public void testTreesWithOneElement() {
        System.out.println("----Test With only One element --------");
        bst.add(500);
        assertTrue(bst.contains(500));
        assertFalse(bst.contains(600));
        assertEquals(1, bst.size());
        assertEquals(0, bst.height());
    }

    /**
     * Test the Breadth First Traversal
     */
    @Test
    public void testBFT() {
        System.out.println("----Test Breadth first traversal------");
        bst.add(50);
        bst.add(70);
        bst.add(100);
        // assertEquals(bst.BFT(), bst.toString());
        bst.BFT();

    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        bst.remove(null);
    } // method testRemoveNull

    @Test(expected = ClassCastException.class)
    public void testRemoveMismatch() {
        Object obj = new Integer(7);
        bst.remove((String) obj);
    } // method testAddRemoveMismatch 

    /**
     * Test of remove method, of class BinarySearchTree.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        bst.add(50);
        bst.add(60);
        bst.add(40);
        assertEquals(3, bst.size());
        assertTrue(bst.remove(40));
        assertFalse(bst.remove(95));
        assertEquals(2, bst.size());

    }

    /**
     * Test of height method, of class BinarySearchTree.
     */
    @Test
    public void testHeight_EmptyTrees() {
        System.out.println("height");
        BinarySearchTree instance = new BinarySearchTree();
        int expResult = -1;
        int result = instance.height();
        assertEquals(expResult, result);
    }

    /**
     * Test of height method, of class BinarySearchTree.
     */
    @Test
    public void testHeight() {
        System.out.println("height");
        for (int i = 1; i <= 200; i++) {
            bst.add(i);
        }
        int expResult = 199;
        int result = bst.height();
        assertEquals(expResult, result);

    }

    /**
     * Test empty iterator
//     */
//    @Test
//    public void testEmptyIterator() {
//
//        System.out.println("----Test Empty Iterator----");
//        Iterator<Integer> itr = bst.iterator();
//        for (int i : bst) {
//           System.out.println(i);
//        }
//        assertEquals(null, itr.next());
//        //assertFalse(itr.hasNext());
//    }
      @Test(expected = NoSuchElementException.class)
    public void testEmptyIterator() {
         System.out.println("----Test Empty Iterator----");
        Iterator<Integer> itr = bst.iterator();
        itr.next();
        
        //assertFalse(itr.hasNext());
    } // method testAddRemoveMismatch 

    @Test
    public void testIteratorConstructor() {
        System.out.println("------Test iterator constructor------");
        bst.add(50);
        bst.add(60);
        bst.add(40);
        bst.add(30);
        bst.add(20);
        Iterator<Integer> itr = bst.iterator();
        Integer expected = 20;
        Integer result = itr.next();
        assertEquals(expected, result);

    }

    /**
     * Test Iterator next method exception. If the next is null it should return
     * NoSuchElement exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextIsNull() {
        Iterator<Integer> itr = bst.iterator();
        itr.next();
    }

    /**
     * Test TreeIterator next method.
     */
    @Test
    public void testIteratorNext() {
        System.out.println("------------Test iterator next method-------");
        bst.add(50);
        bst.add(60);
        bst.add(40);
        bst.add(30);
        bst.add(20);
        Iterator<Integer> itr = bst.iterator();
        itr.next();
        itr.next();
        itr.next();
        Integer expected = 50;
        Integer result = itr.next();
        assertEquals(expected, result);

    }

    @Test
    public void testIteratorHasNext() {
        bst.add(50);
        bst.add(60);
        bst.add(40);
        Iterator<Integer> itr = bst.iterator();
        //assertEquals(true, itr.hasNext());
        assertTrue(itr.hasNext());
        itr.next();
        itr.next();
        itr.next();
        //assertEquals(false, itr.hasNext());
        assertFalse(itr.hasNext());

    }

    @Test(expected = IllegalStateException.class)
    public void testIterartorRemoveBeforeCallingNext() {
        System.out.println("----- Test iterator remove IllegalStateException------");
        bst.add(50);
        bst.add(60);
        bst.add(40);
        Iterator<Integer> itr = bst.iterator();
        itr.remove();
    }

    /**
     * Test Iterator remove method
     */
    @Test
    public void testRemove_iterator() {
        System.out.println("----- Test iterator remove------");
        bst.add(50);
        bst.add(60);
        bst.add(40);
        Iterator<Integer> itr = bst.iterator();
        itr.next();
        itr.remove();
        assertEquals(2, bst.size());
    }

    /**
     * Test of hashCode method, of class BinarySearchTree.
     */
    @Test
    public void testHashCode() {
        System.out.println("------Test hashCode------");
        BinarySearchTree<String> instance = new BinarySearchTree();
        BinarySearchTree<String> instance2 = new BinarySearchTree();
        instance.add("apple");
        instance2.add("apple");
        assertTrue(instance.equals(instance2) && instance2.equals(instance));
        assertTrue(instance.hashCode() == instance2.hashCode());

    }

    /**
     * Test of removeAll method, of class BinarySearchTree.
     */
    @Test
    public void testRemoveAll() {
        System.out.println("removeAll");
        for (int i = 1; i < 50; i++) {
            bst.add(i);
        }
        bst.removeAll();
        assertEquals(0, bst.size());

    }

}
