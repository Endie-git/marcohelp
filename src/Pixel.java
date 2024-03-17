import java.io.*;
import java.util.Scanner;

public class Pixel {
    private int rouge;
    private int vert;
    private int bleu;

    public Pixel(int rouge, int vert, int bleu) {
        if (rouge >= 0 && rouge <= 255) {
            this.rouge = rouge;
        }
        if (vert >= 0 && vert <= 255) {
            this.vert = vert;
        }
        if (bleu >= 0 && bleu <= 255) {
            this.bleu = bleu;
        }
    }

}

