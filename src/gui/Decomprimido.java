/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.Business;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author santiago
 */
public class Decomprimido extends JDialog implements ActionListener{

    private String texto;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private Business business;
    private JButton jbtnsalir;
    

    public Decomprimido(String text) {
        this.setTitle("Decomprimido");
        this.setSize(650, 525);
        this.setLocation(360, 00);
        this.setBackground(Color.black);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.texto = text;
        startComponents();
    }//constructor

    private void startComponents() {

        this.business = new Business(texto);
        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        this.textArea.setForeground(Color.green);
        this.textArea.setBackground(Color.black);
        this.textArea.setFont(new Font("Monospace", Font.PLAIN, 14));
        this.scrollPane = new JScrollPane();
        this.scrollPane.setViewportView(this.textArea);
        this.scrollPane.setBounds(0, 0, 650, 500);
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        
        this.jbtnsalir = new JButton("Salir");
        this.jbtnsalir.setBounds(0, 500, 650, 25);
        this.jbtnsalir.addActionListener(this);
        this.jbtnsalir.setBorder(null);
        this.jbtnsalir.setForeground(Color.white);
        this.jbtnsalir.setBackground(new Color(0,140,0));
        this.jbtnsalir.setFocusable(false);
        this.add(jbtnsalir);

        this.add(this.scrollPane);

        descomp();

    }//startComponents

    public void descomp() {
        try {
            String n = this.business.descomprimir();

            this.textArea.setText(n);
        } catch (IOException ex) {
            Logger.getLogger(Decomprimido.class.getName()).log(Level.SEVERE, null, ex);
        }//try-catch//try-catch

    }//convertText

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }

}//class
