
package com.mycompany.pong_game;

/**
 *
 * @author Rizwan
 */
import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{
    int id;
    int yVelocity;
    int speed=10;

    Paddle(int x,int y,int Paddle_Width,int Paddle_Height,int id){
        super(x+20,y,Paddle_Width,Paddle_Height);
        this.id=id;

    }
    public void keyPressed(KeyEvent e)
    {
        switch(id)
        {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(-speed);
                }
                else if(e.getKeyCode()==KeyEvent.VK_S)
                {
                    setYDirection(speed);
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP)
                {
                    setYDirection(-speed);
                }
                else if(e.getKeyCode()==KeyEvent.VK_DOWN)
                {
                    setYDirection(speed);
                }
                break;
        }
    }
    //KEY RELEASE:to stop moving paddles
    public void keyReleased(KeyEvent e)
    {
          switch(id)
          {
              case 1:
                  if(e.getKeyCode()==KeyEvent.VK_W)
                  {
                      setYDirection(0);
                  }
                  if(e.getKeyCode()==KeyEvent.VK_S)
                  {
                      setYDirection(0);
                  }
                  break;
              case 2:
                  if(e.getKeyCode()==KeyEvent.VK_UP)
                  {
                      setYDirection(0);
                  }
                  if(e.getKeyCode()==KeyEvent.VK_DOWN)
                  {
                      setYDirection(0);
                  }
                  break;
          }
    }
    private void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }
    public void move()//Paddles movement
    {
        y = y + yVelocity;
    }
    public void draw(Graphics g)
    {
        if(id==1){g.setColor(Color.red);}
        else if(id==2){g.setColor(Color.blue);}
        g.fillRect(x , y, width, height);
    }
}
