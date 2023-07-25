package collections;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    @Test
    public void testCorrectListInit() {
        MyLinkedList list = new MyLinkedList();
        assertThat(list.getSize(), is(0));
    }

    @Test
    public void whenAddToEmptyThenAdd() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        assertThat(list.getSize(), is(1));
        assertThat(list.get(0), is(5));
    }

    @Test
    public void whenAddToNonEmptyThenAdd() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        list.add(6);
        assertThat(list.getSize(), is(2));
        assertThat(list.get(0), is(5));
        assertThat(list.get(1), is(6));
    }

    @Test
    public void whenInsertIntoEmptyListOnZeroPosThenInsert() {
        MyLinkedList list = new MyLinkedList();
        list.insert(0, 5);
        assertThat(list.getSize(), is(1));
        assertThat(list.get(0), is(5));
    }

    @Test
    public void whenInsertIntoTheFirstIndexOfNonEmptyListThenInsert() {
        MyLinkedList list = new MyLinkedList();
        list.add(6);
        list.insert(0, 5);
        assertThat(list.getSize(), is(2));
        assertThat(list.get(0), is(5));
        assertThat(list.get(1), is(6));
    }

    @Test
    public void whenInsertIntoTheLastIndexOfNonEmptyListThenInsert() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        list.insert(1, 6);
        assertThat(list.getSize(), is(2));
        assertThat(list.get(0), is(5));
        assertThat(list.get(1), is(6));
    }

    @Test
    public void whenInsertIntoTheIndexEqualsSizeThenInsert() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        list.insert(1, 6);
        assertThat(list.getSize(), is(2));
        assertThat(list.get(0), is(5));
        assertThat(list.get(1), is(6));
    }

    @Test
    public void whenInsertIntoNegativeIndexThenThrow() {
        MyLinkedList list = new MyLinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(-1, 5));
    }

    @Test
    public void whenInsertIntoTheIndexGreaterThanSizeThenThrow() {
        MyLinkedList list = new MyLinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(100, 5));
    }

    @Test
    public void whenRemoveFromListWithOneElementThenRemove() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        list.remove(0);
        assertThat(list.getSize(), is(0));
    }

    @Test
    public void whenRemoveFromEmptyListThenThrow() {
        MyLinkedList list = new MyLinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    public void whenRemoveFirstElementThenRemove() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        list.add(6);
        list.remove(0);
        assertThat(list.getSize(), is(1));
        assertThat(list.get(0), is(6));
    }

    @Test
    public void whenRemoveLastElementThenRemove() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        list.add(6);
        list.remove(1);
        assertThat(list.getSize(), is(1));
        assertThat(list.get(0), is(5));
    }

    @Test
    public void whenPassNegativeIndexToRemoveThenThrow() {
        MyLinkedList list = new MyLinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    public void whenPassIndexEqualsSizeToRemoveThenThrow() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    public void whenPassIndexGreaterThanSizeToRemoveThenThrow() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(100));
    }

    @Test
    public void whenGetThenGet() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        assertThat(list.get(0), is(5));
    }

    @Test
    public void whenGetNegativeIndexThenThrow() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    public void whenGetIndexGreaterThanSizeThenThrow() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(100));
    }
}