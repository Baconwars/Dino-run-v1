package dino;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//max screen size g.fillRect(0, 0, 1540, 760);

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		JButton button1 = new JButton("Start");
		JPanel buttonpanel = new JPanel();
		CellPanel cellpanel = new CellPanel();
		
		window.setTitle("Dino Run");
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		buttonpanel.add(button1);
		button1.addActionListener(e -> {
			cellpanel.died = false;
			//button1.setText("Stop");
			if (cellpanel.died==false)
		    window.requestFocusInWindow();
		});
		
		
		window.add(buttonpanel, BorderLayout.SOUTH);
		window.add(cellpanel, BorderLayout.CENTER);
		
		window.setVisible(true);
				
		//Space key input
				window.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_SPACE && cellpanel.jump<=10) {
								cellpanel.jumped = true;
								
								
							
						}
					}
					
					
				});
				
				window.setFocusable(true);


		
		
	}
}

class CellPanel extends JPanel {
	
	int jump = 0;
	int a1 = 2;
	int a2 = -2;
	
	int[][] xpoint = {{330, 348, 312}, {368, 392, 344}, {630, 652, 608}, {780, 807, 753}, {817, 834, 800}, {980, 1007, 953}};
	
	int[][] ypoint = {{470, 500, 500}, {450, 500, 500}, {470, 500, 500}, {450, 500, 500}, {480, 500, 500}, {465, 500, 500}};
	
	int[] xdino = {559, 549, 479};
	int[] ydino = {504-jump, 564-jump, 564-jump};
	
	int[] xdino2 = {506, 481, 446};
	int[] ydino2 = {544-jump, 564-jump, 564-jump};
	
	boolean jumped = false;
		
	
	//cs.length = 8
	int[] cs = new int[8];
		
	int sy = -80;
	int sx = 350;
	
	int[] score = {0, 0, 0, 0, 0, 0};
	int scorespace = -23;
			
	int cactuspos = 0;
	
	int range = 10;
	
	int dinolowest = 576;
	
	int cactushighest = 524;
	
	double speed = 1;
	
	boolean died = true;	
	
	int newscore = 0;
	
	int highestscore = 0;
	
	int hs[] = {0, 0, 0, 0, 0, 0};
	
	int MinCactusDistance = 43+7;
	
	
	Timer restarttimer = new Timer((int)    (1000/(speed)), e-> {
		
			died = false;
			for (int j=0;j<cs.length;j++) {
				cs[j]=-400;
				
				}
			
			for (int j=0;j<score.length;j++)
				score[j]=0;
			});	
	
	
		Timer gametimer = new Timer((int)    (10/(speed)), e-> {
			if (died == false) {


			//Back Ground
			for (int i=0;i<xpoint.length;i++) {
				for (int j=0;j<xpoint[i].length;j++) {
					xpoint[i][j]--;
					if (xpoint[i][j]<250) for (int k=0;k<3;k++) xpoint[i][k]+=990;
				}
				
			}
			
			
			//cactus
			
			for (int i=0;i<cs.length;i++) {
				if (cs[i]<=-400) {
					cs[i]+=990;
					cs[i]+=(int)(Math.random()*940*3);

				}
				cs[i]-=5;
			}
			
			//minimum cactus distance
			for (int i=0;i<cs.length;i++)
				for (int j=0;j<cs.length;j++) {
					if (cs[i]-cs[j]<MinCactusDistance && cs[i]-cs[j]>0 && cs[i]>433) {
						cs[i]+=1;
					}
				}
			
			
			
			for (int i=0;i<cs.length;i++)
				for (int j=0;j<cs.length;j++) {
					if (cs[i]-cs[j]>MinCactusDistance+2 && cs[i]-cs[j]<MinCactusDistance*4 && cs[i]-cs[j]>0 && cs[i]>633&& cs[i]<1033) {
						cs[i]+=4;
					}
				}

			
			//Death
			
			dinolowest -=jump;
			
			if (dinolowest>cactushighest)
			
			for (int i=0;i<cs.length;i++) {
				cactuspos = 664+cs[i];
				if (Math.abs(530-cactuspos)<range) {
					//if death has occured
					died = true;
					
					
					for (int j=0;j<score.length;j++) {
						
						newscore +=score[j]*Math.pow(10, j);
						
						}
					
					if (highestscore<newscore) {
						highestscore = newscore;
						
						
						for (int k=0;k<Integer.toString(highestscore).length() && k<hs.length;k++) {
							hs[k] = Integer.parseInt(Integer.toString(highestscore).charAt(Integer.toString(highestscore).length()-k-1)+"");
						}
					}
					newscore = 0;
					
			
					restarttimer.start();
					restarttimer.setRepeats(false);
				}			
			}
			dinolowest +=jump;
			
			
			//jump
			
			if (jumped == true) {
				if (jump<80) jump+=2;
				
				if (jump<60) jump+=2;
				
				if (jump<40) jump+=2;
				
				if (jump<20) jump+=3;
			}
				
			if (jumped==false) {
				if (jump<=100 && jump>0) jump-=2;
				
				if (jump<70 && jump>0) jump-=1;
				
				if (jump<50 && jump>0) jump-=1;
				
				if (jump<20 && jump>0) jump-=1;
			}
			
				if (jump>=80) jumped = false;
				
			
			
			repaint(); 
			}
			});
		
		

	
		
		
		
		
	Timer stimer = new Timer((int)    (200/(speed)), e-> {
		if (died == false) {
		
		//score
		
		score[0]++;
		if (score[0]>10) score[0]=0; 
			
		for (int i=1;i<score.length;i++) {
		if (score[i-1]==10) {
			score[i-1]=0;
			score[i]++;
		}
		}		
		
		//Animation of Dino
		
		a1*=-1;
		a2*=-1;
		ydino2[2] +=a1*2;
				
		
			repaint(); 
		}
			});	
		
		
		
	
	CellPanel() {
		gametimer.start();
		stimer.start();
		setBackground(Color.BLACK);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);

		setBackground(Color.BLACK);
		g.setColor(Color.white);
		g.fillRect(300, 100, 940, 560);
		g.setColor(Color.gray);
		g.fillRect(300, 500, 940, 2);
		
		
		for (int i=0;i<cs.length;i++) {
			if  (cs[i]<590) {
		//cactus left
		g.fillOval(650+cs[i], 530, 12, 12);
		g.fillOval(650+cs[i], 542, 12, 12);
		g.fillRect(650+cs[i], 536, 12, 12);
		g.fillOval(654+cs[i], 542, 12, 12);
		g.fillOval(658+cs[i], 542, 12, 12);
		g.fillOval(662+cs[i], 542, 12, 12);
		//middle
		g.fillOval(664+cs[i], 524, 15, 12);
		g.fillOval(664+cs[i], 568, 15, 12);
		g.fillRect(664+cs[i], 530, 15, 44);
		//right
		g.fillOval(681+cs[i], 535, 12, 12);
		g.fillOval(681+cs[i], 547, 12, 12);
		g.fillRect(681+cs[i], 541, 12, 12);
		g.fillOval(677+cs[i], 547, 12, 12);
		g.fillOval(673+cs[i], 547, 12, 12);
		g.fillOval(669+cs[i], 547, 12, 12);
			}
		}
		
		
		//BG
		
		for (int i=0;i<xpoint.length;i++) {
			g.fillPolygon(xpoint[i], ypoint[i], 3);
		}
		g.setColor(Color.black);
		g.fillRect(240, 100, 60, 560);
		
		g.setColor(Color.black);
		g.fillRect(1240, 100, 60, 560);
		
		//Dino head
		
		g.setColor(new Color (100, 100, 100));
		g.fillRect(540, 490+a1-jump, 40, 15);
		g.fillRect(540, 505+a1-jump, 32, 6);
		g.fillRect(540, 511+a1-jump, 40, 9);
		
		//Dino eye
		g.setColor(Color.white);
		g.fillRect(552, 494+a1-jump, 8, 6);
		
		//Dino body
		int[] ybody = {
				ydino[0] - jump,
				ydino[1] - jump,
				ydino[2] - jump,
		};
		g.setColor(new Color (100, 100, 100));
		g.fillPolygon(xdino, ybody, 3);
		
		//Dino tail
		int[] ytail = {
				ydino2[0] - jump,
				ydino2[1] - jump,
				ydino2[2] - jump,
		};
		g.fillPolygon(xdino2, ytail, 3);
		
		//Dino hand
		g.fillRect(549, 534+a2-jump, 12, 7);
		g.fillRect(557, 541+a2-jump, 4, 3);

		//Dino right leg
		g.fillRect(530, 554+a2*2-jump, 8, 22);
		g.fillRect(538, 572+a2*2-jump, 3, 4);
		
		//Dino left leg
		g.fillRect(510, 554+a1*2-jump, 8, 22);
		g.fillRect(518, 572+a1*2-jump, 3, 4);
		
		
		//score
		g.setColor(new Color (50, 50, 50));
		
		
		for (int i=0;i<score.length;i++) {
			g.setColor(new Color (50, 50, 50));
			sx += scorespace*i;
		//0
		if (score[i]==0) {
		g.fillRect(840+sx, 200+sy, 10, 4);
		g.fillRect(850+sx, 204+sy, 4, 10);
		g.fillRect(836+sx, 204+sy, 4, 10);
		
		g.fillRect(850+sx, 218+sy, 4, 10);
		g.fillRect(840+sx, 228+sy, 10, 4);
		g.fillRect(836+sx, 218+sy, 4, 10);
		}
		//1
		if (score[i]==1) {
		g.fillRect(850+sx, 204+sy, 4, 10);
		g.fillRect(850+sx, 218+sy, 4, 10);
		}
		
		//2
		if (score[i]==2) {
		g.fillRect(840+sx, 200+sy, 10, 4);
		g.fillRect(850+sx, 204+sy, 4, 10);
		g.fillRect(840+sx, 214+sy, 10, 4);
		
		g.fillRect(840+sx, 228+sy, 10, 4);
		g.fillRect(836+sx, 218+sy, 4, 10);
		}
		
		//3
		if (score[i]==3) {
		g.fillRect(840+sx, 200+sy, 10, 4);
		g.fillRect(850+sx, 204+sy, 4, 10);
		g.fillRect(840+sx, 214+sy, 10, 4);
		
		g.fillRect(850+sx, 218+sy, 4, 10);
		g.fillRect(840+sx, 228+sy, 10, 4);
		}
		
		//4
		if (score[i]==4) {
		g.fillRect(850+sx, 204+sy, 4, 10);
		g.fillRect(840+sx, 214+sy, 10, 4);
		g.fillRect(836+sx, 204+sy, 4, 10);
		
		g.fillRect(850+sx, 218+sy, 4, 10);
		}
		//5
		if (score[i]==5) {
		g.fillRect(840+sx, 200+sy, 10, 4);
		g.fillRect(840+sx, 214+sy, 10, 4);
		g.fillRect(836+sx, 204+sy, 4, 10);
		
		g.fillRect(850+sx, 218+sy, 4, 10);
		g.fillRect(840+sx, 228+sy, 10, 4);
		}	
		//6
		if (score[i]==6) {
		g.fillRect(840+sx, 200+sy, 10, 4);
		g.fillRect(840+sx, 214+sy, 10, 4);
		g.fillRect(836+sx, 204+sy, 4, 10);
		
		g.fillRect(850+sx, 218+sy, 4, 10);
		g.fillRect(840+sx, 228+sy, 10, 4);
		g.fillRect(836+sx, 218+sy, 4, 10);
		}	
		//7
		if (score[i]==7) {
		g.fillRect(840+sx, 200+sy, 10, 4);
		g.fillRect(850+sx, 204+sy, 4, 10);
		
		g.fillRect(850+sx, 218+sy, 4, 10);
		}
		//8
		if (score[i]==8) {
		g.fillRect(840+sx, 200+sy, 10, 4);
		g.fillRect(850+sx, 204+sy, 4, 10);
		g.fillRect(840+sx, 214+sy, 10, 4);
		g.fillRect(836+sx, 204+sy, 4, 10);
		
		g.fillRect(850+sx, 218+sy, 4, 10);
		g.fillRect(840+sx, 228+sy, 10, 4);
		g.fillRect(836+sx, 218+sy, 4, 10);
		}
		//9
		if (score[i]==9) {
		g.fillRect(840+sx, 200+sy, 10, 4);
		g.fillRect(850+sx, 204+sy, 4, 10);
		g.fillRect(840+sx, 214+sy, 10, 4);
		g.fillRect(836+sx, 204+sy, 4, 10);
		
		g.fillRect(850+sx, 218+sy, 4, 10);
		g.fillRect(840+sx, 228+sy, 10, 4);
		}
		sx -= scorespace*i;
			
		}
		
		//highestscore
				g.setColor(new Color (50, 50, 50));
				
				
				for (int i=0;i<hs.length;i++) {
					g.setColor(new Color (50, 50, 50));
					sx += scorespace*i;
				//0
				if (hs[i]==0) {
				g.fillRect(840+sx, 240+sy, 10, 4);
				g.fillRect(850+sx, 244+sy, 4, 10);
				g.fillRect(836+sx, 244+sy, 4, 10);
				
				g.fillRect(850+sx, 258+sy, 4, 10);
				g.fillRect(840+sx, 268+sy, 10, 4);
				g.fillRect(836+sx, 258+sy, 4, 10);
				}
				//1
				if (hs[i]==1) {
				g.fillRect(850+sx, 244+sy, 4, 10);
				g.fillRect(850+sx, 258+sy, 4, 10);
				}
				
				//2
				if (hs[i]==2) {
				g.fillRect(840+sx, 240+sy, 10, 4);
				g.fillRect(850+sx, 244+sy, 4, 10);
				g.fillRect(840+sx, 254+sy, 10, 4);
				
				g.fillRect(840+sx, 268+sy, 10, 4);
				g.fillRect(836+sx, 258+sy, 4, 10);
				}
				
				//3
				if (hs[i]==3) {
				g.fillRect(840+sx, 240+sy, 10, 4);
				g.fillRect(850+sx, 244+sy, 4, 10);
				g.fillRect(840+sx, 254+sy, 10, 4);
				
				g.fillRect(850+sx, 258+sy, 4, 10);
				g.fillRect(840+sx, 268+sy, 10, 4);
				}
				
				//4
				if (hs[i]==4) {
				g.fillRect(850+sx, 244+sy, 4, 10);
				g.fillRect(840+sx, 254+sy, 10, 4);
				g.fillRect(836+sx, 244+sy, 4, 10);
				
				g.fillRect(850+sx, 258+sy, 4, 10);
				}
				//5
				if (hs[i]==5) {
				g.fillRect(840+sx, 240+sy, 10, 4);
				g.fillRect(840+sx, 254+sy, 10, 4);
				g.fillRect(836+sx, 244+sy, 4, 10);
				
				g.fillRect(850+sx, 258+sy, 4, 10);
				g.fillRect(840+sx, 268+sy, 10, 4);
				}	
				//6
				if (hs[i]==6) {
				g.fillRect(840+sx, 240+sy, 10, 4);
				g.fillRect(840+sx, 254+sy, 10, 4);
				g.fillRect(836+sx, 244+sy, 4, 10);
				
				g.fillRect(850+sx, 258+sy, 4, 10);
				g.fillRect(840+sx, 268+sy, 10, 4);
				g.fillRect(836+sx, 258+sy, 4, 10);
				}	
				//7
				if (hs[i]==7) {
				g.fillRect(840+sx, 240+sy, 10, 4);
				g.fillRect(850+sx, 244+sy, 4, 10);
				
				g.fillRect(850+sx, 258+sy, 4, 10);
				}
				//8
				if (hs[i]==8) {
				g.fillRect(840+sx, 240+sy, 10, 4);
				g.fillRect(850+sx, 244+sy, 4, 10);
				g.fillRect(840+sx, 254+sy, 10, 4);
				g.fillRect(836+sx, 244+sy, 4, 10);
				
				g.fillRect(850+sx, 258+sy, 4, 10);
				g.fillRect(840+sx, 268+sy, 10, 4);
				g.fillRect(836+sx, 258+sy, 4, 10);
				}
				//9
				if (hs[i]==9) {
				g.fillRect(840+sx, 240+sy, 10, 4);
				g.fillRect(850+sx, 244+sy, 4, 10);
				g.fillRect(840+sx, 254+sy, 10, 4);
				g.fillRect(836+sx, 244+sy, 4, 10);
				
				g.fillRect(850+sx, 258+sy, 4, 10);
				g.fillRect(840+sx, 268+sy, 10, 4);
				}
				sx -= scorespace*i;
				}

					//S
					g.fillRect(840+200, 240+sy, 10, 4);
					g.fillRect(840+200, 254+sy, 10, 4);
					g.fillRect(836+200, 244+sy, 4, 10);
					
					g.fillRect(850+200, 258+sy, 4, 10);
					g.fillRect(840+200, 268+sy, 10, 4);
					
					//h
					g.fillRect(850+175, 244+sy, 4, 10);
					g.fillRect(840+175, 254+sy, 10, 4);
					g.fillRect(836+175, 244+sy, 4, 10);
					
					g.fillRect(850+175, 258+sy, 4, 10);
					g.fillRect(836+175, 258+sy, 4, 10);
					
				
				/*g.setColor(new Color (250, 0, 0));
				g.fillRect(264, 228, 10, 10);*/
	
	}

}
