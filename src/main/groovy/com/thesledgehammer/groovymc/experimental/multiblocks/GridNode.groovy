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

class GridNode<V> implements IGridNode<V> {

    private V value;
    private GridNode<V> next;
    private GridNode<V> prev;

    GridNode(V value) {
        setValue(value);
        setNext(null);
        setPrev(null);
    }

    @Override
    void setValue(V value) {
        this.value = value;
    }

    @Override
    void setNext(GridNode<V> next) {
        this.next = next;
    }

    @Override
    void setPrev(GridNode<V> prev) {
        this.prev = prev;
    }

    @Override
    V getValue() {
        return value;
    }

    @Override
    GridNode<V> Next() {
        return next;
    }

    @Override
    GridNode<V> Prev() {
        return prev;
    }
}
