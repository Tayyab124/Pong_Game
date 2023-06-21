
package com.mycompany.pong_game;

/**
 *
 * @author Rizwan
 */
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class GamePanel extends Panel implements Runnable {
    int Width =1000;
    int Height=555;
    Dimension screen=new Dimension(Width,Height);

    int Paddle_Width=15;
    int Paddle_Height=100;
    int ball_diameter=20;

    Image image;
    Graphics graphics;
    Paddle paddle1,paddle2;
    Ball ball;
    Score score;
    Thread GameThread;


    GamePanel(){
       newPaddle();
       newBall();
       
       score = new Score(Width,Height);
       setPreferredSize(screen);
       GameThread=new Thread(this);
       GameThread.start();
       addKeyListener(new AL());
       setFocusable(true);
    }

    private void newBall() {
        Random random=new Random();
        ball=new Ball(Width/2-(ball_diameter/2),random.nextInt(Height-ball_diameter),ball_diameter,ball_diameter);
    }

    private void newPaddle() {
        paddle1=new Paddle(0,(Height-Paddle_Height)/2,Paddle_Width,Paddle_Height,1);
        paddle2=new Paddle(Width-Paddle_Width-40,(Height-Paddle_Height)/2,Paddle_Width,Paddle_Height,2);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        image=createImage(getWidth(),getHeight());
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

    }
   
    @Override
    public void update(Graphics g) {
    paint(g);
    }

    private void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        g.setColor(Color.white);
        g.fill3DRect(1000/2-2, 0, 4, 555, true);
    }
    
        public void run() {
            long lastTime=System.nanoTime();
            double amountOfTicks=60.0;
            double ns=1000000000/amountOfTicks;
            double delta=0;
            while(true){
                long now =System.nanoTime();
                delta += (now - lastTime)/ns;
                lastTime = now;
                if(delta>=1){
                    move();
                    checkCollision();
                    repaint();
                    delta--;
                }
            }
        }
        
     private void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    private void checkCollision() {
        if(ball.y<=0)// If ball hits ceiling,rebound
        {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y>=Height-ball_diameter)// If ball hits bottom, rebound
        {
            ball.setYDirection(-ball.yVelocity);
        }
        // Ball rebounds paddle 1
        if(ball.intersects(paddle1))
        {
            ball.xVelocity=-ball.xVelocity;
            ball.xVelocity++;
            if(ball.yVelocity>0)
            {
                ball.yVelocity++;
            }else {
                ball.yVelocity--;
            }
            ball.setYDirection(ball.yVelocity);
            ball.setXDirection(ball.xVelocity);
        }
           //Ball rebounds paddle 2
        if(ball.intersects(paddle2))
        {
            ball.xVelocity=-ball.xVelocity;
            ball.xVelocity--;
            if(ball.yVelocity>0)
            {
                ball.yVelocity++;
            }else {
                ball.yVelocity--;
            }
            ball.setYDirection(ball.yVelocity);
            ball.setXDirection(ball.xVelocity);

        }
        
        //To block paddles from going off screen
        if(paddle1.y<=0)
        {
            paddle1.y=0;
        }
        if(paddle1.y>=Height-Paddle_Height)
        {
            paddle1.y=Height-Paddle_Height;
        }
        if(paddle2.y<=0)
        {
            paddle2.y=0;
        }
        if(paddle2.y>=Height-Paddle_Height)
        {
            paddle2.y=Height-Paddle_Height;
        }
        
        
        if(ball.x>=Width-ball_diameter)//If ball crosses Right border
        {
            newBall();
            newPaddle();
            score.player1++;
        }
        if(ball.x<=0)// If ball crosses Left Border
           
        {
            newPaddle();
            newBall();
            score.player2++;
        }
    }

   

    public class AL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {

            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {

            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }

}
