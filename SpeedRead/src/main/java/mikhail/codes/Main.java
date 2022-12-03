package mikhail.codes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

import javax.swing.JFrame;
public class Main {
    public static int offset = 0;
    static int index = 0;

    static String text;
    static {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter the path to the PDF file: ");
            String pathInput = keyboard.nextLine();
            text = getText(new File(pathInput));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static String[] textArr = text.split(" ");

    static DisplayWindow w = new DisplayWindow();
    static JFrame f = new JFrame();

    public static void main(String[] args) {


        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter your choice of reading rate in WPM: ");
        long input = keyboard.nextLong();
        long rate = 1000/(input/60);
        System.out.println(rate);
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(Main::outputWord, 0, rate, TimeUnit.MILLISECONDS);
    }
    public static void outputWord() {
        f.add(w);
        f.setSize(1000, 800);
        f.setVisible(true);
        w.textToDisplay = textArr[index];
        offset = textArr[index].length()/2;
        f.repaint();
        index++;
    }
    static String getText(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        return new PDFTextStripper().getText(doc);
    }
}
