
import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class MidiMusicPlayer3{
    static JFrame j = new JFrame("My First Music Video");
    static MyDrawPanel ml;
    public static void main(String[] args) {
        MidiMusicPlayer3 midi3 = new MidiMusicPlayer3();
        midi3.go();

    }

    public void setUpGUI(){
        ml = new MyDrawPanel();
        j.setContentPane(ml);
        j.setBounds(30,30,300,300);
        j.setVisible(true);

    }

    public void go() {
        setUpGUI();

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int[] eventsIwant = {127};
             sequencer.addControllerEventListener(ml,eventsIwant);
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track trk = seq.createTrack();

            int r = 0;
            for (int i = 5; i < 64; i += 4) {
                r = (int) ((Math.random() * 50) +1);
                trk.add(makeEvent(144, 1,r, 100, i));//I cant listen to that
                trk.add(makeEvent(176, 1, 127, 0, i));
                trk.add(makeEvent(128, 1, r, 100, i + 2));//Cant listen to that either
              }

            sequencer.setSequence(seq);
            sequencer.start();
            sequencer.setTempoInBPM(120);


        } catch (Exception e) {
            System.out.println("hoy");

        }
    }

    public  MidiEvent makeEvent(int comd, int channele, int evetNo, int velo, int tick) {
        MidiEvent midiEvent = null;
        try {
            ShortMessage shrt = new ShortMessage();
            shrt.setMessage(comd, channele, evetNo, velo);
            midiEvent = new MidiEvent(shrt, tick);


        } catch (Exception e) {
            System.out.println("bummer");

        }
        return midiEvent;

    }

      class MyDrawPanel extends JPanel implements ControllerEventListener {

        boolean msg = false;

          public void controlChange(ShortMessage event) {
               msg = true;
              repaint();
           }
        //if we have an event the boolean will become true and a shape will be paint


        //create a color, rectangle (x,y, height, width)
        public void paintComponent(Graphics g) {
            if (msg) {
                Graphics2D g2 = (Graphics2D) g;
                int r = (int) (Math.random() * 250);
                int gr = (int) (Math.random() * 250);
                int b = (int) (Math.random() * 250);
                g.setColor(new Color(r,gr,b));
                int ht = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);
                int x = (int) ((Math.random() * 40) + 10);
                int y = (int) ((Math.random() * 40) + 10);
                g.fillRect(x,y,ht, width);
                msg = false;
            } // close if
        } // close method
      } // close inner class
} // close class


