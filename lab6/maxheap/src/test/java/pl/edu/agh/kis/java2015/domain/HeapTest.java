package pl.edu.agh.kis.java2015.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

public class HeapTest {

    public boolean checkIfIsHeap(Heap heap) {
        for (int i = 0; i < heap.size() / 2; ++i) {
            Double parent = 0.0;
            Double left;
            Double right;

            if (heap.leftChildIndex(i) < heap.size()) {
                if (heap.leftChildValue(i) != null) {
                    parent = heap.parentValue(heap.leftChildIndex(i));
                    left = heap.leftChildValue(i);
                    if (left > parent)
                        return false;
                }
            } else
                parent = heap.parentValue(heap.rightChildIndex(i));

            if (heap.rightChildIndex(i) < heap.size()) {
                if (heap.rightChildValue(i) != null) {
                    right = heap.rightChildValue(i);
                    if (right > parent)
                        return false;
                }
            }

        }

        return true;
    }

    @Test
    public void insert0intoNewHeap_topIs0() {

        Heap heap = new Heap();
        heap.insert(0);

        assertEquals("size should be 1", 1, heap.size());
        assertEquals(0, heap.top(), 0.001);
        assertEquals(1, heap.size());
    }

    @Test
    public void insert0AndThen2intoNewHeap_topIs2() {

        Heap heap = new Heap();
        heap.insert(0);
        heap.insert(2);

        assertEquals("size should be 2", 2, heap.size());
        assertEquals(2, heap.top(), 0.001);
    }

    @Test
    public void insert0And2And3And5And6intoNewHeap_topIs6() {

        Heap heap = new Heap();
        heap.insert(0);
        heap.insert(2);
        heap.insert(3);
        heap.insert(5);
        heap.insert(6);

        assertEquals(6, heap.top(), 0.001);
    }

    @Test
    public void extractsMaxFromTheHeapAndRemainsAHeap() {
        Heap heap = new Heap();
        heap.insert(0);
        heap.insert(2);
        heap.insert(8);
        heap.insert(5);
        heap.insert(6);
        heap.printHeap();
        assertEquals(8, heap.extract_max(), 0.001);
        assertTrue(checkIfIsHeap(heap));
    }

    @Test
    public void replacingTheTopWithABiggerElement() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(5.1);
        list.add(6.2);
        list.add(6.1);
        list.add(1.1);
        list.add(3.6);
        list.add(3.4);

        Heap heap = new Heap(list);
        heap.heapify();
        heap.replace(7.5);
        heap.printHeap();
        assertTrue(checkIfIsHeap(heap));
    }

    @Test
    public void replacingTheTopWithASmallerElement() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(5.1);
        list.add(6.2);
        list.add(6.1);
        list.add(1.1);
        list.add(3.6);
        list.add(3.4);

        Heap heap = new Heap(list);
        heap.heapify();
        heap.replace(3.8);
        heap.printHeap();
        assertTrue(checkIfIsHeap(heap));
    }

    @Test
    public void heapifyingAList() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(5.1);
        list.add(6.2);
        list.add(6.1);
        list.add(1.1);
        list.add(3.6);
        list.add(3.4);

        Heap heap = new Heap(list);
        heap.heapify();
        heap.printHeap();
        assertTrue(checkIfIsHeap(heap));
    }

    @Test
    public void mergingTwoHeaps() {
        ArrayList<Double> list1 = new ArrayList<>();
        list1.add(5.1);
        list1.add(6.2);
        list1.add(6.1);
        list1.add(1.1);
        list1.add(3.6);
        list1.add(3.4);

        Heap heap1 = new Heap(list1);
        heap1.heapify();

        ArrayList<Double> list2 = new ArrayList<>();
        list2.add(4.3);
        list2.add(6.8);
        list2.add(7.4);
        list2.add(5.3);
        list2.add(2.2);
        list2.add(1.5);
        Heap heap2 = new Heap(list2);
        heap2.heapify();

        Heap newHeap = Heap.merge(heap1, heap2);
        newHeap.printHeap();
        assertTrue(checkIfIsHeap(newHeap));
    }

    @Test
    public void meldingOneHeapWithAnother() {
        ArrayList<Double> list1 = new ArrayList<>();
        list1.add(5.1);
        list1.add(6.2);
        list1.add(6.1);
        list1.add(1.1);
        list1.add(3.6);
        list1.add(3.4);

        Heap heap1 = new Heap(list1);
        heap1.heapify();

        ArrayList<Double> list2 = new ArrayList<>();
        list2.add(4.3);
        list2.add(6.8);
        list2.add(7.4);
        list2.add(5.3);
        list2.add(2.2);
        list2.add(1.5);
        Heap heap2 = new Heap(list2);
        heap2.heapify();

        heap1.meld(heap2);
        heap1.printHeap();
        assertTrue(checkIfIsHeap(heap1));
    }

}
