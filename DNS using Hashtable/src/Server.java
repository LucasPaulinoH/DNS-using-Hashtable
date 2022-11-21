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
  /*
   * Tarefa para que enquanto o número de endereços lidos ainda não
   * seja o total cadastrado, sejam adicionados de 25 em 25 a cada
   * 10 segundos.
   */
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

    addAddresses(); // adição dos endereços iniciais

    // adiciona mais endereços a cada 10 segundos
    timer.scheduleAtFixedRate(waitTenSecondsToAddAddresses, 10000, 10000);
  }

  /* Realiza a leitura dos endereços e armazená-los em um vetor */
  private void storeAllAddresses() throws FileNotFoundException {
    scan = new Scanner(addressesFile);

    int i = 0;
    while (scan.hasNextLine()) {
      storedAddresses[i] = scan.nextLine();
      i++;
    }
  }

  /* Adiciona 25 endereços à tabela hash */
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

  public void request(String url) {
    addressesTable.search(url);
  }

}
