package bst;

/**
 *
 */
public class RedBlackTree {

    private RedBlackTreeNode root;
    private int size;

    public void put(int value) {
        RedBlackTreeNode t = root;
        if(t == null) {
            root = new RedBlackTreeNode(value, null);
            size = 1;
            return;
        }
        int cmp;
        RedBlackTreeNode parent;
        do {
            parent = t;
            cmp = t.value > value ? -1 : t.value < value ? 1 : 0;
            if(cmp > 0) {
                t = t.right;
            } else if (cmp < 0) {
                t = t.left;
            } else {
                return;
            }
        } while(t != null);
        RedBlackTreeNode newNode = new RedBlackTreeNode(value, parent);
        if(cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        fixAfterInsertion(newNode);
        size++;
    }

    private void fixAfterInsertion(RedBlackTreeNode node) {
        node.color = Color.RED;
        while(node != null && node != root && node.parent.color == Color.RED) {
            /**
             *                Grand
             *            /          \
             *         Parent(red) Uncle(red)
             *         /    \
             *    node  or  node
             * */
            if(parentOf(node) == leftOf(parentOf(parentOf(node)))) {
                RedBlackTreeNode uncle = rightOf(parentOf(parentOf(node)));
                /**
                 *             Grand(black)
                 *            /         \
                 *         Parent(red)  Uncle(red)
                 *         /    \
                 *    node  or  node(red)
                 * */
                if(uncle != null && uncle.color == Color.RED) {
                    setColor(parentOf(node), Color.BLACK);
                    setColor(uncle, Color.BLACK);
                    setColor(parentOf(parentOf(node)), Color.RED);
                    node = parentOf(parentOf(node));
                }
                else {
                    /**
                     *              Grand(red)
                     *            /          \
                     *         Parent(black) Uncle(black)
                     *              \
                     *              node(red)
                     **/
                    if(node == rightOf(parentOf(node))) {
                        node = parentOf(node);
                        rotateLeft(node);
                    }
                    setColor(parentOf(node), Color.BLACK);
                    setColor(parentOf(parentOf(node)), Color.RED);
                    rotateRight(parentOf(parentOf(node)));
                }
            }
            else {
                /**
                 *        Grand
                 *        /     \
                 *   Uncle(Red) Parent(red)
                 *             /    \
                 *          node or node(red)
                 * */
                RedBlackTreeNode uncle = leftOf(parentOf(parentOf(node)));
                if(uncle != null && uncle.color == Color.RED) {
                    setColor(parentOf(node), Color.BLACK);
                    setColor(uncle, Color.BLACK);
                    setColor(parentOf(parentOf(node)), Color.RED);
                    node = parentOf(parentOf(node));
                }
                else {
                    /**
                     *           Grand(red)
                     *           /        \
                     *   Uncle(black) Parent(black)
                     *                /
                     *              node(red)
                     * */
                    if(node == leftOf(parentOf(node))) {
                        node = parentOf(node);
                        rotateRight(node);
                    }
                    setColor(parentOf(node), Color.BLACK);
                    setColor(parentOf(parentOf(node)), Color.RED);
                    rotateLeft(parentOf(parentOf(node)));
                }
            }
        }
        root.color = Color.BLACK;
    }

    public RedBlackTreeNode remove(int value) {
        RedBlackTreeNode p = getNode(value);
        if(p == null) {
            return null;
        }
        RedBlackTreeNode oldNode = p;
        deleteNode(p);
        return oldNode;
    }



    private RedBlackTreeNode parentOf(RedBlackTreeNode node) {
        return node.parent;
    }

    private RedBlackTreeNode leftOf(RedBlackTreeNode node) {
        return node.left;
    }

    private RedBlackTreeNode rightOf(RedBlackTreeNode node) {
        return node.right;
    }

    private void setColor(RedBlackTreeNode node, Color color) {
        node.color = color;
    }
    /*   parent      parent
    *      /          /
    *      p         r
    *    /  \  =>   /  \
    *       r      p
    *      /        \
    *     left      left
    *
    *   parent     parent
    *      \          \
    *      p          r
    *    /  \  =>    / \
    *       r       p
    *      /        \
    *     left     left
    **/
    private void rotateLeft(RedBlackTreeNode p) {
        if(p == null) {
            return;
        }
        RedBlackTreeNode r = p.right;
        p.right = r.left;
        if(r.left != null) {
            r.left.parent = p;
        }
        r.parent = p.parent;
        // If node is root
        if(p.parent == null) {
            root = r;
        }
        // Link the parent to the new child
        else if(p.parent.left == p) {
            p.parent.left = r;
        }
        // Link the parent to the new child
        else {
            p.parent.right = r;
        }
        r.left = p;
        p.parent = r;
    }

    /*   parent      parent
    *      /          /
    *      p         l
    *    /  \  =>   /  \
    *   l               p
    *    \             /
    *   right        right
    *
    *   parent     parent
    *      \          \
    *      p          l
    *    /  \  =>    / \
    *   l               p
    *    \             /
    *   right       right
    **/
    private void rotateRight(RedBlackTreeNode p) {
        if(p == null) {
            return;
        }
        RedBlackTreeNode l = p.left;
        p.left = l.right;
        if(l.right != null) {
            l.right.parent = p;
        }
        l.parent = p.parent;
        // If node is root
        if(p.parent == null) {
            root = l;
        } else if(p.parent.right == p) {
            p.parent.right = l;
        } else {
            p.parent.left = l;
        }
        l.right = p;
        p.parent = l;
    }

    private class RedBlackTreeNode {
        private int value;
        private Color color;
        private RedBlackTreeNode left;
        private RedBlackTreeNode right;
        private RedBlackTreeNode parent;

        RedBlackTreeNode(int value, RedBlackTreeNode parent) {
            this.value = value;
            this.parent = parent;
            this.color = Color.BLACK;
        }
    }

    private enum Color {
        RED,
        BLACK
    }
}
