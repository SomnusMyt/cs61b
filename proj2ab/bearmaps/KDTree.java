package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private Node root;
    private class Node {
        Point point;
        Node left, right;
        boolean compareY; //false -- x ; true -- y;

        public Node(Point point, boolean compareY) {
            this.point = point;
            this.compareY = compareY;
        }
    }

    public KDTree() {
        root = null;
    }

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = insert(false, p, root);
        }
    }

    private Node insert(boolean compareY, Point input, Node node) {
        if (node == null) {
            return new Node(input, compareY);
        }
        if (input.equals(node.point)) {
            return node;
        }
        int cmp = compare(compareY, input, node.point);
        if (cmp < 0) {
            node.left = insert(!compareY, input, node.left);
        }
        else {
            node.right = insert(!compareY, input, node.right);
        }
        return node;
    }

    private int compare(boolean compareY, Point p1, Point p2) {
        if (compareY == true) {
            return Double.compare(p1.getY(), p2.getY());
        }
        else {
            return Double.compare(p1.getX(), p2.getX());
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Node result = nearest(root, goal, root);
        return result.point;
    }

    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (Point.distance(n.point, goal) < Point.distance(best.point, goal)) {
            best = n;
        }
        Node goodSide, badSide;
        if (compare(n.compareY, goal, n.point) == -1) {
            goodSide = n.left;
            badSide = n.right;
        }
        else {
            goodSide = n.right;
            badSide = n.left;
        }
        best = nearest(goodSide, goal, best);
        double badSideDistance;
        if (n.compareY) {
            badSideDistance = Math.pow(n.point.getY() - goal.getY(), 2);
        }
        else {
            badSideDistance = Math.pow(n.point.getX() - goal.getX(), 2);
        }
        if (Point.distance(best.point, goal) > badSideDistance) {
            best = nearest(badSide, goal, best);
        }
        return best;
    }

}
