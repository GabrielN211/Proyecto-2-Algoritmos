
package gui;

import business.Business;
import domain.ArbolAVL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana extends JFrame implements ActionListener {

    private JScrollPane scroll;
    private PanelArbol panelA;
    private ArbolAVL arbol;
    private JFileChooser chooser;
    private JPanel panel;
    private JMenuBar barraMenu;
    private JMenu menuArchivo;
    private JMenuItem itemCargar,itemGuardar,ItemDescomprimir,itemBuscar,itemSalir;

    public Ventana() {

        super("Arbol");
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        
        
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setBounds(0,0,getWidth(),getHeight());
        this.panel.setBackground(Color.black);
        this.add(panel);
        init();

    }//construtor

    private void init() {
        arbol = new ArbolAVL();

      
        ///// MDI
        this.barraMenu = new JMenuBar();
        this.barraMenu.setBackground(Color.black);
        this.barraMenu.setBorder( new BevelBorder(0, Color.green, Color.green));
        this.setJMenuBar(barraMenu);
        
        this.menuArchivo= new JMenu("Archivo");
        this.menuArchivo.setForeground(Color.green);
        this.barraMenu.add(menuArchivo);
        
        this.itemCargar = new JMenuItem("Cargar archivo");
        this.itemCargar.addActionListener(this);
        this.menuArchivo.add(itemCargar);
        
        this.itemGuardar = new JMenuItem("GuardarArchivo");
        this.itemGuardar.addActionListener(this);
        this.menuArchivo.add(itemGuardar);
        
        this.ItemDescomprimir = new JMenuItem("Descomprimir Archivo");
        this.ItemDescomprimir.addActionListener(this);
        this.menuArchivo.add(ItemDescomprimir);
        
        this.itemBuscar = new JMenuItem("Buscar");
        this.itemBuscar.addActionListener(this);
        this.menuArchivo.add(itemBuscar);
        
        this.itemSalir = new JMenuItem("Salir");
        this.itemSalir.addActionListener(this);
        this.menuArchivo.add(itemSalir);


    }//startComponents

    public void pintarArbol() {
        this.panel.setVisible(false);
        this.panelA = new PanelArbol(this.arbol);
        this.scroll = new JScrollPane();
        this.scroll.setBounds(new Rectangle(0,0, getWidth(), getHeight()-46));
        this.scroll.setViewportView(this.panelA);
        this.scroll.getViewport().setView(this.panelA);
        this.panelA.setPreferredSize(new Dimension(2000, 2000));
        this.panelA.repaint();
        this.panelA.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
        this.add(this.scroll);

    }//paint

    public void crearArbol(String message) {

        String[] Array = message.split("[ \n]");

        for (int i = 0; i < Array.length; i++) {
            this.arbol.insertar(Array[i]);
        }

    }//crear

    public String cargarArchivo() throws IOException {
        String information = "";
        chooser = new JFileChooser();
        String rute;
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            FileReader fileReader = null;
            try {
                rute = chooser.getSelectedFile().getAbsolutePath();
                File file = new File(rute);

                fileReader = new FileReader(file);

                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    information += line + "\n";
                }//while
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }//try-catch//try-catch
        } else {

        }

        return information;
    }//openFIle

    public String openRute() throws IOException {

        chooser = new JFileChooser();
        String rute = "";
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            rute += chooser.getSelectedFile().getAbsolutePath();

        } else {

        }

        return rute;
    }//openFIle

    private void saveFileChooser() {
        String rute;
        chooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("txt", "TXT");
        chooser.setFileFilter(extensionFilter);
        try {
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                rute = chooser.getSelectedFile().getAbsolutePath();
                Business treeBusiness = new Business(rute);
                arbol.saveNodes(arbol.root, rute);
            }//if
        } catch (Exception ex) {
        }//try-catch

    }//saveFileChooser

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.itemCargar) {

            try {
                crearArbol(cargarArchivo());
                pintarArbol();
                repaint();
                revalidate();
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//cargar

        if (e.getSource() == this.itemGuardar) {
            saveFileChooser();
        }//guardar

        if (e.getSource() == this.ItemDescomprimir) {
            try {

                Decomprimido d = new Decomprimido(openRute());
                d.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }//try-catch//try-catch

        }//descomprimir

        if (e.getSource() == this.itemBuscar) {
            Buscar jDialogSearchNodes = new Buscar(this, true, arbol);
            jDialogSearchNodes.setVisible(true);
        }//buscar
        if(e.getSource()==this.itemSalir){
            System.exit(0);
        }

    }//action

}//Class
