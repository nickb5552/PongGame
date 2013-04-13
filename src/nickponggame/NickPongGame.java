package nickponggame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class NickPongGame extends JComponent implements MouseMotionListener
{
    JFrame pongField;
    int ballXpos = 150;
    int ballYpos = 150;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    Ellipse2D.Double ball;
    Rectangle2D.Double paddle;
    int ballXspeed = 2;
    int ballYspeed = 2;
    int score = 0;
    
    public static void main(String[] args)
    {
        new NickPongGame().getGoing();
    }
    
    private void getGoing()
    {
        pongField = new JFrame("Pong");
        pongField.setVisible(true);
        pongField.setSize(width, height);
        pongField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pongField.add(this);
        ball = new Ellipse2D.Double(ballXpos, ballYpos, 100, 100);
        pongField.addMouseMotionListener(this);
        paddle = new Rectangle2D.Double(25, height/2, 25, 150);
    }
    
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.yellow);
        g2.fill(ball);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(4f));
        g2.draw(ball);
        repaint();
        ball.x = ball.x + ballXspeed;
        ball.y = ball.y + ballYspeed;
        g2.setColor(Color.BLUE);
        g2.fill(paddle);
        if (ball.y > height - 225)
        {
            ballYspeed = -ballYspeed;
        }
        if (ball.y < 0)
        {
            ballYspeed = -ballYspeed;
        }
        if (ball.x > width - 80)
        {
            ballXspeed = -ballXspeed;
        }
        if (ball.intersects(paddle))
        {
            ballXspeed = -ballXspeed;
            score++;
        }
        g2.setFont(new Font("Bank Gothic", Font.BOLD, 99));
        g2.setColor(Color.red);
        g2.drawString(score + "", 1800, 100);
    }
    
    @Override
    public void mouseDragged(MouseEvent me)
    {
    }
    
    @Override
    public void mouseMoved(MouseEvent me)
    {
        paddle.y = me.getY();
    }
}
