DROP TABLE IF EXISTS MENU;


CREATE TABLE MENU (
  id integer(20) NOT NULL,
  availableEndDate date DEFAULT NULL,
  availableStartDate date DEFAULT NULL,
  description varchar(255),
  picturePath varchar(255) DEFAULT NULL,
  Category integer(20) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
);

INSERT INTO MENU VALUES (100,'2012-06-04','2012-05-28','ask your server for our daily selection with pommes frites & organic greens.',NULL,1,'Omelette Du Jour');
INSERT INTO MENU VALUES (101,'2012-06-04','2012-05-28','french ham & brie omelette with pommes frites & greens',NULL,1,'Parisian Omelette');
INSERT INTO MENU VALUES (102,'2012-06-04','2012-05-28','scrambled egg, brie with ham or bacon served on baguette with pommes frites',NULL,1,'Egg Sandwich');
INSERT INTO MENU VALUES (103,'2012-06-04','2012-05-28','7 pcs of sushi with 1 sushi roll',NULL,9,'Sushi');
INSERT INTO MENU VALUES (104,'2012-06-04','2012-05-28','7 pcs of sushi with 1 vegetable sushi roll',NULL,9,'Vegetable Sushi');
INSERT INTO MENU VALUES (105,'2012-06-04','2012-05-28','8 kinds of sashimi',NULL,9,'Sashimi');
