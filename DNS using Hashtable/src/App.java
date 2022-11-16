public class App {
    public static void main(String[] args) throws Exception {
        LinkedList list = new LinkedList();

        list.insert("www.google.com", "216.239.41.99:5000");
        list.insert("www.yahoo.com", "216.109.118.65:7000");
        list.insert("www.espn.com", "199.181.135.201:6000");

        list.printList();
    }
}
