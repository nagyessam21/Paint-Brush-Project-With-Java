
package paint_brush;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author engna
 */
//Author : Nagy Essam .

public class Panel extends JPanel implements ActionListener ,MouseListener , MouseMotionListener{
    Color c=Color.BLACK;
    int x1,y1,x2,y2,x,y,width,height;
    JToggleButton Red,Green,Blue,Black,Rectangle,Line,Oval,FreeHand,Eraser;
    JCheckBox Dotted,Fill,Default;
    Rectangle rect;
    Oval ov;
    Line ln;
    ImageSh img;
    ShapeParent shp,remove;
    FreeHand fh;
    Eraser er;
    String sh,mode="Reset";
    Vector<ShapeParent> Drawings;
    BufferedImage image2;
    public Panel()
    {
        this.setBackground(Color.WHITE);
        Drawings = new Vector<>();
        
        //Red Button TO set Color Red 
        Red=new JToggleButton("Red");
        Red.setBackground(Color.red);
        Red.addActionListener(this);
        this.add(Red);
        
        //Blue Button TO set Color Blue 
        Blue=new JToggleButton("Blue");
        Blue.setBackground(Color.blue);
        Blue.addActionListener(this);
        this.add(Blue);
        
        //Green Button TO set Color Green 
        Green=new JToggleButton("Green");
        Green.setBackground(Color.green);
        Green.addActionListener(this);
        this.add(Green);
        
        //Black Button TO set Color Black 
        Black=new JToggleButton("Black");
        Black.setBackground(Color.white);
        Black.addActionListener(this);
        this.add(Black);
        
        
        
        //Rectangle Button TO set shape Rectangle 
        Rectangle=new JToggleButton("Rectangle");
        Rectangle.setBackground(Color.white);
        Rectangle.addActionListener(this);
        this.add(Rectangle);
        
        
        //Line Button TO set shape Line 
        Line=new JToggleButton("Line");
        Line.setBackground(Color.white);
        Line.addActionListener(this);
        this.add(Line);
        
        
        //Oval Button TO set shape Oval 
        Oval=new JToggleButton("Oval");
        Oval.setBackground(Color.white);
        Oval.addActionListener(this);
        this.add(Oval);
        
       
        //FreeHand Button TO set shape FreeHand 
        FreeHand=new JToggleButton("FreeHand");
        FreeHand.setBackground(Color.white);
        FreeHand.addActionListener(this);
        this.add(FreeHand);
        
        //Eraser Button TO Let User Erase Some Actions 
        Eraser = new JToggleButton("Eraser");
        Eraser.setBackground(Color.white);
        Eraser.addActionListener(this);
        add(Eraser);
        
        //Fill Button TO set Mode Fill 
        Fill=new JCheckBox("Fill");
        Fill.addActionListener(this);
        add(Fill);
        
        //Dotted Button TO set Mode Dotted 
        Dotted=new JCheckBox("Dotted");
        Dotted.addActionListener(this);
        add(Dotted);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        //Reset Button to set the draw mode to default ( Solid lines )
        JButton Reset = new JButton("Reset");
        Reset.addActionListener(e -> {
            mode = "Reset";
            System.out.println(mode);
            Dotted.setSelected(false);
            Fill.setSelected(false);
        });
        add(Reset);
        
        // Clear Button to clear all the drawings in the panel
        JButton clearButton =new JButton("CLEAR ALL");
	clearButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Drawings.clear();
                 repaint();
        }});
	add(clearButton);
        
        //undo button to delete the last drawing that the user do 
        JButton undoButton =new JButton("Undo");
	undoButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                for(int i = Drawings.size()-1;i>=0;i--){
                    remove=Drawings.get(i);
                    Drawings.remove(i);
                    break;
                }
            repaint(); 
	}});
        add(undoButton);
        
        //Redo Button to redo one shape that user delete it 
        JButton RedoButton =new JButton("Redo");
	RedoButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Drawings.add(remove);
            repaint();
	}});
        add(RedoButton);
               
        //Open Button to let user open photo in the drawing area 
        JButton OpenButton =new JButton("Open");
	OpenButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Image");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
                fileChooser.setFileFilter(filter);
                int userSelection = fileChooser.showOpenDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        image2 = ImageIO.read(selectedFile);
                        img=new ImageSh(60,80,getWidth(),getHeight(),"Reset",c,image2);
                        Drawings.add(img);
                        System.out.println("Image loaded successfully.");
                        } catch (IOException ex) {
                            System.out.println("Error loading image: " + ex.getMessage());
                          }
                    }
                repaint();
        }});
        add(OpenButton);
        
    }
    
    //actions for the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (Red.isSelected())
        {
            Green.setSelected(false);
            Blue.setSelected(false);
            c=Color.RED;
        }
        if (Green.isSelected())
        {
            Red.setSelected(false);
            Blue.setSelected(false);
            c=Color.GREEN;
        }
        if (Blue.isSelected())
        {
            Green.setSelected(false);
            Red.setSelected(false);
            c=Color.BLUE;
        }
        if (Black.isSelected())
        {
            Green.setSelected(false);
            Red.setSelected(false);
            Blue.setSelected(false);
            c=Color.BLACK;
        }
        if (Rectangle.isSelected())
        {
            sh="Rectangle";
            Line.setSelected(false);
            Oval.setSelected(false);
        }
        if (Line.isSelected())
        {
            sh="Line";
            Rectangle.setSelected(false);
            Oval.setSelected(false);
        }
        if (Oval.isSelected())
        {
            sh="Oval";
            Rectangle.setSelected(false);
            Line.setSelected(false);
        }
        if (Eraser.isSelected())
        {
            sh="Eraser";
            Rectangle.setSelected(false);
            Line.setSelected(false);
            Oval.setSelected(false);
            Fill.setSelected(false);
            Dotted.setSelected(false);
        }
        if (Fill.isSelected())
        {
            mode="Fill";
            Dotted.setSelected(false);
        }
        if (Dotted.isSelected())
        {
            mode="Dotted";
            Fill.setSelected(false);
        }
        if (FreeHand.isSelected())
        {
            sh="FreeHand";
            Rectangle.setSelected(false);
            Oval.setSelected(false);
            Line.setSelected(false);
            Eraser.setSelected(false);
        }
        if (Eraser.isSelected())
        {
            sh="Eraser";
            Rectangle.setSelected(false);
            Oval.setSelected(false);
            Line.setSelected(false);
            FreeHand.setSelected(false);
        }
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    //action once the mouse pressed
    @Override
    public void mousePressed(MouseEvent e) {
        x1=e.getX();
        y1=e.getY();        
    }

    //action when mouse released
    @Override
    public void mouseReleased(MouseEvent e) {
        x2=e.getX();
        y2=e.getY();
            switch(sh)
        {
            case "Rectangle":
              width=Math.abs(x2-x1);
              height=Math.abs(y2-y1);
              x=Math.min(x1, x2);
              y=Math.min(y1, y2);
              rect=new Rectangle(x,y,width,height,mode,c);
              Drawings.add(rect);
              break;
            case "Oval":
              width=Math.abs(x2-x1);
              height=Math.abs(y2-y1);
              x=Math.min(x1, x2);
              y=Math.min(y1, y2);
              ov=new Oval(x,y,width,height,mode,c);
              Drawings.add(ov);
              break;
            case "Line":
                ln=new Line(x1,y1,x2,y2,mode,c);
                Drawings.add(ln);    
                break;
        }
            repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    //actions when mouse dragged
    @Override
    public void mouseDragged(MouseEvent e) {
        x2=e.getX();
        y2=e.getY();
        if (sh=="FreeHand")
        {
            fh=new FreeHand(x2,y2,x1,y1,mode,c);
            Drawings.add(fh);
            x1=x2;
            y1=y2;
            repaint();
        }
        if (sh=="Eraser")
        {
            er=new Eraser(x2,y2,20,20,mode,c);
            Drawings.add(er);
            x1=x2-1;
            y1=y2-1;
            repaint();
        }
        if (sh=="Line")
        {
            repaint();
        }
        if (sh=="Rectangle")
        {
            width=Math.abs(x2-x1);
            height=Math.abs(y2-y1);
            x=Math.min(x1, x2);
            y=Math.min(y1, y2);
            repaint();
        }
        if (sh=="Oval")
        {
            width=Math.abs(x2-x1);
            height=Math.abs(y2-y1);
            x=Math.min(x1, x2);
            y=Math.min(y1, y2);
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    
    //paint method for the shapes
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(c);
        if (mode=="Fill")
        {
            if (sh=="Rectangle")
                 {
                     g.fillRect(x, y, WIDTH, HEIGHT);
                 }
            else if (sh=="Oval")
                 {
                     g.fillOval(x, y, WIDTH, HEIGHT);
                 }
            else if (sh=="Line")
                 {
                     g.drawLine(x1, y1, x2, y2);
                 }    
            else if (sh=="Eraser")
                {
                    g.setColor(Color.WHITE);
                    g.fillOval(x, y, WIDTH, HEIGHT);
                }
            else if (sh=="FreeHand")
                {
                    g.drawLine(x1, y1, x2, y2);
                }
            
        }
        else if (mode=="Dotted")
        {
        Graphics2D g2D = (Graphics2D)g.create();
        Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0, new float[]{5},0);
        g2D.setStroke(dashed);
            if (sh=="Rectangle")
                {
                    g2D.drawRect(x, y, WIDTH, HEIGHT);
                }
            else if (sh=="Oval")
                {
                    g2D.drawOval(x, y, WIDTH, HEIGHT);
                }
            else if (sh=="Line")
                {
                    g2D.drawLine(x1, y1, x2, y2);
                }    
            else if (sh=="Eraser")
                {
                    g.setColor(Color.WHITE);
                    g.fillOval(x, y, WIDTH, HEIGHT);
                }
            else if (sh=="FreeHand")
                {
                    g2D.drawLine(x1, y1, x2, y2);
                }
        }
        else if (mode=="Reset")
        {
            if (sh=="Rectangle")
                {
                    g.drawRect(x, y, WIDTH, HEIGHT);
                }
            else if (sh=="Oval")
                {
                    g.drawOval(x, y, WIDTH, HEIGHT);
                }
            else if (sh=="Line")
                {
                    g.drawLine(x1, y1, x2, y2);
                }  
            else if (sh=="Eraser")
                {
                    g.setColor(Color.WHITE);
                    g.fillOval(x, y, WIDTH, HEIGHT);
                }
            else if (sh=="FreeHand")
                {
                    g.drawLine(x1, y1, x2, y2);
                }
        }
        
        //Paint Shapes from the vector of shapes
        for (int i=0;i<Drawings.size();i++)
        {
            if (Drawings.get(i) instanceof Rectangle)
            {
                switch(Drawings.get(i).mode)
                {
                    case "Fill":
                    g.setColor(Drawings.get(i).c);
                    g.fillRect(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;
                    case "Dotted":
                    Graphics2D g2D = (Graphics2D)g.create();
                    Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0, new float[]{5},0);
                    g2D.setStroke(dashed);
                    g2D.setColor(Drawings.get(i).c);
                    g2D.drawRect(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;
                    case "Reset":
                    g.setColor(Drawings.get(i).c);
                    g.drawRect(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;         
                }
            }
            else if (Drawings.get(i) instanceof Line)
            {
                switch(Drawings.get(i).mode)
                {
                    case "Fill":
                    g.setColor(Drawings.get(i).c);
                    g.drawLine(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;
                    case "Dotted":
                    Graphics2D g2D = (Graphics2D)g.create();
                    Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0, new float[]{5},0);
                    g2D.setStroke(dashed);
                    g2D.setColor(Drawings.get(i).c);
                    g2D.drawLine(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;
                    case "Reset":
                    g.setColor(Drawings.get(i).c);
                    g.drawLine(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;         
                }
            }
            else if (Drawings.get(i) instanceof FreeHand)
            {
                switch(Drawings.get(i).mode)
                {
                    case "Fill":
                    g.setColor(Drawings.get(i).c);
                    g.drawLine(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;
                    case "Dotted":
                    Graphics2D g2D = (Graphics2D)g.create();
                    Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0, new float[]{5},0);
                    g2D.setStroke(dashed);
                    g2D.setColor(Drawings.get(i).c);
                    g2D.drawLine(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;
                    case "Reset":
                    g.setColor(Drawings.get(i).c);
                    g.drawLine(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;         
                }
            }
            else if (Drawings.get(i) instanceof Eraser)
            {
                switch(Drawings.get(i).mode)
                {
                    case "Fill":
                    g.setColor(Color.WHITE);
                    g.fillOval(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;
                    case "Dotted":
                    Graphics2D g2D = (Graphics2D)g.create();
                    Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0, new float[]{5},0);
                    g2D.setStroke(dashed);
                    g.setColor(Color.WHITE);
                    g.fillOval(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;
                    case "Reset":
                    g.setColor(Color.WHITE);
                    g.fillOval(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;     
                }
            }
            else if (Drawings.get(i) instanceof Oval)
            {
                switch(Drawings.get(i).mode)
                {
                    case "Fill":
                    g.setColor(Drawings.get(i).c);
                    g.fillOval(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;
                    case "Dotted":
                    Graphics2D g2D = (Graphics2D)g.create();
                    Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0, new float[]{5},0);
                    g2D.setStroke(dashed);
                    g2D.setColor(Drawings.get(i).c);
                    g2D.drawOval(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;
                    case "Reset":
                    g.setColor(Drawings.get(i).c);
                    g.drawOval(Drawings.get(i).x1,Drawings.get(i).y1,Drawings.get(i).x2,Drawings.get(i).y2);
                    break;     
                }
            }
            else if (Drawings.get(i) instanceof ImageSh)
            {
                switch(Drawings.get(i).mode)
                {
                    case "Reset":
                    g.drawImage(Drawings.get(i).ig,Drawings.get(i).x1,Drawings.get(i).y1,null);
                    break;    
                }
            }
        }
    }
}
