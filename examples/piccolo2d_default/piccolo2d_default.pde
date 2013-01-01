/*

    Piccolo2D library for Processing.
    Copyright (c) 2010-2013 held jointly by the individual authors.

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
import org.piccolo2d.processing.*;

int c = 255;
Piccolo2D piccolo;

void setup()
{
  size(320, 180);
  piccolo = new Piccolo2D(this);
  piccolo.createText("Hello Piccolo2D!");
}

// if piccolo is not opaque (the default),
//   it draws after the sketch does
void draw()
{
  fill(c);
  rect(0, 0, width, height);

  if (c > 0)
  {
    c--;
  }
}
