CREATE TABLE formation.user (
    id INT PRIMARY KEY,
    name VARCHAR (50) NOT NULL
);

CREATE TABLE formation.message (
    id INT PRIMARY KEY,
    content TEXT NOT NULL,
    send_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES formation.user (id)
);

CREATE TABLE tag (
    id INT PRIMARY KEY,
    name VARCHAR (50) NOT NULL
);

