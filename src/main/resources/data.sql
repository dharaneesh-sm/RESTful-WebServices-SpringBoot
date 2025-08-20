INSERT INTO USER_DETAILS (DATE_OF_BIRTH, ID, NAME)
VALUES
    (DATE '1980-05-29', 10001, 'Tony Stark'),
    (DATE '1985-07-10', 10002, 'Steve Rogers'),
    (DATE '1990-08-15', 10003, 'Natasha Romanoff'),
    (DATE '1969-12-18', 10004, 'Bruce Banner'),
    (DATE '982-01-01', 10005, 'Thor Odinson');

INSERT INTO POST (Id, Description, User_Id)
VALUES
    (20001, 'I am Iron Man', 10001),
    (20002, 'I can do this all day', 10002),
    (20003, 'Nothing goes over my head… my reflexes are too fast.', 10003),
    (20004, 'I’m always angry.', 10004),
    (20005, 'Genius, billionaire, playboy, philanthropist.', 10001),
    (20006, 'In the end, we will remember not the words of our enemies, but the silence of our friends.', 10005);
