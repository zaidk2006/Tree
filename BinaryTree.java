
/**
 * Name: Zaid Khoury
 * Date: December 3, 2016
 * Computer Science 182 Project 5 
 * Description: In this project, I implement the code provided from the book
 * and on the website to create a binary tree. In this code, the program runs
 * on GUI screen and the console itself. The GUI version maps the binary tree
 * in ovals and lines connecting them. Primarily, the leftmost number is the 
 * the smallest number in the tree, and the rightmost number is the largest
 * number in the tree. I use a recursive method to draw the lines and ovals and
 * print the numbers in the oval to map the tree on GUI.
 * 
 * *************************************************************
 *
 * Project Number 5 - Comp Sci 182 - Data Structures Start Code - Build your
 * program starting with this code
 *
 * Copyright 2003 Christopher C. Ferguson This code may only be used with the
 * permission of Christopher C. Ferguson
 *
 **************************************************************
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BinaryTree extends JFrame implements ActionListener {

    private static int winxpos = 0, winypos = 0;// place window here

// Private state variables.
    private Font boldfont = new Font("TimesRoman", Font.BOLD, 18);
    private Font plainfont = new Font("TimesRoman", Font.PLAIN, 12);

    private JButton insertbutton, exitbutton, deleteButton, inOrderButton, preOrderButton, postOrderButton;
    private JButton successorButton;
    private JTextField infield;
    private JPanel northPanel;
    private MyPanel centerPanel;
    private static final int WINWIDTH = 1400;
    private static final int WINHEIGHT = 800;
    boolean valid = false;
    private Tree theTree = new Tree();

////////////MAIN////////////////////////
    public static void main(String[] args) {
        BinaryTree tpo = new BinaryTree();

        tpo.addWindowListener(new WindowAdapter() {   // this exits the program when X box clicked 
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

////////////CONSTRUCTOR/////////////////////
    public BinaryTree() {
        northPanel = new JPanel();
        northPanel.add(new Label("Enter a number: "));
        infield = new JTextField("", 20);
        northPanel.add(infield);
        insertbutton = new JButton("Insert");
        northPanel.add(insertbutton);
        insertbutton.addActionListener(this);
        deleteButton = new JButton("Delete");
        northPanel.add(deleteButton);
        deleteButton.addActionListener(this);
        inOrderButton = new JButton("In Order");
        northPanel.add(inOrderButton);
        inOrderButton.addActionListener(this);
        preOrderButton = new JButton("Pre Order");
        northPanel.add(preOrderButton);
        preOrderButton.addActionListener(this);
        postOrderButton = new JButton("post Order");
        northPanel.add(postOrderButton);
        postOrderButton.addActionListener(this);
        exitbutton = new JButton("Exit");
        northPanel.add(exitbutton);
        exitbutton.addActionListener(this);
        getContentPane().add("North", northPanel);

        centerPanel = new MyPanel();
        getContentPane().add("Center", centerPanel);

        theTree.insert(50, 1.5);
        theTree.insert(25, 1.2);
        theTree.insert(75, 1.7);
        theTree.insert(12, 1.5);
        theTree.insert(37, 1.2);
        theTree.insert(43, 1.7);
        theTree.insert(30, 1.5);
        theTree.insert(33, 1.2);
        theTree.insert(87, 1.7);
        theTree.insert(93, 1.5);
        theTree.insert(97, 1.5);
        theTree.displayTree();
        setSize(WINWIDTH, WINHEIGHT);
        setLocation(winxpos, winypos);
        setVisible(true);

    }

////////////BUTTON CLICKS ///////////////////////////
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == exitbutton) {
            dispose();
            System.exit(0);
        }
        if (e.getSource() == deleteButton) {
            try {
                theTree.delete(Integer.parseInt(infield.getText()));
            } catch (NumberFormatException r) {
                System.out.println("Enter a valid value");
            }
            repaint();
        }
        if (e.getSource() == inOrderButton) {
            theTree.inOrder(theTree.getRoot());
            repaint();
        }
        if (e.getSource() == preOrderButton) {
            theTree.preOrder(theTree.getRoot());
            repaint();
        }
        if (e.getSource() == postOrderButton) {
            theTree.postOrder(theTree.getRoot());
            repaint();
        }
        if (e.getSource() == insertbutton) {
            try {
                theTree.insert(Integer.parseInt(infield.getText()), 2.1);
                repaint();
            } catch (NumberFormatException r) {
                System.out.println("Enter a valid value");
            }
        }
    }

    class MyPanel extends JPanel {

        ////////////    PAINT   //////////////////////////////// 
        public void paintComponent(Graphics g) {
            g.setFont(plainfont);
            theTree.displayTree();
            theTree.displayTree(g, theTree.getRoot(), WINWIDTH / 2, 80, 1);
        }
    }
}     // End Of BinaryTree 

