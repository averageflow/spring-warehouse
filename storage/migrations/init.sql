CREATE TABLE IF NOT EXISTS articles (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR NOT NULL,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS article_stocks (
    id SERIAL PRIMARY KEY,
    article_id INT NOT NULL UNIQUE,
    stock BIGINT NOT NULL,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL,
    CONSTRAINT fk_article_stocks_article FOREIGN KEY(article_id) REFERENCES articles(id)
);

CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR NOT NULL,
    price FLOAT8 NOT NULL,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS product_articles (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    article_id INT NOT NULL,
    amount_of BIGINT NOT NULL,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL,
    CONSTRAINT fk_product_articles_product FOREIGN KEY(product_id) REFERENCES products(id),
    CONSTRAINT fk_product_articles_article FOREIGN KEY(article_id) REFERENCES articles(id)
);

CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    created_at BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS transaction_products (
    id SERIAL PRIMARY KEY,
    transaction_id INT NOT NULL,
    product_id INT NOT NULL,
    amount_of BIGINT NOT NULL,
    created_at BIGINT NOT NULL,
    CONSTRAINT fk_transaction_products_transaction FOREIGN KEY(transaction_id) REFERENCES transactions(id),
    CONSTRAINT fk_transaction_products_product FOREIGN KEY(product_id) REFERENCES products(id)
);