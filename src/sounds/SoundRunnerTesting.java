package sounds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SoundRunnerTesting {

	public static BSSound ts;
	
	public SoundRunnerTesting() {
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Sound");
		frame.setSize(300, 300);
		JPanel panel = new JPanel();
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setupPanel(panel);
		frame.setVisible(true);
		String file = "chu.wav";
		ts = new BSSound(file);
		
//		while (ts.isPlaying()) {
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	public static void setupPanel(JPanel panel) {
		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ts.play();
			}
		});
		
		JButton pause = new JButton("Pause");
		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ts.pause();
			}
		});
		
		JButton end = new JButton("End");
		end.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ts.end();
			}
		});
		
		panel.add(play);
		panel.add(pause);
		panel.add(end);
		
		panel.revalidate();
		
	}
}
