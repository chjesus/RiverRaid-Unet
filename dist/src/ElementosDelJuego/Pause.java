
package ElementosDelJuego;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**Clase de tipo JPanel que muestra en pantalla los elementos de un menu de pause*/
public class Pause extends JPanel{
    private JButton reanudar;
    private JButton salir;
    private JLabel imgFondo;
    /**constructor por defecto que inicializa y agrega los componenetes necesarios*/
    public Pause(){
        setLayout(null);
        setOpaque(false);
        reanudar = new JButton("Reanudar");
        salir = new JButton("Salir");
        imgFondo = new JLabel(new ImageIcon("src/FondoMenuPause/Menu.png"));
        imgFondo.setBounds(0, 0, 200, 200); 
        reanudar.setBounds(50,45,100,20);
        salir.setBounds(50,120,100,20);

        add(reanudar);
        add(salir);
                add(imgFondo);

    }
    
    /**metodo que retorna el boton reanudar para darle funcionalidad en el gamestate*/
    public JButton getReanudar(){
        return reanudar;
    }
    /**metodo que retorna el boton salir para darle funcionalidad en el gamestate*/
    public JButton getSalir(){
        return salir;
    }
}

