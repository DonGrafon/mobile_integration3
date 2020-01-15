public interface ChristmasTree {
    String decorate();
}

public class ChristmasTreeImpl implements ChristmasTree {
 
    @Override
    public String decorate() {
        return "Christmas tree";
    }
}

public abstract class TreeDecorator implements ChristmasTree {
    private ChristmasTree tree;
     
    // standard constructors
    @Override
    public String decorate() {
        return tree.decorate();
    }
}

public class BubbleLights extends TreeDecorator {
 
    public BubbleLights(ChristmasTree tree) {
        super(tree);
    }
     
    public String decorate() {
        return super.decorate() + decorateWithBubbleLights();
    }
     
    private String decorateWithBubbleLights() {
        return " with Bubble Lights";
    }
}

@Test
public void whenDecoratorsInjectedAtRuntime_thenConfigSuccess() {
    ChristmasTree tree1 = new Garland(new ChristmasTreeImpl());
    assertEquals(tree1.decorate(), 
      "Christmas tree with Garland");
      
    ChristmasTree tree2 = new BubbleLights(
      new Garland(new Garland(new ChristmasTreeImpl())));
    assertEquals(tree2.decorate(), 
      "Christmas tree with Garland with Garland with Bubble Lights");
}