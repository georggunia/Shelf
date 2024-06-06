package ShelfPckg;

public class Tool extends ShelfItem {
    private final String name;

    public Tool(String name){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "Name: " + name;
    }
}

