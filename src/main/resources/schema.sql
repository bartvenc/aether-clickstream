CREATE TABLE IF NOT EXISTS clicks (
    id SERIAL PRIMARY KEY,
    item_id BIGINT NOT NULL,
    item_type VARCHAR(50) NOT NULL,
    timestamp BIGINT NOT NULL
);
