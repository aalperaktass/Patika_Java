import java.util.Scanner;
import java.util.Random;

public class tarla {

    // Matris boyutunu tutacak sabitler
    public static final int ROWS = 3;
    public static final int COLS = 3;

    // Mayınların konumlarını tutacak ikinci bir dizi
    public static boolean[][] mines = new boolean[ROWS][COLS];

    // Oyun alanını tutacak dizi
    public static char[][] board = new char[ROWS][COLS];

    // Oyunun bitip bitmediğini kontrol edecek değişken
    public static boolean gameOver = false;

    // Kullanıcıdan girdi almak için Scanner nesnesi
    public static Scanner scanner = new Scanner(System.in);

    // Rastgele sayı üretmek için Random nesnesi
    public static Random random = new Random();

    // Oyunu başlatan ana metod
    public static void main(String[] args) {
        System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz !");

        // Oyun alanını boşluklarla dolduruyoruz
        fillBoard();

        // Mayınları rastgele yerleştiriyoruz
        placeMines();

        // Oyun bitene kadar devam ediyoruz
        while (!gameOver) {
            // Oyun alanını ekrana yazdırıyoruz
            printBoard();

            // Kullanıcıdan satır ve sütun değerlerini alıyoruz
            int row = getRow();
            int col = getCol();

            // Seçilen noktada mayın varsa oyunu kaybediyoruz
            if (mines[row][col]) {
                System.out.println("Game Over!!");
                gameOver = true;
            }
            else {
                // Seçilen noktada mayın yoksa etrafındaki mayın sayısını hesaplıyoruz
                int count = countMines(row, col);

                // Etrafındaki mayın sayısını ilgili noktaya yazıyoruz
                board[row][col] = (char) (count + '0');

                // Eğer hiçbir mayına basmadan tüm noktaları seçtiysek oyunu kazanıyoruz
                if (isWin()) {
                    System.out.println("Oyunu Kazandınız !");
                    gameOver = true;
                }
            }
        }

        // Oyun bittiğinde son durumu ekrana yazdırıyoruz
        printBoard();
    }

    // Oyun alanını boşluklarla dolduran metod
    public static void fillBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Mayınları rastgele yerleştiren metod
    public static void placeMines() {
        // Diziye ait eleman sayısının çeyreği kadar mayın yerleştiriyoruz
        int mineCount = (ROWS * COLS) / 4;

        // Mayın sayısı kadar döngüye giriyoruz
        for (int i = 0; i < mineCount; i++) {
            // Rastgele bir satır ve sütun değeri üretiyoruz
            int row = random.nextInt(ROWS);
            int col = random.nextInt(COLS);

            // Eğer üretilen konumda zaten mayın varsa tekrar üretiyoruz
            while (mines[row][col]) {
                row = random.nextInt(ROWS);
                col = random.nextInt(COLS);
            }

            // Üretilen konuma mayın koyuyoruz
            mines[row][col] = true;
        }
    }

    // Oyun alanını ekrana yazdıran metod
    public static void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Kullanıcıdan geçerli bir satır değeri alan metod
    public static int getRow() {
        System.out.print("Satır Giriniz : ");
        int row = scanner.nextInt();

        // Satır değeri dizinin sınırları içinde olana kadar tekrar istiyoruz
        while (row < 0 || row >= ROWS) {
            System.out.print("Geçersiz satır değeri. Tekrar giriniz : ");
            row = scanner.nextInt();
        }

        return row;
    }

    // Kullanıcıdan geçerli bir sütun değeri alan metod
    public static int getCol() {
        System.out.print("Sütun Giriniz : ");
        int col = scanner.nextInt();

        // Sütun değeri dizinin sınırları içinde olana kadar tekrar istiyoruz
        while (col < 0 || col >= COLS) {
            System.out.print("Geçersiz sütun değeri. Tekrar giriniz : ");
            col = scanner.nextInt();
        }

        return col;
    }

    // Bir noktaya değen tüm konumlarda kaç tane mayın olduğunu sayan metod
    public static int countMines(int row, int col) {
        // Mayın sayısını tutacak değişken
        int count = 0;

        // Sağı, solu, yukarısı, aşağısı, sol üst çapraz, sağ üst çapraz, sağ alt çapraz, sol alt çapraz konumları kontrol ediyoruz
        if (isMine(row - 1, col - 1)) count++; // sol üst çapraz
        if (isMine(row - 1, col)) count++; // yukarı
        if (isMine(row - 1, col + 1)) count++; // sağ üst çapraz
        if (isMine(row, col - 1)) count++; // sol
        if (isMine(row, col + 1)) count++; // sağ
        if (isMine(row + 1, col - 1)) count++; // sol alt çapraz
        if (isMine(row + 1, col)) count++; // aşağı
        if (isMine(row + 1, col + 1)) count++; // sağ alt çapraz

        return count;
    }
    
    // Oyunun kazanıp kazanmadığını kontrol eden metod
public static boolean isWin() {
    // Oyun alanındaki tüm noktaları dolaşıyoruz
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            // Eğer bir nokta boşluk ise ve o noktada mayın yoksa oyun bitmemiştir
            if (board[i][j] == '-' && !mines[i][j]) {
                return false;
            }
        }
    }
    // Eğer tüm noktalar dolu ise veya mayın ise oyun kazanılmıştır
    return true;
}

// Bir konumda mayın olup olmadığını kontrol eden metod
public static boolean isMine(int row, int col) {
    // Konum dizinin sınırları içinde ise mayın durumuna bakıyoruz
    if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
        return mines[row][col];
    }
    // Konum dizinin sınırları dışında ise false dönüyoruz
    else {
        return false;
    }
}

}