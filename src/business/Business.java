
package business;

import domain.AVLNode;
import data.Data;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Business {

    private Data treeData;

    public Business(String fileName) {
        this.treeData = new Data(fileName);
    }//constructor

    public void saveTree(AVLNode aVLNode) throws FileNotFoundException, IOException {
       
        this.treeData.saveTree(aVLNode);

    }//saveTree

    public String getTree() throws FileNotFoundException, IOException {
        
        return this.treeData.getTree();
        
    }//getTree
    
      public String descomprimir() throws IOException {
          return this.treeData.Convertir();
      }

}//class
