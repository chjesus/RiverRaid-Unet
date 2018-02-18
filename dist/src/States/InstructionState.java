
package States;
import ElementosDelJuego.Sonido;
import riverraid.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import riverraid.Game;


public class InstructionState extends State{
    Game world;
    
    Sonido teclas;
    
    ImageIcon fond;
    ImageIcon volver;
    ImageIcon volver1;
    ImageIcon fondAyuda;
    ImageIcon botonNext;
    ImageIcon botonNext1;
    
    JLabel comoJugarB;
    ImageIcon comoJugar;
    
    JLabel volverB;
    JLabel fondAyudaB;
    JLabel botonNextB;
    JLabel fondContainer;
    
    /** Constructor que recibe la variable de la clase Game para el cambio de menus*/
    public InstructionState(Game world) {
        this.world=world;
        this.setVisible(true);
        teclas = new Sonido();
        onEnter();
    }
    
    @Override
    /** Metodo que carga las imagenes del fondo, titulo, botones del menu de instrucciones y uso 
     del evento MouseListener para devolver al menu inicial*/
    public void onEnter() {
        
        volverB = new JLabel();
        botonNextB = new JLabel();
        fondAyudaB = new JLabel();
        comoJugarB = new JLabel();
        fondContainer = new JLabel();        
        
        try {
            comoJugar = new ImageIcon(ImageIO.read(new File("src/inicio/AyudaJugar.png")));
            fondAyuda = new ImageIcon(ImageIO.read(new File("src/inicio/ComoJugar.png")));
            volver = new ImageIcon(ImageIO.read(new File("src/inicio/volver.png")));
            fond = new ImageIcon(ImageIO.read(new File("src/inicio/fondito.png")));
            volver1 = new ImageIcon(ImageIO.read(new File("src/inicio/volver1.png")));
            botonNext = new ImageIcon(ImageIO.read(new File("src/inicio/botonN.png")));
            botonNext1 = new ImageIcon(ImageIO.read(new File("src/inicio/botonN1.png")));
            
        } catch (IOException ex) {
            Logger.getLogger(InstructionState.class.getName()).log(Level.SEVERE, null, ex);
       
        }
        
        fondContainer.setIcon(fond);
        fondContainer.setName("f");
        fondContainer.setSize(fond.getIconWidth(), fond.getIconHeight());
        fondContainer.setLocation(0,0);
        fondContainer.setFocusable(false);
        fondContainer.setVisible(true);
        
        comoJugarB.setIcon(comoJugar);
        comoJugarB.setSize(comoJugar.getIconWidth(),comoJugar.getIconHeight());
        comoJugarB.setLocation(Ventana.width / 2 - comoJugar.getIconWidth() / 2 ,20);
        comoJugarB.setFocusable(false);
        comoJugarB.setVisible(true);
        
        fondAyudaB.setIcon(fondAyuda);
        fondAyudaB.setSize(fondAyuda.getIconWidth(),fondAyuda.getIconHeight());
        fondAyudaB.setLocation(Ventana.width / 2 - fondAyuda.getIconWidth() / 2 ,10);
        fondAyudaB.setFocusable(false);
        fondAyudaB.setVisible(true); 
        
        volverB.setIcon(volver);
        volverB.setName("v");
        volverB.setSize(volver.getIconWidth(),volver.getIconHeight());
        volverB.setLocation(Ventana.width / 3 - volver.getIconWidth(), 600);
        volverB.setFocusable(false);
        volverB.setVisible(true);
        volverB.addMouseListener(new ActionClick());
        
        botonNextB.setIcon(botonNext);
        botonNextB.setName("n");
        botonNextB.setSize(botonNext.getIconWidth(),botonNext.getIconHeight());
        botonNextB.setLocation(Ventana.width / 2 + botonNext.getIconWidth() , 600);
        botonNextB.setFocusable(false);
        botonNextB.setVisible(true);
        botonNextB.addMouseListener(new ActionClick());
        
        super.add(comoJugarB);
        super.add(volverB);
        super.add(botonNextB);
        super.add(fondAyudaB);
        super.add(fondContainer);
        super.setLayout(null);
        super.setLocation(0, 0);
        super.setFocusable(false);
        super.setSize(Ventana.width, Ventana.heigth);
        super.setVisible(true);
    }
    /** clase para que implementa el evento mouseListener para los botones*/
    class ActionClick implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        /** Retorna al menu Inicial*/
        public void mousePressed(MouseEvent e) {
            if(e.getComponent().getName().equals("v")){ //iniciar partida
                world.VolverIns();
            }
            if(e.getComponent().getName().equals("n")){
                world.NextIns();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        /** Evento cuand posiciona el mouse cambia la imagen del boton*/
        public void mouseEntered(MouseEvent e) {
            if(e.getComponent().getName().equals("v")){
                volverB.setIcon(volver1);
                volverB.setSize(volver1.getIconWidth(),volver1.getIconHeight());
                volverB.setLocation(Ventana.width / 3 - volver1.getIconWidth(), 600);
                volverB.setFocusable(false);
                volverB.setVisible(true);
                teclas.Boton();
            }  
            if(e.getComponent().getName().equals("n")){
                botonNextB.setIcon(botonNext1);
                botonNextB.setSize(botonNext1.getIconWidth(),botonNext1.getIconHeight());
                botonNextB.setLocation(Ventana.width / 2 + botonNext1.getIconWidth() , 600);
                botonNextB.setFocusable(false);
                botonNextB.setVisible(true);
                teclas.Boton();
            }
        }

        @Override
        /** Evento cuand posiciona el mouse fuera del boton retorna la imagen incial del boton*/
        public void mouseExited(MouseEvent e) {
            if(e.getComponent().getName().equals("v")){
                volverB.setIcon(volver);
                volverB.setSize(volver.getIconWidth(),volver.getIconHeight());
                volverB.setLocation(Ventana.width / 3 - volver.getIconWidth(), 600);
                volverB.setFocusable(false);
                volverB.setVisible(true);
            }       
            if(e.getComponent().getName().equals("n")){
                botonNextB.setIcon(botonNext);
                botonNextB.setSize(botonNext.getIconWidth(),botonNext.getIconHeight());
                botonNextB.setLocation(Ventana.width / 2 + botonNext.getIconWidth() , 600);
                botonNextB.setFocusable(false);
                botonNextB.setVisible(true);
            }
        }
    }
}