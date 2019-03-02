package com.clover.autoclick;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class Util extends Thread{
	Robot rb;
	boolean flag;
	int time;
	
	public Util(){
		try {
			rb = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag = false;
	}
	@Override
	public void run(){
		while(flag == true){
			try {
				TimeUnit.SECONDS.sleep(time);

	//			rb.mouseMove(10,10);
				System.out.println(System.currentTimeMillis() + " clicked!");
				rb.mousePress(InputEvent.BUTTON1_MASK);
				rb.mouseRelease(InputEvent.BUTTON1_MASK);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void go(boolean flag, int time){
		this.flag = flag;
		this.time = time;
	}
}
