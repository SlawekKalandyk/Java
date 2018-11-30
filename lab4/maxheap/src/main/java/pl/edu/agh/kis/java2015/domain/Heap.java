package pl.edu.agh.kis.java2015.domain;

import java.util.ArrayList;
import java.util.Collections;

public class Heap <T extends Comparable<T>> {

    private int heapSize = 0;
    private ArrayList<T> tab = new ArrayList<>();

    public Heap() {
    }

    public Heap(ArrayList<T> arr) {
        tab = arr;
        heapSize = tab.size();
    }

    public void insert(T value) {
        int currentIndex = heapSize;
        int parentIndex = parentIndex(currentIndex);
        tab.add(value);
        while (isChildGreaterThanParent(currentIndex, parentIndex)) {
            swapElements(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = parentIndex(currentIndex);
        }
        heapSize++;
    }

    public boolean isChildGreaterThanParent(int currentIndex, int parentIndex) {
        return tab.get(currentIndex).compareTo(tab.get(parentIndex)) > 0;
    }

    public void swapElements(int currentIndex, int parentIndex) {
        T parentValue = parentValue(currentIndex);
        T currentValue = tab.get(currentIndex);
        tab.set(parentIndex, currentValue);
        tab.set(currentIndex, parentValue);
    }

    public T parentValue(int currentIndex) {
        return tab.get(parentIndex(currentIndex));
    }

    public int parentIndex(int currentIndex) {
        return (currentIndex - 1) / 2;
    }

    public int leftChildIndex(int currentIndex) {
        return (2 * currentIndex + 1);
    }

    public int rightChildIndex(int currentIndex) {
        return (2 * currentIndex + 2);
    }

    public T leftChildValue(int parentIndex) {
        return tab.get(leftChildIndex(parentIndex));
    }

    public T rightChildValue(int parentIndex) {
        return tab.get(rightChildIndex(parentIndex));
    }

    public int size() {
        return heapSize;
    }

    public T top() {
        return tab.get(0);
    }

    public T extract_max() {
        T extractedTop = top();
        this.delete_max();
        return extractedTop;
    }

    public void delete_max() {
        Collections.swap(tab, 0, heapSize - 1);
        tab.remove(heapSize - 1);
        --heapSize;
        heapify();
    }

    public void replace(T value) {
        T removedTop = this.top();
        tab.remove(removedTop);
        tab.add(0, value);

        if (removedTop.compareTo(value) > 0)
            heapify();
    }

    public void heapify() {
        for (int i = heapSize / 2; i >= 0; --i) {
            returnToHeap(i);
        }
    }

    public void returnToHeap(int index) {
        int largest = index;
        int leftChild = leftChildIndex(index);
        int rightChild = rightChildIndex(index);

        if (leftChild < heapSize) {
            if (tab.get(leftChild).compareTo(tab.get(index)) > 0)
                largest = leftChild;
        }

        if (rightChild < heapSize) {
            if (tab.get(rightChild).compareTo(tab.get(largest)) > 0)
                largest = rightChild;
        }
        if (largest != index) {
            Collections.swap(tab, index, largest);

            returnToHeap(largest);
        }
    }

    @SuppressWarnings("Duplicates")
    public static Heap merge(Heap heap1, Heap heap2) {
        Heap newHeap = new Heap();
        contentsOfOneHeapToAnother(newHeap, heap1);
        contentsOfOneHeapToAnother(newHeap, heap2);

        return newHeap;
    }

    public void meld(Heap heap) {
        contentsOfOneHeapToAnother(this, heap);
    }

    @SuppressWarnings("Duplicates")
    public static void contentsOfOneHeapToAnother(Heap to, Heap from) {
        to.insert(from.top());
        for (int i = 0; i < from.size() / 2; ++i) {
            if (from.leftChildIndex(i) < from.size()) {
                if (from.leftChildValue(i) != null)
                    to.insert(from.leftChildValue(i));
            }

            if (from.rightChildIndex(i) < from.size()) {
                if (from.rightChildValue(i) != null)
                    to.insert(from.rightChildValue(i));
            }
        }
    }

    @SuppressWarnings("Duplicates")
    public void printHeap() {
        System.out.print(this.top());
        System.out.print(" ");

        for (int i = 0; i < this.size() / 2; ++i) {
            if (this.leftChildIndex(i) < this.size()) {
                if (this.leftChildValue(i) != null) {
                    System.out.print(this.leftChildValue(i));
                    System.out.print(" ");
                }
            }

            if (this.rightChildIndex(i) < this.size()) {
                if (this.rightChildValue(i) != null) {
                    System.out.print(this.rightChildValue(i));
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }

}
