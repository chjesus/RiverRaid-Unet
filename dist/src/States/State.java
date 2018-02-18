
package States;


import javax.swing.JPanel;


/**clase padre de todas las clases state*/
public abstract class State extends JPanel {
  
    
    public State(){}
    /** metodo que sera usado por las clases hijas para iniciar componentes*/
    public abstract void onEnter(); 
 
    
}