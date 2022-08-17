# Sliced

A lightweight, polymorphic view- and slice-API for Java 8+.

### Examples

A view is a read-only "window" into an existing collection or array.  
This can be useful for restricting mutability of an API.
It is also more memory efficient in a lot of cases, since a copy is only created
once an explicit `copy` function is called, not everytime a view to the underlying
reference is created as per usual with ImmutableX types provided by libraries such
as Guava and Apache Commons.

```java
public interface Bar {
    String name();
    
    View<Bar> children();
    
    default boolean isSeth() {
        return name().equals("seth");
    }
}

public final class Foo implements Bar {
    private final String name;
    private final ArrayList<Bar> children = new ArrayList<>();
    private final View<Bar> childrenView = View.of(children);
    
    Foo(final String name) {
        this.name = name;
    }
    
    public void addChild(final Bar child) {
        children.add(child);
    }
    
    @Override
    public String name() {
        return name;
    }
    
    @Override
    public View<Bar> children() {
        return childrenView;
    }
}

// ...

public static void main(final String[] args) {
    final Foo foo = new Foo("adam");
    foo.addChild(new Foo("seth"));
    foo.addChild(new Foo("seth"));
    foo.addChild(new Foo("seth"));
    printChildren(foo);
}

private static void printChildren(final Bar bar) {
    final View<Bar> children = bar.children();
    
    // Views are Iterable, which is neat
    for(final Bar child : children) {
        System.out.println(child.name());
    }
    
    // And they can also be streamed directly
    children.stream()
        .filter(Bar::isSeth)
        .forEach(System.out::println);
}
```