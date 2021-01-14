# Flashcards
### JetBrains Academy
### Java project: Flashcards

About

Flashcards show a hint (a task or a picture) on one side and the right answer on the other. They are used to remember any sort of data and are useful tool for learning.

Objectives

Print the message "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):" each time the user is prompted for their next action. The action is read from the next line, processed, and the message is output again until the user decides to exit the program.
The program's behavior depends on the user's input action:

   • add — create a new flashcard with a unique term and definition. After adding the card, output the message "The pair ("term":"definition") has been added", where "term" is the term entered by the user and "definition" is the definition entered by the user. When the user tries to add a duplicate term, forbid it and output the message "The term "term" already exists. Try again:" using the term instead of "term".
      When the user tries to add a duplicate definition, forbid it and output the message "The definition "definition" already exists. Try again:" with the definition instead of "definition"
    
   • remove — ask the user for the term of the card they want to remove with the message "Which card?" and read the user's input from the next line. If a matching flashcard exists, remove it from the set and output the message "The card has been removed." If there is no such flashcard in the set, output the message "Can't remove "card": there is no such card." where "card" is the user's input.
   
   • import — print the line "File name:", read the user's input from the next line, which is the file name, and import all the flashcards written to this file. If there is no file with such name, print the message "File not found.". After importing the cards, print the message "n cards have been loaded.", where "n" is the number of cards in the file. The imported cards should be added to the ones that already exist in the memory of the program. However, the imported cards have priority: if you import a card with the name that already exists in the memory, the card from the file should overwrite the one in memory. The error count for each flashcard is also imported.
   
   • export — request the file name with the line "File name:" and write all currently available flashcards into this file. Print the message "n cards have been saved., where "n" is the number of cards exported to the file. The error count for each flashcard is also exported.
   
   • ask — ask the user about the number of cards they want to be asked about and then prompt them for definitions. When the user enters the wrong definition for the requested term, but this definition is correct for another term, print the appropriate message "Wrong answer. The correct one is "correct answer", you've just written the definition of  "term for user's answer".", where "correct answer" is the actual definition for the requested term, and "term for user's answer" is the appropriate term for the user-entered definition.
    
   • exit — print "Bye bye!" and finish the program.
   
   • log — ask the user where to save the log with the message "File name:", save all the lines that have been input/output to the console to the file, and print the message "The log has been saved.". Don't clear the log after saving it to the file.
    
   • hardest card — print a string that contains the term of the card with the highest number of wrong answers, for example: "The hardest card is "term". You have N errors answering it.". If there are several cards with the highest number of wrong answers, print all of the terms:"The hardest cards are "term_1", "term_2".". If there are no cards with errors in the user's answers, print the message "There are no cards with errors.".



Examples:

The symbol > represents the user input. Note that it's not part of the input.

Example 1: the user removes an existing card and tries to remove a non-existent one
Input the action (add, remove, import, export, ask, exit):

> add
>
The card:

> France
>
The definition of the card:

> Paris
>
The pair ("France":"Paris") has been added.

Input the action (add, remove, import, export, ask, exit):

> add
>
The card:

> France
>
The card "France" already exists.

Input the action (add, remove, import, export, ask, exit):

> add
>
The card:

> Great Britain
>
The definition of the card:

> Paris
>
The definition "Paris" already exists.

Input the action (add, remove, import, export, ask, exit):

> remove
>
Which card?

> France
>
The card has been removed.

Input the action (add, remove, import, export, ask, exit):

> remove
>
Which card?

> Wakanda
>
Can't remove "Wakanda": there is no such card.

Input the action (add, remove, import, export, ask, exit):

> exit
>
Bye bye!


Example 2:
 the user uses files to import and export their flashcards; definitions of existing cards are updated after import

Input the action (add, remove, import, export, ask, exit):
> import
>
File name:

> ghost_file.txt
>
File not found.

Input the action (add, remove, import, export, ask, exit):

> add
>
The card:

> Japan
>
The definition of the card:

> Tokyo
>
The pair ("Japan":"Tokyo") has been added.

Input the action (add, remove, import, export, ask, exit):

> add
>
The card:

> Russia
>
The definition of the card:

> UpdateMeFromFile
>
The pair ("Russia":"UpdateMeFromFile") has been added.

Input the action (add, remove, import, export, ask, exit):

> import
>
File name:

> capitals.txt
>
28 cards have been loaded.

Input the action (add, remove, import, export, ask, exit):

> ask
>
How many times to ask?

> 1
>
Print the definition of "Russia":

> Moscow
>
Correct!

Input the action (add, remove, import, export, ask, exit):

> export
>
File name:

> capitalsNew.txt
>
29 cards have been saved.

Input the action (add, remove, import, export, ask, exit):

> exit
>
Bye bye!

Example 3: the program asks for definitions several times

Input the action (add, remove, import, export, ask, exit):

> add
>
The card:

> a brother of one's parent
>
The definition of the card:

> uncle
>
The pair ("a brother of one's parent":"uncle") has been added.

Input the action (add, remove, import, export, ask, exit):

> add
>
The card:

> a part of the body where the foot and the leg meet
>
The definition of the card:

> ankle
>
The pair ("a part of the body where the foot and the leg meet":"ankle") has been added.

Input the action (add, remove, import, export, ask, exit):

> ask
>
How many times to ask?

> 6
>
Print the definition of "a brother of one's parent":

> ankle
>
Wrong. The right answer is "uncle", but your definition is correct for "a part of the body where the foot and the leg meet".

Print the definition of "a part of the body where the foot and the leg meet":
> ??
>
Wrong. The right answer is "ankle".

Print the definition of "a brother of one's parent":

> uncle
>
Correct!

Print the definition of "a part of the body where the foot and the leg meet":

> ankle
>
Correct!

Print the definition of "a brother of one's parent":

> ??
>
Wrong. The right answer is "uncle".

Print the definition of "a part of the body where the foot and the leg meet":

> uncle
>
Wrong. The right answer is "ankle", but your definition is correct for "a brother of one's parent".

Input the action (add, remove, import, export, ask, exit):

> exit
>
Bye bye!
