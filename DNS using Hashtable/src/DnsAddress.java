public class DnsAddress {
  private String url; // key
  private String address; // value
  public DnsAddress next;

  public DnsAddress(String url, String address) {
    this.url = url;
    this.address = address;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
