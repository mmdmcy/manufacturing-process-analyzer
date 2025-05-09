CREATE TABLE Equipment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE Process (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    duration INT NOT NULL
);

CREATE TABLE Measurement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    value DECIMAL(10, 2) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    process_id INT,
    FOREIGN KEY (process_id) REFERENCES Process(id)
);