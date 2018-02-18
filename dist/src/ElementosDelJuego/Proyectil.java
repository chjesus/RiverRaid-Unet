
package ElementosDelJuego;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Proyectil {

    private Image look;
    private Rectangle ubicacion;
    private final int ancho=53;
    private final int alto=128;
    private Boolean disparar;
    
    /**metodo que inicializa las variables para el objeto Rectangle y carga a imagen de proyectil*/
    public Proyectil(){
        disparar=false;
        ubicacion= new Rectangle(0,0,ancho,alto);
        try {
            look = ImageIO.read(new File("src/Disparo/disparo.png"));
        } catch (IOException ex) {
            System.out.println("error la imagen del proyectil no se encuentra en la ruta por defecto");
        }
    }
    
    /**metodo que retorna la apariencia del proyectil para pintarlo en pantalla*/
    public Image getlook(){
        
        return look;     
    }
    
    public void setDisparo(Boolean i){
     this.disparar= i;   
    }
    
    
    public Boolean getDisparo(){
        
        return disparar;
    }
    /**metodo que setea la posicion en x del proyectil*/
    public void setDireccionX(int i){
        this.ubicacion.x=i;
    }
    /**metodo que retorna la posicion en x del proyectil*/
    public  int getDireccionX(){
        return this.ubicacion.x;
    }
    
    /**metodo que setea la posicion en y del proyectil*/
     public void setProgresionY(int i){
        this.ubicacion.y=i;
    }
     /**metodo que retorna la posicion actual de proyectil en y*/
     public int getProgresionY(){
        return this.ubicacion.y;
    }
     
     /**mueve y retorna la posicion del proyectil en y*/
     public int mover()
     {   this.ubicacion.y-=20;
         return this.ubicacion.y;
     }
     
     /**retorna el objeto Rectangle para las colisiones*/
     public Rectangle getUbicacion(){
         return ubicacion;
     }
    
}

