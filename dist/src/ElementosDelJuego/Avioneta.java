
package ElementosDelJuego;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;


/**Clase responsable de cargar el enemigo "Avioneta" dentro del gamestate*/
public class Avioneta {
    
    private Image look;
    private Rectangle ubicacion;
    private final int ancho=75;
    private final int alto=28;
    private int enemigo;
    private int borrar;
    private Boolean ini;
    
    /**contructor por defecto que inicializa algunas variables*/
    public Avioneta(){
        borrar=0;
        ini=false;
        enemigo=0;
        ubicacion = new Rectangle(0,0,ancho, alto);
       
    }
    
    /**metodo que retorna la imagen de una avioneta para pintarla en el gamestate*/
    public Image getLook(){
        
        return look;
    }
    
    /**metodo que retorna la posicion de las avionetas en x*/
    public int getX(){
      return ubicacion.x;  
    }
    
    /**metodo que retorna la posicion de las avionetas en y*/
    public int getY(){
      return ubicacion.y;  
    }
    
    /**metodo que retorna el ancho de las avionetas*/
    public int getAncho(){
      return ubicacion.width;  
    }
    
    /**metodo que retorna el alto de las avionetas*/
    public int getAlto(){
      return ubicacion.height;  
    } 
    
    /**metodo que determina si una avioneta debe salir o no, y si debe salir por la derecha o la izquierda, dependiendo de estas opciones carga la imagen correspondiente*/
    public void generar(){
        
        borrar=0;
        ini = false;
        Random a = new Random();//0 para no generar/1 para generar por la derecha /2 para generar por la izquierda
        //generar dos ramdon uno para saber saber si saldra y otro para saber porque lado  
     
        boolean lado=false,generar=false;
        
        int var = a.nextInt(10);
        //determinando si sale o no
        if( var <= 8) //sale
        {
            generar = true;
        }
        else //no sale
        {
            generar = false;
        }
        
        var = a.nextInt(10);
        //determinando por que lado
        if( var <= 4) //derecha
        {
            lado = true;
        }
        else  //izquierda
        {
            lado = false;
        }
        
        if(generar)
        {
            var=a.nextInt(700)-400;
            if(lado)
            {
                try {
                  look = ImageIO.read(new File("src/Nave/Nave2.png"));
                  } catch (IOException ex) {
                      System.out.println("error la imagen de la nave no se encuentra en la ruta por defecto");
                  }
                enemigo = 1;
                ubicacion.x=1100;
                ubicacion.y=var;
            }
            else
            {
                try {
                look = ImageIO.read(new File("src/Nave/Nave1.png"));
                  } catch (IOException ex) {
                      System.out.println("error la imagen de la nave no se encuentra en la ruta por defecto");
                   }
                enemigo = 2;
                ubicacion.x=-100;
                ubicacion.y=var;
            }
            
        }
        else
         enemigo = 0;
    }
   
    /**dependiendo de si la avioneta salio por la izquierda o por la derecha , este metodo devuelve una variable para determinarlo*/
    public int getEnemigo(){
        return enemigo;
    }
    
    /**metodo que setea la variable enemigo en 0 haciendo que se elimine en el mapa*/
    public void removeEnemigo(){
         enemigo=0;
    }
    
    /**metodo que cambia el sprite de la nave por el de la explocion*/
    public void explotar(){
        try {
           look = ImageIO.read(new File("src/Explosion/Explosion.png"));
        } catch (IOException ex) {
            System.out.println("error la imagen de la explosion no se encuentra en la ruta por defecto");
        }
        ini=true;
    }
   
    /**metodo que retorna una variable de tipo int que al tener cierto valor permite que el sprite de la avioneta cambie por el de la explocion*/
    public int getBorrar(){
        return borrar;
    }
   
    /**metodo que mueve la avioneta por el mapa una vez que es generada*/
    public void mover(int velocidad,int des){
        ubicacion.x+=velocidad;
        ubicacion.y+=Math.abs(des);
        if(ini)
        borrar++;
    }
    
    /**metodo que reinicia la posicion de la avioneta dando la impresion de haaberla eliminado*/
    public void removerPorColision(){
       generar();
    }
    
    /**metodo que retorna un objeto Rectangle para determinar las colisiones*/
    public Rectangle getUbicacion(){
        return this.ubicacion;
    }
}
