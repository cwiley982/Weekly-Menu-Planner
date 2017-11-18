package personal_projects.weekly_menu_planner.util;

/**
 * Creates a list by linking nodes together
 * 
 * @author Caitlyn Wiley
 *
 * @param <E>
 *            generic parameter
 */
public class ArrayList<E extends Comparable<E>> {
    
    private E[] data;
    private int size;
    private E[] temp;
    private E[] merged;
    
    private static final int INIT_SIZE = 16;
    private static final int RESIZE = 2;
    
    /**
     * Constructs an array list
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        data = (E[]) (new Comparable[INIT_SIZE]);
        size = 0;
    }
    
    /**
     * Adds an element to the list
     * 
     * @param element
     *            element to add to the list
     * @return true if element can be added
     */
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == data.length) {
            growArray();
        }
        data[size] = element;
        size++;
        return true;
    }
    
    @SuppressWarnings("unchecked")
    private void growArray() {
        temp = (E[]) new Comparable[data.length * RESIZE];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }
    
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size] = null;
        size--;
        return true;
    }
    
    /**
     * Sorts the list by calling mergeSort
     */
    @SuppressWarnings("unchecked")
    public void sort() {
        temp = (E[]) new Comparable[size];
        merged = (E[]) new Comparable[size];
        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        mergeSort(0, temp.length - 1, temp);
        data = merged;
    }
    
    /**
     * Breaks the list into left and right recursively until each side only has
     * one node, then combines left and right and sorts as it goes
     * 
     * @param min
     *            the minimum index for the current chunk of the list being
     *            sorted
     * @param max
     *            the maximum index for the current chunk of the list being
     *            sorted
     * @param temp
     *            the list to split in half recursively then sort
     */
    public void mergeSort(int min, int max, E[] temp) {
        if (min < max) {
            // Find the middle point
            int mid = (min + max) / 2;
            
            // Sort first and second halves
            mergeSort(min, mid, temp);
            mergeSort(mid + 1, max, temp);
            
            // Merge the sorted halves
            mergeParts(min, mid, max, temp);
        }
    }
    
    /**
     * Merges the two halves of a list
     * 
     * @param min
     *            the minimum index to look at
     * @param mid
     *            the middle index to look at
     * @param max
     *            the max index to look at
     * @param temp
     *            the array to read from
     */
    public void mergeParts(int min, int mid, int max, E[] temp) {
        
        int leftIndex = min;
        int rightIndex = mid + 1;
        int mergedIndex = min;
        
        while (leftIndex <= mid && rightIndex <= max) {
            if (temp[leftIndex].compareTo(temp[rightIndex]) < 0) {
                merged[mergedIndex] = temp[leftIndex];
                
                leftIndex++;
            } else {
                merged[mergedIndex] = temp[rightIndex];
                rightIndex++;
            }
            mergedIndex++;
        }
        
        // copy anything left over from left
        while (leftIndex <= mid) {
            merged[mergedIndex] = temp[leftIndex];
            leftIndex++;
            mergedIndex++;
        }
        // Copy anything leftover from right
        while (rightIndex <= max) {
            merged[mergedIndex] = temp[rightIndex];
            rightIndex++;
            mergedIndex++;
        }
        
        // copy sorted chunk from merged into temp
        int mergedStart = min;
        while (mergedStart <= max) {
            temp[mergedStart] = merged[mergedStart];
            mergedStart++;
        }
    }
    
    /**
     * Searches recursively to find the element by narrowing down the list to
     * half of the previous list each time
     * 
     * @param min
     *            minimum index value
     * @param max
     *            maximum index value
     * @param element
     *            element to search for
     * @return index where element was found, -1 if it wasn't found
     */
    public int binarySearch(int min, int max, E element) {
        if (min > max) {
            return -1;
        }
        int mid = ((max - min) / 2) + min;
        E middle = data[mid];
        if (element.compareTo(middle) == 0) {
            return mid;
        } else if (element.compareTo(middle) < 0) {
            return binarySearch(min, mid - 1, element);
        } else {
            return binarySearch(mid + 1, max, element);
        }
    }
    
    /**
     * Gets the element at the index given
     * 
     * @param index
     *            index to get element for
     * @return the element found at the index
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        return data[index];
    }
    
    /**
     * Gets size of list
     * 
     * @return size of list
     */
    public int size() {
        return size;
    }
}
