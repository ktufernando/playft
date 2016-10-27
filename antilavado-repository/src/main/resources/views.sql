CREATE VIEW VW_ClIENTS_LEVEL
(CLIENT_ID,
 NAMES,
 LAST_NAMES,
 BUSINESS_NAME,
 CUIT_CUIL,
 DOCUMENT_NUMBER,
 COUNTRY,
 ACTIVITY,
 MONTHLY_ESTIMATE,
 LEVEL)
AS
SELECT c.CLIENT_ID,
       c.NAMES,
       c.LAST_NAMES,
       c.BUSINESS_NAME,
       c.CUIT_CUIL,
       c.DOCUMENT_NUMBER,
       (SELECT cy.DESCRIPTION FROM COUNTRIES cy WHERE c.COUNTRY_ID = cy.COUNTRY_ID) COUNTRY,
       (SELECT af.DESCRIPTION FROM AFIP_ACTIVITIES af WHERE af.CODE = substring(c.AFIP_ACTIVITY,2)) ACTIVITY,
       (SELECT e.monthly_estimate FROM ECONOMIC_FINANCIAL_PROFILES e WHERE c.ECONOMIC_FINANCIAL_PROFILE_ID = e.ECONOMIC_FINANCIAL_PROFILE_ID) MONTHLY_ESTIMATE,
       COALESCE((SELECT f.DESCRIPTION FROM FACTOR_LEVELS f WHERE m.FACTOR_LEVEL_ID = f.FACTOR_LEVELS_ID),'FALTAN DATOS') LEVEL
FROM CLIENTS c
     LEFT JOIN MATRIX_MASTER m
ON c.CLIENT_ID = m.CLIENT_ID;

--select * from client_level x where x.level = 'FALTAN DATOS'

---------------------------------------------------------------------------------

CREATE VIEW VW_TOTAL_BY_FACTOR AS
SELECT COUNT(c.CLIENT_ID) AS TOTAL,
       f.DESCRIPTION AS FACTOR_LEVEL,
       f.COLOR AS FACTOR_COLOR
FROM ((clients c JOIN matrix_master m) JOIN factor_levels f)
WHERE ((c.CLIENT_ID = m.CLIENT_ID)
AND (m.FACTOR_LEVEL_ID = f.FACTOR_LEVELS_ID))
GROUP BY FACTOR_LEVEL,FACTOR_COLOR;

---------------------------------------------------------------------------------

CREATE VIEW VW_TOTAL_CLIENTS_BY_FACTOR_LEVELS AS
SELECT COALESCE (a.TOTAL,0) AS TOTAL,
       COALESCE (a.FACTOR_LEVEL,f.DESCRIPTION) AS FACTOR_LEVEL,
       COALESCE (a.FACTOR_COLOR,f.COLOR) AS FACTOR_COLOR
FROM (factor_levels f LEFT JOIN VW_TOTAL_BY_FACTOR a ON((f.DESCRIPTION = a.FACTOR_LEVEL)))
WHERE f.active = true
UNION
SELECT COUNT(l.CLIENT_ID) AS TOTAL,
       'FALTAN DATOS' AS FACTOR_LEVEL,
       '#0a10a9' AS FACTOR_COLOR
FROM matrix_error_logs l;