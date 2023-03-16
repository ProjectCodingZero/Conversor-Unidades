
public class test {
    public static void main(String[] args) {
        ObjectStreamClass c = ObjectStreamClass.lookup(MyClass.class);
        long serialID = c.getSerialVersionUID();
        System.out.println(serialID);
    }
}
