DROP TABLE IF EXISTS COCKTAILHASINGREDIENT;
DROP TABLE IF EXISTS COCKTAIL;
DROP TABLE IF EXISTS INGREDIENT;
DROP TABLE IF EXISTS CATEGORY;

CREATE TABLE COCKTAIL(
id INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(50),
description VARCHAR(255),
glass VARCHAR(20),
category VARCHAR(20),
alcoholic VARCHAR(20),
img_link VARCHAR(20),
PRIMARY KEY(id)
);

CREATE TABLE INGREDIENT(
id INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(50),
ing_description VARCHAR(255),
type VARCHAR(20),
PRIMARY KEY(id)
);

CREATE TABLE COCKTAILHASINGREDIENT(
cocktail_id INTEGER NOT NULL,
ingredient_id INTEGER NOT NULL,
amount VARCHAR(20),
FOREIGN KEY(cocktail_id) REFERENCES COCKTAIL(id),
FOREIGN KEY(ingredient_id) REFERENCES INGREDIENT(id)
);

CREATE TABLE CATEGORY(
id INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(50),
cat_description VARCHAR(255),
PRIMARY KEY(id)
);
