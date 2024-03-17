public enum OptionsMenu {
    TOUT("Afficher toutes les images automatiquement"),
    UNIQUE("Traiter un fichier unique");

    private String affichage;

    OptionsMenu(String affichage) {
        this.affichage = affichage;
    }

    @Override
    public String toString() {
        return (ordinal()+1)+"- " + affichage;
    }
}
