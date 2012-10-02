package org.howardism.fpjava;

/**
 * A RealSet is nearer in concept to a mathematical set.  This is not
 * a fixed collection of numbers, but can contains an infinite number
 * of entries, for instance:
 *
 * <pre>
 * public static RealSet evens() {
 *   return new RealSet() {
 *     public boolean contains(final int n) { return n % 2 == 0; }
 *   };
 * }
 * </pre>
 *
 * @see RealSets
 * @author Howard Abrams <howard.abrams@gmail.com>
 */

interface RealSet {
    boolean contains(int i); 
}
