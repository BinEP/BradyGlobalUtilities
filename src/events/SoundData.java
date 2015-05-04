package events;

import sounds.BSSound;

public class SoundData implements DataEvent {

	private BSSound sound;
	
	public SoundData(BSSound sound) {
		this.sound = sound;
	}
	
	public SoundData(String fileName) {
		sound = new BSSound(fileName);
	}

	@Override
	public Object getData() {
		return getSound();
	}

	@Override
	public String getMessage() {
		return null;
	}
	
	public BSSound getSound() {
		return sound;
	}

	@Override
	public BSSound triggerEvent() {
		sound.play();
		return sound;
	}

}
