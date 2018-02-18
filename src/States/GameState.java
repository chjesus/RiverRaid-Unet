
package States;

import ElementosDelJuego.FinDelJuego;
import ElementosDelJuego.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.Timer;
import riverraid.Game;
import riverraid.Ventana;

/**Clase donde ocurre toda la interaccion del juego, desde los timer para los enemigos como las colisiones y sonido*/
public final class GameState extends State{

    //private int FPS=0; opcional para setear los fps
    private ArrayList<Puente>puentes;
    private Puente puente2 , puente1;
    private Nave nave;
    private Boolean movIz;
    private Boolean movDer;
    private Avioneta avioneta; 
    private Barco barco;
    private ArrayList<Barco> enemigo; 
    private Timer desplazamiento;
    private Timer genE1;
    private Timer genE2;
    private Timer genTank;
    private KeyListener teclas; 
    private int aceleracion = 2;
    private BarraDeControl barra;
    private Tanque tanque;
    private ArrayList <Tanque> combustible; 
    private Proyectil misil;
    private ArrayList<Proyectil> misiles; 
    private Boolean gasolina=false;
    private Pause pause;
    private int tiempo;
    private int cont;
    private JLabel contadorTiempo;
    private Mapa mapa;
    private FinDelJuego end;
    private int reinicio=0;
    private BufferedWriter guardaScore;
    private BufferedReader solicitarRespaldo;
    private Sonido sonidos;
    
    /** contructor por defecto que inicializa las variables tipicas de un JPanel asi como variables auxiliares*/
    public GameState() {
        this.cont = 0;
        this.tiempo=0;
        this.onEnter();
        this.setBackground(Color.BLUE); 
        this.setLayout(null);
        this.movDer= false;
        this.movIz=false;
        this.addKeyListener(teclas);
        setFocusable(true);
        setVisible(true);
       
        
    }
   
    /** metodo sobreescrito que inicializa todos los componentes de juego*/
    @Override
    public void onEnter() {
            sonidos = new Sonido();
            sonidos.mOtor();
            contadorTiempo = new JLabel("0");
            contadorTiempo.setBounds(990, 0, 60,60);
            contadorTiempo.setVisible(true);
            Font cont = new Font("Agency FB",Font.BOLD,30);
            contadorTiempo.setFont(cont);
            contadorTiempo.setForeground(Color.white);
            puente1 = new Puente(433,-6097);
            puente2 = new Puente(430,-15136);
            puentes = new ArrayList<>();
            puentes.add(puente2);
            puentes.add(puente1);
            
            mapa = new Mapa();
            nave= new Nave();
            avioneta = new Avioneta();
            enemigo = new ArrayList<>();
            
            barra = new BarraDeControl();
            barra.setBounds(0,600,1100,100);
            
            end = new FinDelJuego();
            end.getContinuar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GameStart.instance().close_();
                    try {
                        Game.instance().NuevoJuego();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            pause = new Pause();
            pause.setBounds(Ventana.width/2 -90, 200, 300, 400);
            pause.setVisible(false);
            pause.getReanudar().addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    desplazamiento.restart();
                    genE1.restart();
                    genE2.restart();
                    pause.setVisible(false);
                }
            });
            
            pause.getSalir().addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sonidos.detenermOtor();
                    GameStart.instance().close_();
                    try {
                        Game.instance().NuevoJuego();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            combustible = new ArrayList<>();
            misiles = new ArrayList<>();
            
            misil = new Proyectil();
            
            add(pause);
            add(barra);
            add(contadorTiempo);
            comenzar();
            GenerarEnemigo1();
            GenerarEnemigo2();
            GenerarTanke();
            
            teclas  = new KeyListener() {
                
                @Override
                public void keyTyped(KeyEvent e) {
                    
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                    
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        movIz=true;
                        
                        repaint();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        movDer=true;
                        
                        repaint();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        aceleracion=2;
                        repaint();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        aceleracion= 15;
                        repaint();
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    aceleracion = 5;
                    movIz = false;
                    movDer = false;
                    
                    if(e.getKeyCode() == KeyEvent.VK_SPACE)
                    {
                        misil = new Proyectil();
                        misil.setDisparo(true);
                        misil.setDireccionX(nave.getPosx()-3);
                        misil.setProgresionY(nave.getPosy());
                       sonidos.disparo();
                    }
                    
                    if(e.getKeyCode() == KeyEvent.VK_P){
                        desplazamiento.stop();
                        genE1.stop();
                        genE2.stop();
                        pause.setVisible(true);
                    }
                }
            };  
    }
    
    /** metodo que inicializa el timer que controla todo el desarrollo del juego menos la aparicion de enemigos*/
    public void comenzar(){
        
        desplazamiento = new Timer(50, new ActionListener() {
  
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(nave.getVida()==0 || tiempo==90){
                   sonidos.detenermOtor();
                    genE1.stop();
                    genE2.stop();
                    desplazamiento.stop();
                    try {
                         solicitarRespaldo = new BufferedReader(new FileReader("respaldo.txt"));
                         guardaScore= new BufferedWriter(new FileWriter("Lista.txt",true));
                         guardaScore.write(solicitarRespaldo.readLine()+";"+String.valueOf(barra.getPuntos()));
                         guardaScore.newLine();
                         guardaScore.close();
                         solicitarRespaldo.close();
                 } catch (IOException ex) {
                        System.out.println("error no se pudo cargar el archivo Lista.txt");
                 }
                    add(end);
                }
                
                cont++;
                if(cont>=20)
                {
                  tiempo++;
                  contadorTiempo.setText(String.valueOf(tiempo));
                  cont =0;
                }
                if(misil.getDisparo())
                {
                     misiles.add(misil);
                     misil.setDisparo(false);
                }
                
                mapa.aumentarPosY(aceleracion);
                
                //desplaza el puente
                for(Puente p : puentes)
                {
                    p.AumentarPosicion(aceleracion);// acomodar esta parte
                }

                if(movIz){
                    nave.setPosx(-15); 
                }
                else if(movDer){
                    nave.setPosx(15);
                }
                
                for(Barco enemigo1 : enemigo) {
                    enemigo1.desplazar(aceleracion);
                }
                 for(Barco enemigo1 : enemigo) {
                     if((540-enemigo1.getY())<=100)
                     enemigo1.atacar(mapa.getPosIzquierda(),mapa.getPosDerecha(),mapa.getPosMedia());
                }
                for(Tanque tanque1 : combustible) {
                    tanque1.desplazar(aceleracion);
                }
             
               if(gasolina)
               {
                   barra.aumentaCombustible();
               }
               else
               {
                   barra.disminuyeCombustible();
               }
                if(avioneta.getEnemigo()==1)
                  avioneta.mover(-20,aceleracion);   
                else if(avioneta.getEnemigo()==2)
                 avioneta.mover(20,aceleracion);
                repaint();
            }
        });
        desplazamiento.start();
    }
    
    /** metodo que genera el enemigo "Avioneta" de manera aleatoria*/
    public void GenerarEnemigo1(){
        genE1 = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                avioneta.generar();
               
            }
        });
        genE1.start();
    }
    
    /** metodo que genera el enemigo "Barco" de manera aleatoria*/
    public void GenerarEnemigo2(){
        
        genE2 = new Timer(900, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                barco = new Barco();
                barco.generar(mapa.getPosIzquierda(),mapa.getPosDerecha(),mapa.getPosMedia());
                enemigo.add(barco);
                
                
            }
        });
        genE2.start();
    }
    
    /** metodo que genera los tanques de combustible de manera aletoria*/
    public void GenerarTanke(){
        
        genTank = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               tanque = new Tanque();
                tanque.generar(mapa.getPosIzquierda(),mapa.getPosDerecha(),mapa.getPosMedia());       
                combustible.add(tanque);
            }
        });
        genTank.start();
    }
    
    /**metodo sobreescrito que permite repintar el JPanel cada cierto tiempo dando la persepcion de que las cosas se mueven, valida todas las colicciones al ser llamado por el metodo Comenzar*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
           
        
            //dibuja el mapa
            g.drawImage(mapa.getMapa(),0,mapa.getPosY(),this);
        
            
            //mueve el puente
            for(Puente p : puentes)
             {
                   g.drawImage(p.getLook(),p.getPosX(),p.getPosY(),this);
             }
            
     
        //Dibuja los tanques del combustible
        for(Tanque comb : combustible) {
            g.drawImage(comb.getImagen(), comb.getPosx(), comb.getPosy(), this);
        }
        
        //Dibuja los barcos
        for(int i =0;i<enemigo.size();i++) {
            g.drawImage(enemigo.get(i).getLook(), enemigo.get(i).getX(),enemigo.get(i).getY(), this);
            
            //
           if(enemigo.get(i).getY()>750)
                enemigo.remove(i);
        }
        
        
        
        //Dibuja los proyectiles
        for(int i =0;i<misiles.size() ;i++) {
            g.drawImage(misiles.get(i).getlook(),misiles.get(i).getDireccionX(),misiles.get(i).mover(),this);
            if(misiles.get(i).getProgresionY()<-20)
                misiles.remove(i);
        }
        
        //*Dibuja las Avionetas*/
        if(avioneta.getEnemigo()!=0)
        {
            g.drawImage(avioneta.getLook(),avioneta.getX(),avioneta.getY(), this);
        }
        
        //Dibuja la nave
        g.drawImage(nave.getImagen(),nave.getPosx(),nave.getPosy(), this); 
       

        //para validar el impacto con avionetas
        if(nave.getUbicacion().intersects(avioneta.getUbicacion()))
        {
                barra.setCombustible();
                nave.perderVida();
                barra.getVida().setVisible(false);
                if(reinicio==0)
                {
                    avioneta.removerPorColision();
                    enemigo.clear();
                    mapa.actualizarPos(-29400);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-6097);
                    puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)
                {
                    avioneta.removerPorColision();
                    enemigo.clear();
                    mapa.actualizarPos(-22940);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-15136);
                }
        }
        
        //valida cuando el combustible se agota
        if(barra.getCombustible()==0){
            barra.setCombustible();
            nave.perderVida();
            barra.getVida().setVisible(false);
                if(reinicio==0)
                {
                    avioneta.removeEnemigo();
                    enemigo.clear();
                    mapa.actualizarPos(-29400);
                    
                    nave.reiniciar();
                    combustible.clear();
                   
                    puentes.get(0).resetear(-6097);
                    puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)
                {
                    avioneta.removeEnemigo();
                    enemigo.clear();
                    mapa.actualizarPos(-22940);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-15136);
                }
        }
        //para validar el impacto con barcos
       for(Barco enemigo1 : enemigo) {
            if(nave.getUbicacion().intersects(enemigo1.getUbicacion()))
            {
                barra.setCombustible();
                nave.perderVida();
                barra.getVida().setVisible(false);
                if(reinicio==0)//significa que no he destruido ningun puente
                {
                    avioneta.removeEnemigo();
                    enemigo.clear();
                    mapa.actualizarPos(-29400);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-6097);
                    puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)//ya he destruido un puente
                {
                    avioneta.removeEnemigo();
                    enemigo.clear();
                    mapa.actualizarPos(-22940);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-15136);
                }
                 break;
            }
        }
       for(Puente puente1 : puentes) {
            if(nave.getUbicacion().intersects(puente1.getUbicacion()))
            {
                barra.setCombustible();
                nave.perderVida();
                barra.getVida().setVisible(false);
                if(reinicio==0)//significa que no he destruido ningun puente
                {
                    avioneta.removeEnemigo();
                    enemigo.clear();
                    mapa.actualizarPos(-29400);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-6097);
                    puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)//ya he destruido un puente
                {
                    avioneta.removeEnemigo();
                    enemigo.clear();
                    mapa.actualizarPos(-22940);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-15136);
                }
                 break;
            }
        }
       
       //para validar el impacto con el combustible
       for(Tanque tanque1 : combustible) {
            if(nave.getUbicacion().intersects(tanque1.getUbicacion()))
            {
                
                gasolina=true;
                break;
            }
            else
                gasolina=false;
            
        }
       
        //para validar el impacto con el terreno --desde aqui
       for (int i =0;i<mapa.getPosDerecha().size();i++){
                
             if (nave.getUbicacion().intersects(mapa.getPosDerecha().get(i))){
                 
                barra.setCombustible();
                nave.perderVida();
                barra.getVida().setVisible(false);
                if(reinicio==0)//significa que no he destruido ningun puente
                {
                    avioneta.removeEnemigo();
                    enemigo.clear();
                    mapa.actualizarPos(-29400);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-6097);
                    puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)//ya he destruido un puente
                {
                    avioneta.removeEnemigo();
                    enemigo.clear();
                    mapa.actualizarPos(-22940);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-15136);
                }
                 break;
                }
            }
       for (int i =0;i<mapa.getPosIzquierda().size();i++){
                
             if (nave.getUbicacion().intersects(mapa.getPosIzquierda().get(i))){
                 barra.setCombustible();
                nave.perderVida();
                barra.getVida().setVisible(false);
                if(reinicio==0)//significa que no he destruido ningun puente
                {
                    avioneta.removeEnemigo();
                    enemigo.clear();
                    mapa.actualizarPos(-29400);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-6097);
                    puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)//ya he destruido un puente
                {
                    avioneta.removeEnemigo();
                    enemigo.clear();
                    mapa.actualizarPos(-22940);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-15136);
                }
                 break;
                }
            }
       for (int i =0;i<mapa.getPosMedia().size();i++){
                
             if (nave.getUbicacion().intersects(mapa.getPosMedia().get(i))){
                    barra.setCombustible();
                nave.perderVida();
                barra.getVida().setVisible(false);
                if(reinicio==0)//significa que no he destruido ningun puente
                {
                    avioneta.removeEnemigo();
                    enemigo.clear();
                    mapa.actualizarPos(-29400);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-6097);
                    puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)//ya he destruido un puente
                {
                    avioneta.removeEnemigo();
                    enemigo.clear();
                    mapa.actualizarPos(-22940);
                    nave.reiniciar();
                    combustible.clear();
                    puentes.get(0).resetear(-15136);
                }
                 break;
                }
            }
       //--hasta aqui
       
       //para desaparecer los barcos
       for(int i=0;i<misiles.size();i++) {
           for(int j=0;j<enemigo.size();j++){
                if(misiles.get(i).getUbicacion().intersects(enemigo.get(j).getUbicacion()))
                {
                     sonidos.explo_barco();
                    barra.setPuntaje(100);
                    misiles.remove(i);
                    enemigo.get(j).explotar();
                    break;
                }
           }
        }
       // para borrar los enemigos ,el tiempo
       for(int j=0;j<enemigo.size();j++)
       {
           if(enemigo.get(j).getBorrar()>3)
               enemigo.remove(j);
       }
       
       // para eliminar los puentes
       for(int i=0;i<misiles.size();i++) {
           for(int j=0;j<puentes.size();j++){
                if(misiles.get(i).getUbicacion().intersects(puentes.get(j).getUbicacion()))
                {
                    sonidos.explo_barco();
                    barra.setPuntaje(200);//los puentes dan 200 pts
                    misiles.clear();
                    puentes.get(j).explotar();
                    enemigo.clear();
                    combustible.clear();
                    reinicio++;
                    break;
                }
           }}
       for(int j=0;j<puentes.size();j++)
       {
           if(puentes.get(j).getBorrar()>3)
           {
               puentes.remove(j);
           }
       }
               
       
       //desaparecer los tanques
       for(int i=0;i<misiles.size();i++) {
           for(int j=0;j<combustible.size();j++){
                if(misiles.get(i).getUbicacion().intersects(combustible.get(j).getUbicacion()))
                {
                    barra.setPuntaje(-100);
                    misiles.remove(i);
                    combustible.get(j).explotar();
                    break;
                }
           }}
       for(int j=0;j<combustible.size();j++)
       {
           if(combustible.get(j).getBorrar()>3)
               combustible.remove(j);
       }
       
       //para desaparecer las avionetas
       for(int i=0;i<misiles.size();i++) {
             if(misiles.get(i).getUbicacion().intersects(avioneta.getUbicacion()))
                {
                     sonidos.explo_barco();
                    barra.setPuntaje(150);
                    misiles.remove(i);
                    avioneta.explotar();
                }
           }
       
           if(avioneta.getBorrar()>3)
               avioneta.removeEnemigo();
       
       
       
    }
  
}