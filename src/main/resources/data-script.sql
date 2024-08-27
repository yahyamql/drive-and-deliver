INSERT INTO Customer (id, name) VALUES
    (1, 'Alice Johnson'),
    (2, 'Bob Smith'),
    (3, 'Charlie Brown'),
    (4, 'Diana Prince'),
    (5, 'Eve Davis'),
    (6, 'Frank Miller'),
    (7, 'Grace Lee'),
    (8, 'Hank Turner');

INSERT INTO Delivery (id, delivery_method, delivery_date_time, customer_id) VALUES
    (1, 'DRIVE', '2024-07-30 10:00:00', 1),
    (2, 'DELIVERY', '2024-07-30 12:00:00', 2),
    (3, 'DELIVERY_TODAY', '2024-07-31 09:00:00', 3),
    (4, 'DELIVERY_ASAP', '2024-07-31 15:00:00', 4),
    (5, 'DRIVE', '2024-08-01 14:00:00', 1),
    (6, 'DELIVERY', '2024-08-01 16:00:00', 5),
    (7, 'DELIVERY_TODAY', '2024-08-02 08:00:00', 6),
    (8, 'DELIVERY_ASAP', '2024-08-02 13:00:00', 7),
    (9, 'DRIVE', '2024-08-03 11:00:00', 8),
    (10, 'DELIVERY', '2024-08-03 17:00:00', 5),
    (11, 'DELIVERY_TODAY', '2024-08-04 09:30:00', 6),
    (12, 'DELIVERY_ASAP', '2024-08-04 14:30:00', 8);
