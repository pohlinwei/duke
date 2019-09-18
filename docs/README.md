# User Guide

## What is Duke?
Duke is a not only your task manager but also your study companion. This desktop application allows you to easily keep track of your tasks; and quiz yourself on important concepts taught in school. Furthermore, it is optimised for users who loves to type!

## Features 
An overview of Duke's main features.

### Feature 1: Add a to-do
Do you have a to-do task which does not have a specific deadline? You can add it to duke to keep track of it!

### Feature 2: Add a deadline 
Do you have a task which has to be completed by a certain date and time? Duke can easily handle that too!

### Feature 3: Add an event
Do you need to attend or organise an event (task) which has a specified date, start time and end time? Why not add that to Duke as well for easy tracking?

### Feature 4: Mark task as complete
When you are done with a task, you can easily mark it as complete.

### Feature 5: Find a task
If you are in a hurry and need to look for tasks which contain a certain expression, Duke can do that for you!

### Feature 6: List tasks
With this feature, getting an overview of all your tasks is a breeze.

### Feature 7: Delete a task
Do you have ever changing priorities and tasks? Don't fret! You can easily delete your tasks.

### Feature 8: Store tasks in a specified file
Do you want to store your tasks in a specific file for easy exporting? Or do you want to have multiple task managers? Then, this feature is just for you!

### Feature 9: Add a question with its accompanying answer
Add a new question and answer. Then, quiz yourself or review it later!

### Feature 10: Quiz yourself
Test yourself by asking Duke to select a random question from your question bank! Do note that you must attempt to answer the question. If otherwise, Duke will be unhappy and you won't be able to access any question-and-answer features until you provide an answer.

### Feature 11: Check the answer of a specified question
With this feature, you can easily get an answer to a question from your question bank easily! Just type your question and Duke will fetch the answer for you.

### Feature 12: Show all questions and answers
Do you need to review all your questions and answers? Duke can help you with that too!

### Feature 13: Remove a question and its accompanying answer
Have you just finished your test? Do you no longer need a pair of question and answer? Don't fret. Removing it is a breeze with Duke.

### Feature 14: Close application without a mouse
Close Duke with a simple command: no mouse is needed for this action!

## Usage
This section explains the various commands you might need. Examples are also included!

### `todo` - adds a to-do 

Adds a to-do task. 

Command:

```bash
todo <task to do>
```

Example of usage: 

`todo read the elegant universe`

Expected outcome:

`outcome`

### `deadline` - adds a deadline

Adds a task which has to be completed by a specified date and time.


Command:

```bash
deadline <task to do> /by <day/Month> <time>
```
where `day` and `month` are specified using numbers, and `time` is the specified time in 24 hours format.

**Note:**
Please represent `time` with four digits, with the first 2 specifying the hours and the latter 2 specifying the minutes of the day (prepend zeros whenever necessary)

Example of usage: 

`deadline complete week 6 iP /by 19/9 1900`

Expected outcome:

`outcome`

### `event` - adds an event

Adds an event (task) that is happening on a specified date, and has a start and end time.

Command:

```bash
event <task to do or event> /at <day/Month> <start time>-<end time>
```
where `day` and `month` are specified using numbers, and `start time` and `end time` are specified in 24 hours format.

**Note:**
Please represent `start time` and `end time` with four digits, with the first 2 specifying the hours and the latter 2 specifying the minutes of the day (prepend zeros whenever necessary)


Example of usage: 

`event family dinner /at 20/9 1830-2000`

Expected outcome:

`outcome`


### `done` - marks a task as done

Marks your `index`th task in the task manager as done.

Command:
```bash
done <index>
```

Example of usage: 

`done 1`

Expected outcome:

`outcome`

### `find` - finds tasks 

Finds and returns any tasks which contain `expression`. The search process is not case-sensitive.

Command:
```bash
find <expression>
```

Example of usage: 

`find ip`

Expected outcome:

`outcome`

### `list` - shows all tasks

Shows all tasks in your task manager.

Command:
```bash
list
```

Example of usage: 

`list`

Expected outcome:

`outcome`

### `delete` - deletes a task

Deletes your `index`th task.

Command:
```bash
delete <index>
```

Example of usage: 

`delete 1`

Expected outcome:

`outcome`

### `store` - changes the file that is used to store your tasks locally

Uses the `specified file` for storing your tasks locally, i.e. tasks will not be stored in the default file. In addition, all tasks from the default file will no longer be accessible by Duke. However, when the command is run, Duke will load any of your tasks that are found in the `specified file`.

Command:
```bash
store <specified file>
```

Example of usage: 

`store taskData.txt`

Expected outcome:

`outcome`

### `new` - adds a question with its accompanying answer

Adds a new `question` and `answer`. In the future, you may refer to it.

Command:
```bash
new <question> /ans <answer>
```

Example of usage: 

`new where is Singapore? /ans South East Asia`

Expected outcome:

`outcome`

### `ask` - retrieves a question

Retrieves a question which you are expected to answer. It is great if you get it right but it is okay to make mistakes! We are all here to learn (:

Command:
```bash
ask
```

Example of usage: 

`ask`

Expected outcome:

`outcome`

### `answer` - answer a question

To answer a question Duke has retrieved, please use this command. Note that the check is not case-sensitive.
(Refer to the command before this to find out how to get Duke to ask you a question) 

Command:
```bash
answer <your answer>
```

Example of usage: 

`answer asia`

Expected outcome:

`outcome`

### `check` - checks the answer to a specified question

Checks the answer to your `question`.

Command:
```bash
check <question>
```

Example of usage: 

`check where is singapore?`

Expected outcome:

`outcome`

### `show` - shows all questions and their corresponding answers

Shows all your questions and answers.

Command:
```bash
show
```

Example of usage: 

`show`

Expected outcome:

`outcome`

### `remove` - removes a specified question and answer

Removes your `index`th question and answer.

Command:
```bash
remove <index>
```

Example of usage: 

`remove 1`

Expected outcome:

`outcome`

### `bye` - closes the application

Closes the application and the application's window for you.

Command: 

```bash
bye
```

Expected outcome:

`outcome`

## Glossary
This section explains the command terms used.

#### task
This refers to any to-do, deadline or event items.

#### local(ly)
This refers to files that are stored on your PC or Mac. 
