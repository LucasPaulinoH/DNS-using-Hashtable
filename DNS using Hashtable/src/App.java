public class App {
    public static void main(String[] args) throws Exception {
        HashTable hashTable = new HashTable(20);

        hashTable.insert("www.yahoo.com", "216.109.118.65:7000");
        hashTable.insert("www.espn.com", "199.181.135.201:6000");
        hashTable.insert("www.google.com", "216.239.41.99:5000");
        hashTable.insert("www.espn.com", "199.181.135.201:6000");
        hashTable.insert("www.youtube.com", "127.0.0.1:3000");

        hashTable.printTable();

        hashTable.search("www.reddit.com");

    }
}
