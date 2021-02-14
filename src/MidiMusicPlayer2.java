import javax.sound.midi.*;
//I need to implement it for phase 2 to work properly
public class MidiMusicPlayer2 implements ControllerEventListener{
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
    public void go() {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            //Creating the controllerEventListner that requieres an array of MIDI controller numbers
            int[] eventsIwant = {127};//I want only event number 127
            sequencer.addControllerEventListener(this, eventsIwant);

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track trk = seq.createTrack();
//1.Fill the Track with Midi Events - Phase One
            for (int i = 5; i < 64; i += 4) {
                trk.add(makeEvent(144, 1, i, 100, i));//I cant listen to that
//2.fire an event with each noteOn (the same thick number)   (176 says the event type is ControllerEvent)
                trk.add(makeEvent(176, 1, 127, 0, i));
                trk.add(makeEvent(128, 1, i, 100, i + 2));//Cant listen to that either
            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();

        } catch (Exception e) {

        }
    }
    @Override
    public void controlChange(ShortMessage event) {
        System.out.println("la");
    }


    public static void main(String[] args) {
        MidiMusicPlayer2 mini = new MidiMusicPlayer2();
        mini.go();





    }


}




