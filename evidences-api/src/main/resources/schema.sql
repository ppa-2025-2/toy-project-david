

CREATE TABLE evidences (
    id UUID PRIMARY KEY,
    content_type VARCHAR(200) NOT NULL,
    content bytea NOT NULL,
    file_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp()
);
