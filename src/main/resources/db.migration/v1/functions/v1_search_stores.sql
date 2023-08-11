--liquibase formatted sql
--changeSet runAlways:true splitStatements:false

DROP FUNCTION IF EXISTS search_stores(params json);
CREATE FUNCTION search_stores(params json)
    RETURNS TABLE (
        "id" uuid,
        name varchar(255),
        description varchar(255)
    )
    LANGUAGE plpgsql AS $$

DECLARE

    f_name varchar(255);

BEGIN

    f_name := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'name')::varchar(255), '');

    RETURN QUERY

        SELECT
            s.id,
            s.name,
            s.description

        FROM
            store AS s

        WHERE (

            (LENGTH(LOWER(f_name)) > 0 AND POSITION(LOWER(f_name) in LOWER(s.name)) > 0)
            OR
            (LENGTH(f_name) = 0 AND s.name = s.name)

        )
        ;
END;
$$;
