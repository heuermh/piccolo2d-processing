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
