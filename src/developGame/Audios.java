package developGame;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author ADMINISTRATOR
 */
public class Audios {
    public String path; 
    public Clip MainClip; 
    public AudioInputStream audio; 
    public Long TimePlay; 
   
    
    public Audios(String path, boolean st){
        this.path = path; 
        try{
            if (st){
                audio = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile()); 
                MainClip = AudioSystem.getClip(); 
                MainClip.loop(Clip.LOOP_CONTINUOUSLY);
                MainClip.open(audio); 
                System.out.println(path);
            }else{
                audio = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile()); 
                MainClip = AudioSystem.getClip(); 
                MainClip.open(audio); 
            }
        }catch(UnsupportedAudioFileException e){
            System.out.println("loi load audio !!!");
        }catch (IOException ex) {
            Logger.getLogger(Audios.class.getName()).log(Level.SEVERE, null, ex);
        }catch (LineUnavailableException ex) {
            Logger.getLogger(Audios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String getPath(){
        return path; 
    }
    
    public void setPath(String path){
        this.path = path; 
    }
    // bắt đầu chơi nhạc. 
    public void Player() {
        MainClip.start();
    }
    // dừng lại. 
    public void PauseAudio(){
        this.TimePlay = this.MainClip.getMicrosecondPosition();
        MainClip.stop();
    }
    // chơi tiếp.
    public void PlayResume()
    {
        
        try{
            MainClip.close(); 
            resetAudioStream(); 
            MainClip.setMicrosecondPosition(TimePlay); 
            this.Player(); 
        }catch(UnsupportedAudioFileException e){
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Audios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Audios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    // dừng hẳn khuyến cáo không nên dùng bị lỗi chỗ này. 
    public void StopAudio(){
        if (MainClip.isOpen()){
            TimePlay = MainClip.getMicrosecondPosition();
            MainClip.stop();
            MainClip.close();
        }
    }
    
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
                                            LineUnavailableException  
    { 
        audio = AudioSystem.getAudioInputStream( 
        new File(path).getAbsoluteFile()); 
        MainClip.open(audio); 
    } 
    
    public void playAgain(){
        try{
            if (MainClip.isOpen()){
                MainClip.close();
            }
            this.resetAudioStream();
            this.Player();
        }catch(UnsupportedAudioFileException e){
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Audios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Audios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
