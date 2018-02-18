
package ElementosDelJuego;


import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**Clase que contiene todas las varibles para controlar la nave que el usuario usara*/
public class Nave {
    
    private ArrayList<Image>apariencia;
    private Rectangle ubicacion;
    private final int ancho=50;
    private final int alto=50;
    private int combustible;
    private int vida = 3;
    
    /**constructor por defecto que se encarga de cargar la imagen de la nave, e inicializa el objeto de tipo Rectangle de las colisiones*/
    public Nave() {
        this.combustible = 5000;
        ubicacion=new Rectangle(550,540,ancho,alto);
        apariencia = new ArrayList<>();
        try {
            apariencia.add(0,ImageIO.read(new File("src/NaveUser/NaveUser.png")));
        } catch (IOException ex) {
            System.out.println("error la imagen de la nave no se encuentra en la ruta por defecto");
        }
           
    }
    
    /**metodo que retorna la posicion en x del jugador*/
    public int getPosx() {
        return ubicacion.x;
    }
    
    /**metodo que retorna la posicion en y del jugador*/
    public int getPosy() {
        return ubicacion.y;
    }
    
    /**metodo que retorna el combustible del jugador*/
    public int getCombustible(){
        return combustible;
    }
    
    /**metodo que setea la posicion en x del jugador*/
    public void setPosx(int posx) {
        ubicacion.x += posx;
    }
    
    /**metodo que reiniciar la ubicacion de la nave*/
    public void reiniciar(){
        ubicacion.x=510;
    }
    
    /**metodo que retorna la imagen de la nave que controlara el jugador*/
    public Image getImagen() {
        return apariencia.get(0);
    }
    
    /**metodo que cambia el sprite de la nave cuando el jugador gira*/
    public void setImagen(int index,Image imagen) {
        this.apariencia.set(index, imagen);
    }
    
    /**metodo que retorna el objeto Rectangle para las colisiones*/
    public Rectangle getUbicacion(){
        
        return this.ubicacion;
    }
    
    /**metodo que disminuye la vida de jugador en 1 cada vez que es llamado*/
    public void perderVida(){
        vida--;
    }
    
    /**metodo que retorna la vida del jugador actual*/
    public int getVida(){
        return vida;
    }
    
    
    
    }
