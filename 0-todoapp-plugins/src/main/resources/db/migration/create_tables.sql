CREATE TABLE t_todo_list (
    id          UUID          NOT NULL,
    userId      UUID          NOT NULL,
    name        VARCHAR(255)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE t_todo (
    id              UUID          NOT NULL,
    todoListId      UUID          NOT NULL,
    todoId          UUID,
    name            VARCHAR(255)  NOT NULL,
    description     VARCHAR(255)  NOT NULL,
    due_date        DATE          NOT NULL,
    reminder_date   DATE,
    done            BOOLEAN       NOT NULL,
    creation_date   DATE          NOT NULL,
    completion_date DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (todoListId) REFERENCES t_todo_list(id)
);

CREATE TABLE t_user (
    id          UUID          NOT NULL,
    mail        VARCHAR(255)  NOT NULL,
    password    VARCHAR(255)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE t_notification (
    id          UUID          NOT NULL,
    userId      UUID          NOT NULL,
    name        VARCHAR(255)  NOT NULL,
    webHookUrl  VARCHAR(255)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES t_user(id)
);