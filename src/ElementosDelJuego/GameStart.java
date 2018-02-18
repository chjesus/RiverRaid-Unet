
package ElementosDelJuego;

import States.GameState;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import riverraid.Game;

/**Clase de tipo JFrame que es sobre donde se ejecutada el gamestate, el motivo de esto fue por un problema al momento de captar el focus en el Frame de menu*/
public class GameStart extends JFrame{
 
    private static GameStart instancia;
    
    /**constructor por defecto donde se pone un panel gamestate sobre este frame para empezar el juego*/
    private GameStart()
    {
        super("River Raid");

         setSize(1100,700);
         try{
             Image icon =  new ImageIcon(getClass().getResource("Imagenes/pycharm.png")).getImage();
             setIconImage(icon);
         }
         catch(NullPointerException e){
             System.out.println("El icono de la ventana no se encuentra en su ruta por defecto");
         }
         setDefaultCloseOperation(Game.EXIT_ON_CLOSE);
         GameState e=new GameState();
         getContentPane().add(e,0);
         setResizable(false);
         setLocationRelativeTo(null);
         setVisible(true);
    }
    
    /**metodo estatico para usar solo una instancia de este frame*/
    public static GameStart instance()
    {
        if(instancia == null)
        {
            instancia = new GameStart();
        }
        return instancia;
    }
    
    /**metodo para eliminar este Frame*/
    public static void close_()
    {
        instance().dispose();
        instancia = null;
    }
}
