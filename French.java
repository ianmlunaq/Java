public class French {
    public static void main(String[] args) {
        String countryName;
        String genderedArticle;
        char firstLetter;

        countryName = "Iraq";

        firstLetter = Character.toLowerCase(countryName.charAt(0));
        
        if (countryName == "Etats-Unis" || countryName == "Pays-Bas") {
            genderedArticle = "les ";
        } else if (firstLetter == 'a' || firstLetter == 'e' || firstLetter == 'i' || firstLetter == 'o' || firstLetter == 'u') {
            genderedArticle = "l\'";
        } else if (!countryName.endsWith("e") || countryName == "Belize" || countryName == "Mexique" || countryName == "Cambodge" || countryName == "Mozambique" || countryName == "Zaïre" || countryName == "Zimbabwe") {
            genderedArticle = "le ";
        } else {
            genderedArticle = "la ";
        }

        System.out.println(genderedArticle + countryName);
    }
}