/*
 * Copyright 2022 Karma Krafts
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.karma.sliced.util;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Mostly modified functions copied straight from {@link java.util.Arrays},
 * to mitigate the need of copying arrays when hashing a slice.
 *
 * @author Alexander Hinze
 * @since 04/09/2022
 */
@API(status = Status.STABLE)
public final class MoreArrays {
    // @formatter:off
    private MoreArrays() {}
    // @formatter:on

    /**
     * Copies the given range from the given array reference.
     *
     * @param ref    The array to copy elements from.
     * @param offset The offset into the array from which to copy.
     * @param size   The number of elements to copy into the newly created array.
     * @return A new array with the given size, containing
     *         all the elements from the given array, at the given indices.
     */
    public static boolean[] copy(final boolean[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final boolean[] result = new boolean[size];
        System.arraycopy(ref, offset, result, 0, size);
        return result;
    }

    /**
     * Copies the given array.
     *
     * @param ref The array to copy.
     * @return A new array containing all elements from the given array.
     */
    public static boolean[] copy(final boolean[] ref) {
        return copy(ref, 0, ref.length);
    }

    /**
     * Copies the given range from the given array reference.
     *
     * @param ref    The array to copy elements from.
     * @param offset The offset into the array from which to copy.
     * @param size   The number of elements to copy into the newly created array.
     * @return A new array with the given size, containing
     *         all the elements from the given array, at the given indices.
     */
    public static byte[] copy(final byte[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final byte[] result = new byte[size];
        System.arraycopy(ref, offset, result, 0, size);
        return result;
    }

    /**
     * Copies the given array.
     *
     * @param ref The array to copy.
     * @return A new array containing all elements from the given array.
     */
    public static byte[] copy(final byte[] ref) {
        return copy(ref, 0, ref.length);
    }

    /**
     * Copies the given range from the given array reference.
     *
     * @param ref    The array to copy elements from.
     * @param offset The offset into the array from which to copy.
     * @param size   The number of elements to copy into the newly created array.
     * @return A new array with the given size, containing
     *         all the elements from the given array, at the given indices.
     */
    public static short[] copy(final short[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final short[] result = new short[size];
        System.arraycopy(ref, offset, result, 0, size);
        return result;
    }

    /**
     * Copies the given array.
     *
     * @param ref The array to copy.
     * @return A new array containing all elements from the given array.
     */
    public static short[] copy(final short[] ref) {
        return copy(ref, 0, ref.length);
    }

    /**
     * Copies the given range from the given array reference.
     *
     * @param ref    The array to copy elements from.
     * @param offset The offset into the array from which to copy.
     * @param size   The number of elements to copy into the newly created array.
     * @return A new array with the given size, containing
     *         all the elements from the given array, at the given indices.
     */
    public static int[] copy(final int[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final int[] result = new int[size];
        System.arraycopy(ref, offset, result, 0, size);
        return result;
    }

    /**
     * Copies the given array.
     *
     * @param ref The array to copy.
     * @return A new array containing all elements from the given array.
     */
    public static int[] copy(final int[] ref) {
        return copy(ref, 0, ref.length);
    }

    /**
     * Copies the given range from the given array reference.
     *
     * @param ref    The array to copy elements from.
     * @param offset The offset into the array from which to copy.
     * @param size   The number of elements to copy into the newly created array.
     * @return A new array with the given size, containing
     *         all the elements from the given array, at the given indices.
     */
    public static long[] copy(final long[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final long[] result = new long[size];
        System.arraycopy(ref, offset, result, 0, size);
        return result;
    }

    /**
     * Copies the given array.
     *
     * @param ref The array to copy.
     * @return A new array containing all elements from the given array.
     */
    public static long[] copy(final long[] ref) {
        return copy(ref, 0, ref.length);
    }

    /**
     * Copies the given range from the given array reference.
     *
     * @param ref    The array to copy elements from.
     * @param offset The offset into the array from which to copy.
     * @param size   The number of elements to copy into the newly created array.
     * @return A new array with the given size, containing
     *         all the elements from the given array, at the given indices.
     */
    public static float[] copy(final float[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final float[] result = new float[size];
        System.arraycopy(ref, offset, result, 0, size);
        return result;
    }

    /**
     * Copies the given array.
     *
     * @param ref The array to copy.
     * @return A new array containing all elements from the given array.
     */
    public static float[] copy(final float[] ref) {
        return copy(ref, 0, ref.length);
    }

    /**
     * Copies the given range from the given array reference.
     *
     * @param ref    The array to copy elements from.
     * @param offset The offset into the array from which to copy.
     * @param size   The number of elements to copy into the newly created array.
     * @return A new array with the given size, containing
     *         all the elements from the given array, at the given indices.
     */
    public static double[] copy(final double[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final double[] result = new double[size];
        System.arraycopy(ref, offset, result, 0, size);
        return result;
    }

    /**
     * Copies the given array.
     *
     * @param ref The array to copy.
     * @return A new array containing all elements from the given array.
     */
    public static double[] copy(final double[] ref) {
        return copy(ref, 0, ref.length);
    }

    /**
     * Copies the given range from the given array reference.
     *
     * @param ref    The array to copy elements from.
     * @param offset The offset into the array from which to copy.
     * @param size   The number of elements to copy into the newly created array.
     * @return A new array with the given size, containing
     *         all the elements from the given array, at the given indices.
     */
    public static char[] copy(final char[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final char[] result = new char[size];
        System.arraycopy(ref, offset, result, 0, size);
        return result;
    }

    /**
     * Copies the given array.
     *
     * @param ref The array to copy.
     * @return A new array containing all elements from the given array.
     */
    public static char[] copy(final char[] ref) {
        return copy(ref, 0, ref.length);
    }

    /**
     * Returns a hash code based on the contents of the specified array.
     * For any two <tt>boolean</tt> arrays <tt>a</tt> and <tt>b</tt>
     * such that <tt>Arrays.equals(a, b)</tt>, it is also the case that
     * <tt>Arrays.hashCode(a) == Arrays.hashCode(b)</tt>.
     * <p>The value returned by this method is the same value that would be
     * obtained by invoking the {@link List#hashCode() <tt>hashCode</tt>}
     * method on a {@link List} containing a sequence of {@link Boolean}
     * instances representing the elements of <tt>a</tt> in the same order.
     * If <tt>a</tt> is <tt>null</tt>, this method returns 0.
     *
     * @param ref    the array whose hash value to compute.
     * @param offset The offset into the array at which to start hashing.
     * @param size   The size of the element sequence to hash.
     * @return a content-based hash code for <tt>a</tt>.
     */
    public static int hashCode(final boolean[] ref, final int offset, final int size) {
        if (ref == null || offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final int maxIndex = offset + size;
        int result = 1;

        for (int i = offset; i < maxIndex; i++) {
            result = 31 * result + (ref[i] ? 1231 : 1237);
        }

        return result;
    }

    /**
     * Returns a hash code based on the contents of the specified array.
     * For any two <tt>byte</tt> arrays <tt>a</tt> and <tt>b</tt>
     * such that <tt>Arrays.equals(a, b)</tt>, it is also the case that
     * <tt>Arrays.hashCode(a) == Arrays.hashCode(b)</tt>.
     * <p>The value returned by this method is the same value that would be
     * obtained by invoking the {@link List#hashCode() <tt>hashCode</tt>}
     * method on a {@link List} containing a sequence of {@link Byte}
     * instances representing the elements of <tt>a</tt> in the same order.
     * If <tt>a</tt> is <tt>null</tt>, this method returns 0.
     *
     * @param ref    the array whose hash value to compute.
     * @param offset The offset into the array at which to start hashing.
     * @param size   The size of the element sequence to hash.
     * @return a content-based hash code for <tt>a</tt>.
     */
    public static int hashCode(final byte[] ref, final int offset, final int size) {
        if (ref == null || offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final int maxIndex = offset + size;
        int result = 1;

        for (int i = offset; i < maxIndex; i++) {
            result = 31 * result + ref[i];
        }

        return result;
    }

    /**
     * Returns a hash code based on the contents of the specified array.
     * For any two <tt>short</tt> arrays <tt>a</tt> and <tt>b</tt>
     * such that <tt>Arrays.equals(a, b)</tt>, it is also the case that
     * <tt>Arrays.hashCode(a) == Arrays.hashCode(b)</tt>.
     * <p>The value returned by this method is the same value that would be
     * obtained by invoking the {@link List#hashCode() <tt>hashCode</tt>}
     * method on a {@link List} containing a sequence of {@link Short}
     * instances representing the elements of <tt>a</tt> in the same order.
     * If <tt>a</tt> is <tt>null</tt>, this method returns 0.
     *
     * @param ref    the array whose hash value to compute.
     * @param offset The offset into the array at which to start hashing.
     * @param size   The size of the element sequence to hash.
     * @return a content-based hash code for <tt>a</tt>.
     */
    public static int hashCode(final short[] ref, final int offset, final int size) {
        if (ref == null || offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final int maxIndex = offset + size;
        int result = 1;

        for (int i = offset; i < maxIndex; i++) {
            result = 31 * result + ref[i];
        }

        return result;
    }

    /**
     * Returns a hash code based on the contents of the specified array.
     * For any two <tt>int</tt> arrays <tt>a</tt> and <tt>b</tt>
     * such that <tt>Arrays.equals(a, b)</tt>, it is also the case that
     * <tt>Arrays.hashCode(a) == Arrays.hashCode(b)</tt>.
     * <p>The value returned by this method is the same value that would be
     * obtained by invoking the {@link List#hashCode() <tt>hashCode</tt>}
     * method on a {@link List} containing a sequence of {@link Integer}
     * instances representing the elements of <tt>a</tt> in the same order.
     * If <tt>a</tt> is <tt>null</tt>, this method returns 0.
     *
     * @param ref    the array whose hash value to compute.
     * @param offset The offset into the array at which to start hashing.
     * @param size   The size of the element sequence to hash.
     * @return a content-based hash code for <tt>a</tt>.
     */
    public static int hashCode(final int[] ref, final int offset, final int size) {
        if (ref == null || offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final int maxIndex = offset + size;
        int result = 1;

        for (int i = offset; i < maxIndex; i++) {
            result = 31 * result + ref[i];
        }

        return result;
    }

    /**
     * Returns a hash code based on the contents of the specified array.
     * For any two <tt>long</tt> arrays <tt>a</tt> and <tt>b</tt>
     * such that <tt>Arrays.equals(a, b)</tt>, it is also the case that
     * <tt>Arrays.hashCode(a) == Arrays.hashCode(b)</tt>.
     * <p>The value returned by this method is the same value that would be
     * obtained by invoking the {@link List#hashCode() <tt>hashCode</tt>}
     * method on a {@link List} containing a sequence of {@link Long}
     * instances representing the elements of <tt>a</tt> in the same order.
     * If <tt>a</tt> is <tt>null</tt>, this method returns 0.
     *
     * @param ref    the array whose hash value to compute.
     * @param offset The offset into the array at which to start hashing.
     * @param size   The size of the element sequence to hash.
     * @return a content-based hash code for <tt>a</tt>.
     */
    public static int hashCode(final long[] ref, final int offset, final int size) {
        if (ref == null || offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final int maxIndex = offset + size;
        int result = 1;

        for (int i = offset; i < maxIndex; i++) {
            final long value = ref[i];
            result = 31 * result + (int) (value ^ (value >>> 32));
        }

        return result;
    }

    /**
     * Returns a hash code based on the contents of the specified array.
     * For any two <tt>float</tt> arrays <tt>a</tt> and <tt>b</tt>
     * such that <tt>Arrays.equals(a, b)</tt>, it is also the case that
     * <tt>Arrays.hashCode(a) == Arrays.hashCode(b)</tt>.
     * <p>The value returned by this method is the same value that would be
     * obtained by invoking the {@link List#hashCode() <tt>hashCode</tt>}
     * method on a {@link List} containing a sequence of {@link Float}
     * instances representing the elements of <tt>a</tt> in the same order.
     * If <tt>a</tt> is <tt>null</tt>, this method returns 0.
     *
     * @param ref    the array whose hash value to compute.
     * @param offset The offset into the array at which to start hashing.
     * @param size   The size of the element sequence to hash.
     * @return a content-based hash code for <tt>a</tt>.
     */
    public static int hashCode(final float[] ref, final int offset, final int size) {
        if (ref == null || offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final int maxIndex = offset + size;
        int result = 1;

        for (int i = offset; i < maxIndex; i++) {
            result = 31 * result + Float.floatToIntBits(ref[i]);
        }

        return result;
    }

    /**
     * Returns a hash code based on the contents of the specified array.
     * For any two <tt>double</tt> arrays <tt>a</tt> and <tt>b</tt>
     * such that <tt>Arrays.equals(a, b)</tt>, it is also the case that
     * <tt>Arrays.hashCode(a) == Arrays.hashCode(b)</tt>.
     * <p>The value returned by this method is the same value that would be
     * obtained by invoking the {@link List#hashCode() <tt>hashCode</tt>}
     * method on a {@link List} containing a sequence of {@link Double}
     * instances representing the elements of <tt>a</tt> in the same order.
     * If <tt>a</tt> is <tt>null</tt>, this method returns 0.
     *
     * @param ref    the array whose hash value to compute.
     * @param offset The offset into the array at which to start hashing.
     * @param size   The size of the element sequence to hash.
     * @return a content-based hash code for <tt>a</tt>.
     */
    public static int hashCode(final double[] ref, final int offset, final int size) {
        if (ref == null || offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final int maxIndex = offset + size;
        int result = 1;

        for (int i = offset; i < maxIndex; i++) {
            final long value = Double.doubleToLongBits(ref[i]);
            result = 31 * result + (int) (value ^ (value >>> 32));
        }

        return result;
    }

    /**
     * Returns a hash code based on the contents of the specified array.
     * For any two <tt>char</tt> arrays <tt>a</tt> and <tt>b</tt>
     * such that <tt>Arrays.equals(a, b)</tt>, it is also the case that
     * <tt>Arrays.hashCode(a) == Arrays.hashCode(b)</tt>.
     * <p>The value returned by this method is the same value that would be
     * obtained by invoking the {@link List#hashCode() <tt>hashCode</tt>}
     * method on a {@link List} containing a sequence of {@link Character}
     * instances representing the elements of <tt>a</tt> in the same order.
     * If <tt>a</tt> is <tt>null</tt>, this method returns 0.
     *
     * @param ref    the array whose hash value to compute.
     * @param offset The offset into the array at which to start hashing.
     * @param size   The size of the element sequence to hash.
     * @return a content-based hash code for <tt>a</tt>.
     */
    public static int hashCode(final char[] ref, final int offset, final int size) {
        if (ref == null || offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        int result = 1;

        for (int i = 0; i < size; i++) {
            result = 31 * result + (int) ref[offset + i];
        }

        return result;
    }

    /**
     * Returns a hash code based on the contents of the specified array.
     * For any two <tt>Object</tt> arrays <tt>a</tt> and <tt>b</tt>
     * such that <tt>Arrays.equals(a, b)</tt>, it is also the case that
     * <tt>Arrays.hashCode(a) == Arrays.hashCode(b)</tt>.
     * <p>The value returned by this method is the same value that would be
     * obtained by invoking the {@link List#hashCode() <tt>hashCode</tt>}
     * method on a {@link List} containing a sequence of {@link Object}
     * instances representing the elements of <tt>a</tt> in the same order.
     * If <tt>a</tt> is <tt>null</tt>, this method returns 0.
     *
     * @param ref    the array whose hash value to compute.
     * @param offset The offset into the array at which to start hashing.
     * @param size   The size of the element sequence to hash.
     * @return a content-based hash code for <tt>a</tt>.
     */
    public static int hashCode(final @NotNull Object[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        int result = 1;

        for (int i = 0; i < size; i++) {
            result = 31 * result + ref[offset + i].hashCode();
        }

        return result;
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(long)</tt>.  Returns <tt>"null"</tt> if <tt>ref</tt>
     * is <tt>null</tt>.
     *
     * @param ref    the array whose string representation to return.
     * @param offset The offset into the array at which to start the conversion.
     * @param size   The size of the element sequence to convert.
     * @return a string representation of <tt>ref</tt>.
     */
    public static @NotNull String toString(final boolean[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < size; i++) {
            builder.append(ref[i]);

            if (i < size - 1) {
                builder.append(',');
            }
        }

        return builder.append(']').toString();
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(long)</tt>.  Returns <tt>"null"</tt> if <tt>ref</tt>
     * is <tt>null</tt>.
     *
     * @param ref    the array whose string representation to return.
     * @param offset The offset into the array at which to start the conversion.
     * @param size   The size of the element sequence to convert.
     * @return a string representation of <tt>ref</tt>.
     */
    public static @NotNull String toString(final byte[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < size; i++) {
            builder.append(ref[i]);

            if (i < size - 1) {
                builder.append(',');
            }
        }

        return builder.append(']').toString();
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(long)</tt>.  Returns <tt>"null"</tt> if <tt>ref</tt>
     * is <tt>null</tt>.
     *
     * @param ref    the array whose string representation to return.
     * @param offset The offset into the array at which to start the conversion.
     * @param size   The size of the element sequence to convert.
     * @return a string representation of <tt>ref</tt>.
     */
    public static @NotNull String toString(final short[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < size; i++) {
            builder.append(ref[i]);

            if (i < size - 1) {
                builder.append(',');
            }
        }

        return builder.append(']').toString();
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(long)</tt>.  Returns <tt>"null"</tt> if <tt>ref</tt>
     * is <tt>null</tt>.
     *
     * @param ref    the array whose string representation to return.
     * @param offset The offset into the array at which to start the conversion.
     * @param size   The size of the element sequence to convert.
     * @return a string representation of <tt>ref</tt>.
     */
    public static @NotNull String toString(final int[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < size; i++) {
            builder.append(ref[i]);

            if (i < size - 1) {
                builder.append(',');
            }
        }

        return builder.append(']').toString();
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(long)</tt>.  Returns <tt>"null"</tt> if <tt>ref</tt>
     * is <tt>null</tt>.
     *
     * @param ref    the array whose string representation to return.
     * @param offset The offset into the array at which to start the conversion.
     * @param size   The size of the element sequence to convert.
     * @return a string representation of <tt>ref</tt>.
     */
    public static @NotNull String toString(final long[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < size; i++) {
            builder.append(ref[i]);

            if (i < size - 1) {
                builder.append(',');
            }
        }

        return builder.append(']').toString();
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(long)</tt>.  Returns <tt>"null"</tt> if <tt>ref</tt>
     * is <tt>null</tt>.
     *
     * @param ref    the array whose string representation to return.
     * @param offset The offset into the array at which to start the conversion.
     * @param size   The size of the element sequence to convert.
     * @return a string representation of <tt>ref</tt>.
     */
    public static @NotNull String toString(final float[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < size; i++) {
            builder.append(ref[i]);

            if (i < size - 1) {
                builder.append(',');
            }
        }

        return builder.append(']').toString();
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(long)</tt>.  Returns <tt>"null"</tt> if <tt>ref</tt>
     * is <tt>null</tt>.
     *
     * @param ref    the array whose string representation to return.
     * @param offset The offset into the array at which to start the conversion.
     * @param size   The size of the element sequence to convert.
     * @return a string representation of <tt>ref</tt>.
     */
    public static @NotNull String toString(final double[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < size; i++) {
            builder.append(ref[i]);

            if (i < size - 1) {
                builder.append(',');
            }
        }

        return builder.append(']').toString();
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(long)</tt>.  Returns <tt>"null"</tt> if <tt>ref</tt>
     * is <tt>null</tt>.
     *
     * @param ref    the array whose string representation to return.
     * @param offset The offset into the array at which to start the conversion.
     * @param size   The size of the element sequence to convert.
     * @return a string representation of <tt>ref</tt>.
     */
    public static @NotNull String toString(final char[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < size; i++) {
            builder.append(ref[i]);

            if (i < size - 1) {
                builder.append(',');
            }
        }

        return builder.append(']').toString();
    }

    /**
     * Returns a string representation of the contents of the specified array.
     * The string representation consists of a list of the array's elements,
     * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements are
     * separated by the characters <tt>", "</tt> (a comma followed by a
     * space).  Elements are converted to strings as by
     * <tt>String.valueOf(long)</tt>.  Returns <tt>"null"</tt> if <tt>ref</tt>
     * is <tt>null</tt>.
     *
     * @param ref    the array whose string representation to return.
     * @param offset The offset into the array at which to start the conversion.
     * @param size   The size of the element sequence to convert.
     * @return a string representation of <tt>ref</tt>.
     */
    public static @NotNull String toString(final @NotNull Object[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < size; i++) {
            builder.append(ref[i]);

            if (i < size - 1) {
                builder.append(',');
            }
        }

        return builder.append(']').toString();
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Boolean[] box(final boolean[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final Boolean[] result = new Boolean[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly box
        }

        return result;
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Boolean[] box(final boolean[] ref) {
        return box(ref, 0, ref.length);
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Byte[] box(final byte[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final Byte[] result = new Byte[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly box
        }

        return result;
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Byte[] box(final byte[] ref) {
        return box(ref, 0, ref.length);
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Short[] box(final short[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final Short[] result = new Short[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly box
        }

        return result;
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Short[] box(final short[] ref) {
        return box(ref, 0, ref.length);
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Integer[] box(final int[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final Integer[] result = new Integer[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly box
        }

        return result;
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Integer[] box(final int[] ref) {
        return box(ref, 0, ref.length);
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Long[] box(final long[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final Long[] result = new Long[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly box
        }

        return result;
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Long[] box(final long[] ref) {
        return box(ref, 0, ref.length);
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Float[] box(final float[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final Float[] result = new Float[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly box
        }

        return result;
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Float[] box(final float[] ref) {
        return box(ref, 0, ref.length);
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Double[] box(final double[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final Double[] result = new Double[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly box
        }

        return result;
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Double[] box(final double[] ref) {
        return box(ref, 0, ref.length);
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Character[] box(final char[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final Character[] result = new Character[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly box
        }

        return result;
    }

    /**
     * Boxes all values from the given array range,
     * and returns them in a new array of the relative boxed type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         wrapped as a boxed type instance.
     */
    public static @NotNull Character[] box(final char[] ref) {
        return box(ref, 0, ref.length);
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static boolean[] unbox(final @NotNull Boolean[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final boolean[] result = new boolean[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly unbox
        }

        return result;
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static boolean[] unbox(final @NotNull Boolean[] ref) {
        return unbox(ref, 0, ref.length);
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static byte[] unbox(final @NotNull Byte[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final byte[] result = new byte[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly unbox
        }

        return result;
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static byte[] unbox(final @NotNull Byte[] ref) {
        return unbox(ref, 0, ref.length);
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static short[] unbox(final @NotNull Short[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final short[] result = new short[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly unbox
        }

        return result;
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static short[] unbox(final @NotNull Short[] ref) {
        return unbox(ref, 0, ref.length);
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static int[] unbox(final @NotNull Integer[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly unbox
        }

        return result;
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static int[] unbox(final @NotNull Integer[] ref) {
        return unbox(ref, 0, ref.length);
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static long[] unbox(final @NotNull Long[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final long[] result = new long[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly unbox
        }

        return result;
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static long[] unbox(final @NotNull Long[] ref) {
        return unbox(ref, 0, ref.length);
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static float[] unbox(final @NotNull Float[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final float[] result = new float[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly unbox
        }

        return result;
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static float[] unbox(final @NotNull Float[] ref) {
        return unbox(ref, 0, ref.length);
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static double[] unbox(final @NotNull Double[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final double[] result = new double[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly unbox
        }

        return result;
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static double[] unbox(final @NotNull Double[] ref) {
        return unbox(ref, 0, ref.length);
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref    The array to box.
     * @param offset The offset into the array to box.
     * @param size   The number of elements to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static char[] unbox(final @NotNull Character[] ref, final int offset, final int size) {
        if (offset < 0 || size < 0 || size > ref.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final char[] result = new char[size];

        for (int i = 0; i < size; i++) {
            result[i] = ref[offset + i]; // Implicitly unbox
        }

        return result;
    }

    /**
     * Unboxes all values from the given array range,
     * and returns them in a new array of the relative primitive type.
     *
     * @param ref The array to box.
     * @return A new array containing all values in the given range,
     *         as their natural primitive type.
     */
    public static char[] unbox(final @NotNull Character[] ref) {
        return unbox(ref, 0, ref.length);
    }

    /**
     * Checks whether all element references in the given range of {@code a1}
     * are equal to the element references in {@code a2}.
     *
     * @param a1     The first array to compare references from.
     * @param offset The offset into the first array from which to compare references.
     * @param size   The number of element references to compare.
     * @param a2     The second array to compare references from.
     * @return True if all element references are equal.
     */
    public static boolean refsEqual(final @NotNull Object[] a1, final int offset, final int size, final @NotNull Object[] a2) {
        if (offset < 0 || size < 0 || size > a1.length || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        for (int i = 0; i < size; i++) {
            if (a1[offset + i] != a2[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether all element references in the given range of {@code a1}
     * are equal to the element references in {@code a2}.
     *
     * @param a1 The first array to compare references from.
     * @param a2 The second array to compare references from.
     * @return True if all element references are equal.
     */
    public static boolean refsEqual(final @NotNull Object[] a1, final @NotNull Object[] a2) {
        return refsEqual(a1, 0, a1.length, a2);
    }
}
