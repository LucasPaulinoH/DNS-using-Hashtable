public class LinkedList {
  int size;
  DnsAddress firstAddress, lastAddress;

  public LinkedList() {
    this.size = 0;
    this.firstAddress = null;
    this.lastAddress = null;
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

  public void insert(String url, String ip) {
    DnsAddress newAddress = new DnsAddress(url, ip);

    if (firstAddress == null) {
      firstAddress = newAddress;
      lastAddress = newAddress;
    } else {
      newAddress.previous = lastAddress;
      lastAddress.next = newAddress;
      lastAddress = newAddress;
    }
    size++;
  }

  /*public void insertAtBeginning(String url, String ip) {
    DnsAddress newAddress = new DnsAddress(url, ip);

    if (firstAddress == null) {
      firstAddress = newAddress;
      lastAddress = newAddress;
    } else {
      newAddress.next = firstAddress;
      firstAddress.previous = firstAddress;
      firstAddress = newAddress;
    }
    size++;
  }*/

  public DnsAddress searchAddress(String url) {
    DnsAddress finder = firstAddress;

    while (finder != null) {
      if (finder.getUrl().equals(url)) {
        System.out.println("Endereço encontrado: "+ finder.getUrl() + " | " + finder.getAddress());
        finder.frequencyCounter++;
        return finder;
      }
      finder = finder.next;
    }
    return null;
  }

  public DnsAddress remove(String url) {
    if (firstAddress == null) {
      System.out.println("Nenhum endereço removido, lista vazia!");
      return null;
    }

    DnsAddress previousAddress = null;
    DnsAddress removedAddress = searchAddress(url);

    if (removedAddress != null) {
      previousAddress = removedAddress.previous;
    }

    if (previousAddress == null) {
      if (!firstAddress.getUrl().equals(url)) {
        System.out.println("URL inexistente!");
        return null;
      } else {
        removedAddress = firstAddress;

        if (firstAddress == lastAddress) {
          firstAddress = null;
          lastAddress = null;
        } else {
          firstAddress = firstAddress.next;
          firstAddress.previous = null;
          removedAddress.next = null;
        }
      }
    } else {
      removedAddress = previousAddress.next;

      if (removedAddress == lastAddress) {
        lastAddress.previous = null;
        lastAddress = previousAddress;
        lastAddress.next = null;
      } else {
        DnsAddress forward = removedAddress.next;
        previousAddress.next = forward;
        forward.previous = previousAddress;

        removedAddress.next = null;
        removedAddress.previous = null;
      }
    }
    size--;

    System.out.println("Endereço removido: " + removedAddress.getUrl() + " | " + removedAddress.getAddress());
    return removedAddress;
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
