package com.tw.burnbrain;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * @author Internet
 * @date 2012-4-22
 * @function 背景音乐播放
 * @version 1.0
 */
public class MusicPlayer implements Runnable{

	public void run() {
		AudioInputStream m_audioInputStream = null;
		SourceDataLine m_line = null;
		AudioFormat audioFormat = null;
		try {
			File file = new File("D:\\KuGou\\01.苏打绿 - 片刻永恒.mp3");
			m_audioInputStream = AudioSystem.getAudioInputStream(file);
			audioFormat = m_audioInputStream.getFormat();

			if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
				AudioFormat newFormat = new AudioFormat(
						AudioFormat.Encoding.PCM_SIGNED,
						audioFormat.getSampleRate(), 16,
						audioFormat.getChannels(),
						audioFormat.getChannels() * 2,
						audioFormat.getSampleRate(), false);
			//	System.out.println("Converting audio format to " + newFormat);
				AudioInputStream newStream = AudioSystem.getAudioInputStream(
						newFormat, m_audioInputStream);
				audioFormat = newFormat;
				m_audioInputStream = newStream;
			}

			DataLine.Info info = new DataLine.Info(SourceDataLine.class,
					audioFormat);
			m_line = (SourceDataLine) AudioSystem.getLine(info);
			m_line.open(audioFormat, m_line.getBufferSize());
			m_line.start();
			int bufferSize = (int) audioFormat.getSampleRate()
					* audioFormat.getFrameSize();
			byte[] buffer = new byte[bufferSize];
			int bytesRead = 0;
			while (bytesRead >= 0) {
				bytesRead = m_audioInputStream.read(buffer, 0, buffer.length);
				if (bytesRead >= 0) {
					m_line.write(buffer, 0, bytesRead);
				}
			}
			m_line.drain();
			m_line.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String [] args){
		new Thread(new MusicPlayer()).start();
	}
}