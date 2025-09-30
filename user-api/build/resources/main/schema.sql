CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    handle VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS roles (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS profiles (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255),
    company VARCHAR(255),
    type VARCHAR(255),
    FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS tickets (
    id              INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
    criador_id      INTEGER      NOT NULL,
    responsavel_id  INTEGER,
    destinatario_id INTEGER      NOT NULL,
    objeto          VARCHAR(255) NOT NULL,
    acao            VARCHAR(255) NOT NULL,
    detalhes        VARCHAR(255),
    local           VARCHAR(255) NOT NULL,
    tickets_status  VARCHAR(255) NOT NULL DEFAULT 'PENDENTE',
    motivo          VARCHAR(255), 
    created_at      TIMESTAMP DEFAULT current_timestamp NOT NULL,
    updated_at       TIMESTAMP DEFAULT current_timestamp NOT NULL,
    FOREIGN KEY (destinatario_id) REFERENCES users(id),
    FOREIGN KEY (criador_id)      REFERENCES users(id),
    FOREIGN KEY (responsavel_id)  REFERENCES users(id)
);

-- entidade/tabela associativa
CREATE TABLE IF NOT EXISTS ticket_user (    
    user_id    INT NOT NULL,
    ticket_id  INT NOT NULL,
    PRIMARY KEY (user_id, ticket_id),
    FOREIGN KEY (user_id)   REFERENCES users(id),
    FOREIGN KEY (ticket_id) REFERENCES tickets(id)
);


