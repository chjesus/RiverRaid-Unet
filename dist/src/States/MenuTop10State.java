
package States;

import ElementosDelJuego.Sonido;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import riverraid.Game;
import riverraid.Ventana;


public class MenuTop10State extends State{
    int cont,a=0;
    int temp;
    String temp1;
    String linea;
    String[] auxData;
    String[] auxArchivo;
        
    String[] nombre;
    int[] score;
    String[] nombreM;
    String[] scoreM;
    
    Game world;
    
    Sonido teclas;
    
    JLabel tituloB;
    JLabel volverB;
    JLabel fonditoConteiner  = new JLabel();
     
    JLabel Titulo = new JLabel("Usuario");
    JLabel Score = new JLabel("Score");
    
    JLabel[] UsuarioNom = new JLabel[10];
    JLabel[] ScoreNom = new JLabel[10];
    
    ImageIcon titulo;
    ImageIcon volver;
    ImageIcon volver1;
    ImageIcon fondito;
    
    Font fuente = new Font("Agency FB", 3, 40);
    Font fuente2 = new Font("Agency FB", 3, 30);
    /** Constructor que recibe la variable de la clase Game para el cambio de menus y inicializa los meotods
     de cargar, ordernar*/
    public MenuTop10State(Game world){
        this.world=world;
        teclas = new Sonido();
        CargarScore();
        OrdernarTop();
        TituloF();
        onEnter();
    }
    /** Meotodo para ordernar nombres y score de mayor a menor*/
    public void CargarScore(){
        try {
            BufferedReader abrir = new BufferedReader(new FileReader("Lista.txt"));
            while(abrir.readLine() != null){
                cont++;
            }abrir.close();
            abrir = new BufferedReader(new FileReader("Lista.txt"));
            String linea=abrir.readLine();
            auxData = new String[cont];
                while(linea!=null){
                    auxData[a]=linea;
                    a++;
                    linea=abrir.readLine();
                }abrir.close();
                
            nombre = new String[cont]; 
            score = new int[cont];
            
            for (int i = 0; i < cont; i++) {
                auxArchivo=auxData[i].split(";");
                nombre[i]=auxArchivo[0];
                score[i]=Integer.parseInt(auxArchivo[1]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenuTop10State.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuTop10State.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        /** Meotodo para ordernar nombres y score de mayor a menor*/
    public void OrdernarTop(){
        nombreM = new String[cont]; 
        scoreM = new String[cont];
        
            for (int i = 0; i < cont; i++) {
                for (int j = 0; j < cont-1; j++) {
                    if(score[j] < score[j+1]){
                        temp = score[j];
                        temp1 = nombre[j];
                        score[j] = score[j+1];
                        nombre[j] = nombre[j+1];
                        score[j+1] = temp;
                        nombre[j+1] = temp1;
                    }  
                }
            }
            for (int i = 0; i < cont; i++) {
                scoreM[i]=String.valueOf(score[i]);
                nombreM[i]=String.valueOf(nombre[i]);
                System.out.println("Score: "+scoreM[i]);
            }  
    }
    /** Meotodo para ordernar nombres y score de mayor a menor*/
    public void TituloF(){
        if(cont>=10){
          publicacion(cont);
        }else if(cont<10){
          publicacion(cont);
        }

    }
    
    /** meotodo  imprimir nombre y score*/
    public void publicacion(int cont){
        int aux=0;
        if(cont>=10){
            aux=10;
        }else if(cont<10){
            aux=cont;
        }
        for (int i = 0; i < aux; i++) {
            UsuarioNom[i] = new JLabel();
            ScoreNom[i] = new JLabel();
            UsuarioNom[i].setText(nombreM[i]);
            ScoreNom[i].setText(scoreM[i]);
        }

        Titulo.setBounds(Ventana.width/3 - 100, 200, 200, 40);
        Titulo.setFont(fuente); 
        Titulo.setForeground(Color.WHITE);
        Score.setBounds(Ventana.width/3 + 400, 200, 200, 40);
        Score.setFont(fuente);
        Score.setForeground(Color.WHITE);
        for (int i = 0; i < aux; i++) {
            UsuarioNom[i].setBounds(Ventana.width/3 - 100, 250+(i*30), 200, 40);
            UsuarioNom[i].setFont(fuente2);
            UsuarioNom[i].setForeground(new Color(216,129,36));
            ScoreNom[i].setBounds(Ventana.width/3 + 400, 250+(i*30), 200, 40);
            ScoreNom[i].setFont(fuente2);
            ScoreNom[i].setForeground(new Color(216,129,36));
            this.add(UsuarioNom[i]);
            this.add(ScoreNom[i]);
        }
        this.add(Titulo);
        this.add(Score);
    }
    @Override
     /** Metodo que carga las imagenes del fondo, titulo, botones del menu de de top 10, informacion y uso 
    del evento MouseListener para devolver al menu inicial*/
    public void onEnter() {
        volverB = new JLabel();
        tituloB = new JLabel();  
        fonditoConteiner = new JLabel();
        
        try {  
            volver = new ImageIcon(ImageIO.read(new File("src/inicio/volver.png")));
            titulo = new ImageIcon(ImageIO.read(new File("src/inicio/Top.png")));
            fondito = new ImageIcon(ImageIO.read(new File("src/inicio/fondito.png")));
            volver1 = new ImageIcon(ImageIO.read(new File("src/inicio/volver1.png")));
        } catch (IOException ex) {
            Logger.getLogger(MenuSecundaryState.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fonditoConteiner.setIcon(fondito);
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
        volverB.addMouseListener(new MenuTop10State.ActionClick());
        
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
                world.VolverTop10();
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
}
