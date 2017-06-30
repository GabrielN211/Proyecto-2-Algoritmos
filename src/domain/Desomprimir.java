
package domain;


public class Desomprimir {
    
    private String name;
    private int position;

    public Desomprimir(String name, int position) {
        this.name = name;
        this.position = position;
    }//constructor

    public Desomprimir() {
        this.name="";
        this.position=0;
    }//default

  
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    

    
}//class
