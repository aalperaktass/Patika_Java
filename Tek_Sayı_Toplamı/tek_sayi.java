import java.util.Scanner;

public class tek_sayi {
  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);
    
    int sum = 0;

        boolean condition = true;
        
    while (condition) {

      System.out.print("Lütfen bir sayı giriniz: ");
      int number = input.nextInt();

      if (number % 2 == 1) {
        condition = false;
      } else {
        if (number % 4 == 0) {
          sum = sum + number;
        }
      }
    }
    System.out.println("Toplam: " + sum);

    input.close();
  }
}
