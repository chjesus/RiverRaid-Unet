package ElementosDelJuego;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import riverraid.Ventana;


/**clase de tipo JPanel que es la que contiene los elementos que se muestran en pantalla cuando el jugador pierde las tres vidas*/
public class FinDelJuego extends JPanel{
    
    private JButton continuar;
    JLabel img;
    ImageIcon continuarButton;
    /**construcor por defecto que inicializa algunos componentes y los agrega*/
    public FinDelJuego() {
        setLayout(null);
        setOpaque(false);
        try {
            continuarButton = new ImageIcon(ImageIO.read(new File("src/FondoMenuPause/Continuar.png")));
        } catch (IOException ex) {
            Logger.getLogger(FinDelJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
        img = new JLabel(new ImageIcon("src/FondoMenuPause/Menu1.png"));
        
        img.setBounds(0,0,450,200);
        continuar = new JButton();
        setBounds(Ventana.width / 2 - img.getWidth() / 2, 250,450, 190);
        continuar.setBounds(Ventana.width / 4 - ((img.getWidth() / 6) + 25),120,150,50);
        continuar.setIcon(continuarButton);
        continuar.setOpaque(false);
        add(continuar);
        add(img);
    }
    
    /**metodo que retorna el boton de continuar para darle funcionalidad en el gamestate*/
    public JButton getContinuar(){
        return continuar;
    }
}
