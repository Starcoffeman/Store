CREATE TABLE users
(
    id               SERIAL PRIMARY KEY,
    username         VARCHAR(50)  NOT NULL,
    name             VARCHAR(100) NOT NULL,
    surname          VARCHAR(100) NOT NULL,
    password         VARCHAR(100) NOT NULL,
    email            VARCHAR(100) NOT NULL,
    phone            VARCHAR(20)  NOT NULL,
    registrationDate DATE,
    address          VARCHAR(255)
);

CREATE TABLE category
(
    id       SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
)

CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100)   NOT NULL,
    description TEXT,
    categoryID    varchar(255)  NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    fileurl     VARCHAR(500)   NOT NULL,
    FOREIGN KEY (categoryID) REFERENCES category (id) ON DELETE CASCADE

);


CREATE TABLE orders
(
    id              SERIAL PRIMARY KEY,
    userID          INT            NOT NULL,
    orderDate       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status          VARCHAR(20)    NOT NULL,
    totalPrice      DECIMAL(10, 2) NOT NULL,
    delivery_method VARCHAR(30)    NOT NULL,
    payment_method  VARCHAR(40)    NOT NULL,
    FOREIGN KEY (userID) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE order_items
(
    id        SERIAL PRIMARY KEY,
    orderID   INT NOT NULL,
    productID INT NOT NULL,
    quantity  INT NOT NULL CHECK (quantity > 0),
    FOREIGN KEY (orderID) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (productID) REFERENCES product (id) ON DELETE CASCADE
);

CREATE TABLE payment
(
    id          SERIAL PRIMARY KEY,
    orderID     INT            NOT NULL,
    userID      INT            NOT NULL,
    amount      DECIMAL(10, 2) NOT NULL,
    paymentDate TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status      VARCHAR(20)    NOT NULL,
    FOREIGN KEY (orderID) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (userID) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE review
(
    id         SERIAL PRIMARY KEY,
    userID     INT       NOT NULL,
    productID  INT       NOT NULL,
    rating     INT CHECK (rating >= 1 AND rating <= 5),
    comment    TEXT,
    reviewDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userID) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (productID) REFERENCES product (id) ON DELETE CASCADE
);

CREATE TABLE shoppingcart
(
    id        SERIAL PRIMARY KEY,
    userID    INT NOT NULL,
    productID INT NOT NULL,
    quantity  INT NOT NULL,
    FOREIGN KEY (userID) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (productID) REFERENCES product (id) ON DELETE CASCADE
);
