/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint_brush;

import java.awt.Color;

/**
 *
 * @author engna
 */
public class Line extends ShapeParent{
    public Line(int x1,int y1,int x2,int y2,String mode,Color c)
    {
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.mode=mode;
        this.c=c;
    }
}
