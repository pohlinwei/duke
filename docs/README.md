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

### Feature 11: Find the answer to a specified question
With this feature, you can easily get an answer to a question from your question bank easily! Just type your question and Duke will fetch the answer for you.

### Feature 12: Show all questions and answers
Do you need to review all your questions and answers? Duke can help you with that too!

### Feature 13: Remove a question and its accompanying answer
Have you just finished your test? Do you no longer need a pair of question and answer? Don't fret. Removing it is a breeze with Duke.

### Feature 14: Close application without a mouse
Close Duke with a simple command: no mouse is needed for this action!

## Usage

### `todo` - adds a to-do 

Adds a to-do task. 

Command:

```bash
todo [task to do]
```

Example of usage: 

`todo read the elegant universe`

Expected outcome:

`outcome`

### `deadline` - adds a deadline

Adds a task which has to be completed by a specified date and time.


Command:

```bash
deadline [task to do] /by [day/Month] [time]
```
where `day` and `month` are specified using numbers, and `time` is the specified time in 24 hours format.

:warning: please represent `time` with four digits, with the first 2 specifying the hours and the latter 2 specifying the minutes of the day (prepend zeros whenever necessary)

Example of usage: 

`deadline complete week 6 iP /by 19/9 1900`

Expected outcome:

`outcome`

### `event` - adds an event

Adds an event (task) that is happening on a specified date, and has a start and end time.

Command:

```bash
event [task to do or event] /at [day/Month] [start time]-[end time]
```
where `day` and `month` are specified using numbers, and `start time` and `end time` are specified in 24 hours format.

Example of usage: 

`event family dinner /at 20/9 1830-2000`

Expected outcome:

`outcome`


### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`

