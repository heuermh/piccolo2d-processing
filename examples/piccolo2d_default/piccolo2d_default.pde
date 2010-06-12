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
