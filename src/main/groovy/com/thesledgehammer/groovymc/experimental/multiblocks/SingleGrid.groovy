/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thesledgehammer.groovymc.experimental.multiblocks

class SingleGrid<V> {

    private GridNode<V> head;
    private GridNode<V> tail;
    private int size = 0;

    SingleGrid(V value) {
        head = new GridNode<V>(value);
    }

    SingleGrid() {
        head = null;
    }

    GridNode<V> Head() {
        return head;
    }

    GridNode<V> Tail() {
        return tail;
    }

    boolean isEmpty() {
        if (head == null || tail == null) {
            return true;
        }
        return false;
    }

    int size() {
        return size;
    }

    GridNode<V> addToHead(V value) {
        GridNode<V> node = new GridNode<V>(value);
        if (size == 0 || head == null) {
            head = node;
            tail = head;
        } else {
            head.setPrev(node);
            node.setNext(head);
            head = node;
        }
        size++;
        return node;
    }

    GridNode<V> addToTail(V value) {
        GridNode<V> node = new GridNode<V>(value);
        if (size == 0 || head == null) {
            head = node;
            tail = head;
        } else {
            node.setPrev(tail);
            tail.setNext(node);
            tail = node;
        }
        size++;
        return node;
    }

    GridNode<V> addAtPos(int index, V value) {
        GridNode<V> node =  new GridNode<V>(value);
        if(index == 1) {
            node = addToHead(value);
        }
        GridNode<V> other = head;
        for(int i = 2; i <= size; i++) {
            if(i == index) {
                GridNode<V> tmp = other.Next();
                other.setNext(node);
                node.setPrev(other);
                node.setNext(tmp);
                tmp.setPrev(node);
            }
            other = other.Next();
        }
        size++;
        return node;
    }

    V get(int index) {
        return getNode(index).getValue();
    }

    V set(int index, V value) {
        GridNode<V> node = getNode(index);
        V val = node.getValue();
        node.setValue(value);
        return val;
    }

    GridNode<V> getNode(int i) {
        GridNode<V> node = null;
        if (i < size / 2) {
            node = head.Next();
            for (int j = 0; j < i; j++) {
                node = node.Next();
            }
        } else {
            node = head;
            for (int j = size - 1; j > i; j--) {
                node = node.Prev();
            }
        }
        return node;
    }

    boolean HeadHasNext() {
        if (head.Next() != null) {
            return true;
        }
        return false;
    }

    boolean HeadHasPrev() {
        if (head.Prev() != null) {
            return true;
        }
        return false;
    }

    boolean TailHasNext() {
        if (tail.Next() != null) {
            return true;
        }
        return false;
    }

    boolean TailHasPrev() {
        if (tail.Prev() != null) {
            return true;
        }
        return false;
    }

    V searchNextValue(V value) {
        while (HeadHasNext()) {
            head = head.Next();
            if (head.getValue().equals(value)) {
                return head.getValue();
            }
        }
        return null;
    }

    V searchPrevValue(V value) {
        while (TailHasPrev()) {
            tail = tail.Prev();
            if (tail.getValue().equals(value)) {
                return tail.getValue();
            }
        }
        return null;
    }

    void deleteFromHead() {
        if (size == 0) {
            return;
        } else {
            //System.out.println("\ndeleting node " + head.getValue() + " from start");
            head = head.Next();
            size--;
        }
    }

    void deleteFromTail() {
        if (size == 0) {
            return;
        } else if (size == 1) {
            deleteFromHead();
        } else {
            V x = tail.getValue();
            GridNode<V> prevTail = tail.Prev();
            tail = prevTail;
            tail.setNext(null);
           // System.out.println("\ndeleting node " + x + " from end");
            size--;
        }
    }

    void deleteAtPos(int index) {
        if(index == 1) {
            if(size == 1) {
                head = null;
                tail = null;
                size = 0;
                return;
            }

            head = head.Next();
            head.setPrev(tail);
            tail.setNext(head);
            size--;
            return;
        }

        if(index == size) {
            tail = tail.Prev();
            tail.setNext(head);
            head.setPrev(tail);
            size--;
        }

        GridNode<V> other = head.Next();
        for(int i = 2; i <= size; i++) {
            if(i == index) {
                GridNode<V> p = other.Prev();
                GridNode<V> n = other.Next();
                p.setNext(n);
                n.setPrev(p);
                size--;
                return;
            }
            other = other.Next();
        }
    }

    Iterator<V> iterator() {
        Set<V> dll = new HashSet<>();
        for (int i = 0; i < size; i++) {
            dll.add(head.getValue());
        }
        return dll.iterator();
    }
}
