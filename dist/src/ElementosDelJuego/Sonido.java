

package ElementosDelJuego;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonido {
    private Clip sonidoMenu;
    private Clip sonidoJuego;
    private Clip sonidoDisparo;
    private Clip sonidoNave;
    private Clip Explo_barco;
    private Clip Explo_avioneta;
    private Clip sonidoFondoJuego;
    private Clip mouseMenu;
    private Clip motor;
    
    /** Constructor donde inicializa los sonidos*/
    public Sonido(){
        try {
            motor= AudioSystem.getClip();
            Explo_barco = AudioSystem.getClip();
            Explo_barco.open(AudioSystem.getAudioInputStream(new File("src/Sonido/explo-barco.wav")));
            Explo_avioneta = AudioSystem.getClip();
            Explo_avioneta.open(AudioSystem.getAudioInputStream(new File("src/Sonido/explo-avioneta.wav")));
            sonidoMenu = AudioSystem.getClip();
            sonidoDisparo = AudioSystem.getClip();
            sonidoDisparo.open(AudioSystem.getAudioInputStream(new File("src/Sonido/flaunch.wav")));
            mouseMenu = AudioSystem.getClip();
            mouseMenu.open(AudioSystem.getAudioInputStream(new File("src/Sonido/rollover1.wav")));
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            System.out.println("error cargando el sonido");
        }
       
    }
    /** Meotod donde inicia el sonido de disparo*/
    public void disparo()
    {
        sonidoDisparo.setMicrosecondPosition(800);
        
        sonidoDisparo.start();
    }
    /** sonido de la explosion del barco*/
     public void explo_barco()
    {
        Explo_barco.setMicrosecondPosition(800);
        Explo_barco.start();
    }
     /** sonido de explosion de avioneta*/
      public void explo_avioneta()
    {
        Explo_avioneta.setMicrosecondPosition(800);
        Explo_avioneta.start();
    }
      /** sonido del fondo menu*/
    public void fondoMenu()
    {
        try {
            sonidoMenu.open(AudioSystem.getAudioInputStream(new File("src/Sonido/littletown.mid")));
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        }
        sonidoMenu.loop(Clip.LOOP_CONTINUOUSLY);
    }
    /**sonido del motor nave*/
    public void mOtor()
    {
        try {
            motor.open(AudioSystem.getAudioInputStream(new File("src/Sonido/plane.wav")));
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        }
         motor.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void detenermOtor()
    {
       
         motor.close();
    }
    /** detiene el sonido del fondo*/
    public void pararSonidoFondo()
    {
        sonidoMenu.close();
    }
    /** inicia sonido del boton*/
    public void Boton()
    {
        mouseMenu.setMicrosecondPosition(1000);
        mouseMenu.start();
    }
  
}
