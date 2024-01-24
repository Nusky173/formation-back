CREATE TABLE formation.user IF NOT EXISTS (
    id INT PRIMARY KEY,
    name VARCHAR (50) NOT NULL
);

CREATE TABLE formation.message IF NOT EXISTS (
    id INT PRIMARY KEY,
    content TEXT NOT NULL,
    send_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES formation.user (id)
);

CREATE TABLE tag IF NOT EXISTS (
    id INT PRIMARY KEY,
    name VARCHAR (50) NOT NULL
);

