//Done

import javax.sound.midi.*;

public class MiniMiniMusicApp {

        // I need a Sequencer (a player) , a sequence (a CD) and a Track (that hold the instructions , MIDI type)
        public void play() {
            try {
                //1.Create a sequencer (
                Sequencer player = MidiSystem.getSequencer();
                player.open(); // so I can use it
                //create new sequence - a CD
                Sequence seq = new Sequence(Sequence.PPQ, 4);
                //Create a track - a Track
                Track track = seq.createTrack();
                // fill it with 2 midi events

               //the first - create the sound
                ShortMessage a = new ShortMessage();// the obj to contain the MIDI instructions
                a.setMessage(144, 1, 44, 100);// the midi instructions. the argms are:
                MidiEvent noteOn = new MidiEvent(a, 1); //Instruction for part of the song. what to do (a) and when (1st beat)
                track.add(noteOn);// add to the Track.

                //the second - create the echoing. and finish the note
                ShortMessage b = new ShortMessage();
                b.setMessage(128, 1, 44, 100);//128 NOTE OFF  - stop playing
                MidiEvent noteOff = new MidiEvent(b, 5);//a Midi Event takes the WHAT part (Message obj) and add a timing - WHEN
                track.add(noteOff);

                player.setSequence(seq); // I am giving the Player the CD that I want it to play
                player.start(); //pushing PLAY
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } // close play


    public static void main(String[] args) {

        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        mini.play();

    } // close main
    } // close class

