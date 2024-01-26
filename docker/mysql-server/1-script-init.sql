CREATE TABLE IF NOT EXISTS formation.user (
    id VARCHAR(36) PRIMARY KEY DEFAULT (uuid()),
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS formation.message (
     id VARCHAR(36) PRIMARY KEY DEFAULT (uuid()),
    content TEXT NOT NULL,
    send_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_id  VARCHAR(32) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES formation.user (id)
);

CREATE TABLE IF NOT EXISTS formation.tag (
     id VARCHAR(36) PRIMARY KEY DEFAULT (uuid()),
    name VARCHAR (50) NOT NULL
);

CREATE TABLE IF NOT EXISTS formation.tagOnMessage(
     message_id VARCHAR(36),
     tag_id VARCHAR(36),
     PRIMARY KEY (message_id, tags_id),
);

