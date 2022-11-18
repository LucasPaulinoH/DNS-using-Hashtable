public class HashTable {
  private int tableSize;
  LinkedList table[];

  public HashTable(int size) {
    this.tableSize = size;
    this.table = new LinkedList[size];
  }

  public void insert(String url, String ip) {
    int index = hash(url);

    if (table[index] == null) {
      table[index] = new LinkedList();
    }
    table[index].insert(url, ip);
    table[index].size++;
  }

  public DnsAddress search(String url){
    int index = hash(url);

    if(table[index] == null){
      System.out.println("Endereço não encontrado!");
      return null;
    }

    return table[index].searchAddress(url);
  }

  public void printTable(){
    for(int i = 0; i < this.tableSize; i++){
      if(table[i] == null){
        System.out.println("[" + (i) + "]---> ");
      } else{
        System.out.print("[" + (i) + "]---> ");
        table[i].printList();
      }
    }
  }

  public int hash(String url) {
    int hash = 0;

    for (int i = 0; i < url.length(); i++) {
      hash = ((31 * hash + url.charAt(i)) % this.tableSize);
    }

    return hash;
  }
}
