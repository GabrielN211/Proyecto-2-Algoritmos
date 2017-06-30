
package domain;


public class AVLNode {
    
    int balanceFactor;
    private String chain;
    private String quantityPositions;
    public AVLNode leftChild;
    public AVLNode rightChild;

    public AVLNode(String chain) {
        this.balanceFactor=0;
        this.leftChild= null;
        this.rightChild=null;
        this.chain=chain;
        this.quantityPositions="";
    }//constructor


    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getQuantityPositions() {
        return quantityPositions;
    }

    public void setQuantityPositions(String quantityPositions) {
        this.quantityPositions = quantityPositions;
    }

}//class
