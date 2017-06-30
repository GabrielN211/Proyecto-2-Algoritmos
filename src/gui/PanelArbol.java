
package gui;

import domain.AVLNode;
import domain.ArbolAVL;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class PanelArbol extends JPanel {

    private ArbolAVL arbol;
    private AVLNode myNode;
    private Graphics2D g2;
    private BufferedImage bufferedImage;
    private int x;
    private int y;
    private boolean next;

    public PanelArbol(ArbolAVL myTree) {
        super();     
        this.arbol = myTree;
        this.myNode = this.arbol.root; 
        this.next = true;
    }//constructor

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bufferedImage, 0, 0, 1500, 1500, null);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g2 = (Graphics2D)g;
        g2.setFont(new Font("Monospace", Font.PLAIN, 11));
        g2.setColor(Color.green);
        g2.drawString("Nivel:", 2, 16);
        pintarArbol(myNode, getWidth()/2, 20, 110, 70, 0,g2,1);
    }//paintComponent

     private void pintarArbol(AVLNode nodo, int x, int y, int Xx, int Yy, int nivel,Graphics2D g2,int tmp) {
        boolean aux = true;
        boolean auxl=true;
        aux=true;
        if (nodo == null) {
            return;
        }
        g2.setStroke(new BasicStroke(1));
        //g2.setColor(new Color(90,20,12));
        g2.setColor(Color.green);
//            
            
        
        if(tmp==1){
            tmp++;
            if (nodo.leftChild != null) {

                g2.drawLine(x -2, y + 60, x - Xx + (nivel * 2)-70, (y + Yy) + 55);
//          

            }
            if (nodo.rightChild != null ) {
                g2.drawLine(x + 22, y + 60, x + Xx - (nivel * 2)+70, (y + Yy)+55);
//               
                
            }
        
        }else{

            if(nodo==this.arbol.root.leftChild){
                x-=130;
                if(this.arbol.root.leftChild.leftChild.leftChild!=null)
                    auxl=false;
            }    
            if(nodo==this.arbol.root.rightChild){
                x+=130;
               
                if(this.arbol.root.rightChild.rightChild.rightChild!=null && this.arbol.root.rightChild.rightChild.leftChild!=null )
                   aux=false;
                
                   
            }    
            
            
               if(nodo.leftChild!=null && nodo.rightChild !=null && nodo==this.arbol.root.leftChild.leftChild && aux){
                   x-=130;    
                   g2.drawLine(x+10, y-20,x+207,y-81);
                  auxl=false;
               }
               if(nodo.leftChild!=null && nodo.rightChild !=null && nodo==this.arbol.root.rightChild.rightChild){
                   x+=130; 
                   aux=false;
                   g2.drawLine(x-10, y-15,x-168,y-81);
               }


               if(nodo.leftChild!=null && nodo.rightChild !=null && nodo==this.arbol.root.leftChild.leftChild.leftChild  && aux){
                   x-=130;    
                   g2.drawLine(x+10, y-20,x+207,y-81);
                   //auxl=false;
               }
               if(nodo.leftChild!=null && nodo.rightChild !=null && nodo==this.arbol.root.rightChild.rightChild.rightChild){
                   x+=130; 
                   g2.drawLine(x-10, y-15,x-168,y-81);
                   //aux=false;
               }
 
           
                if (nodo.leftChild != null && auxl) {
                    g2.drawLine(x -2, y + 60, x - Xx + (nivel * 2) + 60, (y + Yy) + 55);
                }
                if (nodo.rightChild != null && aux ) {
                    g2.drawLine(x + 22, y + 60, x + Xx - (nivel * 2)-60, (y + Yy)+55);
                }
           
            
        } 
        
            g2.setColor(new Color(20,15,15)); 
            g2.setFont(new Font("Monospace", Font.PLAIN, 17));
            for (int i = 30; i < getHeight(); i+=25) {
                g2.drawLine(i, y+25, i+20, y+25);
            }
            g2.setColor(Color.green);
            g2.drawOval(7, y+12, 25, 25);
            g2.drawString(""+nivel, 14, y+31);
            
        
         
           
            
            g2.drawOval(x - 30, y-20, 80, 80);
            
            g2.setColor(Color.black);
            g2.setFont(new Font("Monospace", Font.BOLD, 12));
            g2.fillRect(x-25, y+6,10*nodo.getChain().length(), 20);
            g2.setColor(Color.green);
            g2.drawString(nodo.getChain(), x - 22, y + 20);
            g2.drawRect(x-24, y+7,10*nodo.getChain().length()-4, 17);
            
            
            
        if(nodo.leftChild!=null)    
            pintarArbol(nodo.leftChild, (int) (x - Xx) + 30, (y + Yy) + 70, Xx + nivel , Yy, nivel + 1,g2,tmp);
        
        if(nodo.rightChild!=null) 
            pintarArbol(nodo.rightChild, (int) (x + Xx) - 50, (y + Yy) + 70, Xx - nivel , Yy, nivel + 1,g2,tmp);

    }//paintTree

 
    public ArbolAVL getMyTree() {
        return arbol;
    }

    public void setMyTree(ArbolAVL myTree) {
        this.arbol = myTree;
    }

}//class
