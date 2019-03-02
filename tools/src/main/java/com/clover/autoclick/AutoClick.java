package com.clover.autoclick;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AutoClick extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JTextField time = null;

	private JButton go = null;

	private JButton stop = null;
	
	Util ut;  //  @jve:decl-index=0:

	private JLabel jLabel2 = null;

	/**
	 * This method initializes time	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTime() {
		if (time == null) {
			time = new JTextField();
			time.setBounds(new Rectangle(124, 34, 76, 29));
		}
		return time;
	}

	/**
	 * This method initializes go	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGo() {
		if (go == null) {
			go = new JButton();
			go.setBounds(new Rectangle(45, 120, 65, 31));
			go.setText("start");
			go.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!(ut.isAlive())){
						System.out.println("go()"); 
						ut = new Util();
						ut.go(true, Integer.parseInt(time.getText()));
						ut.start();
					}
					// TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return go;
	}

	/**
	 * This method initializes stop	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStop() {
		if (stop == null) {
			stop = new JButton();
			stop.setBounds(new Rectangle(135, 120, 67, 31));
			stop.setText("stop");
			stop.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(ut.isAlive()){
						System.out.println("stop()"); 
						ut.stop();
					}
					// TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return stop;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				AutoClick thisClass = new AutoClick();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public AutoClick() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		ut = new Util();
		this.setSize(300, 200);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(215, 33, 71, 28));
			jLabel2.setText("second");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(45, 31, 76, 30));
			jLabel.setText("delay");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getTime(), null);
			jContentPane.add(getGo(), null);
			jContentPane.add(getStop(), null);
			jContentPane.add(jLabel2, null);
			
		}
		return jContentPane;
	}

}
