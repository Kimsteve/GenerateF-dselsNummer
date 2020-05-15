
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stephenkimogol
 */
public class Fodselsnummer {
    HashMap<Integer, Fnummer> persons = new HashMap<>();
    public static void main(String[] args) {

        System.out.println("Hello world");

        Fnummer newNummer = new Fnummer(15, 8, 2011, "m");
        //newNummer.generatePersonNummer();
        System.out.println("Returned number "+ newNummer.generatePersonNummer());
    }
}
