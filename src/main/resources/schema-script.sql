CREATE TABLE Customer (
                          id BIGINT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE Delivery (
                          id BIGINT PRIMARY KEY,
                          delivery_method VARCHAR(255) NOT NULL,
                          delivery_date_time TIMESTAMP NOT NULL,
                          customer_id BIGINT,
                          FOREIGN KEY (customer_id) REFERENCES Customer(id)
);