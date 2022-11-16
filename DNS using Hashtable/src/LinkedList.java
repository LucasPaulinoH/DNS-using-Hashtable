public class LinkedList {
  int size;
  DnsAddress firstAddress;

  public LinkedList() {
    this.size = 0;
    this.firstAddress = null;
  }

  public void insert(String url, String address) {
    DnsAddress newAddress = new DnsAddress(url, address);

    if (this.firstAddress == null) {
      this.firstAddress = newAddress;
    } else {
      DnsAddress currentAddress = firstAddress;

      while (currentAddress.next != null) {
        currentAddress = currentAddress.next;
      }
      currentAddress.next = newAddress;
    }
    size++;
  }

  public DnsAddress get(int index) {
    if (index < 0 || index > size)
      throw new IndexOutOfBoundsException();

    DnsAddress currentAddress = firstAddress;
    if (currentAddress != null) {
      for (int i = 0; i < index; i++) {
        currentAddress = currentAddress.next;
      }
      System.out.println(currentAddress.getUrl() + " | " + currentAddress.getAddress());
      return currentAddress;
    } else
      return null;
  }

  public void printList() {
    this.printList(firstAddress);
  }

  private void printList(DnsAddress initial) {
    DnsAddress iterative;

    for (iterative = initial; iterative != null; iterative = iterative.next) {
      System.out.println(iterative.getUrl() + " | " + iterative.getAddress());
    }
  }
}
