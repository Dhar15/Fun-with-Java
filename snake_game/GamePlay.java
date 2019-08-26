import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class GamePlay extends JPanel implements KeyListener, ActionListener {

	private ImageIcon titleImage;
	
	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private ImageIcon leftMouth;
	private ImageIcon rightMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	
	private int snakeLength = 3;
	
	private Timer timer;
	private int delay = 100;
	
	private ImageIcon snakeImage;
	
	private int[] enemyXpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,250,375,400,425,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	
	private int[] enemyYpos = {75,100,125,150,175,200,225,250,275,300,325,250,375,400,425,475,500,525,550,575,600,625};
	
	private ImageIcon enemyImage;
	
	private Random rand = new Random();
	
	private int xpos = rand.nextInt(34);
	private int ypos = rand.nextInt(23);
	
	private int score = 0;
	
	private int moves=0;
	
	public GamePlay() 
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) 
	{
		super.paintComponent(g);
		
		if(moves==0) 
		{
			snakeXlength[2]=50;
			snakeXlength[1]=75;
			snakeXlength[0]=100;
			
			snakeYlength[2]=100;
			snakeYlength[1]=100;
			snakeYlength[0]=100;
		}
		
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		g.setColor(Color.black);
		g.drawRect(25, 75, 850, 575);
		
		//draw score
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Scores:" + score,780,30);
		
		//draw length
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Length:" + snakeLength,780,50);
		
		rightMouth = new ImageIcon("rightmouth.png");
		rightMouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		for(int i=0; i<snakeLength;i++) {
			
			if(i==0 && right) {
				rightMouth = new ImageIcon("rightmouth.png");
				rightMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && left) {
				leftMouth = new ImageIcon("leftmouth.png");
				leftMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && down) {
				downMouth = new ImageIcon("downmouth.png");
				downMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && up) {
				upMouth = new ImageIcon("upmouth.png");
				upMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i!=0) {
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
		}
		
		enemyImage = new ImageIcon("enemy.png");
		
		if((enemyXpos[xpos] == snakeXlength[0] && enemyYpos[ypos] == snakeYlength[0])) {
			score++;
			snakeLength++;
			xpos = rand.nextInt(34);
			ypos = rand.nextInt(23);
		}
		
		enemyImage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);
		
		for(int v=1;v<snakeLength;v++) {
			if(snakeXlength[v] == snakeXlength[0] && snakeYlength[v] == snakeYlength[0]) {
				right=false;
				left=false;
				up=false;
				down=false;
				
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Press SpaceBar To RESTART", 350, 340);
			}
		}
		
		g.dispose();
	}
	
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(right) 
		{	
			for(int r=snakeLength-1 ; r>=0 ; r--) 
			{
				snakeYlength[r+1] = snakeYlength[r];
			}
			for(int r=snakeLength;r>=0;r--) 
			{
				if(r==0) {
					snakeXlength[r] = snakeXlength[r] + 25;
				}
				else {
					snakeXlength[r] = snakeXlength[r-1];
				}
				if(snakeXlength[r] > 850) {
					snakeXlength[r] = 25;
				}
			}
			
			repaint();
		}
		
	
		if(left) 
		{
			for(int r=snakeLength-1 ; r>=0 ; r--) 
			{
				snakeYlength[r+1] = snakeYlength[r];
			}
			for(int r=snakeLength;r>=0;r--) 
			{
				if(r==0) {
					snakeXlength[r] = snakeXlength[r] - 25;
				}
				else {
					snakeXlength[r] = snakeXlength[r-1];
				}
				if(snakeXlength[r] < 25) {
					snakeXlength[r] = 850;
				}
			}
				
			repaint();
		}

		if(up) 
		{
			for(int r=snakeLength-1 ; r>=0 ; r--) 
			{
				snakeXlength[r+1] = snakeXlength[r];
			}
			for(int r=snakeLength;r>=0;r--) 
			{
				if(r==0) {
					snakeYlength[r] = snakeYlength[r] - 25;
				}
				else {
					snakeYlength[r] = snakeYlength[r-1];
				}
				if(snakeXlength[r] < 75) {
					snakeXlength[r] = 625;
				}
			}
			
			repaint();
		}
		
		if(down) 
		{
			for(int r=snakeLength-1 ; r>=0 ; r--) 
			{
				snakeXlength[r+1] = snakeXlength[r];
			}
			for(int r=snakeLength;r>=0;r--) 
			{
				if(r==0) {
					snakeYlength[r] = snakeYlength[r] + 25;
				}
				else {
					snakeYlength[r] = snakeYlength[r-1];
				}
				if(snakeYlength[r] > 625) {
					snakeYlength[r] = 75;
				}
			}
			repaint();
		}
	
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves=0;
			score=0;
			snakeLength=3;
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right = true;
			if(!left) {
				right = true;
			} else {
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			if(!right) {
				left = true;
			} else {
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			if(!down) {
				up = true;
			} else {
				up = false;
				down = true;
			}
			left = false;
			right = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			if(!up) {
				down = true;
			} else {
				down = false;
				up = true;
			}
			left = false;
			right = false;
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	
}
