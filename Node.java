
class Node {

    public int iData;
    public double dData;
    public Node leftChild;
    public Node rightChild;
    public int xpos = 0;  //stores x and y position of the node in the tree
    public int ypos = 0;

    public void displayNode() {
        System.out.print("{'");
        System.out.print(iData);
        System.out.print(",");
        System.out.print(dData);
        System.out.print("} ");
    }

} // end class Node
