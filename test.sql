

// 1
DELIMITER |
CREATE FUNCTION insertOeuvre(p_AuthorName VARCHAR(255), p_GenreName VARCHAR(255)) RETURNS VARCHAR(10)
BEGIN
    DECLARE myvar varchar(10);

		INSERT INTO author (name) VALUES (p_AuthorName);
		INSERT INTO author (name) VALUES (p_GenreName);
 
 RETURN ("ok");
END
