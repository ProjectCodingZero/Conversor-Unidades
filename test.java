import java.Collection.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class test {
    final static List<List<Integer>> ejemplo = new ArrayList<List<Integer>>(){
        {
            add(new ArrayList<>());
            add(new LinkedList<>());
        }
    };
    public static void main(String[] args) {
        for(List<Integer> lista : ejemplo){
            System.out.println();
        }
    }
}

https://www.digitalocean.com/community/tutorials/jackson-json-java-parser-api-example-tutorial

https://www.digitalocean.com/community/tutorials/jackson-json-java-parser-api-example-tutorial
