
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Stack;
import javax.swing.JPanel;

class Tree {

    private int width = 1400;

    int[] counter = {2, 4, 8, 16, 32, 64, 128, 128, 128, 128, 128, 128, 128};
    private Node root;

    // first node of tree
// -------------------------------------------------------------
    public Tree() // constructor
    {
        root = null;
    } // no nodes in tree yet
// -------------------------------------------------------------

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node find(int key) {
        Node current = root;
        while (current.iData != key) {
            if (key < current.iData) {
                current = current.leftChild;
            } else if (current == null) {
                return null;
            }
        }
        return current;
    }
// -------------------------------------------------------------

    public void insert(int id, double dd) {
        try {

            Node newNode = new Node();
            newNode.iData = id;
            newNode.dData = dd;

            if (root == null) {
                root = newNode;
            } else {
                Node current = root;
                Node parent;
                while (true) {
                    parent = current;
                    if (id < current.iData) { //go left?
                        current = current.leftChild;
                        if (current == null) {
                            parent.leftChild = newNode;
                            return;
                        }
                    } else {
                        current = current.rightChild;
                        if (current == null) {
                            parent.rightChild = newNode;
                            return;
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException k) {
            System.out.println("Array too small");
        }

    }

// -------------------------------------------------------------
    public boolean delete(int key) // delete node with given key
    {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while (current.iData != key) {
            parent = current;
            if (key < current.iData) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null) // end of the line, 
            {
                System.out.println("Can not find key");

                return false; // didn’t find it
            }

        }

        if (current.leftChild == null
                && current.rightChild == null) {
            if (current == root) // if root,
            {
                root = null; // tree is empty
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        } else if (current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
        } else if (current.leftChild == null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }

            Node successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
                successor.leftChild = current.leftChild;
            }

        }
        return true;
    }
// -------------------------------------------------------------

    public Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        System.out.println(successor);
        return successor;
    }
// -------------------------------------------------------------

    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal: ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;

        }
        System.out.println("");
    }

    public void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }
// -------------------------------------------------------------

    public void inOrder(Node localRoot) {

        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }
// -------------------------------------------------------------

    public void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }
// -------------------------------------------------------------

    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("......................................................");
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for (int j = 0; j < nBlanks; j++) {
                System.out.print(" ");
            }

            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if (temp.leftChild != null
                            || temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
        } // end while isRowEmpty is false
        System.out.println("......................................................");
    }

    public void displayTree(Graphics g, Node localTree, int x, int y, int level) {
        // Display the root
        int valueHeight = 115;
        int lineHeight = 100;
        g.drawOval(x, y - 20, 30, 30);
        g.drawString(Integer.toString(localTree.iData), x + 10, y);
        if (localTree.leftChild != null) {
            try {
                g.drawLine(x, y, (x - (width / counter[level])) + 30, y + lineHeight);
                displayTree(g, localTree.leftChild, (x - (width / counter[level])), y + valueHeight, level + 1);
            } catch (ArrayIndexOutOfBoundsException l) {
                System.out.println("Array too small");
            }
        }
        if (localTree.rightChild != null) {
            try {
                g.drawLine(x + 30, y, (x + (width / counter[level])), y + lineHeight);
                displayTree(g, localTree.rightChild, (x + (width / counter[level])), y + valueHeight, level + 1);
            } catch (ArrayIndexOutOfBoundsException d) {
                System.out.println("Array too small");
            }
        }
    }

}
