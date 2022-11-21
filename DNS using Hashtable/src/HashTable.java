public class HashTable {
  public int tableSize;
  LinkedList table[];

  public HashTable(int size) {
    this.tableSize = size;
    this.table = new LinkedList[size];
  }

  /* ============== MÉTODOS PRINCIPAIS ============== */
  private int hash(String url, int size) {
    int hash = 0;

    for (int i = 0; i < url.length(); i++) {
      hash = ((hash + url.charAt(i)) % size);
    }

    return hash;
  }

  public void insert(String url, String ip) {
    int index = hash(url, tableSize);

    if (table[index] == null) {
      table[index] = new LinkedList();
    }
    table[index].insert(url, ip);
    table[index].size++;
  }

  public DnsAddress search(String url) {
    int index = hash(url, tableSize);

    if (table[index] == null) {
      System.out.println("Endereço não encontrado!");
      return null;
    }
    DnsAddress findedAddress = table[index].searchAddress(url);

    return findedAddress;
  }

  public DnsAddress remove(String url) {
    int index = hash(url, tableSize);

    if (table[index] == null) {
      System.out.println("Endereço não encontrado!");
      return null;
    }
    return table[index].remove(url);
  }

  /* ============== MÉTODOS AUXILIARES ============== */
  public void resizeTable() {
    switch (this.tableSize) {
      case 29:
        adjustTable(61);
        break;
      case 61:
        adjustTable(127);
        break;
      case 127:
        adjustTable(127);
        break;
      default:
        adjustTable(127);
        break;
    }
  }

  private void adjustTable(int newSize) {
    LinkedList[] newTable = new LinkedList[newSize];
    int index;

    for (int i = 0; i < this.tableSize; i++) {
      if (table[i] != null) {

        for (int j = 0; j < this.table[i].size; j++) {
          if (table[i].get(j) == null) {
            break;
          }
          index = hash(table[i].get(j).getUrl(), newSize);
          if (newTable[index] == null) {
            newTable[index] = new LinkedList();
          }
          newTable[index].insert(table[i].get(j).getUrl(), table[i].get(j).getAddress());
        }
      }
    }
    tableSize = newSize;
    table = newTable;
  }

  public void printTable() {
    System.out.println("======================================");
    for (int i = 0; i < this.tableSize; i++) {
      if (table[i] == null) {
        System.out.println("[" + (i) + "]---> ");
      } else {
        System.out.print("[" + (i) + "]---> ");
        table[i].printList();
      }
    }
    System.out.println("======================================");
  }
}
