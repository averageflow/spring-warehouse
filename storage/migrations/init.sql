CREATE TABLE IF NOT EXISTS articles (
    uid UUID PRIMARY KEY,
    item_id INT NOT NULL UNIQUE,
    item_name VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS article_stocks (
    uid UUID PRIMARY KEY,
    article_id INT NOT NULL UNIQUE,
    stock BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_article_stocks_article FOREIGN KEY(article_id) REFERENCES articles(item_id)
);

CREATE TABLE IF NOT EXISTS products (
    uid UUID PRIMARY KEY,
    item_id INT NOT NULL UNIQUE,
    item_name VARCHAR NOT NULL,
    price FLOAT8 NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS product_articles (
    uid UUID PRIMARY KEY,
    product_id INT NOT NULL,
    article_id INT NOT NULL,
    amount_of BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_product_articles_product FOREIGN KEY(product_id) REFERENCES products(item_id),
    CONSTRAINT fk_product_articles_article FOREIGN KEY(article_id) REFERENCES articles(item_id)
);

CREATE TABLE IF NOT EXISTS transactions (
    uid UUID PRIMARY KEY,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS transaction_products (
    uid UUID PRIMARY KEY,
    transaction_uid UUID NOT NULL,
    product_id INT NOT NULL,
    amount_of BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_transaction_products_transaction FOREIGN KEY(transaction_uid) REFERENCES transactions(uid),
    CONSTRAINT fk_transaction_products_product FOREIGN KEY(product_id) REFERENCES products(item_id)
);