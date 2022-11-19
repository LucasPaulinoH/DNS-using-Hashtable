public class DnsAddress {
  private String url; // key
  private String ip; // value
  public DnsAddress next, previous;
  public int frequencyCounter;

  public DnsAddress(String url, String ip) {
    this.url = url;
    this.ip = ip;
    this.next = null;
    this.previous = null;
    this.frequencyCounter = 0;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getAddress() {
    return ip;
  }

  public void setAddress(String ip) {
    this.ip = ip;
  }
}
