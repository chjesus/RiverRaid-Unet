
package States;

import ElementosDelJuego.Sonido;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import riverraid.Game;
import riverraid.Ventana;

public class InstructionState2 extends State{
    Game world;
    
    Sonido teclas;
        
    ImageIcon fondo;
    ImageIcon botonVolver;
    ImageIcon botonVolver1;
    ImageIcon titulo;
    ImageIcon botonNext;
    ImageIcon botonNext1;
    
    JLabel comoJugarB;
    ImageIcon comoJugar;
    
    JLabel botonVolverB;
    JLabel tituloB;
    JLabel botonNextB;
    JLabel fondoContainer;

    /** Constructor que recibe la variable de la clase Game para el cambio de menus*/
    public InstructionState2(Game world) {
        this.world=world;
        this.setVisible(true);
        teclas = new Sonido();
        onEnter();
    }
    @Override
    public void onEnter() {
        
        fondoContainer = new JLabel();
        botonNextB = new JLabel();
        tituloB = new JLabel();
        botonVolverB = new JLabel();
        comoJugarB = new JLabel();
        
        try {
            fondo = new ImageIcon(ImageIO.read(new File("src/inicio/fondito.png")));
            titulo = new ImageIcon(ImageIO.read(new File("src/inicio/AyudaJugar.png")));
            comoJugar = new ImageIcon(ImageIO.read(new File("src/inicio/ComoJugar1.png")));
            //botonNext = new ImageIcon(ImageIO.read(new File("src/inicio/fondito.png")));
            //botonNext1 = new ImageIcon(ImageIO.read(new File("src/inicio/fondito.png")));
            botonVolver = new ImageIcon(ImageIO.read(new File("src/inicio/volver.png")));
            botonVolver1 = new ImageIcon(ImageIO.read(new File("src/inicio/volver1.png")));
        } catch (IOException ex) {
            Logger.getLogger(InstructionState2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fondoContainer.setIcon(fondo);
        fondoContainer.setSize(fondo.getIconWidth(),fondo.getIconHeight());
        fondoContainer.setLocation(0, 0);
        fondoContainer.setFocusable(false);
        fondoContainer.setVisible(true);
        
        tituloB.setIcon(titulo);
        tituloB.setSize(titulo.getIconWidth(),titulo.getIconHeight());
        tituloB.setLocation(Ventana.width / 2 - titulo.getIconWidth() / 2 ,20);
        tituloB.setFocusable(false);
        tituloB.setVisible(true);
        
        comoJugarB.setIcon(comoJugar);
        comoJugarB.setSize(comoJugar.getIconWidth(),comoJugar.getIconHeight());
        comoJugarB.setLocation(Ventana.width / 2 - comoJugar.getIconWidth() / 2 ,10);
        comoJugarB.setFocusable(false);
        comoJugarB.setVisible(true);
        
        botonVolverB.setIcon(botonVolver);
        botonVolverB.setName("v");
        botonVolverB.setSize(botonVolver.getIconWidth(),botonVolver.getIconHeight());
        botonVolverB.setLocation(Ventana.width / 2 - botonVolver.getIconWidth() / 2 , 600);
        botonVolverB.setFocusable(false);
        botonVolverB.setVisible(true);
        botonVolverB.addMouseListener(new ActionClick());
        
        super.add(tituloB);
        super.add(comoJugarB);
        super.add(botonVolverB);
        super.add(fondoContainer);
        super.setLayout(null);
        super.setLocation(0, 0);
        super.setFocusable(false);
        super.setSize(Ventana.width,Ventana.heigth);
        super.setVisible(true);
    }
    class ActionClick implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getComponent().getName().equals("v")){ //iniciar partida
                world.NextInsV();
            }      
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getComponent().getName().equals("v")){
                botonVolverB.setIcon(botonVolver1);
                botonVolverB.setSize(botonVolver1.getIconWidth(),botonVolver1.getIconHeight());
                botonVolverB.setLocation(Ventana.width / 2 - botonVolver1.getIconWidth() / 2, 600);
                botonVolverB.setFocusable(false);
                botonVolverB.setVisible(true);
                teclas.Boton();
            }         
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getComponent().getName().equals("v")){
                botonVolverB.setIcon(botonVolver);
                botonVolverB.setSize(botonVolver.getIconWidth(),botonVolver.getIconHeight());
                botonVolverB.setLocation(Ventana.width / 2 - botonVolver.getIconWidth() / 2, 600);
                botonVolverB.setFocusable(false);
                botonVolverB.setVisible(true);
            }         
        }
    }
}
