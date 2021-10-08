package ru.ssau.tk.respect.laboratorywork1.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

    private Node head;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
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

    private Node getNode(int index) {
        Node node;
        node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
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
            return 0;
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
        if (head.x == head.prev.x) {
            return head.y;
        }
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    public double extrapolateRight(double x) {
        if (head.x == head.prev.x) {
            return head.y;
        }
        return interpolate(x, count - 1);
    }

    public double interpolate(double x, int floorIndex) {
        if (head.x == head.prev.x) {
            return head.y;
        }
        Node node = getNode(floorIndex);
        Node nodeNext = node.next;
        return interpolate(x, node.x, nodeNext.x, node.y, nodeNext.y);
    }

    protected Node floorNodeOfX(double x) {
        Node node = head;
        if (node.x > x) {
            return head;
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

}
