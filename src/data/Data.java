
package data;

import domain.AVLNode;
import domain.Desomprimir;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Data {

    private String fileName;
    private int Code=0;

    public Data(String fileName) {
        this.fileName = fileName;
    }//constructor

    public void saveTree(AVLNode aVLNode) throws FileNotFoundException, IOException {
        String n = aVLNode.getChain();
    
        String m=comprimir(n);
        
        File file = new File(this.fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintStream printStream = new PrintStream(fileOutputStream);
       
        printStream.println(m.substring(0, 3) + "," +aVLNode.getQuantityPositions());

    }//saveTree

    public String getTree() throws FileNotFoundException, IOException {
        String result = "";
        File file = new File(this.fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {

            result += line + "&";

        }//while
        result = result.substring(0, result.length() - 1);

        
        return result;
    }//getTree

    public String Convertir() throws IOException {
        String splitBar = this.getTree();
        String[] splitBars = splitBar.split("&");
        ArrayList<Desomprimir> bt = new ArrayList<Desomprimir>();

        for (int i = 0; i < splitBars.length; i++) {
            String[] temp = splitBars[i].split(",");
            String tempWord = temp[0], tempNumber = temp[1];
            String[] tempNumbers = tempNumber.split("-");

            for (int j = 0; j < tempNumbers.length; j++) {
                bt.add(new Desomprimir(tempWord, Integer.parseInt(tempNumbers[j])));
            }//forTempNumber

        }//forSplitBar
        
        Desomprimir[] finalResult= new Desomprimir[bt.size()];
        
        for (int i = 0; i < finalResult.length; i++) {
           finalResult[i]=bt.get(i);
        }//for
        
        return insertion(finalResult);

    }//backToText
    
    public String insertion(Desomprimir[] backToTexts) throws IOException {
        String result = "";
        int p, j;
        Desomprimir aux;
        for (p = 1; p < backToTexts.length; p++) {
            aux = backToTexts[p];
            j = p - 1;
            while ((j >= 0) && (aux.getPosition() < backToTexts[j].getPosition())) {

                backToTexts[j + 1] = backToTexts[j];
                j--;
            }
            backToTexts[j + 1] = aux;
        }//for i

        for (int x = 0; x < backToTexts.length; x++) {
            result += backToTexts[x].getName() + " ";
        }//forConcatena
        result=(descomprimir(result));
        return result;

    }//insertion
    
    
    /////////////////////COMPRECION POR REGISTRO/////////////////////////////////////////
    
    public String comprimir(String cad) throws FileNotFoundException, IOException{
        
        String n ="";
        int code =  getLastIndex();
       
        // String m[] = leer("t.txt").split(" ");
       // for (int i = 0; i < m.length; i++) {
            code++;
            
            
            //System.out.println("lleaga");
            if(nueva(cad)==false){
                
                return guardar(cad,code," ");
            }else{
                return (getCode(cad)+cad+" ");
            }
    
    }
    public static String descomprimir(String n) throws IOException{
        String vec[]=n.split(" ");
        String vecL[] = (leer("datos.txt")).split(" ");
        String vecAux[] = (leer("datos.txt")).split(" ");
         
        for (int i = 0; i < vecL.length; i++) {
           vecL[i]=vecL[i].substring(0, 3);
        }
        for (int i = 0; i < vecAux.length; i++) {//palabras
           vecAux[i]=vecAux[i].substring(3,vecAux[i].length());
        }
        for (int i = 0; i < vec.length; i++) {
            
            for (int j = 0; j < vecL.length; j++) {
                
                if(vec[i].equals(vecL[j])){
                    vec[i]=vecAux[j];
                }
            }
        }
        String result="";
        for (int i = 0; i < vec.length; i++) {
            result+=(vec[i]+" ");
        }
        
      
        //vectores listos
        
        
        return result;
    }//descomprimir
    
    public static String getCode(String cad) throws IOException{
        String n = leer("datos.txt");
         String[]m = n.split(" ");
         for (int i = 0; i < m.length; i++) {
            if(m[i].substring(3).equals(cad)){
                return m[i].substring(0, 3);
            }else{
                n= "";
            }
        }
        return n;  
    }
    
    public static int getLastIndex() throws IOException{
        
        String n = leer("datos.txt");

        String[]m = n.split(" ");
        
        n=m[m.length-1].substring(0,3);
        return Integer.parseInt(n);
    }
    public static boolean nueva(String m) throws IOException{
        String[] n = leer("datos.txt").split(" ");
        boolean nueva = false;
        for (int i = 0; i < n.length; i++) {
//            System.out.println(m+"<<<<agregar");
//            System.out.println(n[i].substring(3)+"<<<<esta");
            if(n[i].substring(3).equals(m)){

                nueva = true;
            }
            
        }
        return nueva;
    }
    public static String guardar(String n,int code,String m) throws FileNotFoundException{
        
        File file = new File("datos.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(code+n+m);
        return (code+n+m);
    }
        
    
    public static String leer(String path) throws FileNotFoundException, IOException{
        String result = "";
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        
        while ((line = bufferedReader.readLine()) != null) {

            result += line;

        }//while
        return result;
    }
      
   
    
    
    

}//class
