
package States;
import ElementosDelJuego.Sonido;
import riverraid.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;



public class MenuInitialState extends State implements Ventana{
 
    Timer main;
    
    JLabel Titulo;
    JLabel cargarBTN,iniciarBTN,cheatsBTN,aiudaBTN,creditosBTM,exitBTN;
    
    ImageIcon Titulo1;
    ImageIcon cargar,iniciar,cheats,aiuda,creditos,exit;
    ImageIcon cargar1,iniciar1,cheats1,aiuda1,creditos1,exit1;
    Sonido teclas;
  
    int contador = 0;

    JLabel fonditoConteiner;
    ImageIcon fondito;

    Game world;
    /** Constructor que recibe la variable de la clase Game para el cambio de menus*/
    public MenuInitialState(Game world) {
        teclas= new Sonido();
        this.world=world;
        this.onEnter();
    }
    
    @Override
    /** Metodo que carga las imagenes del fondo, titulo, botones del menu de inicial y uso 
    del evento MouseListener para acceder a los sub-menus*/
    public void onEnter() { 
        
        Titulo = new JLabel();
        
        cargarBTN = new JLabel();
        iniciarBTN = new JLabel();
        cheatsBTN = new JLabel();
        aiudaBTN = new JLabel();
        creditosBTM =new JLabel();
        exitBTN = new JLabel();
        fonditoConteiner = new JLabel();
        
        try {
            Titulo1 = new ImageIcon(ImageIO.read(new File("src/inicio/Titulo.png")));
            cargar = new ImageIcon(ImageIO.read(new File("src/inicio/charge0.png")));
            iniciar = new ImageIcon(ImageIO.read(new File("src/inicio/start0.png")));
            aiuda = new ImageIcon(ImageIO.read(new File("src/inicio/help0.png")));
            exit = new ImageIcon(ImageIO.read(new File("src/inicio/exit0.png")));
            creditos = new ImageIcon(ImageIO.read(new File("src/inicio/creditos0.png")));
            fondito = new ImageIcon(ImageIO.read(new File("src/inicio/fondito.png")));
            
            cargar1 = new ImageIcon(ImageIO.read(new File("src/inicio/charge1.png")));
            iniciar1 = new ImageIcon(ImageIO.read(new File("src/inicio/start1.png")));
            aiuda1 = new ImageIcon(ImageIO.read(new File("src/inicio/help1.png")));
            creditos1 = new ImageIcon(ImageIO.read(new File("src/inicio/creditos1.png")));
            exit1 = new ImageIcon(ImageIO.read(new File("src/inicio/exit1.png")));


        } catch (IOException ex) {
            System.out.println("No entro");
        }
        
        
        fonditoConteiner.setIcon(fondito);
        fonditoConteiner.setName("c");
        fonditoConteiner.setSize(fondito.getIconWidth(), fondito.getIconHeight());
        fonditoConteiner.setLocation(0,0);
        fonditoConteiner.setFocusable(false);
        fonditoConteiner.setVisible(true);
        
        Titulo.setIcon(Titulo1);
        Titulo.setSize(Titulo1.getIconWidth(), Titulo1.getIconHeight());
        Titulo.setLocation(Ventana.width / 2 - Titulo1.getIconWidth() / 3 ,20);
        Titulo.setFocusable(false);
        Titulo.setVisible(true);
        
        cargarBTN.setIcon(cargar);
        cargarBTN.setName("c");
        cargarBTN.setSize(cargar.getIconWidth(), cargar.getIconHeight());
        cargarBTN.setLocation(Ventana.width / 2 + cargar.getIconWidth() / 2, 200);
        cargarBTN.setFocusable(false);
        cargarBTN.setVisible(true);
        cargarBTN.addMouseListener(new ActionClick());
            
        iniciarBTN.setIcon(iniciar);
        iniciarBTN.setName("i");
        iniciarBTN.setLocation(Ventana.width / 2 + iniciar.getIconWidth() / 2 , 200 + cargar.getIconHeight() + 10);
        iniciarBTN.setSize(iniciar.getIconWidth(), iniciar.getIconHeight());
        iniciarBTN.setFocusable(false);
        iniciarBTN.setVisible(true);
        iniciarBTN.addMouseListener(new ActionClick());

        aiudaBTN.setIcon(aiuda);
        aiudaBTN.setName("a");
        aiudaBTN.setLocation(Ventana.width / 2 + aiuda.getIconWidth() / 2 , 200 + cargar.getIconHeight() + iniciar.getIconHeight() + 10 + 10);
        aiudaBTN.setSize(aiuda.getIconWidth(), aiuda.getIconHeight());
        aiudaBTN.setFocusable(false);
        aiudaBTN.setVisible(true);
        aiudaBTN.addMouseListener(new ActionClick());
        
        creditosBTM.setIcon(creditos);
        creditosBTM.setName("r");
        creditosBTM.setLocation(Ventana.width / 2 + creditos.getIconWidth() / 2, 200 + creditos.getIconHeight() + creditos.getIconHeight() + iniciar.getIconHeight() + 10 + 10 + 10);
        creditosBTM.setSize(creditos.getIconWidth(), creditos.getIconHeight());
        creditosBTM.setFocusable(false);
        creditosBTM.setVisible(true);
        creditosBTM.addMouseListener(new ActionClick());

        exitBTN.setIcon(exit);
        exitBTN.setName("e");
        exitBTN.setLocation(Ventana.width / 2 + exit.getIconWidth() / 2 , 200 + aiuda.getIconHeight() + cargar.getIconHeight() + iniciar.getIconHeight() + iniciar.getIconHeight() + 10 + 10 + 10 + 10);
        exitBTN.setSize(exit.getIconWidth(), exit.getIconHeight());
        exitBTN.setFocusable(false);
        exitBTN.setVisible(true);
        exitBTN.addMouseListener(new ActionClick());
        
        super.add(Titulo);
        super.add(cargarBTN);
        super.add(iniciarBTN);
        super.add(aiudaBTN);
        super.add(creditosBTM);
        super.add(exitBTN);
        super.add(fonditoConteiner);
         
        super.setLayout(null);
        super.setLocation(0, 0);
        super.setFocusable(false);
        super.setSize(Ventana.width, Ventana.heigth);
        super.setBackground(Color.WHITE);
        super.setVisible(true);
    }
    /** clase para que implementa el evento mouseListener para los botones*/
    class ActionClick implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        /** clase para que implementa el evento mouseListener para los botones*/
        public void mousePressed(MouseEvent e) {
            if(e.getComponent().getName().equals("i")){ //top10
                world.Top10();
            }
            if(e.getComponent().getName().equals("c")){ //iniciar partida
                world.NuevoJuego();
            }
            if(e.getComponent().getName().equals("a")){ //ayuda
                world.Ayuda();
            }
            if(e.getComponent().getName().equals("r")){ //Creditos
                world.Creditos();
            }
            if(e.getComponent().getName().equals("e")){ //salida
                world.Salir();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        /** Evento cuand posiciona el mouse cambia la imagen del boton*/
        public void mouseEntered(MouseEvent e) {
            if(e.getComponent().getName().equals("c")){ //cargar partida
                cargarBTN.setIcon(cargar1);
                cargarBTN.setSize(cargar1.getIconWidth(), cargar1.getIconHeight());
                cargarBTN.setLocation(Ventana.width / 2 + cargar1.getIconWidth() / 2, 200);
                cargarBTN.setFocusable(false);
                cargarBTN.setVisible(true);
                teclas.Boton();
            }
            if(e.getComponent().getName().equals("i")){
                iniciarBTN.setIcon(iniciar1);
                iniciarBTN.setLocation(Ventana.width / 2 + iniciar1.getIconWidth() / 2, 200 + cargar.getIconHeight() + 10);
                iniciarBTN.setSize(iniciar1.getIconWidth(), iniciar1.getIconHeight());
                iniciarBTN.setFocusable(false);
                iniciarBTN.setVisible(true);
                teclas.Boton();
            }
            if(e.getComponent().getName().equals("a")){
                aiudaBTN.setIcon(aiuda1);
                aiudaBTN.setLocation(Ventana.width / 2 + aiuda1.getIconWidth() / 2, 200 + cargar1.getIconHeight() + iniciar.getIconHeight() + 10 + 10);
                aiudaBTN.setSize(aiuda1.getIconWidth(), aiuda1.getIconHeight());
                aiudaBTN.setFocusable(false);
                aiudaBTN.setVisible(true);
                teclas.Boton();
            }
            if(e.getComponent().getName().equals("r")){
                creditosBTM.setIcon(creditos1);
                creditosBTM.setLocation(Ventana.width / 2 + creditos1.getIconWidth() / 2, 200 + creditos1.getIconHeight() + creditos1.getIconHeight() + iniciar1.getIconHeight() + 10 + 10 + 10);
                creditosBTM.setSize(creditos1.getIconWidth(), creditos1.getIconHeight());
                creditosBTM.setFocusable(false);
                creditosBTM.setVisible(true);
                teclas.Boton();
            }
            if(e.getComponent().getName().equals("e")){
                exitBTN.setIcon(exit1);
                exitBTN.setLocation(Ventana.width / 2 + exit1.getIconWidth() / 2, 200 + aiuda1.getIconHeight() + cargar1.getIconHeight() + iniciar1.getIconHeight() + iniciar1.getIconHeight() + 10 + 10 + 10 + 10);
                exitBTN.setSize(exit1.getIconWidth(), exit1.getIconHeight());
                exitBTN.setFocusable(false);
                exitBTN.setVisible(true);
                teclas.Boton();
                 
            }
        }

        @Override
        /** Evento cuand posiciona el mouse fuera del boton retorna la imagen incial del boton*/
        public void mouseExited(MouseEvent e) {
            if(e.getComponent().getName().equals("c")){ //cargar partida
                cargarBTN.setIcon(cargar);
                cargarBTN.setSize(cargar.getIconWidth(), cargar.getIconHeight());
                cargarBTN.setLocation(Ventana.width / 2 + cargar.getIconWidth() / 2, 200);
                cargarBTN.setFocusable(false);
                cargarBTN.setVisible(true);
                
            }
            if(e.getComponent().getName().equals("i")){
                iniciarBTN.setIcon(iniciar);
                iniciarBTN.setLocation(Ventana.width / 2 + iniciar.getIconWidth() / 2, 200 + iniciar.getIconHeight() + 10);
                iniciarBTN.setSize(iniciar.getIconWidth(), iniciar.getIconHeight());
                iniciarBTN.setFocusable(false);
                iniciarBTN.setVisible(true);
                
            }
            if(e.getComponent().getName().equals("a")){
                aiudaBTN.setIcon(aiuda);
                aiudaBTN.setLocation(Ventana.width / 2 + aiuda.getIconWidth() / 2, 200 + aiuda.getIconHeight() + iniciar.getIconHeight() + 10 + 10);
                aiudaBTN.setSize(aiuda.getIconWidth(), aiuda.getIconHeight());
                aiudaBTN.setFocusable(false);
                aiudaBTN.setVisible(true);
            }
            if(e.getComponent().getName().equals("r")){
                creditosBTM.setIcon(creditos);
                creditosBTM.setLocation(Ventana.width / 2 + creditos.getIconWidth() / 2, 200 + creditos.getIconHeight() + creditos.getIconHeight() + iniciar.getIconHeight() + 10 + 10 + 10);
                creditosBTM.setSize(creditos.getIconWidth(), creditos.getIconHeight());
                creditosBTM.setFocusable(false);
                creditosBTM.setVisible(true);
            }
            if(e.getComponent().getName().equals("e")){
                exitBTN.setIcon(exit);
                exitBTN.setLocation(Ventana.width / 2 + exit.getIconWidth() / 2, 200 + aiuda.getIconHeight() + cargar.getIconHeight() + iniciar.getIconHeight() + iniciar.getIconHeight() + 10 + 10 + 10 + 10);
                exitBTN.setSize(exit.getIconWidth(), exit.getIconHeight());
                exitBTN.setFocusable(false);
                exitBTN.setVisible(true);
            }
        }
        
    }
}