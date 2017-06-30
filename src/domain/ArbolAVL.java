
package domain;

import business.Business;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ArbolAVL {

    public AVLNode root;
    private int position;

    public ArbolAVL() {
        this.root = null;
        this.position = 0;

    }//constructor

    
    public int getBF(AVLNode x) {//Balance Factor
        if (x == null) {
            return -1;
        } else {
            return x.balanceFactor;
        }
    }//getBF

    //Simple rotations
    public AVLNode leftRotation(AVLNode c) {
        AVLNode aux = c.rightChild;
        c.rightChild = aux.leftChild;
        aux.leftChild = c;
        c.balanceFactor = Math.max(getBF(c.leftChild), getBF(c.rightChild)) + 1;
        aux.balanceFactor = Math.max(getBF(aux.leftChild), getBF(aux.rightChild));

        return aux;
    }// leftRotation

    public AVLNode rightRotation(AVLNode c) {
        AVLNode aux = c.leftChild;
        c.leftChild = aux.rightChild;
        aux.rightChild = c;

        c.balanceFactor = Math.max(getBF(c.leftChild), getBF(c.rightChild));
        aux.balanceFactor = Math.max(getBF(aux.leftChild), getBF(aux.rightChild));

        return aux;
    }//rightRotation

    public AVLNode leftRightRotation(AVLNode c) {
        AVLNode temp;
        c.leftChild = leftRotation(c.leftChild);
        temp = rightRotation(c);

        return temp;

    }// left_right_rotation

    public AVLNode rightLeftRotation(AVLNode c) {
        AVLNode temp;
        c.rightChild = rightRotation(c.rightChild);
        temp = leftRotation(c);

        return temp;

    }//right_left_rotation

    
    public AVLNode insertAVL(AVLNode new_, AVLNode subTree) {
        AVLNode newParent = subTree;

        if (new_.getChain().compareTo(subTree.getChain()) < 0) {
            if (subTree.leftChild == null) {
                subTree.leftChild = new_;
                subTree.leftChild.setQuantityPositions(subTree.leftChild.getQuantityPositions() + this.position );
                this.position++;
            } else {
                subTree.leftChild = insertAVL(new_, subTree.leftChild);

                if ((getBF(subTree.leftChild) - getBF(subTree.rightChild) == 2)) {
                    if (new_.getChain().compareTo(subTree.leftChild.getChain()) < 0) {
                        newParent = rightRotation(subTree);
                    } else {
                        newParent = leftRightRotation(subTree);
                    }
                }
            }
        } else if (new_.getChain().compareTo(subTree.getChain()) > 0) {
            if (subTree.rightChild == null) {
                subTree.rightChild = new_;
                subTree.rightChild.setQuantityPositions(subTree.rightChild.getQuantityPositions() + this.position );
                this.position++;
            } else {
                subTree.rightChild = insertAVL(new_, subTree.rightChild);

                if ((getBF(subTree.leftChild) - getBF(subTree.rightChild) == -2)) {
                    if (new_.getChain().compareTo(subTree.rightChild.getChain()) > 0) {
                        newParent = leftRotation(subTree);
                    } else {
                        newParent = rightLeftRotation(subTree);
                    }
                }
            }
        } else {
            subTree.setQuantityPositions(subTree.getQuantityPositions() + this.position);
            this.position++;
        }
        //updata bf
        if ((subTree.leftChild == null) && (subTree.rightChild != null)) {
            subTree.balanceFactor = subTree.rightChild.balanceFactor + 1;
        } else if ((subTree.rightChild == null) && (subTree.leftChild != null)) {
            subTree.balanceFactor = subTree.leftChild.balanceFactor + 1;
        } else {
            subTree.balanceFactor = Math.max(getBF(subTree.leftChild), getBF(subTree.rightChild)) + 1;
        }

        return newParent;
    }

    public void insertar(String c) {
        AVLNode newLNode = new AVLNode(c);
        if (this.root == null) {
            this.root = newLNode;
            this.root.setQuantityPositions(this.root.getQuantityPositions() + this.position );
            this.position++;
        } else {
            this.root = insertAVL(newLNode, root);

        }
    }//insert

    public void saveNodes(AVLNode r, String rute) throws FileNotFoundException, IOException {
        Business treeBusiness = new Business(rute);
        if (r != null) {
            treeBusiness.saveTree(r);
            saveNodes(r.leftChild, rute);
            saveNodes(r.rightChild, rute);
        }
    }//preOrder

    public AVLNode find(AVLNode node, String chain) {
        while (node != null) {
            if (node.getChain().contains(chain)) {
                return node;
            } else if (node.getChain().compareTo(chain) > 0) {
                node = node.leftChild;
            } else {
                node = node.rightChild;
            }
        }//while

        return null;

    }//traversalTreeInPostOrder

}//class
