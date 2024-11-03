import java.util.Random;
import java.util.Scanner;

public class BlackJack {

    //region создание карт

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

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String firtsMassage = "Добро пожаловать в игру Блэкджек!\n" +
                "Попробуйте обыграть дилера и набрать 21 очко, не превышая эту сумму.\n" +
                "Желаем удачи и приятной игры!";

        System.out.println(firtsMassage);
        System.out.println();
        //region создание колоды

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
        int cardsCount = 10;
        String[] playerCards = new String[cardsCount];
        String[] playerCardsValues = new String[playerCards.length];

        String[] dealerCards = new String[cardsCount];
        String[] dealerCardsValues = new String[playerCards.length];

        int countPlayerCards = 0;
        int firstHand = 2;

        do {
            int index = random.nextInt(0, 52);

            if (deck[index] != null) {
                playerCards[countPlayerCards] = deck[index];

                countPlayerCards++;
                deck[index] = null;
            } else {
                continue;
            }
        } while (countPlayerCards != firstHand);

        System.out.println("Карты игрока:");
        for (int i = 0; i < firstHand; i++) {
            System.out.print(playerCards[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < firstHand; i++) {
            playerCardsValues[i] = playerCards[i].replaceAll("[\\♣♦♥♠,]", "");
        }

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
        } while (countDealerCards != firstHand);

        for (int i = 0; i < firstHand; i++) {
            dealerCardsValues[i] = dealerCards[i].replaceAll("[\\♣♦♥♠,]", "");
        }

        System.out.println("Карты диллера:");
        for (int i = 0; i < firstHand; i++) {
            if (i % 2 == 0) {
                System.out.print(dealerCards[i] + " ");
            } else System.out.print("X");
        }
        System.out.println();

        //endregion

        //region подсчет очков после раздачи
        int countPlayerPoints = 0;
        int countDealerPoints = 0;


        for (int i = 0; i < countPlayerCards; i++) {
            for (int k = 0; k < cardRanks.length; k++) {
                if (playerCardsValues[i].equals(cardRanks[k].rank)) {
                    countPlayerPoints += cardRanks[k].value;
                }
            }
        }
        System.out.println("количество очков игрока после раздачи:" + countPlayerPoints);

        for (int i = 0; i < countDealerCards; i++) {
            for (int k = 0; k < cardRanks.length; k++) {
                if (dealerCardsValues[i].equals(cardRanks[k].rank)) {
                    countDealerPoints += cardRanks[k].value;
                }
            }
        }
        //endregion

        //region игра
        boolean isTaking = true;

        while (isTaking == true) {
            for (int i = 0; i < 3; i++) {
                System.out.println();
            }
            int indexAddCard = random.nextInt(0, 52);
            int itteration = 0;

            System.out.println("Добавить карту?");
            System.out.println("Если хотите добавить карту введите <hit>, если хотите остановить введите <stand>:");
            String addCard = scanner.nextLine();

            if (addCard.equals("hit")) {
                do {
                    if (deck[indexAddCard] != null) {
                        playerCards[countPlayerCards] = deck[indexAddCard];
                        playerCardsValues[countPlayerCards] = deck[indexAddCard].replaceAll("[\\♣♦♥♠,]", "");

                        countPlayerCards++;

                        deck[indexAddCard] = null;
                        itteration++;
                    } else {
                        continue;
                    }
                } while (itteration != 1);

                System.out.println("Карты игрока: ");
                for (int i = 0; i < countPlayerCards; i++) {
                    System.out.print(playerCards[i] + " ");
                }
                System.out.println();


            }
            if (addCard.equals("hit")) {
                countPlayerPoints = 0;
                for (int i = 0; i < countPlayerCards; i++) {
                    for (int k = 0; k < cardRanks.length; k++) {
                        if (playerCardsValues[i].equals(cardRanks[k].rank)) {
                            countPlayerPoints += cardRanks[k].value;

                        }
                    }
                }
                for (int i = 0; i < countPlayerCards; i++) {
                    if (playerCardsValues[i].equals(cardRanks[12].rank) && countPlayerPoints > 18) {
                        countPlayerPoints -= 10;
                    }
                }
                System.out.println("количество очков игрока:" + countPlayerPoints);

            }
            if (addCard.equals("stand")) {
                isTaking = false;
            }

        }
        //endregion

        //region добавление карт диллера
        if (countDealerPoints < 17) {
            do {
                int indexAddCardDealer = random.nextInt(0, 52);
                if (deck[indexAddCardDealer] != null) {
                    dealerCards[countDealerCards] = deck[indexAddCardDealer];
                    dealerCardsValues[countDealerCards] = deck[indexAddCardDealer].replaceAll("[\\♣♦♥♠,]", "");

                    countDealerCards++;
                    deck[indexAddCardDealer] = null;
                } else {
                    continue;
                }
                countDealerPoints = 0;
                for (int i = 0; i < countDealerCards; i++) {
                    for (int k = 0; k < cardRanks.length; k++) {
                        if (dealerCardsValues[i].equals(cardRanks[k].rank)) {
                            countDealerPoints += cardRanks[k].value;

                        }
                    }
                }
                for (int i = 0; i < countDealerCards; i++) {
                    if (dealerCardsValues[i].equals(cardRanks[12].rank) && countDealerPoints > 18) {
                        countDealerPoints -= 10;
                    }

                }

            } while (countDealerPoints < 17);
        }
        //endregion

        //region определение победителя
        if (countDealerPoints == 21 && countPlayerPoints == 21) {
            System.out.println("Ничья! У Вас и диллера 21!");
        } else if (countPlayerPoints == 21) {
            System.out.println("Вы победили! У вас 21!");
        } else if (countDealerPoints == 21) {
            System.out.println("У диллера 21! К сожалению, вы проиграли(");
        } else if (countPlayerPoints > 21) {
            System.out.println("К сожалению, вы проиграли. У вас перебор");
        } else if (countDealerPoints > 21) {
            System.out.println("Вы выиграли! У диллера перебор");
        } else if (countDealerPoints > countPlayerPoints) {
            System.out.println("Вы проиграли(");
        } else if (countPlayerPoints > countDealerPoints) {
            System.out.println("Вы выиграли!");
        }

        if (countDealerPoints == countPlayerPoints) {
            System.out.println("Ничья!");
        }


        //endregion

        System.out.println();

        System.out.println("Карты игрока:");
        for (int i = 0; i < countPlayerCards; i++) {
            System.out.print(playerCards[i] + " ");
        }
        System.out.println();

        System.out.println("Карты диллера:");
        for (int i = 0; i < countDealerCards; i++) {
            System.out.print(dealerCards[i] + " ");
        }
        System.out.println();

        System.out.println("Количество очков игрока:" + countPlayerPoints);
        System.out.println("Количество очков диллера:" + countDealerPoints);

    }
}