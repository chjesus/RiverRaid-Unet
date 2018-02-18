
package States;

import ElementosDelJuego.GameStart;
import ElementosDelJuego.Sonido;
import java.awt.Font;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import riverraid.Game;
import riverraid.Ventana;

public class MenuSecundaryState extends State {
    
    Game world;
    
    Sonido teclas;
    
    JLabel enterB;
    JLabel tituloB;
    JLabel volverB;
    JPanel containerText = new JPanel();
    JLabel fonditoConteiner  = new JLabel();
    JLabel Titulo = new JLabel("Ingrese un Usuario minimo de 3 Caracter y Maximo de 7 Caracteres!");
    JLabel TituloN = new JLabel("Nombre de Usuario: ");
    
    ImageIcon enter;
    ImageIcon titulo;
    ImageIcon volver;
    ImageIcon volver1;
    ImageIcon fondito;
    
    private BufferedWriter controlador;
    
    JTextField text = new JTextField("",8);  
    
    Font fuente = new Font("Agency FB",Font.BOLD,30);
    Font fuente2 = new Font("Agency FB",Font.BOLD,25);
    int valor =50;
     /** Constructor que recibe la variable de la clase Game para el cambio de menus
     y posiciona la caja de texto*/
    public MenuSecundaryState(Game world) {
        this.world=world;
        text.setBounds(Ventana.width/2 - 100, 150, 200, 20);
        text.addKeyListener(new Teclado());
        teclas = new Sonido();
        Titulo.setFont(fuente);
        TituloN.setFont(fuente2);
        Titulo.setBounds(Ventana.width / 2 - Ventana.heigth / (5/2), 160, 800, 100);
        TituloN.setBounds(280, 108, 400, 100);
        this.add(TituloN);
        this.add(Titulo);
        this.add(text); 
        this.setVisible(true);
        onEnter();
    }
    @Override
    /** Metodo que carga las imagenes del fondo, titulo, botones del menu de usuario y uso 
    del evento MouseListener para devolver al menu inicial*/
    public void onEnter() {
        enterB = new JLabel();
        volverB = new JLabel();
        tituloB = new JLabel();  
        fonditoConteiner = new JLabel();
        try {  
            enter = new ImageIcon(ImageIO.read(new File("src/inicio/enter.png")));
            volver = new ImageIcon(ImageIO.read(new File("src/inicio/volver.png")));
            titulo = new ImageIcon(ImageIO.read(new File("src/inicio/Usuario.png")));
            fondito = new ImageIcon(ImageIO.read(new File("src/inicio/fondito.png")));
            volver1 = new ImageIcon(ImageIO.read(new File("src/inicio/volver1.png")));
        } catch (IOException ex) {
            Logger.getLogger(MenuSecundaryState.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fonditoConteiner.setIcon(fondito);
        fonditoConteiner.setName("c");
        fonditoConteiner.setSize(fondito.getIconWidth(), fondito.getIconHeight());
        fonditoConteiner.setLocation(0,0);
        fonditoConteiner.setFocusable(false);
        fonditoConteiner.setVisible(true);
        
        tituloB.setIcon(titulo);
        tituloB.setSize(titulo.getIconWidth(), titulo.getIconHeight());
        tituloB.setLocation(Ventana.width / 2 - titulo.getIconWidth() / 2 ,20);
        tituloB.setFocusable(false);
        tituloB.setVisible(true);
        
        volverB.setIcon(volver);
        volverB.setName("v");
        volverB.setSize(volver.getIconWidth(),volver.getIconHeight());
        volverB.setLocation(Ventana.width / 2 - volver.getIconWidth() / 2, 600);
        volverB.setFocusable(false);
        volverB.setVisible(true);
        volverB.addMouseListener(new ActionClick());
        
        super.add(enterB);
        super.add(tituloB);
        super.add(volverB);
        super.add(fonditoConteiner);
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
                world.VolverNuevoJuego();
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
                volverB.setLocation(Ventana.width / 2 - volver.getIconWidth() / 2, 600);
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
    /**clase del evento teclado*/
    class Teclado extends KeyAdapter{
        int enterF=0;
        @Override
         /** metodo para maxima  7 caracteres en la caja de texto*/
        public void keyTyped(KeyEvent e) {
            if(text.getText().length()>7){
                e.consume();
            }
            if(text.getText().length()>1 && e.getKeyCode()!=KeyEvent.VK_ENTER){
                enterB.setIcon(enter);
                enterB.setSize(enter.getIconWidth(), enter.getIconHeight());
                enterB.setLocation(Ventana.width / 2 - enter.getIconWidth() / 2, 70);
                enterB.setFocusable(false);
                enterB.setVisible(true);
                enterF=1;
            }
        }

        @Override
         /** metodo para maxima  7 caracteres en la caja de texto*/
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_ENTER && enterF==1){
                try {
                    Game.instance().paparSonido();
                    controlador= new BufferedWriter(new FileWriter("respaldo.txt"));
                    controlador.write(text.getText());
                    controlador.close();
                } catch (IOException ex) {
                    System.out.println("error el archivo de respaldo no se pudo crear");
                }
                   GameStart.instance();
                    try {
                        Game.close_();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(MenuSecundaryState.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }    
}
