import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
  private HashTable addressesTable = new HashTable(29);
  private String[] storedAddresses = new String[100];
  private File addressesFile;
  private Scanner scan;
  private int LinesReadTotal = 0;
  private int LinesToBeRead = 25;

  private Timer timer = new Timer();

  private TimerTask waitTenSecondsToAddAddresses = new TimerTask() {
    @Override
    public void run() {
      if (LinesReadTotal != 100) {
        addressesTable.resizeTable();
        addAddresses();
      } else {
        timer.cancel();
        timer.purge();
      }
    }
  };

  public Server() throws FileNotFoundException {
    addressesFile = new File(
        "E:/Faculdadex/UFERSA 2022.1/Estrutura de Dados II/DNS-using-Hashtable/DNS using Hashtable/src/addresses.txt");
    storeAllAddresses();
    addressesTable.printTable();

    timer.scheduleAtFixedRate(waitTenSecondsToAddAddresses, 10000, 10000);
  }

  public void request(String url) {
    addressesTable.search(url);
  }

  private void storeAllAddresses() throws FileNotFoundException {
    scan = new Scanner(addressesFile);

    int i = 0;
    while (scan.hasNextLine()) {
      storedAddresses[i] = scan.nextLine();
      i++;
    }
  }

  private void addAddresses() {
    String temporaryString, url = "", ip = "";
    boolean readIp = false;

    for (int i = LinesReadTotal; LinesReadTotal < LinesToBeRead; i++) {
      temporaryString = storedAddresses[i];

      for (int j = 0; j < temporaryString.length(); j++) {
        if (readIp == false) {
          if (temporaryString.charAt(j) != ' ') {
            url += temporaryString.charAt(j);
          } else {
            readIp = true;
          }
        } else {
          ip += temporaryString.charAt(j);
        }
      }
      addressesTable.insert(url, ip);
      readIp = false;
      url = "";
      ip = "";
      LinesReadTotal++;
    }
    LinesToBeRead += 25;
  }
}
