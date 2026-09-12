# Vega User Guide

Vega is a desktop chatbot that manages tasks and records short notes.

## Managing tasks

- `todo DESCRIPTION` adds a todo.
- `deadline DESCRIPTION /by yyyy-MM-dd` adds a deadline.
- `event DESCRIPTION /from START /to END` adds an event.
- `list` lists every task.
- `mark NUMBER` and `unmark NUMBER` update a task's completion status.
- `delete NUMBER` removes a task.
- `find KEYWORD` finds matching task descriptions.

## Managing notes

Notes are separate from tasks and are saved automatically between sessions.

- `note TEXT` records a new note, e.g. `note room code is 1234`.
- `notes` lists every saved note.
- `delete-note NUMBER` removes the numbered note, e.g. `delete-note 1`.

## Exiting

Enter `bye` to close Vega.
