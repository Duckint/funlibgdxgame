package com.ducky.game;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class Quadtree {
    class Node {
        Key x, y;
        Node NW, NE, SW, SE;
        Value value;

        Node(Key x, Key y, Value value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    public void insert()
    {

    }
}
