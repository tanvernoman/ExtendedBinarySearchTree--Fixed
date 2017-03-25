/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part1solution;

/**
 *
 * @author thnom
 */
public class Item implements Comparable<Item> {

    /**
     * Provides an immutable integer valued item that counts comparisons.
     */
    private static long compCount = 0;
    private final Integer value;

    /**
     * Constructor - creates an Item and sets its value
     *
     * @param value - the value for the Item
     */
    public Item(Integer value) {
        this.value = value;
    }

    /**
     * The value of this Item
     *
     * @return the Item's value
     */
    public Integer value() {
        return value;
    }

    /**
     * Compares the value of this Item with that of other according to the
     * contract for Comparable. Increments the count of comparisons.
     * @param other
     * @return 
     */
    @Override
    public int compareTo(Item other) {
        compCount++;
        return value.compareTo(other.value);
    }

    /**
     * Returns the total number of comparisons performed on instances of type
     * Item since the counter was last reset (or the total if it has never been
     * reset).
     *
     * @return the count of calls to compareTo() and equals() for type Item
     */
    public static long getCompCount() {
        return compCount;
    }

    /**
     * Resets the count of comparisons to zero.
     */
    public static void resetCompCount() {
        compCount = 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object that) {
        compCount++;
        if (this == that) {
            return true;
        }
        if (!(that instanceof Item)) {
            return false;
        }
        Item other = (Item) that;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
