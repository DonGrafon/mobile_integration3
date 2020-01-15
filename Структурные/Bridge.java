public interface Color {
    String fill();
}

public class Blue implements Color {
    @Override
    public String fill() {
        return "Color is Blue";
    }
}

public abstract class Shape {
    protected Color color;
     
    //standard constructors
     
    abstract public String draw();
}

public class Square extends Shape {
 
    public Square(Color color) {
        super(color);
    }
 
    @Override
    public String draw() {
        return "Square drawn. " + color.fill();
    }
}

@Test
public void whenBridgePatternInvoked_thenConfigSuccess() {
    //a square with red color
    Shape square = new Square(new Red());
  
    assertEquals(square.draw(), "Square drawn. Color is Red");
}