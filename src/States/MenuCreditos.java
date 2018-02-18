
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

public class MenuCreditos extends State{
    Game world;
    
    Sonido teclas;
    
    ImageIcon fond;
    ImageIcon volver;
    ImageIcon volver1;
    ImageIcon titCreditos;
    ImageIcon fondCreditos;
    
    JLabel volverB;
    JLabel titCreditosB;
    JLabel fondCreditosB;
    JLabel fondContainer;
    
    /** Evento cuand posiciona el mouse fuera del boton retorna la imagen incial del boton*/
    public MenuCreditos(Game world) {
        this.world=world;
        this.setVisible(true);
        teclas= new Sonido();
        onEnter();
    }

    @Override
    /** Metodo que carga las imagenes del fondo, titulo, botones del menu de creditos, informacion y uso 
    del evento MouseListener para devolver al menu inicial*/
    public void onEnter() {
        volverB = new JLabel();
        titCreditosB = new JLabel();
        fondCreditosB = new JLabel();
        fondContainer = new JLabel();
        
        try {
            fond = new ImageIcon(ImageIO.read(new File("src/inicio/fondito.png")));
            titCreditos = new ImageIcon(ImageIO.read(new File("src/inicio/CreditosT.png")));
            fondCreditos = new ImageIcon(ImageIO.read(new File("src/inicio/Creditos.png")));
            volver = new ImageIcon(ImageIO.read(new File("src/inicio/volver.png")));
            volver1 = new ImageIcon(ImageIO.read(new File("src/inicio/volver1.png")));        
        } catch (IOException ex) {
            Logger.getLogger(MenuCreditos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fondContainer.setIcon(fond);
        fondContainer.setName("f");
        fondContainer.setSize(fond.getIconWidth(), fond.getIconHeight());
        fondContainer.setLocation(0,0);
        fondContainer.setFocusable(false);
        fondContainer.setVisible(true);
        
        titCreditosB.setIcon(titCreditos);
        titCreditosB.setSize(titCreditos.getIconWidth(),titCreditos.getIconHeight());
        titCreditosB.setLocation(Ventana.width / 2 - titCreditos.getIconWidth() / 2 ,20);
        titCreditosB.setFocusable(false);
        titCreditosB.setVisible(true);
        
        fondCreditosB.setIcon(fondCreditos);
        fondCreditosB.setSize(fondCreditos.getIconWidth(),fondCreditos.getIconHeight());
        fondCreditosB.setLocation(Ventana.width - fondCreditos.getIconWidth() ,10);
        fondCreditosB.setFocusable(false);
        fondCreditosB.setVisible(true);  
        
        volverB.setIcon(volver);
        volverB.setName("v");
        volverB.setSize(volver.getIconWidth(),volver.getIconHeight());
        volverB.setLocation(Ventana.width / 2 - volver.getIconWidth() / 2, 600);
        volverB.setFocusable(false);
        volverB.setVisible(true);
        volverB.addMouseListener(new ActionClick());
        
        super.add(volverB);
        super.add(titCreditosB);
        super.add(fondCreditosB);
        super.add(fondContainer);
        super.setLayout(null);
        super.setLocation(0,0);
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
                world.VolverCreditos();
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
                volverB.setLocation(Ventana.width / 2 - volver1.getIconWidth() / 2, 600);
                volverB.setFocusable(false);
                volverB.setVisible(true);
                teclas.Boton();
            } 
        }

        @Override
        /** Evento cuand posiciona el mouse fuera del boton retorna la imagen incial del boton*/
        public void mouseExited(MouseEvent e) {
            if(e.getComponent().getName().equals("v")){
                volverB.setIcon(volver);
                volverB.setSize(volver.getIconWidth(),volver.getIconHeight());
                volverB.setLocation(Ventana.width / 2 - volver.getIconWidth() / 2, 600);
                volverB.setFocusable(false);
                volverB.setVisible(true);
            } 
        }
    }
}
