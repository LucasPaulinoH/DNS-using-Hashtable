public class LinkedList {
  int size;
  DnsAddress firstAddress, lastAddress;

  public LinkedList() {
    this.size = 0;
    this.firstAddress = null;
    this.lastAddress = null;
  }

  /* ============== MÉTODOS PRINCIPAIS ============== */
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

  public DnsAddress searchAddress(String url) {
    DnsAddress finded = firstAddress;

    while (finded != null) {
      if (finded.getUrl().equals(url)) {

        System.out.println(finded.getAddress());
        finded.frequencyCounter++;
        adjustAddress(finded);
        return finded;
      }

      finded = finded.next;
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

    public DnsAddress get(int index) {
      if (index < 0 || index > size){
        throw new IndexOutOfBoundsException();
      }
  
      DnsAddress currentAddress = firstAddress;
      if (currentAddress != null) {
        for (int i = 0; i < index; i++) {
          currentAddress = currentAddress.next;
        }
        return currentAddress;
      } else
        return null;
    }

  /* ============== MÉTODOS AUXILIARES ============== */
  private boolean addressNeedsToBeAdjusted(DnsAddress address){
    DnsAddress previous = address.previous;

    if(previous == null){
      return false;
    }

    int comparisonResult = compare(address, previous);

    return comparisonResult > 0;
  }


  private int compare(DnsAddress address, DnsAddress previous) {
    return address.frequencyCounter - previous.frequencyCounter;
  }

  private void adjustAddress(DnsAddress address){
    if(!addressNeedsToBeAdjusted(address)){
      return;
    }

    DnsAddress previous = address.previous;
    DnsAddress next = address.next;
    DnsAddress previousPrevious = previous.previous;
   
    if(previousPrevious == null){
      firstAddress = address;
    } else{
      previousPrevious.next = address;
    }

    address.previous = previousPrevious;
    address.next = previous;

    previous.previous = address;
    previous.next = next;

    if(next != null){
      next.previous = previous;
    }
    adjustAddress(address);
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
