import java.util.Scanner;

public class vke {

  public static void main(String[] args) {

  
    Scanner input = new Scanner(System.in);

    System.out.print("Lütfen ağırlığınızı kilogram cinsinden giriniz: ");
    double weight = input.nextDouble();

    System.out.print("Lütfen boyunuzu metre cinsinden giriniz: ");
    double height = input.nextDouble();

    double vke = weight / (height * height);

    System.out.println("Vücut kitle endeksiniz: " + vke);

    if (vke < 18.5) {
      System.out.println("Zayıfsınız.");
    } else if (vke >= 18.5 && vke <= 24.9) {
      System.out.println("Normalsiniz.");
    } else if (vke >= 25 && vke <= 29.9) {
      System.out.println("Fazla kilolusunuz.");
    } else {
      System.out.println("Obezsiniz.");
    }

    input.close();
  }
}
