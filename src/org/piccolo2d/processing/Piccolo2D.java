/*

    Piccolo2D library for Processing.
    Copyright (c) 2010-2012 held jointly by the individual authors.

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
import java.awt.Stroke;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import org.piccolo2d.PCamera;
import org.piccolo2d.PLayer;
import org.piccolo2d.PNode;
import org.piccolo2d.POffscreenCanvas;
import org.piccolo2d.PRoot;

import org.piccolo2d.activities.PTransformActivity;

import org.piccolo2d.nodes.PArea;
import org.piccolo2d.nodes.PHtmlView;
import org.piccolo2d.nodes.PImage;
import org.piccolo2d.nodes.PPath;
import org.piccolo2d.nodes.PText;

import org.piccolo2d.util.PPaintContext;

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

    /** Alias to <code>PPaintContext.HIGH_QUALITY_RENDERING</code>. */
    public static final int HIGH = PPaintContext.HIGH_QUALITY_RENDERING;

    /** Alias to <code>PPaintContext.LOW_QUALITY_RENDERING</code>. */
    public static final int LOW = PPaintContext.LOW_QUALITY_RENDERING;


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
        applet.registerMethod("draw", this);
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

    public int getRenderQuality()
    {
        return canvas.getRenderQuality();
    }

    public void setRenderQuality(final int renderQuality)
    {
        canvas.setRenderQuality(renderQuality);
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

    // create nodes on first layer

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

    public PArea createArea()
    {
        PArea area = new PArea();
        getLayer().addChild(area);
        return area;
    }

    public PPath createArc(final float x,
                           final float y,
                           final float width,
                           final float height,
                           final float start,
                           final float extent,
                           final int type)
    {
        PPath pathNode = PPath.createArc(x, y, width, height, start, extent, type);
        getLayer().addChild(pathNode);
        return pathNode;
    }

    public PPath createCubicCurve(final float x1,
                                  final float y1,
                                  final float ctrlx1,
                                  final float ctrly1,
                                  final float ctrlx2,
                                  final float ctrly2,
                                  final float x2,
                                  final float y2)
    {
        PPath pathNode = PPath.createCubicCurve(x1, y1, ctrlx1, ctrly1, ctrlx2, ctrly2, x2, y2);
        getLayer().addChild(pathNode);
        return pathNode;
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

    public PPath createQuadCurve(final float x1,
                                 final float y1,
                                 final float ctrlx,
                                 final float ctrly,
                                 final float x2,
                                 final float y2)
    {
        PPath pathNode = PPath.createQuadCurve(x1, y1, ctrlx, ctrly, x2, y2);
        getLayer().addChild(pathNode);
        return pathNode;
    }

    public PPath.Double createPath(final Path2D.Double path)
    {
        PPath.Double pathNode = new PPath.Double(path);
        getLayer().addChild(pathNode);
        return pathNode;
    }

    public PPath.Float createPath(final Path2D.Float path)
    {
        PPath.Float pathNode = new PPath.Float(path);
        getLayer().addChild(pathNode);
        return pathNode;
    }

    public PPath.Double createPath(final Path2D.Double path, final Stroke stroke)
    {
        PPath.Double pathNode = new PPath.Double(path, stroke);
        getLayer().addChild(pathNode);
        return pathNode;
    }

    public PPath.Float createPath(final Path2D.Float path, final Stroke stroke)
    {
        PPath.Float pathNode = new PPath.Float(path, stroke);
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

    // transform view

    public void scaleView(final float s)
    {
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

    public void scaleView(final float sx, final float sy)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.scale(sx, sy);
        camera.setViewTransform(transform);
    }

    public void shearView(final float shx, final float shy)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.shear(shx, shy);
        camera.setViewTransform(transform);
    }

    public void rotateView(final float theta)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.rotate(theta);
        camera.setViewTransform(transform);
    }

    public void rotateView(final float theta, final float anchorx, final float anchory)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.rotate(theta, anchorx, anchory);
        camera.setViewTransform(transform);
    }

    public void rotateView(final float vecx, final float vecy)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.rotate(vecx, vecy);
        camera.setViewTransform(transform);
    }

    public void rotateView(final float vecx, final float vecy, final float anchorx, final float anchory)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.rotate(vecx, vecy, anchorx, anchory);
        camera.setViewTransform(transform);
    }

    public void quadrantRotateView(final int quadrants)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.quadrantRotate(quadrants);
        camera.setViewTransform(transform);
    }

    public void quadrantRotateView(final int quadrants, final float anchorx, final float anchory)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.quadrantRotate(quadrants, anchorx, anchory);
        camera.setViewTransform(transform);
    }

    // animate view transforms

    public PTransformActivity animateScaleView(final float s, final long duration)
    {
        return animateScaleView(s, s, duration);
    }

    public PTransformActivity animateScaleView(final float sx, final float sy, final long duration)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.scale(sx, sy);
        return camera.animateViewToTransform(transform, duration);
    }

    public PTransformActivity animateTranslateView(final float sx, final float sy, final long duration)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.translate(sx, sy);
        return camera.animateViewToTransform(transform, duration);
    }

    public PTransformActivity animateShearView(final float shx, final float shy, final long duration)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.shear(shx, shy);
        return camera.animateViewToTransform(transform, duration);
    }

    public PTransformActivity animateRotateView(final float theta, final long duration)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.rotate(theta);
        camera.setViewTransform(transform);
        return camera.animateViewToTransform(transform, duration);
    }

    public PTransformActivity animateRotateView(final float theta, final float anchorx, final float anchory, final long duration)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.rotate(theta, anchorx, anchory);
        return camera.animateViewToTransform(transform, duration);
    }

    public PTransformActivity animateRotateView(final float vecx, final float vecy, final long duration)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.rotate(vecx, vecy);
        return camera.animateViewToTransform(transform, duration);
    }

    public PTransformActivity animateRotateView(final float vecx, final float vecy, final float anchorx, final float anchory, final long duration)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.rotate(vecx, vecy, anchorx, anchory);
        return camera.animateViewToTransform(transform, duration);
    }

    public PTransformActivity animateQuadrantRotateView(final int quadrants, final long duration)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.quadrantRotate(quadrants);
        return camera.animateViewToTransform(transform, duration);
    }

    public PTransformActivity animateQuadrantRotateView(final int quadrants, final float anchorx, final float anchory, final long duration)
    {
        PCamera camera = canvas.getCamera();
        AffineTransform transform = camera.getViewTransform();
        transform.quadrantRotate(quadrants, anchorx, anchory);
        return camera.animateViewToTransform(transform, duration);
    }

    // transform nodes

    public void scale(final float sx, final float sy, final PNode node)
    {
        AffineTransform transform = node.getTransform();
        transform.scale(sx, sy);
        node.setTransform(transform);
    }

    public void shear(final float shx, final float shy, final PNode node)
    {
        AffineTransform transform = node.getTransform();
        transform.shear(shx, shy);
        node.setTransform(transform);
    }

    public void translate(final float tx, final float ty, final PNode node)
    {
        AffineTransform transform = node.getTransform();
        transform.translate(tx, ty);
        node.setTransform(transform);
    }

    public void rotate(final float theta, final PNode node)
    {
        AffineTransform transform = node.getTransform();
        transform.rotate(theta);
        node.setTransform(transform);
    }

    public void rotate(final float theta, final float anchorx, final float anchory, final PNode node)
    {
        AffineTransform transform = node.getTransform();
        transform.rotate(theta, anchorx, anchory);
        node.setTransform(transform);
    }

    public void rotate(final float vecx, final float vecy, final PNode node)
    {
        AffineTransform transform = node.getTransform();
        transform.rotate(vecx, vecy);
        node.setTransform(transform);
    }

    public void rotate(final float vecx, final float vecy, final float anchorx, final float anchory, final PNode node)
    {
        AffineTransform transform = node.getTransform();
        transform.rotate(vecx, vecy, anchorx, anchory);
        node.setTransform(transform);
    }

    public void quadrantRotate(final int quadrants, final PNode node)
    {
        AffineTransform transform = node.getTransform();
        transform.quadrantRotate(quadrants);
        node.setTransform(transform);
    }

    public void quadrantRotate(final int quadrants, final float anchorx, final float anchory, final PNode node)
    {
        AffineTransform transform = node.getTransform();
        transform.quadrantRotate(quadrants, anchorx, anchory);
        node.setTransform(transform);
    }

    // animate node transforms

    public PTransformActivity animateScale(final float s, final PNode node, final long duration)
    {
        return animateScaleView(s, s, duration);
    }

    public PTransformActivity animateScale(final float sx, final float sy, final PNode node, final long duration)
    {
        AffineTransform transform = node.getTransform();
        transform.scale(sx, sy);
        return node.animateToTransform(transform, duration);
    }

    public PTransformActivity animateTranslate(final float sx, final float sy, final PNode node, final long duration)
    {
        AffineTransform transform = node.getTransform();
        transform.translate(sx, sy);
        return node.animateToTransform(transform, duration);
    }

    public PTransformActivity animateShear(final float shx, final float shy, final PNode node, final long duration)
    {
        AffineTransform transform = node.getTransform();
        transform.shear(shx, shy);
        return node.animateToTransform(transform, duration);
    }

    public PTransformActivity animateRotate(final float theta, final PNode node, final long duration)
    {
        AffineTransform transform = node.getTransform();
        transform.rotate(theta);
        node.setTransform(transform);
        return node.animateToTransform(transform, duration);
    }

    public PTransformActivity animateRotate(final float theta, final float anchorx, final float anchory, final PNode node, final long duration)
    {
        AffineTransform transform = node.getTransform();
        transform.rotate(theta, anchorx, anchory);
        return node.animateToTransform(transform, duration);
    }

    public PTransformActivity animateRotate(final float vecx, final float vecy, final PNode node, final long duration)
    {
        AffineTransform transform = node.getTransform();
        transform.rotate(vecx, vecy);
        return node.animateToTransform(transform, duration);
    }

    public PTransformActivity animateRotate(final float vecx, final float vecy, final float anchorx, final float anchory, final PNode node, final long duration)
    {
        AffineTransform transform = node.getTransform();
        transform.rotate(vecx, vecy, anchorx, anchory);
        return node.animateToTransform(transform, duration);
    }

    public PTransformActivity animateQuadrantRotate(final int quadrants, final PNode node, final long duration)
    {
        AffineTransform transform = node.getTransform();
        transform.quadrantRotate(quadrants);
        return node.animateToTransform(transform, duration);
    }

    public PTransformActivity animateQuadrantRotate(final int quadrants, final float anchorx, final float anchory, final PNode node, final long duration)
    {
        AffineTransform transform = node.getTransform();
        transform.quadrantRotate(quadrants, anchorx, anchory);
        return node.animateToTransform(transform, duration);
    }


    /*
      constrainView({ALL, CENTER, NONE});
    */
}