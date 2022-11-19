public class App {
    public static void main(String[] args) throws Exception {
        HashTable hashTable = new HashTable(29);

        hashTable.insert("www.yahoo.com", "216.109.118.65:7000");
        hashTable.insert("www.espn.com", "111.111.111.111:11111");
        hashTable.insert("www.google.com", "216.239.41.99:5000");

        hashTable.printTable();

        hashTable.resizeTable(100);

        hashTable.printTable();

    }
}
