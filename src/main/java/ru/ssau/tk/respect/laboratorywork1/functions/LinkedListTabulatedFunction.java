package ru.ssau.tk.respect.laboratorywork1.functions;

import ru.ssau.tk.respect.laboratorywork1.exceptions.InterpolationException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

    private Node head;

    public static class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Length is less than permissible");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Length is less than permissible");
        }
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("Incorrect bounds");
        }
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            addNode(xFrom, source.apply(xFrom));
            xFrom += step;
        }
    }

    public void addNode(double x, double y) {
        Node node = new Node();
        node.x = x;
        node.y = y;
        count++;
        if (head == null) {
            head = node;
            node.prev = node;
            node.next = node;
        } else {
            head.prev.next = node;
            head.prev = node;
            node.prev = head.prev;
            node.next = head;
        }
    }

    public int getCount() {
        return count;
    }

    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return head.prev.x;
    }

    Node getNode(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        Node node;
        node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public double getX(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        getNode(index).y = value;
    }

    public int indexOfX(double x) {
        Node xNode = head;
        for (int i = 0; i < count; i++) {
            if (xNode.x == x) {
                return i;
            }
            xNode = xNode.next;
        }
        return -1;
    }

    public int indexOfY(double y) {
        Node yNode = head;
        for (int i = 0; i < count; i++) {
            if (yNode.y == y) {
                return i;
            }
            yNode = yNode.next;
        }
        return -1;
    }

    public int floorIndexOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("x is less than left bound");
        }
        Node node = head;
        for (int i = 0; i <= count; i++) {
            if (node.x < x) {
                node = node.next;
            } else {
                return i;
            }
        }
        return getCount();
    }

    public double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    public double extrapolateRight(double x) {
        return interpolate(x, count - 1);
    }

    public double interpolate(double x, int floorIndex) {
        Node node = getNode(floorIndex);
        Node nodeNext = node.next;
        return interpolate(x, node.x, nodeNext.x, node.y, nodeNext.y);
    }

    protected Node floorNodeOfX(double x) {
        Node node = head;
        if (x < head.x) {
            throw new IllegalArgumentException("x is less than left bound");
        }
        for (int i = 0; i < count; i++) {
            if (node.x < x) {
                node = node.next;
            } else {
                return node.prev;
            }
        }
        return head.prev;
    }

    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else {
            Node node = floorNodeOfX(x);
            if (node.x == x) {
                return node.y;
            } else {
                return interpolate(x, node.x, node.next.x, node.y, node.next.y);
            }
        }
    }

    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(node.x, node.y);
                if (node == head.prev) {
                    node = null;
                } else {
                    node = node.next;
                }
                return point;
            }
        };
    }
}

