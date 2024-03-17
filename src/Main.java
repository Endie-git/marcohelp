import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final int MAX_FORMES = 30;

    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        Image[] formes = new Image[MAX_FORMES];
        int numeroForme = 1;
        int indiceTabForme = 0;
        int nbImages = compterFormesOuImages("image");
        int nbFormes = compterFormesOuImages("forme");
        Image[] images = new Image[nbImages];
        do {
            if (numeroForme < 10) {
                formes[indiceTabForme] = Image.chargerImage("forme0" + numeroForme + ".txt");
                indiceTabForme++;
            } else {
                formes[indiceTabForme] = Image.chargerImage("forme" + numeroForme + ".txt");
                indiceTabForme++;
            }
            numeroForme++;
        } while (numeroForme < nbFormes);

        switch (ecranAccueil()) {
            case TOUT -> {
                for (int i = 0; i < nbImages; i++) {
                    if (i < 10) {
                        images[i] = Image.chargerImage("image0" + (i + 1) + ".txt");
                    } else {
                        images[i] = Image.chargerImage("image" + (i + 1) + ".txt");
                    }
                }
            }
            case UNIQUE -> {
                System.out.print("Veuillez saisir le nom du fichier (X pour quitter): ");
                String nomFichier = clavier.nextLine();
                if (nomFichier.equalsIgnoreCase("X")) {
                    System.out.println("Fin.");
                } else {
                    if (!nomFichier.endsWith(".txt")) {
                        nomFichier = nomFichier + ".txt";
                    }
                    images[0] = Image.chargerImage(nomFichier);
                }
            }
        }
        for (int i = 0; i < nbImages; i++) {
            if (images[i] != null) {
                for (int indiceRotation = 0; indiceRotation < 4; indiceRotation++) {
                    for (int j = 0; j < nbFormes; j++) {
                        if (formes[j] != null) {
                            if(indiceRotation==0) {
                                System.out.println("Forme " + j + " trouvée dans l'image " + i + " à la position ");
                                Image.trouverCoordonneesFormesDansImage(images[i], formes[j]);
                            }
                            else{
                                System.out.println("Forme " + j + " trouvée dans l'image " + i + " à la position ");
                                Image.trouverCoordonneesFormesDansImage(images[i], formes[j]);
                            }
                        }
                    }
                    images[i] = Image.rotation90Deg(images[i]);
                }
            }
        }

    }
    //ctrl alt L to fix indentations

    //IndexOf --> retourne position de string dans une plus grande string

    // formes 7 et 8 --> erreur
    //validation : pixels de 0 à 255 slm
    //isBlank --> comme isEmpty mais peut avoir espace (donc pas de caractère lisible)
    public static OptionsMenu ecranAccueil() {
        Scanner clavier = new Scanner(System.in);

        int choix = 0;
        System.out.println("TP1 sim202 2024 d'Andy, Thai");
        System.out.println("************ MENU ************");
        System.out.println(OptionsMenu.TOUT);
        System.out.println(OptionsMenu.UNIQUE);
        do {
            System.out.print("Votre choix: ");
            try {
                choix = clavier.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Erreur: saisie doit être un chiffre");
            }
            clavier.nextLine();
            if (choix != 1 && choix != 2) {
                System.out.println("Pas une option valide, veuillez réessayer.\n");
            }

        } while (choix != 1 && choix != 2);
        if (choix == 1) {
            return OptionsMenu.TOUT;
        } else {
            return OptionsMenu.UNIQUE;
        }
    }

    public static int compterFormesOuImages(String type) {
        int compteur = 1;
        try {
            for (compteur = 1; compteur <= MAX_FORMES; compteur++) {
                if (compteur < 10) {
                    BufferedReader lecture = new BufferedReader(new FileReader(type + "0" + compteur + ".txt"));
                } else {
                    BufferedReader lecture = new BufferedReader(new FileReader(type + compteur + ".txt"));
                }
            }
        } catch (FileNotFoundException e) {
            return compteur - 1;
        }
        return 0;
    }
}