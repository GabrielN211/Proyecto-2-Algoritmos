
package gui;

import domain.AVLNode;
import domain.ArbolAVL;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Buscar extends JDialog implements ActionListener{
    
    private JLabel jlbBanner;
    private JLabel jlbResult;
    private JTextField jtxtFind;
    private JButton jbtnFind,jbtnsalir;
    private ArbolAVL aVLtree;
    private JPanel panel;
    private String text;

    public Buscar(JFrame frame, boolean  b,ArbolAVL aVLtree) {
        super(frame, b);
        this.setUndecorated(true);
        this.aVLtree = aVLtree;
        this.setTitle("Search a node");
        this.setLayout(null);
        this.setSize(350,300);
        this.setLocation(10,100);
        this.panel = new JPanel();
        this.panel.setBounds(0,0,getWidth(),getHeight());
        this.panel.setBackground(new Color(9,9,9));
        this.panel.setLayout(null);
        startComponents();
    }//constructor
    
    private void startComponents(){
        
        this.jlbBanner= new JLabel("Escriba una palabra:");
        this.jlbBanner.setBounds(40, 20, 340, 30);
        this.jlbBanner.setFont(new Font("Monospace", Font.PLAIN, 15));
        this.jlbBanner.setForeground(Color.GREEN);
        
        this.jlbResult= new JLabel();
        this.jlbResult.setBounds(50, 185, 340, 30);
        this.jlbResult.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        this.jlbResult.setForeground(new Color(0,255,0));
        
        this.jtxtFind= new JTextField();
        this.jtxtFind.setBounds(230, 20, 80, 30);
        this.jtxtFind.setFont(new Font("Monospace", Font.PLAIN, 14));
        
        this.jbtnFind = new JButton("Search");
        this.jbtnFind.setBounds(70, 70, 150, 35);
        this.jbtnFind.addActionListener(this);
        this.jbtnFind.setBorder(null);
        this.jbtnFind.setForeground(Color.green);
        this.jbtnFind.setBackground(new Color(25,23,20));
        this.jbtnFind.setFocusable(false);
        
        this.jbtnsalir = new JButton("Salir");
        this.jbtnsalir.setBounds(140, 270, 80, 25);
        this.jbtnsalir.addActionListener(this);
        this.jbtnsalir.setBorder(null);
        this.jbtnsalir.setForeground(Color.white);
        this.jbtnsalir.setBackground(new Color(35,0,0));
        this.jbtnsalir.setFocusable(false);

       
        this.panel.add(this.jtxtFind);
        this.panel.add(this.jlbBanner);
        this.panel.add(this.jlbResult);
        this.panel.add(this.jbtnFind);
        this.panel.add(this.jbtnsalir);
        this.add(this.panel);
       
    }// startComponents
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.jbtnFind) {
            try{
                
                AVLNode node = this.aVLtree.find(aVLtree.root, this.jtxtFind.getText());
                String node1 = "";
                node1 = node.getQuantityPositions();
                node1 = node1.substring(0, node1.length() - 1);
                if (node != null) {
                    this.jlbResult.setForeground(Color.green);
                    this.jlbResult.setText("Node: " + node.getChain());
                } else {
                    this.jlbResult.setText(" Not found!");
                }
                this.jtxtFind.setText("");
            }
            catch(NullPointerException ex){
                this.jlbResult.setForeground(Color.red);
                this.jlbResult.setText("No se encontro el dato");
            }
        }//this.jbtnFind
        if(e.getSource() == this.jbtnsalir){
            this.dispose();
        }
    
    }//action

    
}//class
