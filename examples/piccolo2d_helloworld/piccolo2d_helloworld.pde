import org.piccolo2d.processing.*;

Piccolo2D piccolo;

void setup()
{
  size(320, 180);
  piccolo = new Piccolo2D(this);
  piccolo.createText("Hello Piccolo2D!");
}

