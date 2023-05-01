package com.ducky.game;


import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.security.Key;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import java.util.ArrayList;
import java.util.List;

public class Quadtree {
    private Node root;

    private class Node {
        float x, y;
        Node NW, NE, SE, SW;
        Rectangle rect;

        Node(float x, float y, Rectangle rect) {
            this.x = x;
            this.y = y;
            this.rect = rect;
        }
    }
    private Node insert(Node h, float x, float y, Rectangle rect) {
        if (h == null) return new Node(x, y, rect);

        return h;
    }

    public void insert(Rectangle rect) {
        root = insert(root, rect.x, rect.y, rect);
    }
    public List<Rectangle> query2D(Rectangle rect) {
        List<Rectangle> foundRect = new ArrayList<>();
        return foundRect;
    }

    private void query2D(Node h, Rectangle rect) {
        if (h == null) return;
    }


}