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
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    criador_id INT NOT NULL,
    destinatario_id INT NOT NULL,
    objeto VARCHAR(255) NOT NULL,
    acao VARCHAR(255) NOT NULL,
    detalhes VARCHAR(255) NULL,
    local VARCHAR(255) NOT NULL,
    tickets_status VARCHAR(255) DEFAULT 'PENDENTE',
    motivo VARCHAR(255) NULL, 
    created_at TIMESTAMP DEFAULT current_timestamp NOT NULL,
    update_at TIMESTAMP DEFAULT current_timestamp NOT NULL,
    FOREIGN KEY (destinatario_id) REFERENCES users(id),
    FOREIGN KEY (criador_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS observadores (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    observador_id INT NOT NULL,
    ticket_id INT NOT NULL,
    FOREIGN KEY (observador_id) REFERENCES users(id),
    FOREIGN KEY (ticket_id) REFERENCES tickets(id)
);


