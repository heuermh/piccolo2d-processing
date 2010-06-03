/*

    Piccolo2D library for Processing.
    Copyright (c) 2010 held jointly by the individual authors.

    This file is part of Piccolo2D library for Processing.

    Piccolo2D library for Processing is free software: you can redistribute it and/or
    modify it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Piccolo2D library for Processing is distributed in the hope that it will be
    useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Piccolo2D library for Processing.  If not, see
    <http://www.gnu.org/licenses/>.

*/
package org.piccolo2d.processing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import org.piccolo2d.PCamera;
import org.piccolo2d.PLayer;
import org.piccolo2d.POffscreenCanvas;
import org.piccolo2d.PRoot;

import org.piccolo2d.activities.PTransformActivity;

import org.piccolo2d.nodes.PHtmlView;
import org.piccolo2d.nodes.PImage;
import org.piccolo2d.nodes.PPath;
import org.piccolo2d.nodes.PText;

import processing.core.PApplet;

import processing.core.PGraphicsJava2D;

/**
 * Piccolo2D library for Processing.
 *
 * @author  Michael Heuer
 */
public final class Piccolo2D
{
    /** Processing applet for this Piccolo2D library. */
    private final PApplet applet;

    /** Offscreen canvas for this Piccolo2D library. */
    private final POffscreenCanvas canvas;


    /**
     * Create a new Piccolo2D library for the specified Processing applet.
     *
     * @param applet Processing applet
     */
    public Piccolo2D(final PApplet applet)
    {
        super();
        if (applet == null)
        {
            throw new IllegalArgumentException("applet must not be null");
        }
        this.applet = applet;
        applet.registerDraw(this);
        canvas = new POffscreenCanvas(applet.width, applet.height);
    }


    public void draw()
    {
        Graphics2D g2 = ((PGraphicsJava2D) applet.g).g2;
        canvas.render(g2);
    }

    public boolean isOpaque()
    {
        return canvas.isOpaque();
    }

    public void setOpaque(final boolean opaque)
    {
        canvas.setOpaque(opaque);
    }

    public Color getBackground()
    {
        return canvas.getBackground();
    }

    public void setBackground(final Color color)
    {
        canvas.setBackground(color);
    }

    public PRoot getRoot()
    {
        return (PRoot) canvas.getCamera().getParent();
    }

    public PCamera getCamera()
    {
        return canvas.getCamera();
    }
  
    public PLayer getLayer()
    {
        return canvas.getCamera().getLayer(0);
    }

    public POffscreenCanvas getCanvas()
    {
        return canvas;
    }

    public PText createText(final String text)
    {
        PText textNode = new PText(text);
        getLayer().addChild(textNode);
        return textNode;
    }

    public PHtmlView createHtmlView(final String text)
    {
        PHtmlView htmlView = new PHtmlView(text);
        getLayer().addChild(htmlView);
        return htmlView;
    }

    public PImage createImage(final Image image)
    {
        PImage imageNode = new PImage(image);
        getLayer().addChild(imageNode);
        return imageNode;    
    }

    public PImage createImage(final processing.core.PImage image)
    {
        PImage imageNode = new PImage(image.getImage());
        getLayer().addChild(imageNode);
        return imageNode;    
    }

    public PPath createEllipse(final float x, final float y, final float w, final float h)
    {
        PPath pathNode = PPath.createEllipse(x, y, w, h);
        getLayer().addChild(pathNode);
        return pathNode;
    }

    public PPath createLine(final float x1, final float y1, final float x2, final float y2)
    {
        PPath pathNode = PPath.createLine(x1, y1, x2, y2);
        getLayer().addChild(pathNode);
        return pathNode;
    }

    public PPath createPolyline(final float[] xp, final float[] yp)
    {
        PPath pathNode = PPath.createPolyline(xp, yp);
        getLayer().addChild(pathNode);
        return pathNode;
    }

    public PPath createRectangle(final float x, final float y, final float w, final float h)
    {
        PPath pathNode = PPath.createRectangle(x, y, w, h);
        getLayer().addChild(pathNode);
        return pathNode;
    }

    public PPath createRoundRectangle(final float x, final float y, final float w, final float h,
                                      final float arcWidth, final float arcHeight)
    {
        PPath pathNode = PPath.createRoundRectangle(x, y, w, h, arcWidth, arcHeight);
        getLayer().addChild(pathNode);
        return pathNode;
    }

    // pick(...)

    //public void scaleView(final float sx, final float sy)
    public void scaleView(final float s)
    {
        //if (abs(sx - sy) > 0.001)
        canvas.getCamera().scaleView(s);
    }

    public void scaleViewAboutPoint(final float s, final float x, final float y)
    {
        canvas.getCamera().scaleViewAboutPoint(s, x, y);
    }

    public void scaleViewAboutCenter(final float s)
    {
        Point2D center = canvas.getCamera().getViewBounds().getCenter2D();
        scaleViewAboutPoint(s, (float) center.getX(), (float) center.getY());
    }

    public void translateView(final float tx, final float ty)
    {
        canvas.getCamera().translateView(tx, ty);
    }

    //public PTransformActivity animateScaleView(final float sx, final float sy, final long duration)
    public PTransformActivity animateScaleView(final float s, final long duration)
    {
        AffineTransform transform = canvas.getCamera().getViewTransform();
        //transform.scale(sx, sy);
        transform.scale(s, s);
        return canvas.getCamera().animateViewToTransform(transform, duration);
    }

    public PTransformActivity animateTranslateView(final float sx, final float sy, final long duration)
    {
        AffineTransform transform = canvas.getCamera().getViewTransform();
        transform.translate(sx, sy);
        return canvas.getCamera().animateViewToTransform(transform, duration);
    }

    /*
      scale(sx, sy, PNode);
      shear(shx, shy, PNode);
      translate(tx, ty, PNode);  or offset?
      rotate(theta, PNode);
      rotate(vecx, vecy, PNode);
      rotate(theta, anchorx, anchory, PNode);
      rotate(vecx, vecy, anchorx, anchory, PNode);
      quadrantRotate(quadrants, PNode);
      quadrantRotate(quadrants, anchorx, anchory, PNode);

      scaleView(sx, sy);
      shearView(shx, shy);
      translateView(tx, ty);
      rotateView(theta);
      rotateView(vecx, vecy);
      rotateView(theta, anchorx, anchory);
      rotateView(vecx, vecy, anchorx, anchory);
      quadrantRotateView(quadrants);
      quadrantRotateView(quadrants, anchorx, anchory);
      constrainView({ALL, CENTER, NONE});

      animateScale(sx, sy, PNode, duration);
      animateShear(shx, shy, PNode, duration);
      animateTranslate(tx, ty, PNode, duration);
      animateRotate(theta, PNode, duration);
      animateRotate(vecx, vecy, PNode, duration);
      animateRotate(theta, anchorx, anchory, PNode, duration);
      animateRotate(vecx, vecy, anchorx, anchory, PNode, duration);
      animateQuadrantRotate(quadrants, PNode, duration);
      animateQuadrantRotate(quadrants, anchorx, anchory, PNode, duration);

      animateScaleView(sx, sy, duration);
      animateShearView(shx, shy, duration);
      animateTranslateView(tx, ty, duration);
      animateRotateView(theta, duration);
      animateRotateView(vecx, vecy, duration);
      animateRotateView(theta, anchorx, anchory, duration);
      animateRotateView(vecx, vecy, anchorx, anchory, duration);
      animateQuadrantRotateView(quadrants, duration);
      animateQuadrantRotateView(quadrants, anchorx, anchory, duration);
    */
}