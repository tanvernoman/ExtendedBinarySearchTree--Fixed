package part1solution;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import part1solution.BinarySearchTree.Entry;

/**
 * A class that represents a binary search tree.
 *
 * @author William Collins, with some minor modifications by Richard Beeby
 * @author (B00316640)
 * @param <E> the element type for the tree
 */
public class BinarySearchTree<E extends Comparable<? super E>>
        extends AbstractSet<E> implements Serializable {

    private static final long serialVersionUID = 1234L;

    // The root of this tree. This is null when the tree is empty.
    transient protected Entry<E> root;

    // The number of entries in this tree. This is zero when the tree is empty.
    transient protected int size;

    // Counts the number of modifications made to this tree
    // other than by the iterator.
    protected int modCount = 0;

    /**
     * Initializes this BinarySearchTree object to be empty, to contain only
     * elements of type E, to be ordered by the Comparable interface, and to
     * contain no duplicate elements.
     *
     */
    public BinarySearchTree() {

        root = new Entry<>(null);
        size = 0;
    } // default constructor

    /**
     * Initializes this BinarySearchTree object to contain a shallow copy of a
     * specified BinarySearchTree object. The worstTime(n) is O(n), where n is
     * the number of elements in the specified BinarySearchTree object.
     *
     * @param otherTree - the specified BinarySearchTree object that this
     * BinarySearchTree object will be assigned a shallow copy of.
     */
    public BinarySearchTree(BinarySearchTree<? extends E> otherTree) {
        root = copy(otherTree.root, null);
        size = otherTree.size;
    } // copy constructor

    protected Entry<E> copy(Entry<? extends E> p, Entry<E> parent) {
        if (p != null) {
            Entry<E> q = new Entry<>(p.element, parent);
            q.left = copy(p.left, q);
            q.right = copy(p.right, q);
            return q;
        } // if
        return null;
    } // method copy

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BinarySearchTree)) {
            return false;
        }
        return equals(root, ((BinarySearchTree<? extends E>) obj).root);
    } // method 1-parameter equals

    public boolean equals(Entry<E> p, Entry<? extends E> q) {
        if (p == null || q == null) {
            return p == q;
        }
        if (p.isExternal() || q.isExternal()) {
            return p.isExternal() && q.isExternal();
        }
        if (!p.element.equals(q.element)) {
            return false;
        }
        return equals(p.left, q.left) && equals(p.right, q.right);

    } // method 2-parameter equals

    /**
     * Returns the hash code value for this set. The hash code of a set is
     * defined to be the sum of the hash codes of the elements in the set, where
     * the hash code of a <tt>null</tt> element is defined to be zero. This
     * ensures that <tt>s1.equals(s2)</tt> implies that
     * <tt>s1.hashCode()==s2.hashCode()</tt> for any two sets <tt>s1</tt>
     * and <tt>s2</tt>, as required by the general contract of
     * {@link Object#hashCode}.
     *
     * <p>
     * This implementation iterates over the set, calling the
     * <tt>hashCode</tt> method on each element in the set, and adding up the
     * results.
     *
     * @return the hash code value for this set
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns the size of this BinarySearchTree object.
     *
     * @return the size of this BinarySearchTree object.
     *
     */
    @Override
    public int size() {
        return size;
    } // method size()

    /**
     * Determines the tree is empty or not
     *
     * @return true if the size is zero else false
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }//End of method isEmpty

    /**
     * This method removes all node and make the size 0
     */
    public void removeAll() {
        this.root = null;
        this.size = 0;

    }

//    public int ExternalPathLength(){
//        
//        if(isEmpty()){
//            return 0;
//        }
//         return   getPathLength(root);
//        
//        
//    }
//    private int getPathLength(Entry<E> node) {
//       if(node.getLeft().isExternal() && node.getRight().isExternal()){
//           return 0;
//       }
//       
//       
//       return getPathLength(node.getLeft()) + getPathLength(node.getRight());
//       
//    }
    /**
     * Returns an iterator positioned at the smallest element in this
     * BinarySearchTree object.
     *
     * @return an iterator positioned at the smallest element in this
     * BinarySearchTree object.
     *
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeIterator();
    } // method iterator

    /**
     * Determines if there is an element in this BinarySearchTree object that
     * equals a specified element. The worstTime(n) is O(n) and averageTime(n)
     * is O(log n).
     *
     * @param obj - the element sought in this BinarySearchTree object.
     *
     * @return true - if there is an element in this BinarySearchTree object
     * that equals obj; otherwise, return false.
     *
     * @throws ClassCastException - if obj cannot be compared to the elements in
     * this BinarySearchTree object.
     * @throws NullPointerException - if obj is null.
     *
     */
    //@Override
    @Override
    public boolean contains(Object obj) {
        if (obj == null) {
            throw new NullPointerException("argument to contains() is null");
        }
        // Entry e = getEntry(obj);
        // System.out.println(e.element);
        return getEntry(obj).element != null;
    } // method contains

    /**
     * Ensures that this BinarySearchTree object contains a specified element.
     * The worstTime(n) is O(n) and averageTime(n) is O(log n).
     *
     * @param element - the element whose presence is ensured in this
     * BinarySearchTree object.
     *
     * @return true - if this BinarySearchTree object changed as a result of
     * this method call (that is, if element was actually inserted); otherwise,
     * return false.
     *
     * @throws ClassCastException - if element cannot be compared to the
     * elements already in this BinarySearchTree object.
     * @throws NullPointerException - if element is null.
     *
     */
    @Override
    public boolean add(E element) {
        Entry<E> entry = getEntry(element);
        // System.out.println(entry);
        if (entry.isExternal()) {
            entry.makeInternal(element);
            size++;
            modCount++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Ensures that this BinarySearchTree object does not contain a specified
     * element. The worstTime(n) is O(n) and averageTime(n) is O(log n).
     *
     * @param obj - the object whose absence is ensured in this BinarySearchTree
     * object.
     *
     * @return true - if this BinarySearchTree object changed as a result of
     * this method call (that is, if obj was actually removed); otherwise,
     * return false.
     *
     * @throws ClassCastException - if obj cannot be compared to the elements
     * already in this BinarySearchTree object.
     * @throws NullPointerException - if obj is null.
     *
     */
    @Override
    public boolean remove(Object obj) {
        Entry<E> e = getEntry(obj);
        if (e.element == null) {
            return false;
        }
        deleteEntry(e);
        modCount++;
        return true;
    } // method remove

    /**
     * Finds the Entry object that houses a specified element, if there is such
     * an Entry. The worstTime(n) is O(n), and averageTime(n) is O(log n).
     *
     * @param obj - the element whose Entry is sought.
     *
     * @return the Entry object that houses obj - if there is such an Entry;
     * otherwise, return null.
     *
     * @throws ClassCastException - if obj is not comparable to the elements
     * already in this BinarySearchTree object.
     * @throws NullPointerException - if obj is null.
     *
     */
    protected Entry<E> getEntry(Object obj) {
        int comp;

        if (obj == null) {
            throw new NullPointerException();
        }

        Entry<E> e = root;

        while (!e.isExternal()) {
            comp = ((Comparable<? super E>) obj).compareTo(e.element);

            if (comp == 0) {
                return e;

            } else if (comp < 0) {
                e = e.left;

            } else {
                e = e.right;
            }
        } // while

        return e;
    } // method getEntry

    /**
     * Deletes the element in a specified Entry object from this
     * BinarySearchTree.
     *
     * @param p The Entry object whose element is to be deleted from this
     * BinarySearchTree object.
     *
     * @return the Entry object that was actually deleted from this
     * BinarySearchTree object.
     *
     */
    protected Entry<E> deleteEntry(Entry<E> p) {

        size--;
        // If p has two children, replace p's element with p's successor's
        // element, then make p reference that successor.
        if (!p.left.isExternal() && !p.right.isExternal()) {
            Entry<E> s = successor(p);
            p.element = s.element;
            p = s;
        } // p had two children

        // At this point, p has either no children or one child.
        Entry<E> replacement = null;

        if (!p.left.isExternal()) {
            replacement = p.left;
        } else if (!p.right.isExternal()) {
            replacement = p.right;

        }
        // If p has at least one child, link replacement to p.parent.
        if (replacement != null) {
            replacement.parent = p.parent;
            if (p.parent == null) {
                root = replacement;
            } else if (p == p.parent.left) {
                p.parent.left = replacement;
            } else {
                p.parent.right = replacement;
            }
        } // p has at least one child  
        else if (p.parent == null) {
            root.makeExternal();
        } else {
            p.makeExternal();
        } // p has a parent but no children

        return p;
    } // method deleteEntry

    /**
     * Finds the successor of a specified Entry object in this BinarySearchTree.
     * The worstTime(n) is O(n) and averageTime(n) is constant.
     *
     * @param e - the Entry object whose successor is to be found.
     *
     * @return the successor of e if e has a successor; otherwise, return null.
     *
     */
    protected Entry<E> successor(Entry<E> e) {
        if (e.isExternal()) {
            return null;
        } else if (!e.right.isExternal()) {
            // successor is leftmost Entry in right subtree of e
            Entry<E> p = e.right;
            while (!p.left.isExternal()) {  //p.left.isExternal
                p = p.left;
            }
            return p;

        } // e has a right child
        else {
            // go up the tree to the left as far as possible, then go up
            // to the right.
            Entry<E> p = e.parent;
            Entry<E> ch = e;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            } // while
            return p;
        } // e has no right child
    } // method successor

    /**
     * Determines the height of the tree
     *
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {

        return height(root);

    }

    protected int height(Entry<E> x) {

        if (x.isExternal()) {
            return -1;
        }
        return 1 + Math.max(height(x.getLeft()), height(x.getRight()));

    }

    /**
     * Breadth first traversal visit the trees element level by level
     */
    public void BFT() {
        if (!isEmpty()) {
            breadthFirstTraversal(this.root);
        }
    }

    private void breadthFirstTraversal(Entry<E> t) {

        Queue<Entry<E>> q = new ArrayDeque<>();
        if (!t.isExternal()) {
            q.add(t);
            System.out.print("[");
            while (!q.isEmpty()) {
                Entry n = q.remove();

                if (!n.left.isExternal()) {
                    q.add(n.getLeft());
                }
                if (!n.right.isExternal()) {
                    q.add(n.getRight());
                }
                System.out.print(n.getElement() + ",");

            }
            System.out.print("\b]");
        }
    }

    /**
     * Overriding the writeObject method of ISerializable interface to serialize
     * Extended binary search tree
     *
     * @param os
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream os) throws IOException {
        os.defaultWriteObject();
        os.writeInt(size);
        Queue<Entry<E>> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Entry n = q.remove();

            os.writeObject(n.getElement());

            if (!n.left.isExternal()) {
                q.add(n.getLeft());
            }
            if (!n.right.isExternal()) {
                q.add(n.getRight());
            }
        }
    }

    /**
     * Overriding the readObject method of ISerializable interface to serialize
     * extended binary search tree
     *
     * @param is
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
        is.defaultReadObject();
        int s = is.readInt();
        root = new Entry<>();
        System.out.println("size of the tree : "+s);
        for (int i = 0; i < s; i++) {
            E copy = (E) is.readObject();
            if (copy != null) {
                add(copy);
            }
        }

    }
    // http://stackoverflow.com/questions/3878456/determine-the-internal-path-length-of-a-tree-c

    /**
     * Finding the internal pathlength of binary search tree
     *
     * @param r
     * @param value
     * @return
     */
    public int internalpathlength() {
        if (root == null) {
            return 0;
        }
        return ipl(root, 0);
    }

    public int ipl(Entry<E> r, int value) {

        if (r.left == null && r.right == null) {
            return value + 1;
        }
        return value
                + ipl(r.left, value + 1)
                + ipl(r.right, value + 1);
    }

    /**
     * Finding the external pathlength of binary search tree
     *
     * @return
     */
    public int epl() {
        return internalpathlength() + 2 * size;
    }

    /**
     * Counting the number of leaves
     *
     * @param node
     * @return
     */
    private int countLeaves(Entry<E> node) {
        if (node.isExternal()) {
            return 0;
        }
        if (node.isLeaf()) {
            return 1;
        }
        int count = countLeaves(node.left);
        count += countLeaves(node.right);
        return count;
    }

    public int leaves() {
        return countLeaves(root);
    }

    protected class TreeIterator implements Iterator<E> {

        protected Entry<E> lastReturned = null,
                next;

        protected int modCountOnCreation;

        /**
         * Positions this TreeIterator to the smallest element, according to the
         * Comparable interface, in the BinarySearchTree object. The
         * worstTime(n) is O(n) and averageTime(n) is O(log n).
         *
         */
        protected TreeIterator() {
            //  if (root != null) {

            next = root;

            if (!next.isExternal()) {
                while (!next.left.isExternal()) {
                    next = next.left;
                }
            }
            modCountOnCreation = modCount;
            // default constructor
            // }
        }

        /**
         * Determines if there are still some elements, in the BinarySearchTree
         * object this TreeIterator object is iterating over, that have not been
         * accessed by this TreeIterator object.
         *
         * @return true - if there are still some elements that have not been
         * accessed by this TreeIterator object; otherwise, return false.
         *
         */
        @Override
        public boolean hasNext() {
            return next != null && !next.isExternal();
        } // method hasNext

        /**
         * Returns the element in the Entry this TreeIterator object was
         * positioned at before this call, and advances this TreeIterator
         * object. The worstTime(n) is O(n) and averageTime(n) is constant.
         *
         * @return the element this TreeIterator object was positioned at before
         * this call.
         *
         * @throws NoSuchElementException - if this TreeIterator object was not
         * positioned at an Entry before this call.
         * @throws IllegalStateException - if the tree has been modified by a
         * call to the tree's add() or remove() method since this TreeIterator
         * was created.
         *
         */
        @Override
        public E next() {
            if (modCountOnCreation != modCount) {
                throw new ConcurrentModificationException();
            }
            if (next == null || next.isExternal()) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            next = successor(next);
            // successor(next);

            return lastReturned.element;

        } // method next

        /**
         * Removes the element returned by the most recent call to this
         * TreeIterator object's next() method. The worstTime(n) is O(n) and
         * averageTime(n) is constant.
         *
         * @throws IllegalStateException - if this TreeIterator's next() method
         * was not called before this call, or if this TreeIterator's remove()
         * method was called between the call to the next() method and this
         * call.
         * @throws ConcurrentModificationException if the tree has been modified
         * by a call to the tree's add() or remove() method since this
         * TreeIterator was created.
         */
        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            if (modCountOnCreation != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!lastReturned.left.isExternal() && !lastReturned.right.isExternal()) {
                next = lastReturned;
            }
            deleteEntry(lastReturned);
            lastReturned = null;
        } // method remove     

    } // class TreeIterator

    protected static class Entry<E> {

        public E getElement() {
            return element;
        }

        protected Entry<E> getLeft() {
            return left;
        }

        protected Entry<E> getRight() {
            return right;
        }

        protected E element;

        protected Entry<E> left = null,
                right = null,
                parent;

        /**
         * Initializes this Entry object.
         *
         * This default constructor is defined for the sake of subclasses of the
         * BinarySearchTree class.
         */
        public Entry() {
        }

        public Entry(Entry<E> parent) {
            this.parent = parent;
            this.element = null;
            this.left = null;
            this.right = null;
        }

        /**
         * Initializes this Entry object from element and parent.
         *
         * @param element
         * @param parent
         */
        public Entry(E element, Entry<E> parent) {
            this.element = element;
            this.parent = parent;
            this.left = new Entry<>(this);
            this.right = new Entry<>(this);
        } // constructor

        //constructor
        /**
         * @return @returns true if this entry is an external node and false
         * otherwise
         *
         */
        public boolean isExternal() {
            return this.element == null;
        }

        /**
         * converts internal node to an external node and returns the element
         * that the internal node contained (used when deleting an internal node
         * that has no internal nodes as children)
         *
         * @return
         */
        public E makeExternal() {
            if (this.isExternal()) {
                throw new IllegalStateException();
            }
            if (!this.left.isExternal() || !this.right.isExternal()) {
                throw new IllegalStateException();
            }
            E e = this.element;
            this.element = null;
            this.left = null;
            this.right = null;
            return e;

        }

        /**
         * converts external node to an internal node containing the given
         * element and adds two new external nodes as the left and right
         * children of the node (used when inserting an element into the tree)
         *
         * @param element
         */
        public void makeInternal(E element) {
            if (!this.isExternal()) {
                throw new IllegalStateException();
            }
            this.element = element;
            this.left = new Entry<>(null, this);
            this.right = new Entry<>(null, this);

        }

        public boolean isLeaf() {
            return !isExternal()
                    && (left.isExternal() && right.isExternal());
        }
    } // class Entry

} // class BinarySearchTree

