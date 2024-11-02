import java.util.Arrays;
import java.util.Random;

public class BlackJack {

    //region создание карт
    /*
    Карточные масти на английском (suit):
    ♣️ Трефы — clubs.
    ♦️ Бубны — diamonds.
    ♥️ Червы — hearts.
    ♠️ Пики — spades.

    Значение карты (rank):
    Ace (Туз)
    Jack (Валет / Джек)
    Queen (Дама / Королева(
    King (Король)
    Joker (Джокер)
    Остальные карты, с двойки по десятку, называются просто цифрой.
     */

    enum CardSuit {
        DIAMONDS("♦"),
        HEARTS("♥"),
        CLUBS("♣"),
        SPADES("♠"),
        ;

        public String suits;

        CardSuit(String suits) {
            this.suits = suits;
        }
    }

    enum CardRank {
        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 4),
        FIVE("5", 5),
        SIX("6", 6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("В", 10),
        QUEEN("Д", 10),
        KING("К", 10),
        ACE("Т", 11),
        ;

        public String rank;
        public int value;

        CardRank(String rank, int value) {
            this.rank = rank;
            this.value = value;
        }
    }


    //endregion


    public static void main(String[] args) {
        //region создание колоды
        Random random = new Random();

        CardSuit[] cardSuits = CardSuit.values();
        CardRank[] cardRanks = CardRank.values();

        String[] deck = new String[cardSuits.length * cardRanks.length];
        int countCards = 0;
        int j = 0;

        for (int i = 1; i <= deck.length; i++) {
            if (i % 13 != 0) {
                deck[i - 1] = cardRanks[j].rank + "" + cardSuits[countCards].suits;
                j++;
            } else {
                j = 0;
                deck[i - 1] = cardRanks[cardRanks.length - 1].rank + "" + cardSuits[countCards++].suits;
            }
        }

        //endregion

        //region перемешивание карт

        for (int i = 0; i < deck.length; i++) {
            int index = random.nextInt(0, 52);
            String change = deck[index];

            deck[index] = deck[i];
            deck[i] = change;
        }
        //endregion

        //region раздача карт
        int cardsCount = 2;
        String[] playerCards = new String[cardsCount];
        String[] playerCardsValues = new String[playerCards.length];

        String[] dealerCards = new String[cardsCount];
        String[] dealerCardsValues = new String[playerCards.length];

        int countPlayerCards = 0;

        do {
            int index = random.nextInt(0, 52);

            if (deck[index] != null) {
                playerCards[countPlayerCards] = deck[index];

                countPlayerCards++;
                deck[index] = null;
            } else {
                continue;
            }
        } while (countPlayerCards != playerCards.length);

        for (int i = 0; i < playerCards.length; i++) {
            System.out.print(playerCards[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < playerCardsValues.length; i++) {
            playerCardsValues[i] = playerCards[i].replaceAll("[\\♣♦♥♠,]", "");
        }

        for (int i = 0; i < playerCardsValues.length; i++) {
            System.out.print(playerCardsValues[i] + " ");
        }
        System.out.println();

        int countDealerCards = 0;

        do {
            int index = random.nextInt(0, 52);

            if (deck[index] != null) {
                dealerCards[countDealerCards] = deck[index];

                countDealerCards++;
                deck[index] = null;
            } else {
                continue;
            }
        } while (countDealerCards != dealerCards.length);

        for (int i = 0; i < dealerCardsValues.length; i++) {
            dealerCardsValues[i] = dealerCards[i].replaceAll("[\\♣♦♥♠,]", "");
        }

        for (int i = 0; i < dealerCards.length; i++) {
            System.out.print(dealerCards[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < dealerCardsValues.length; i++) {
            System.out.print(dealerCardsValues[i] + " ");
        }
        System.out.println();
        //endregion

        //region подсчет очков
        int countPlayerPoints = 0;
        int countDealerPoints = 0;


        for (int i = 0; i < playerCardsValues.length; i++) {
            for (int k = 0; k < cardRanks.length; k++) {
                if (playerCardsValues[i].equals(cardRanks[k].rank)) {
                    countPlayerPoints += cardRanks[k].value;
                }
            }
        }
        System.out.println("количество очков игрока:" + countPlayerPoints);

        for (int i = 0; i < dealerCardsValues.length; i++) {
            for (int k = 0; k < cardRanks.length; k++) {
                if (dealerCardsValues[i].equals(cardRanks[k].rank)) {
                    countDealerPoints += cardRanks[k].value;
                }
            }
        }
        System.out.println("количество очков диллера:" + countDealerPoints);
        //endregion
    }
}