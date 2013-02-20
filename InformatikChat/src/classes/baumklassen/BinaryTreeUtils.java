/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.baumklassen;

import classes.listenklassen.List;
import classes.listenklassen.Queue;

/**
 *
 * @author mars
 */
public class BinaryTreeUtils {

    public static int height(BinaryTree root) {
        int h = -1;

        if (!root.isEmpty()) {
            int dLeft = height(root.getLeftTree());
            int dRight = height(root.getRightTree());
            h = Math.max(dLeft, dRight) + 1;
        }
        return h;
    }

    public static int nodeCount(BinaryTree tree) {
        if (tree.isEmpty()) {
            return 0;
        } else {
            int nLeft = nodeCount(tree.getLeftTree());
            int nRight = nodeCount(tree.getRightTree());
            return nLeft + nRight + 1;
        }
    }

    public static boolean isLeaf(BinaryTree tree) {
        if (!tree.isEmpty()) {
            return (tree.getLeftTree().isEmpty() && tree.getRightTree().isEmpty());
        } else {
            return false;
        }
    }

    public static int leafCount(BinaryTree tree) {
        if (tree.isEmpty()) {
            return 0;
        } else {
            if (isLeaf(tree)) {
                return 1;
            } else {
                int countLeft = leafCount(tree.getLeftTree());
                int countRight = leafCount(tree.getRightTree());
                return countLeft + countRight;
            }
        }
    }

    public static BinaryTree convertToBinaryTree(BinarySearchTree bst) {
        BinaryTree tree = new BinaryTree();

        if (!bst.isEmpty()) {
            tree.setObject(bst.getItem());

            tree.setLeftTree(convertToBinaryTree(bst.getLeftTree()));
            tree.setRightTree(convertToBinaryTree(bst.getRightTree()));
        }

        return tree;
    }

//    public static List getSortedList(BinarySearchTree bst) {
//        List list = new List();
//
//        BinaryTree tree = convertToBinaryTree(bst);
//        CreateQueueTraversal traversal = new CreateQueueTraversal();
//        traversal.inOrder(tree);
//        Queue q = traversal.getQueue();
//        while (!q.isEmpty()) {
//            list.append(q.front());
//            q.dequeue();
//        }
//
//        return list;
//    }
    
    public static List getSortedList(BinarySearchTree bst) {
        List list = new List();

        if (!bst.isEmpty()) {
            List lList = getSortedList(bst.getLeftTree());
            list.concat(lList);
            list.append(bst.getItem());
            List rList = getSortedList(bst.getRightTree());
            list.concat(rList);
        }

        return list;
    }

    public static Item findMaxRec(BinarySearchTree root) {
        if (root.isEmpty()) {
            return null;
        } else {
            if (root.getRightTree().isEmpty()) {
                return root.getItem();
            } else {
                return findMaxRec(root.getRightTree());
            }
        }       
    }

    public static Item findMax(BinarySearchTree root) {
        if (root.isEmpty()) {
            return null;
        } else {
            BinarySearchTree temp = root;
            while (!temp.getRightTree().isEmpty()) {
                temp = temp.getRightTree();
            }
            return temp.getItem();
        }
    }
}
