import javax.sound.midi.*;

public class MidiMusicPlayer1 implements ControllerEventListener {


 public static MidiEvent makeEvent(int comd, int channele, int evetNo, int velo, int tick) {
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
 



    public static void main(String[] args) {
     try{
          Sequencer sequencer = MidiSystem.getSequencer()   ;
          sequencer.open();


          Sequence seq = new Sequence(Sequence.PPQ, 4);
          Track trk = seq.createTrack();
//1.Fill the Track with Midi Events - Phase One
          for (int i = 5; i<64; i+=4){
              trk.add(makeEvent(144, 1, i, 100, i));


              trk.add(makeEvent(128, 1, i, 100, i+2));
          }

          sequencer.setSequence(seq);
          sequencer.setTempoInBPM(220);
          sequencer.start();

     } catch (Exception e){

     }
     
     

    }

    @Override
    public void controlChange(ShortMessage event) {

    }
}
