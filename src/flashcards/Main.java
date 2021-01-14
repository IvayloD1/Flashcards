package flashcards;

import java.io.File;
import java.util.Scanner;

public class Main {

  public static boolean executeCommand(Flashcards set, String command, Scanner in) {
    boolean exit = false;
    set.printAndLog(command, true);
    switch (command) {
      case "add" -> set.add();
      case "remove" -> set.remove();
      case "import" -> {
        set.printAndLog("File name:", false);
        String fileName = in.nextLine(); //Ask the user for a filename
        set.printAndLog(fileName, true);
        File file = new File(fileName); //Create a File object to pass to the set method
        set.importSetFromFile(file);
      }
      case "export" -> {
        set.printAndLog("File Name:", false);
        String name = in.nextLine();
        set.printAndLog(name, true);
        File writingFile = new File(name);
        set.exportSetToFile(writingFile);
      }
      case "ask" -> {
        set.printAndLog("How many times to ask?", false);
        int times = Integer.parseInt(
            in.nextLine());
        set.printAndLog(String.valueOf(times), true);
        for (int i = 0; i < times; i++) {
          set.ask();
        }
      }
      case "exit" -> {
        set.printAndLog("Bye bye!", false);
        exit = true;
      }
      case "hardest card" -> set.hardestCard();
      case "log" -> {
        set.printAndLog("File Name:", false);
        String logName = in.nextLine();
        set.printAndLog(logName, true);
        File logFile = new File(logName);
        set.saveLogToFile(logFile);
      }
      case "reset stats" -> set.resetErrors();
      default -> set
          .printAndLog("Command not valid", false);
    }
    return exit;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Flashcards set = new Flashcards();
    boolean exit = false;
    while (!exit) {
      set.printAndLog(
          "Input the action (add, remove, import, export, ask, log, hardest card, reset stats, exit):",
          false);
      String a = in.nextLine();
      exit = executeCommand(set, a, in); //Runs  until exit option is chosen
    }
  }
}