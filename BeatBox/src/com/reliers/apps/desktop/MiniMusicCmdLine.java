package com.reliers.apps.desktop;

import javax.sound.midi.*;


public class MiniMusicCmdLine {
	// this is the first one
	public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException {
		MiniMusicCmdLine mini = new MiniMusicCmdLine();
		if (args.length < 2) {
			System.out.println("Don’t forget the instrument and note args");
		} else {
			int instrument = Integer.parseInt(args[0]);
			int note = Integer.parseInt(args[1]);
			mini.play(instrument, note);
		}
	} // close main
	public void play(int instrument, int note) throws MidiUnavailableException, InvalidMidiDataException {
		
		//get the player
		Sequencer player = MidiSystem.getSequencer();
		player.open();
		//get the sequence and create a track out of that.
		Sequence seq = new Sequence(Sequence.PPQ, 4);
		Track track = seq.createTrack();
		
		//create midi events and add them to track
		
		//change the instrument
		MidiEvent event = null;
		ShortMessage first = new ShortMessage();
		first.setMessage(192, 1, instrument, 0);
		MidiEvent changeInstrument = new MidiEvent(first, 1);
		track.add(changeInstrument);
		
		//note on event
		ShortMessage a = new ShortMessage();
		a.setMessage(144, 1, note, 100);
		MidiEvent noteOn = new MidiEvent(a, 1);
		track.add(noteOn);
		
		//note off event
		ShortMessage b = new ShortMessage();
		b.setMessage(128, 1, note, 100);
		MidiEvent noteOff = new MidiEvent(b, 16);
		track.add(noteOff);
		
		//set the sequence.
		player.setSequence(seq);
		player.start();
	
	}
}

