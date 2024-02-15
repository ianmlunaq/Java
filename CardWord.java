public class CardWord {
    private char cardValue;
    private char suitValue;
    private String fullCardString;
    private String description;

    public CardWord(String cardNotation) {
        // This try...catch is here for when a user enters an incomplete card notation such as "5"
        try {
            cardValue = cardNotation.charAt(0);
            suitValue = cardNotation.charAt(1);
            fullCardString = cardNotation;
            description = "";
        } catch (Exception ignored) {}
    }

    public String getDescription() {
        if ((cardValue >= '1' && cardValue <= '9') || cardValue == 'A' || cardValue == 'J' || cardValue == 'K' || cardValue == 'Q'){
            switch (cardValue) {
                case 'A':
                    description += "Ace";
                    break;

                case 'J':
                    description += "Jack";
                    break;

                case 'Q':
                    description += "Queen";
                    break;

                case 'K':
                    description += "King";
                    break;

                case '1':
                    // This if...else statement is here to deal with the "10" card value
                    if (suitValue == '0' && fullCardString.length() == 3) {
                        description += "10";
                        suitValue = fullCardString.charAt(2);
                    } else {
                        description += "1";
                    }
                    break;

                default:
                    description += cardValue;
            }

            switch (suitValue) {
                case 'D':
                    description += " of Diamonds";
                    break;

                case 'H':
                    description += " of Hearts";
                    break;

                case 'S':
                    description += " of Spades";
                    break;

                case 'C':
                    description += " of Clubs";
                    break;

                default:
                    description = "Unknown";
            }
        } else {
            description = "Unknown";
        }

        return description;
    }
}
