import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//La classe Image est utilisée pour les fichiers d'image et de formes
public class Image {
    private int hauteur;
    private int largeur;
    private Pixel[][] tabPixel;

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public Pixel[][] getTabPixel() {
        return tabPixel;
    }

    public Image(int hauteur, int largeur, Pixel[][] tabPixel) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.tabPixel = tabPixel;

    }

    public static Image rotation90Deg(Image imageOuForme) {
        int nouvLargeur = imageOuForme.getHauteur();
        int nouvHauteur = imageOuForme.getLargeur();
        Pixel[][] tabPixelTourne = new Pixel[nouvHauteur][nouvLargeur];
        Pixel[][] tabPixelOrig = imageOuForme.getTabPixel();

        for (int iHauteur = 0; iHauteur < nouvHauteur; iHauteur++) {
            for (int iLargeur = 0; iLargeur < nouvLargeur; iLargeur++) {
                tabPixelTourne[iHauteur][iLargeur] = tabPixelOrig[iLargeur][nouvLargeur - iHauteur];
            }
        }
        return new Image(nouvHauteur, nouvLargeur, tabPixelTourne);
    }

    public static Image chargerImage(String nomFichier) {
        try {
            //Établir les dimensions
            BufferedReader lecture = new BufferedReader(new FileReader(nomFichier));
            String premiereLigne = lecture.readLine();
            String[] dimensions = premiereLigne.split("x");
            int hauteur = Integer.parseInt(dimensions[1]);
            int largeur = Integer.parseInt(dimensions[0]);

            //Lire le fichier
            Pixel[][] tabPixel = new Pixel[hauteur][largeur];
            String[] ligneCompleteFichier = new String[hauteur];
            String[] tabPixelStr = new String[largeur];
            String[] rvb = new String[3];

            for (int indiceHauteur = 0; indiceHauteur < hauteur; indiceHauteur++) {
                ligneCompleteFichier[indiceHauteur] = lecture.readLine();
                tabPixelStr = ligneCompleteFichier[indiceHauteur].split(",");
                for (int indiceLargeur = 0; indiceLargeur < largeur; indiceLargeur++) {
                    rvb = tabPixelStr[indiceLargeur].split("-");
                    tabPixel[indiceHauteur][indiceLargeur] = new Pixel(Integer.parseInt(rvb[0]), Integer.parseInt(rvb[1]), Integer.parseInt(rvb[2]));
                }
            }
            lecture.close();
            return new Image(hauteur, largeur, tabPixel);

        } catch (FileNotFoundException e) {
            System.out.println("Erreur, fichier " + nomFichier + " introuvable: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier: " + nomFichier + " (" + e.getMessage() + ")");
            return null;
        }
    }

    public static void trouverCoordonneesFormesDansImage(Image image, Image forme) {
        Pixel[][] tabPixelImage = image.getTabPixel();
        Pixel[][] tabPixelForme = forme.getTabPixel();
        int hauteurImage = image.getHauteur();
        int largeurImage = image.getLargeur();
        int hauteurForme = forme.getHauteur();
        int largeurForme = forme.getLargeur();

        int nbCoord = 0;
        int[][] coord = new int[(hauteurImage - hauteurForme) * (largeurImage - largeurForme)][2];

        for (int indiceImageHaut = 0; indiceImageHaut <= hauteurImage - hauteurForme; indiceImageHaut++) {
            for (int indiceImageLarg = 0; indiceImageLarg <= largeurImage - largeurForme; indiceImageLarg++) {
                if (comparer(tabPixelImage, tabPixelForme, indiceImageHaut, indiceImageLarg)) {

                    System.out.println("("+indiceImageHaut + "," + indiceImageLarg+")");
                }
            }
        }
    }

    public static boolean comparer(Pixel[][] tabPixelImage, Pixel[][] tabPixelForme, int indiceImageHaut, int indiceImageLarg) {
        int compte = 0;
        for (int indiceFormeHaut = 0; indiceFormeHaut < tabPixelForme.length; indiceFormeHaut++) {
            for (int indiceFormelarg = 0; indiceFormelarg < tabPixelForme[indiceFormeHaut].length; indiceFormelarg++) {
                if (tabPixelForme[indiceFormeHaut][indiceFormelarg] == tabPixelImage[indiceFormeHaut + indiceImageHaut][indiceFormelarg + indiceImageLarg]) {
                    compte++;
                    //fgjb
                }
            }
        }
        if(compte == tabPixelForme.length * tabPixelForme[0].length) {
            return true;
        } else {
            return false;
        }
    }
}
