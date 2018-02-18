
package riverraid;

import ElementosDelJuego.*;
import States.GameState;
import States.InstructionState;
import States.InstructionState2;
import States.MenuCreditos;
import States.MenuInitialState;
import States.MenuSecundaryState;
import States.MenuTop10State;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;



public class Game extends JFrame implements Ventana{
    private static Game instancia = null;
    
    JLabel fond,fond1;
    MenuInitialState men;
    MenuTop10State top;
    MenuSecundaryState menu;
    InstructionState inst;
    InstructionState2 inst2;
    MenuCreditos menuCreditos;
    GameState nuevoJuego;
    Sonido fondo;
    Icon icon1;
    Timer main,main2;
    int contador=0;
    
    /** inisializa un objeto de tipo Game*/
    public void initGame() throws MalformedURLException{
        GameStart.instance();  
    }
    /** Meotodo que genera menu de usuario*/
    public void NuevoJuego(){
        menu = new MenuSecundaryState(this);
        getContentPane().remove(men);
        getContentPane().remove(fond);
        super.getContentPane().add(menu);
        repaint();
    }
    /** Metodo que retorna al menu inicial*/
    public void VolverNuevoJuego(){
        men = new MenuInitialState(this);
        getContentPane().remove(menu);
        super.getContentPane().add(men);
        repaint();
    }
    /** Meotodo que genera menu de de top 10*/
    public void Top10(){
        top = new MenuTop10State(this);
        getContentPane().remove(men);
        super.getContentPane().add(top);
        repaint();
    }
    /** Metodo que retorna al menu inicial*/
    public void VolverTop10(){
        men = new MenuInitialState(this);
        getContentPane().remove(top);
        super.getContentPane().add(men);
        repaint();
    }
    /** Meotodo que genera menu de instrucciones*/
    public void Ayuda(){
        inst = new InstructionState(this);
        getContentPane().remove(men);
        super.getContentPane().add(inst);
        repaint();
    }
    /** Metodo que retorna al menu inicial*/
    public void VolverIns(){
        men = new MenuInitialState(this);
        getContentPane().remove(inst);
        super.getContentPane().add(men);
        repaint();
    }
    /** Meotodo que genera menu de instrucciones 2*/
    public void NextIns(){
        inst2 = new InstructionState2(this);
        getContentPane().remove(inst);
        super.getContentPane().add(inst2);
        repaint();
    }
    /** Metodo que retorna al menu instrucciones 1*/
    public void NextInsV(){
        inst = new InstructionState(this);
        getContentPane().remove(inst2);
        super.getContentPane().add(inst);
        repaint();
    }
    /** Meotodo que genera menu de creditos*/
    public void Creditos(){
        menuCreditos = new MenuCreditos(this);
        getContentPane().remove(men);
        super.getContentPane().add(menuCreditos);
        repaint();
    }
    /** Metodo que retorna al menu inicial*/
    public void VolverCreditos(){        
        men = new MenuInitialState(this);
        getContentPane().remove(menuCreditos);
        super.getContentPane().add(men);
        repaint();
    }
    /** Meotodo que tiene como funcion cerrar el juego*/
    public void Salir(){
        setVisible(false);
        dispose();
    }
    /** Meotodo que carga el gif y lo visualiza, usando un timer para luego mostrar el menu inicial*/
    private Game() throws MalformedURLException{
        
         super("River Raid");
         try{
             icon1 = new ImageIcon((new File("src/inicio/carga.gif")).toURI().toURL());
         }
         catch(NullPointerException e){
             System.out.println("El icono de la ventana no se encuentra en su ruta por defecto");
         }
         men = new MenuInitialState(this);
         fond = new JLabel(icon1);
         fondo = new Sonido();
         fondo.fondoMenu();
         fond.setLocation(0,0);
         fond.setSize(Ventana.width,Ventana.heigth);
         fond.setVisible(true);
         
         super.getContentPane().setBackground(new Color(0,0,0));
         super.setUndecorated(true);
         super.setSize(Ventana.width, Ventana.heigth);
         super.setLayout(null);
         super.setResizable(false);
         super.setLocationRelativeTo(null);
         super.getContentPane().add(fond);
         super.setVisible(true);
        main = new Timer(1000,new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
                 if(contador > 4){
                     getContentPane().remove(fond);
                     getContentPane().add(men);
                     repaint();
                     contador=0;
                     main.stop();
                 }
                 contador++;
             }
         });
         setDefaultCloseOperation(Game.EXIT_ON_CLOSE);
    }
    /** Permite crear una sola instancia de la clase Game*/
    public static Game instance() throws MalformedURLException{
        if(instancia == null){   
            instancia = new Game();
        }
        return instancia;
    }
    /** meotodo que detiene el sonido del fondo*/
   public void paparSonido()
   {
       fondo.pararSonidoFondo();
   }
   /** cierra el JFrame del menu*/
    public static void close_() throws MalformedURLException{
        instance().dispose();
        instancia = null;
    }
}