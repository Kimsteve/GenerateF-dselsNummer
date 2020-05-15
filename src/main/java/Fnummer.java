
import java.util.HashSet;
import java.util.Random;
import java.time.*;
import java.time.format.DateTimeFormatter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author stephenkimogol
 */
class Fnummer {

    int date;
    int month;
    int year;
    String gender;
    int personnummer;
    int individsifre;
    int kontrollsifre;
    HashSet<Integer> individsifre499 = new HashSet();
    HashSet<Integer> individsifre500To749 = new HashSet();
    HashSet<Integer> individsifre500To999 = new HashSet();
    HashSet<Integer> individsifre900To999 = new HashSet();

    public Fnummer(int date, int month, int year, String gender) {
        this.date = date;
        this.month = month;
        this.year = year;
        this.gender = gender;
    }

    public String generatePersonNummer() {

        LocalDate dt = LocalDate.of(year, month, date);
        System.out.println(dt);
        DateTimeFormatter formatter_2 = DateTimeFormatter.ofPattern("ddMMyyyy");
        String format_2 = (dt).format(formatter_2);
        System.out.println(format_2);
        char char_d1 = format_2.charAt(0);
        char char_d2 = format_2.charAt(1);
        char char_m1 = format_2.charAt(2);
        char char_m2 = format_2.charAt(3);
        char char_y1 = format_2.charAt(6);
        char char_y2 = format_2.charAt(7);

        int d1 = Character.getNumericValue(char_d1);
        int d2 = Character.getNumericValue(char_d2);
        int m1 = Character.getNumericValue(char_m1);
        int m2 = Character.getNumericValue(char_m2);
        int y1 = Character.getNumericValue(char_y1);
        int y2 = Character.getNumericValue(char_y2);

        //int personNummer =0;
        int personNummerReturn = 0;

        int personNummer = getIndividNummer();
        String zeroPaddedIndividNummer = addPadding(personNummer);

        System.out.println("before padded " + personNummer);
        System.out.println("afterpadded " + zeroPaddedIndividNummer);
        int i1 = Character.getNumericValue(zeroPaddedIndividNummer.charAt(0));
        int i2 = Character.getNumericValue(zeroPaddedIndividNummer.charAt(1));
        int i3 = Character.getNumericValue(zeroPaddedIndividNummer.charAt(2));

        char[] dateFormat = {char_d1, char_d2, char_m1, char_m2, char_y1, char_y2};

        String dateOfBirth = new String(dateFormat);

        System.out.println(dateOfBirth);

        int k1 = confirmCheckSum(11 - ((3 * d1 + 7 * d2 + 6 * m1 + 1 * m2 + 8 * y1 + 9 * y2 + 4 * i1 + 5 * i2 + 2 * i3) % 11));
        int k2 = confirmCheckSum(11 - ((5 * d1 + 4 * d2 + 3 * m1 + 2 * m2 + 7 * y1 + 6 * y2 + 5 * i1 + 4 * i2 + 3 * i3 + 2 * k1) % 11));

        String kontrollSiffer1 = Integer.toString(k1);
        String kontrollSiffer2 = Integer.toString(k2);

        //String ss1 = generateCheckSum(personNummer);
        String ind = Integer.toString(personNummer);

        String ff = dateOfBirth + zeroPaddedIndividNummer + kontrollSiffer1 + kontrollSiffer2;

        System.out.println(ff);

        if (ff.length() > 11) {
            System.out.println("incorrect");
            generatePersonNummer();
        }

        return ff;
    }

    private int getRandomNumberInRange(int min, int max) {
        //generates a random integer from origin (inclusive) to bound (exclusive)
        int genderCorrectRandomNumber =0;
        Random r = new Random();
        int randomNumber = r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();

        System.out.println("Random before everything k " + randomNumber);
        if (gender.equalsIgnoreCase("k")) {
            if(getNumericValueOfDigit(randomNumber) % 2 == 0) {
                genderCorrectRandomNumber = randomNumber;
                System.out.println("Random number before loop " + genderCorrectRandomNumber);
                return genderCorrectRandomNumber;
            } else {
            
                genderCorrectRandomNumber = getRandomNumberInRange(min, max);
            }
        }

        System.out.println("Random before everything in m " + randomNumber);
        if (gender.equalsIgnoreCase("m")) {
            if(getNumericValueOfDigit(randomNumber) % 2 != 0) {
                genderCorrectRandomNumber = randomNumber;
                System.out.println("Random number before loop " + genderCorrectRandomNumber);
                return genderCorrectRandomNumber;
            } else {
            genderCorrectRandomNumber=getRandomNumberInRange(min, max);
        }
 
        } 
        System.out.println("Return value " + randomNumber);
        return genderCorrectRandomNumber;
    }

    private int getNumericValueOfDigit(int randomNumber) {
        String temp2 = addPadding(randomNumber);
        char c = temp2.charAt(2);
        int a = Character.getNumericValue(c);

        return a;
    }

    private int getIndividNummer() {
        //int getGenderNumber = 0;
        int randomNumber = 0;
        if (year >= 1900 && year <= 1999) {
            randomNumber = getRandomNumberInRange(0, 499);
        } else if (((year >= 1854) && (year <= 1899)) || (year > 2000)) {
            randomNumber = getRandomNumberInRange(500, 749);

        } else if (year >= 2000 && year <= 2039) {  //500–999 omfatter personer født i perioden 2000–2039.
            randomNumber = getRandomNumberInRange(500, 599);

        } else if ((year >= 1940 && year <= 1999) || (year >= 2000 && year <= 2039)) {
            randomNumber = getRandomNumberInRange(900, 999);
        }

        return randomNumber;
    }

    private int confirmCheckSum(int check) {
        if (check == 11) {
            return 0;
        }
        return check;

    }

    private String addPadding(int number) {
        String paddedString = "";
        //String stringifiedInt ="";

        if (number <= 9) {
            paddedString = String.format("%03d", number);
        } else if (number <= 99) {
            paddedString = String.format("%03d", number);
        } else if (number > 99) {
            paddedString = Integer.toString(number);
        }

        return paddedString;
    }

}
