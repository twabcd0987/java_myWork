package tw.StudentManager;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyGui {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(400,400);
		frame.setLayout(new BorderLayout());
		frame.add(TimePanel.getInstance(),BorderLayout.EAST);
	}
}
class TimePanel extends JPanel{
	private static JPanel timePanel=new TimePanel();
	JTextField jf2 ;
	public static JPanel getInstance(){
		if(timePanel==null)
			timePanel=new TimePanel();
		return timePanel;
	}
	private TimePanel(){
		this.setLayout(new BorderLayout());
		JTextField jf1 = new JTextField();
		jf2 = new JTextField(" yyyy-MM-dd HH:mm:ss ");
		jf1.setBorder(BorderFactory.createLoweredBevelBorder());
		jf2.setBorder(BorderFactory.createLoweredBevelBorder());
		jf2.setHorizontalAlignment(JTextField.RIGHT);
		jf1.setEditable(false);
		jf2.setEditable(false);
		this.add(jf1);
		this.add(jf2);
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setVisible(true);
		Timer t = new Timer();
		t.schedule(new MyTask(), 1000,1000);
	}
	
	class MyTask extends TimerTask{
		@Override
		public void run() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s = sdf.format(new Date());
			jf2.setText(s);			
		}
	}
}