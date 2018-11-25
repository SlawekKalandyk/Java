package pl.edu.agh.kis.java2015.domain;

import java.util.ArrayList;
import java.util.Collections;

public class Heap {

    private int heapSize = 0;
    private ArrayList<Double> tab = new ArrayList<>();

    public Heap() {
    }

    public Heap(ArrayList<Double> arr) {
        tab = arr;
        heapSize = tab.size();
    }

    public void insert(double value) {
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
        return tab.get(currentIndex) > tab.get(parentIndex);
    }

    public void swapElements(int currentIndex, int parentIndex) {
        Double parentValue = parentValue(currentIndex);
        Double currentValue = tab.get(currentIndex);
        tab.set(parentIndex, currentValue);
        tab.set(currentIndex, parentValue);
    }

    public Double parentValue(int currentIndex) {
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

    public Double leftChildValue(int parentIndex) {
        return tab.get(leftChildIndex(parentIndex));
    }

    public Double rightChildValue(int parentIndex) {
        return tab.get(rightChildIndex(parentIndex));
    }

    public int size() {
        return heapSize;
    }

    public double top() {
        return tab.get(0);
    }

    public double extract_max() {
        Double extractedTop = top();
        this.delete_max();
        return extractedTop;
    }

    public void delete_max() {
        Collections.swap(tab, 0, heapSize - 1);
        tab.remove(heapSize - 1);
        --heapSize;
        heapify();
    }

    public void replace(Double value) {
        Double removedTop = this.top();
        tab.remove(removedTop);
        tab.add(0, value);

        if (value < removedTop)
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
            if (tab.get(leftChild) > tab.get(index))
                largest = leftChild;
        }

        if (rightChild < heapSize) {
            if (tab.get(rightChild) > tab.get(largest))
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
        /*
        newHeap.insert(heap1.top());
        for (int i = 0; i < heap1.size() / 2; ++i) {
            if (heap1.leftChildIndex(i) < heap1.size()) {
                if (heap1.leftChildValue(i) != null)
                    newHeap.insert(heap1.leftChildValue(i));
            }

            if (heap1.rightChildIndex(i) < heap1.size()) {
                if (heap1.rightChildValue(i) != null)
                    newHeap.insert(heap1.rightChildValue(i));
            }
        }

        newHeap.insert(heap2.top());
        for (int i = 0; i < heap2.size() / 2; ++i) {
            if (heap2.leftChildIndex(i) < heap2.size()) {
                if (heap2.leftChildValue(i) != null)
                    newHeap.insert(heap2.leftChildValue(i));
            }

            if (heap2.rightChildIndex(i) < heap2.size()) {
                if (heap2.rightChildValue(i) != null)
                    newHeap.insert(heap2.rightChildValue(i));
            }
        }*/

        return newHeap;
    }

    public void meld(Heap heap) {
        contentsOfOneHeapToAnother(this, heap);
        /*
        this.insert(heap.top());
        for (int i = 0; i < heap.size() / 2; ++i) {
            if (heap.leftChildIndex(i) < heap.size()) {
                if (heap.leftChildValue(i) != null)
                    this.insert(heap.leftChildValue(i));
            }

            if (heap.rightChildIndex(i) < heap.size()) {
                if (heap.rightChildValue(i) != null)
                    this.insert(heap.rightChildValue(i));
            }
        }*/
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
