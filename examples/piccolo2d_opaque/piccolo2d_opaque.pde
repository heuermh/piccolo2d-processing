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
import java.awt.Color;
import org.piccolo2d.processing.*;

int c = 255;
Piccolo2D piccolo;

void setup()
{
  size(320, 180);
  piccolo = new Piccolo2D(this);
  piccolo.setOpaque(true);
  piccolo.createText("Hello Piccolo2D!");
}

// if piccolo is opaque, it fills the sketch
//   with its background color before drawing
void draw()
{
  // note Piccolo2D uses java.awt.Color
  piccolo.setBackground(new Color(c, c, c));

  if (c > 0)
  {
    c--;
  }
}
