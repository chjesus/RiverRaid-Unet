
package ElementosDelJuego;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

/**Clase responsable de cargar el enemigo "Barco" dentro del gamestate*/
public class Barco {
    private Image look;
    private Rectangle ubicacion;
    private final int ancho=73;
    private final int alto=28;
    private Point coord;
    private int enemigo;
    private int borrar;
    private Boolean ini;
    private int mover=5;
    private int direccion=0;
    
    /**carga la imagen necesaria y inicializa variable auxiliares y el Rectangle para colision*/
     public Barco(){
        ini = false;
        borrar=0;
        enemigo=0;
        ubicacion = new Rectangle(0,-28,ancho, alto);
        
        try {
           look = ImageIO.read(new File("src/Barco/Barco.png"));
        } catch (IOException ex) {
            System.out.println("error la imagen de la nave no se encuentra en la ruta por defecto");
        }
    }
    
     /**metodo que  retorna la imagen que representa el barco*/
     public Image getLook(){
         return look;
    }
     
     /**metodo que retorna la posicion del barco en x*/
    public int getX(){
      return ubicacion.x;  
    }
    
    /**metodo que setea la posicion del barco en y*/
    public void setY(int y){
        ubicacion.y = y;
    }
    
    /**metodo que retorna la posicion del barco en y*/
    public int getY(){
      return ubicacion.y;  
    }
    
    /**metodo que retorna el ancho del barco*/
    public int getAncho(){
      return ubicacion.width;  
    }
    
    /**metodo que retorna el alto del barco*/
    public int getAlto(){
      return ubicacion.height;  
    } 
    
    /**metodo que setea la posicion de los barcos en y para simular que se mueven con el mapa*/
    public void desplazar(int d){
        this.ubicacion.y+=d;
        if(ini)
        borrar++;
    }
    
    /**metodo que cambia la imagen del barco por la de la explocion*/
    public void explotar(){
        try {
           look = ImageIO.read(new File("src/Explosion/Explosion.png"));
        } catch (IOException ex) {
            System.out.println("error la imagen de la nave no se encuentra en la ruta por defecto");
        }
        ini=true;
    }
    
    /**metodo que retorna una variable de tipo int que al tener cierto valor permite que el sprite de el barco cambie por el de la explocion*/
    public int getBorrar(){
        return borrar;
    }
    
    /**metodo que genera un barco dentro de los rangos correctos es decir fuera de las areas de tierra, es la unica parte del proyecto que presenta fallas pues los barcos aveces si salen en la tierra*/
    public void generar(ArrayList<Rectangle> izquierdo, ArrayList<Rectangle> derecho,ArrayList<Rectangle> medio){
        
        Random a = new Random();
        ubicacion.x=a.nextInt(900)+100;
        if(ubicacion.x<550){
            direccion=0;
            for(Rectangle rec :izquierdo){
            
            if(ubicacion.intersects(rec) )
            { 
             
             ubicacion.x = rec.width;
            }
            break;
            }
            for(Rectangle rec :medio){
              
            if(ubicacion.intersects(rec))
            {  
                 direccion=1; 
                 int aux = ubicacion.x-rec.x;
                 ubicacion.x = ubicacion.x-ubicacion.width-aux; 
                  break;
            }
            
            }
        
            
        }
        else
        {   
            direccion=3; 
            for(Rectangle rec :derecho){
                
                if(ubicacion.intersects(rec) )
                 { 
                 int aux = ubicacion.x-rec.x;
                 ubicacion.x = ubicacion.x-ubicacion.width-aux; 
                 break;
                 }
            }
            for(Rectangle rec :medio){
                
            if(ubicacion.intersects(rec))
            {    direccion=4;
                 ubicacion.x = rec.width;
                 break;
            }
            
            }
            

        }
    } 
    
    /**metodo que se encarga de mover el barco hacia a izquierda o la derecha (dependiendo de donde se encuentre) cuando se encuentra a una distancia del jugador*/
    public void atacar(ArrayList<Rectangle> izquierdo, ArrayList<Rectangle> derecho,ArrayList<Rectangle> medio){ // si es 0 corre a la derecha si es 1 correhac
        
        if(direccion==0 || direccion==4){
            ubicacion.x+=mover;
            for(Rectangle rec :derecho){
                
            if(ubicacion.intersects(rec) )
            { 
             mover=0;
             break;
            }
            
            }
            for(Rectangle rec :medio){
                
            if(ubicacion.intersects(rec))
            {  
                
                 mover=0; 
                  break;
            }
            
            }
        
            
        }
        else
        {
            ubicacion.x-=mover; 
            
            for(Rectangle rec :izquierdo){
                
                if(ubicacion.intersects(rec) )
                 {  
                 mover=0;
                 break;
                 }
            }
            for(Rectangle rec :medio){
                
            if(ubicacion.intersects(rec))
            {  
                 mover=0;
                 break;
            }
            
            }
            

        }
        
    }
    
    /**metodo que retorna un objeto Rectangle para determinar las colisiones*/
    public Rectangle getUbicacion(){
        return this.ubicacion;
    }
}
