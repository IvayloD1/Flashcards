package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class Flashcards {

  private final Map<String, String> set; //Represents Term - Definition pairs
  private final Map<String, Integer> errors; //Represents Term - Mistakes
  private final Scanner in;
  private final ArrayList<String> log; // Storing all input and output

  public Flashcards() {
    this.set = new HashMap<>(); // Order is not required
    this.errors = new HashMap<>();
    this.in = new Scanner(System.in);
    this.log = new ArrayList<>();
  }

  public void writeLog(String term) {
    this.log.add(term);
  }

  public void printAndLog(String message, boolean onlyLog) {
    if (!onlyLog) {
      System.out.println(message);
    }
    this.writeLog(message);
  }

  public void add() {
    String term, definition;
    this.printAndLog("The card:", false); //Ask the user for the card term
    term = this.in.nextLine(); //Read the card's term
    this.printAndLog(term, true);
    if (this.set.containsKey(term)) {
      this.printAndLog(String.format("The card \"%s\" already exists.", term), false);
      return; //If there is such a card in the set, don't save it
    }
    this.printAndLog("The definition of card:", false); //Ask the user for the card definition
    definition = this.in.nextLine(); //Get the definition from the user
    this.printAndLog(definition, true);
    if (this.set.containsValue(
        definition)) { //Check if one of the cards in the set, already has this definition
      this.printAndLog(
          String.format("The definition \"%s\" already exists.\n", definition), false);
      return; //If one of the cards has that definition, don't save the card
    }
    this.set.putIfAbsent(term, definition); //Save the card with its definition
    this.errors.put(term, 0); //Initialize the new card with 0 mistakes
    this.printAndLog(
        String.format("The pair (\"%s\" : \"%s\") has been added.\n", term, definition),
        false);
  }

  public void remove() {
    String term;
    this.printAndLog("Which card? ", false);
    term = this.in.nextLine(); //Ask the user for the name of the card to be removed
    this.printAndLog(term, true);
    if (this.set.containsKey(term)) { //Search the set for the card
      this.set.remove(term); //Remove the card from the set
      this.errors.remove(term); //Remove the card from the error counts
      this.printAndLog("The card has been removed.", false);
    } else {
      this.printAndLog(String.format("Can't remove \"%s\": there is no such card.\n", term), false);
    }
  }

  public void ask() {
    //Select a random card from the set
    Random random = new Random();
    int index = random.nextInt(this.set.size()); //Generate a random index to get a random card
    String[] keys = this.set.keySet()
        .toArray(String[]::new); //Convert the set into an index accessible array
    String question = keys[index]; //Get a random card
    String correctAnswer = this.set.get(question); //Get the randoms' car definition

    this.printAndLog(String.format("Print the definition of \"%s\":\n", question), false);
    String answer = this.in.nextLine(); //Ask the user for the definition of the card
    this.printAndLog(answer, true);
    int numOfErrors = this.errors.get(question);
    if (answer.equals(correctAnswer)) {
      this.printAndLog("Correct!", false);
      return;
    } else if (this.set.containsValue(
        answer)) { //If the user's answer was wrong, but it's a correct answer to a different card in the set
      numOfErrors++; //Add an error to the card
      for (String t1 : this.set.keySet()) {
        if (this.set.get(t1).equals(
            answer)) { //Search for the card that has the inputted definition and inform the user
          this.printAndLog(String.format("Wrong. The right answer is \"%s\", " +
              "but your definition is correct for " +
              "\"%s\".\n", correctAnswer, t1), false);
          break;
        }
      }
    } else { //If the user's answer is  wrong
      numOfErrors++; //Add an error to the card
      this.printAndLog("Wrong. The right answer is \"" + correctAnswer + "\".", false);
    }
    this.errors.put(question, numOfErrors); //Update the number of errors of the card
  }


  /*
  Each card in the file has to be formatted in the following way: "_NAME : _DEFINITION : _MISTAKES"
  Doesn't overwrite the current set (if there is one), just adds more cards
  */
  public void importSetFromFile(File file) {
    try (Scanner fileReader = new Scanner(file)) { //try-with-resources
      String line;
      String[] result;
      int numberOfImportedCards = 0;
      while (fileReader.hasNextLine()) {
        line = fileReader.nextLine();
        result = line.split(" : ");
        this.set.put(result[0], result[1]);
        this.errors.put(result[0], Integer.parseInt(result[2]));
        numberOfImportedCards++;
      }
      this.printAndLog(numberOfImportedCards + " cards have been loaded.", false);
    } catch (FileNotFoundException e) {
      this.printAndLog("File not found.", false);
    }
  }

  // Exports the current set, but does not reset it. More cards can be added during the program execution.

  public void exportSetToFile(File file) {
    try (PrintWriter writer = new PrintWriter(file)) {
      String line;
      for (String term : this.set.keySet()) {
        line = term + " : " + this.set.get(term) + " : " + this.errors.get(term);
        writer.println(line); //Write the card in the file
      }
      this.printAndLog(this.set.size() + " cards have been saved.", false);
    } catch (FileNotFoundException e) {
      this.printAndLog("Error while exporting", false);
    }
  }

  //The hardest cards are considered to be the ones with the most mistakes

  public void hardestCard() {
    //If there are no cards
    if (this.errors.size() == 0) {
      this.printAndLog("There are no cards with errors.", false);
    } else {
      int biggestMistakeCount = 0;
      Set<String> keys = new HashSet<>(); // All possible cards with the highest mistake count
      for (Integer i : this.errors.values()) { //Finding the biggest mistake count
        if (i > biggestMistakeCount) {
          biggestMistakeCount = i;
        }
      }
      for (String key : this.errors
          .keySet()) { //Saving all cards with the highest mistake count in the set
        if (this.errors.get(key) == biggestMistakeCount) {
          keys.add("\"" + key + "\"");
        }
      }
      if (biggestMistakeCount == 0) {
        this.printAndLog("There are no cards with errors.", false);
      } else {
        String result = keys
            .toString()
            .replace("[", "")
            .replace("]", "");
        this.printAndLog(String
                .format("The hardest card is %s. You have %d errors answering it.\n", result,
                    biggestMistakeCount),
            false);
      }
    }
  }

  public void saveLogToFile(File file) {
    try (PrintWriter writer = new PrintWriter(file)) {
      for (String line : this.log) {
        writer.println(line); //Save each entry of the log in a separate file
      }
      this.printAndLog("The log has been saved.", false);
    } catch (FileNotFoundException e) {
      this.printAndLog("Error while saving log", false);
    }
  }

  public void resetErrors() {
    this.errors.replaceAll((k, v) -> 0); // All cards now have 0 mistakes
    this.printAndLog("Card statistics has been reset.", false);
  }
}