--------------------------------------------------------------------------------
-- Step 1: Generate Flat Property Table
--------------------------------------------------------------------------------
create table PIM_STAGE.TEMP_RESISTANCE NOLOGGING as
  select  ar."ID" as ID,
  ar."Identifier" as PART_ID --ItemNo/VariantNo/ProductNo
  , attrlang."Name" as ATTRIBUTE_NM
  , attrval."Value" as ATTRIBUTE_VALUE_TX
  , ul."Symbol" as ATTRIBUTE_UOM_CD
  , ul."Name"  as ATTRIBUTE_UOM_NM
  ,DECODE( "EntityID", 1000, 'Items',1100, 'Products', 1200, 'Variants') as ARTICLE_TYPE_CD
  ,DECODE( ad."Res_Int_01", 1, 'Supplier Item', 2, 'ERP Item', 3, 'URL Asset')  as ITEM_TYPE_CD
  , ad."Res_Int_01" as PART_NO
  from hpm_master."ArticleRevision" ar
  ,hpm_master."ArticleAttribute" attr
  ,hpm_master."ArticleDetail" ad
  ,hpm_master."ArticleAttributeLang" attrlang
  ,hpm_master."ArticleAttributeValue" attrval
  ,HPM_MAIN."UnitLang" ul
  where ar."ID" = attr."ArticleRevisionID"
  and ar.ID = ad."ArticleRevisionID"
  --and  ar."Identifier" = '101005128'
  and attr.ID = attrlang."ArticleAttributeID"
  and attrlang."Name" = 'Resistance Value'  --Put the Attribute Name here
  and attrlang."ArticleAttributeID" = attrval."ArticleAttributeID"
  and attrval."LanguageID" = 9 --English (Everything is English for the moment)
  and attr."UnitID" = ul."UnitID"
  and ul."LanguageID" = 9
  ;
--------------------------------------------------------------------------------
-- Step 2: Generate RESISTANCE Table
-- Identify Errors
-- Identify Records with Multi Values or Multi UOM
--------------------------------------------------------------------------------
--Variants
--Variant No|Attribute Name|Data Type|Value Identifier|Value|Unit
--Products
--Product No|Attribute Name|Data Type|Value Identifier|Value|Unit
--  Use the following identifiers  
--  DEFAULT – Goes as the display value to WCS
--  MIN – Minimum value.
--  MAX – Maximum value
--  RANGEDEFAULT – Normalized value column if the it is special formating. If not the DEFAULT value will go
--  VALUE1, VALUE2, VALUE3… - Multple values that need to be stored.  WCS doesn’t need this separately. It is up to us if we need to load.
--For the Part, Attribute Name combo, the Data type and Unit should be same.  Different parts can have different units for the same attribute.
--------------------------------------------------------------------------------
create table PIM_STAGE.TEMP_RESISTANCEB NOLOGGING as
-- Normal Single UOM
select v.PART_ID as VARIANT_NO, v.ATTRIBUTE_NM,
       'Character String' as DATA_TYPE_CD, 'DEFAULT' as VALUE_IDENTIFIER_CD, v.ATTRIBUTE_VALUE_TX as VALUE_AM, v.ATTRIBUTE_UOM_CD as UNIT_CD,
       DECODE(v.ATTRIBUTE_UOM_CD, 'MOhm', NULL, 'mOhm', NULL, 'Ohm', NULL, 'GOhm', NULL,'kOhm', NULL, 'ERROR: UOM is ' ||v.ATTRIBUTE_UOM_CD ) as ERROR_TX,
       DECODE(v.ATTRIBUTE_UOM_CD, 'MOhm', NULL, 'mOhm', NULL, 'Ohm', NULL, 'GOhm', NULL,'kOhm', NULL, 'X' ) as ERROR_FL,
       NULL as MULTI_FL,
       NULL as MULT_UOM_FL
       ,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCE v
where NOT REGEXP_LIKE(v.ATTRIBUTE_VALUE_TX, '[^0-9.]+')
and instr(v.ATTRIBUTE_UOM_CD,'|') = 0
UNION
-- Multi Value, Single UOM
select v.PART_ID as VARIANT_NO, v.ATTRIBUTE_NM,
       'Character String' as DATA_TYPE_CD, 'DEFAULT' as VALUE_IDENTIFIER_CD, 
       REPLACE(REPLACE(v.ATTRIBUTE_VALUE_TX, '/', '|'), ',', '') as VALUE_AM, 
       v.ATTRIBUTE_UOM_CD as UNIT_CD,
       DECODE(v.ATTRIBUTE_UOM_CD, 'MOhm', NULL, 'mOhm', NULL, 'Ohm', NULL, 'GOhm', NULL,'kOhm', NULL, 'ERROR: UOM is ' ||v.ATTRIBUTE_UOM_CD ) as ERROR_TX,
       DECODE(v.ATTRIBUTE_UOM_CD, 'MOhm', NULL, 'mOhm', NULL, 'Ohm', NULL, 'GOhm', NULL,'kOhm', NULL, 'X' ) as ERROR_FL,
       'X' as MULTI_FL,
       NULL as MULT_UOM_FL
       ,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCE v
where instr(v.ATTRIBUTE_UOM_CD,'|') = 0
and instr(v.ATTRIBUTE_UOM_CD,'/') = 0
and REGEXP_LIKE(v.ATTRIBUTE_VALUE_TX, '[^0-9.]+')
UNION
-- Multiple UOM -- All would error
select v.PART_ID as VARIANT_NO, v.ATTRIBUTE_NM,
       'Character String' as DATA_TYPE_CD, 'DEFAULT' as VALUE_IDENTIFIER_CD, 
       REPLACE(REPLACE(v.ATTRIBUTE_VALUE_TX, '/', '|'), ',', '') as VALUE_AM, 
       v.ATTRIBUTE_UOM_CD as UNIT_CD,
       DECODE(v.ATTRIBUTE_UOM_CD, 'MOhm', NULL, 'mOhm', NULL, 'Ohm', NULL, 'GOhm', NULL,'kOhm', NULL, 'ERROR: UOM is ' ||v.ATTRIBUTE_UOM_CD ) as ERROR_TX,
       DECODE(v.ATTRIBUTE_UOM_CD, 'MOhm', NULL, 'mOhm', NULL, 'Ohm', NULL, 'GOhm', NULL,'kOhm', NULL, 'X' ) as ERROR_FL,
       NULL as MULTI_FL,
       'X' as MULT_UOM_FL
       ,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCE v
where (instr(v.ATTRIBUTE_UOM_CD,'|') > 0
or instr(v.ATTRIBUTE_UOM_CD,'/') > 0)
and REGEXP_LIKE(v.ATTRIBUTE_VALUE_TX, '[^0-9.]+')
UNION
-- Just Bad records
select v.PART_ID as VARIANT_NO, v.ATTRIBUTE_NM,
       'Character String' as DATA_TYPE_CD, 'DEFAULT' as VALUE_IDENTIFIER_CD, v.ATTRIBUTE_VALUE_TX as VALUE_AM, v.ATTRIBUTE_UOM_CD as UNIT_CD,
       DECODE(v.ATTRIBUTE_UOM_CD, 'MOhm', NULL, 'mOhm', NULL, 'Ohm', NULL, 'GOhm', NULL,'kOhm', NULL, 'ERROR: UOM is ' ||v.ATTRIBUTE_UOM_CD ) as ERROR_TX,
       DECODE(v.ATTRIBUTE_UOM_CD, 'MOhm', NULL, 'mOhm', NULL, 'Ohm', NULL, 'GOhm', NULL,'kOhm', NULL, 'X' ) as ERROR_FL,
       NULL as MULTI_FL,
       NULL as MULT_UOM_FL
       ,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCE v
where (instr(v.ATTRIBUTE_UOM_CD,'|') > 0
or instr(v.ATTRIBUTE_UOM_CD,'/') > 0)
and not REGEXP_LIKE(v.ATTRIBUTE_VALUE_TX, '[^0-9.]+')
;
--------------------------------------------------------------------------------
-- Step 3: Split Multi
--------------------------------------------------------------------------------
create index PIM_STAGE.TEMP_RESISTANCEB_IE01 on PIM_STAGE.TEMP_RESISTANCEB(VARIANT_NO);
create index PIM_STAGE.TEMP_RESISTANCEB_IE02 on PIM_STAGE.TEMP_RESISTANCEB(VALUE_AM);

create table PIM_STAGE.TEMP_RESISTANCEC NOLOGGING as
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'VALUE1' as VALUE_IDENTIFIER_CD, 
REGEXP_SUBSTR(v.VALUE_AM, '[^|]+', 1, 1) as VALUE_AM, v.UNIT_CD, v.ERROR_TX, v.ERROR_FL, v.MULTI_FL,
v.MULT_UOM_FL, REGEXP_COUNT(v.VALUE_AM, '[^|]+') as VALUE_QT
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEB v
where v.MULTI_FL = 'X'
and v.ERROR_FL is null
--and v.VARIANT_NO = '101017913'
and REGEXP_COUNT(v.VALUE_AM, '[^|]+') >= 1
UNION
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'VALUE2' as VALUE_IDENTIFIER_CD, 
REGEXP_SUBSTR(v.VALUE_AM, '[^|]+', 1, 2) as VALUE_AM, v.UNIT_CD, v.ERROR_TX, v.ERROR_FL, v.MULTI_FL,
v.MULT_UOM_FL, REGEXP_COUNT(v.VALUE_AM, '[^|]+') as VALUE_QT
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEB v
where v.MULTI_FL = 'X'
and v.ERROR_FL is null
--and v.VARIANT_NO = '101234594'
and REGEXP_COUNT(v.VALUE_AM, '[^|]+') >= 2
UNION
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'VALUE3' as VALUE_IDENTIFIER_CD, 
REGEXP_SUBSTR(v.VALUE_AM, '[^|]+', 1, 3) as VALUE_AM, v.UNIT_CD, v.ERROR_TX, v.ERROR_FL, v.MULTI_FL,
v.MULT_UOM_FL, REGEXP_COUNT(v.VALUE_AM, '[^|]+') as VALUE_QT
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEB v
where v.MULTI_FL = 'X'
and v.ERROR_FL is null
--and v.VARIANT_NO = '101234594'
and REGEXP_COUNT(v.VALUE_AM, '[^|]+') >= 3
UNION
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'VALUE4' as VALUE_IDENTIFIER_CD, 
REGEXP_SUBSTR(v.VALUE_AM, '[^|]+', 1, 4) as VALUE_AM, v.UNIT_CD, v.ERROR_TX, v.ERROR_FL, v.MULTI_FL,
v.MULT_UOM_FL, REGEXP_COUNT(v.VALUE_AM, '[^|]+') as VALUE_QT
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEB v
where v.MULTI_FL = 'X'
and v.ERROR_FL is null
--and v.VARIANT_NO = '101234594'
and REGEXP_COUNT(v.VALUE_AM, '[^|]+') >= 4
UNION
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'VALUE5' as VALUE_IDENTIFIER_CD, 
REGEXP_SUBSTR(v.VALUE_AM, '[^|]+', 1, 5) as VALUE_AM, v.UNIT_CD, v.ERROR_TX, v.ERROR_FL, v.MULTI_FL,
v.MULT_UOM_FL, REGEXP_COUNT(v.VALUE_AM, '[^|]+') as VALUE_QT
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEB v
where v.MULTI_FL = 'X'
and v.ERROR_FL is null
--and v.VARIANT_NO = '101234594'
and REGEXP_COUNT(v.VALUE_AM, '[^|]+') >= 5
UNION
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'VALUE6' as VALUE_IDENTIFIER_CD, 
REGEXP_SUBSTR(v.VALUE_AM, '[^|]+', 1, 6) as VALUE_AM, v.UNIT_CD, v.ERROR_TX, v.ERROR_FL, v.MULTI_FL,
v.MULT_UOM_FL, REGEXP_COUNT(v.VALUE_AM, '[^|]+') as VALUE_QT
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEB v
where v.MULTI_FL = 'X'
and v.ERROR_FL is null
--and v.VARIANT_NO = '101234594'
and REGEXP_COUNT(v.VALUE_AM, '[^|]+') >= 6
UNION
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'VALUE7' as VALUE_IDENTIFIER_CD, 
REGEXP_SUBSTR(v.VALUE_AM, '[^|]+', 1, 7) as VALUE_AM, v.UNIT_CD, v.ERROR_TX, v.ERROR_FL, v.MULTI_FL,
v.MULT_UOM_FL, REGEXP_COUNT(v.VALUE_AM, '[^|]+') as VALUE_QT
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEB v
where v.MULTI_FL = 'X'
and v.ERROR_FL is null
--and v.VARIANT_NO = '101234594'
and REGEXP_COUNT(v.VALUE_AM, '[^|]+') >= 7
UNION
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'VALUE8' as VALUE_IDENTIFIER_CD, 
REGEXP_SUBSTR(v.VALUE_AM, '[^|]+', 1, 8) as VALUE_AM, v.UNIT_CD, v.ERROR_TX, v.ERROR_FL, v.MULTI_FL,
v.MULT_UOM_FL, REGEXP_COUNT(v.VALUE_AM, '[^|]+') as VALUE_QT
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEB v
where v.MULTI_FL = 'X'
and v.ERROR_FL is null
--and v.VARIANT_NO = '101234594'
and REGEXP_COUNT(v.VALUE_AM, '[^|]+') >= 8
;
--------------------------------------------------------------------------------
-- Step 4: Split Multi UOM 

--------------------------------------------------------------------------------
-- Resistance does not have any records with Multiple UOM Codes
--------------------------------------------------------------------------------
-- Step 6: Cleaned Set
--------------------------------------------------------------------------------
create table PIM_STAGE.TEMP_RESISTANCEE NOLOGGING as
-- Normal
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, v.VALUE_IDENTIFIER_CD, v.VALUE_AM, v.UNIT_CD
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEB v
where v.ERROR_FL is NULL
and v.MULT_UOM_FL is NULL
and v.MULTI_FL is NULL
UNION
--Multi Value
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, v.VALUE_IDENTIFIER_CD, v.VALUE_AM, v.UNIT_CD
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEC v
;
--------------------------------------------------------------------------------
-- Step 7: Determine Min
--------------------------------------------------------------------------------
select count(*) 
from PIM_STAGE.TEMP_RESISTANCEB v; -- 763,402

--Check for invalid characters that might have slipped through
--And insert Error records for the report to clean up
insert into PIM_STAGE.TEMP_RESISTANCEB 
(VARIANT_NO, ATTRIBUTE_NM, DATA_TYPE_CD, VALUE_IDENTIFIER_CD, VALUE_AM, UNIT_CD, ERROR_TX, ERROR_FL, MULTI_FL, MULT_UOM_FL, ARTICLE_TYPE_CD)
select v.PART_ID as VARIANT_NO, v.ATTRIBUTE_NM,
       'Character String' as DATA_TYPE_CD, 'DEFAULT' as VALUE_IDENTIFIER_CD, v.ATTRIBUTE_VALUE_TX as VALUE_AM, v.ATTRIBUTE_UOM_CD as UNIT_CD,
       'ERROR: Actual Value is Invalid - ' ||v.ATTRIBUTE_VALUE_TX  as ERROR_TX,
       'X' as ERROR_FL,
       NULL as MULTI_FL,
       NULL as MULT_UOM_FL
       ,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCE v,
PIM_STAGE.TEMP_RESISTANCEE v2
where REGEXP_LIKE(v2.VALUE_AM, '[^0-9|.]+')
and v.PART_ID = v2.VARIANT_NO
and v2.VALUE_IDENTIFIER_CD = 'VALUE1'
;
commit;
-- Delete the positive records that made it through
DELETE PIM_STAGE.TEMP_RESISTANCEB X
where x.ERROR_FL is NULL
and X.VARIANT_NO in (
      select v.PART_ID as VARIANT_NO
      from PIM_STAGE.TEMP_RESISTANCE v,
      PIM_STAGE.TEMP_RESISTANCEE v2
      where REGEXP_LIKE(v2.VALUE_AM, '[^0-9|.]+')
      and v.PART_ID = v2.VARIANT_NO
      and v2.VALUE_IDENTIFIER_CD = 'VALUE1'
);
commit;
--Delete this from the cleaned up data or conversion will fail
DELETE PIM_STAGE.TEMP_RESISTANCEE X
where X.VARIANT_NO in (
      select v.PART_ID as VARIANT_NO
      from PIM_STAGE.TEMP_RESISTANCE v,
      PIM_STAGE.TEMP_RESISTANCEE v2
      where REGEXP_LIKE(v2.VALUE_AM, '[^0-9|.]+')
      and v.PART_ID = v2.VARIANT_NO
      and v2.VALUE_IDENTIFIER_CD = 'VALUE1'
);
commit;

create table PIM_STAGE.TEMP_RESISTANCE_MIN NOLOGGING as
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'MIN' as VALUE_IDENTIFIER_CD, MIN(TO_NUMBER(v.VALUE_AM)) as VALUE_AM, v.UNIT_CD
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEE v
--where v.VARIANT_NO = '43818353'
group by v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, v.UNIT_CD
,v.ARTICLE_TYPE_CD
;

select count(*) 
from PIM_STAGE.TEMP_RESISTANCE_MIN v; -- 761,868
--------------------------------------------------------------------------------
-- Step 8: Determine Max
--------------------------------------------------------------------------------
create table PIM_STAGE.TEMP_RESISTANCE_MAX NOLOGGING as
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'MAX' as VALUE_IDENTIFIER_CD, MAX(TO_NUMBER(v.VALUE_AM))  as VALUE_AM, v.UNIT_CD
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEE v
--where v.VARIANT_NO = '43818353'
group by v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, v.UNIT_CD
,v.ARTICLE_TYPE_CD
;

select count(*) 
from PIM_STAGE.TEMP_RESISTANCE_MAX v; -- 426,284
--------------------------------------------------------------------------------
-- Step 9: Cleaned Set
--------------------------------------------------------------------------------
create table PIM_STAGE.TEMP_RESISTANCEF NOLOGGING as
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, v.VALUE_IDENTIFIER_CD, TO_CHAR(v.VALUE_AM) as VALUE_AM, v.UNIT_CD ,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEE v
UNION 
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, v.VALUE_IDENTIFIER_CD, TO_CHAR(v.VALUE_AM) as VALUE_AM, v.UNIT_CD, v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCE_MIN v
UNION
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, v.VALUE_IDENTIFIER_CD, TO_CHAR(v.VALUE_AM) as VALUE_AM, v.UNIT_CD, v.ARTICLE_TYPE_CD 
from PIM_STAGE.TEMP_RESISTANCE_MAX v
;

select *
from PIM_STAGE.TEMP_RESISTANCEF v
where v.VARIANT_NO = '43818353';
--------------------------------------------------------------------------------
-- Step 10: Fix Large UOM Values
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--Conversion Rate Chart
--mOhm	0.001
--Ohm	  1
--kOhm	1,000
--MOhm	1,000,000
--GOhm	1,000,000,000
--------------------------------------------------------------------------------
-- Increase UOM by on tier if value is at least 1K
update PIM_STAGE.TEMP_RESISTANCEF v
set v.UNIT_CD = Decode(v.UNIT_CD, 'mOhm','Ohm','Ohm','kOhm','kOhm','MOhm','MOhm','GOhm','GOhm','HOLD')
where v.VALUE_AM >= 1000
and v.UNIT_CD <> 'GOhm'
;
commit;
-- Update the Value Amount. 
-- HOLD is to prevent Dividing records that were already in GOhm, we still want to do the math for records that were MOhm
update PIM_STAGE.TEMP_RESISTANCEF v
set v.VALUE_AM = v.VALUE_AM/1000
where v.VALUE_AM >= 1000
and v.UNIT_CD <> 'HOLD'
;
commit;
-- Set HOLD UOM back to GOhm as a valid UOM. 
--This will allow only GOhm values to be over 1K as we do not have a higher UOM configured at time of this script
update PIM_STAGE.TEMP_RESISTANCEF v
set v.UNIT_CD = 'GOhm'
where v.UNIT_CD = 'HOLD'
;
commit;

--------------------------------------------------------------------------------
-- Step 11: Fix Small UOM Values
--------------------------------------------------------------------------------
--We have no issues with decimals but I did notice 0's which I will throw in a report.
-- Check one example (All we had in production)
--select *
--from PIM_STAGE.TEMP_RESISTANCEF v
--where v.VALUE_AM <= .0001
--and v.VALUE_AM <> 0
--;

--------------------------------------------------------------------------------
-- Insert Default values for Multi Values
--------------------------------------------------------------------------------
insert into PIM_STAGE.TEMP_RESISTANCEF 
(VARIANT_NO, ATTRIBUTE_NM, DATA_TYPE_CD, VALUE_IDENTIFIER_CD, VALUE_AM, UNIT_CD, ARTICLE_TYPE_CD)
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'DEFAULT' as VALUE_IDENTIFIER_CD, 
--v.VALUE_AM, X.VALUE_AM, Y.VALUE_AM,
DECODE(E.VALUE_AM, NULL, 
    DECODE(D.VALUE_AM, NULL, 
        DECODE(C.VALUE_AM, NULL, 
            DECODE(B.VALUE_AM, NULL, 
                DECODE(A.VALUE_AM, NULL, 
                    DECODE(Y.VALUE_AM, NULL, 
                        DECODE(X.VALUE_AM, NULL, v.VALUE_AM, v.VALUE_AM || ', ' || X.VALUE_AM)
                    , v.VALUE_AM || ', ' || X.VALUE_AM || ', ' || Y.VALUE_AM)
                , v.VALUE_AM || ', ' || X.VALUE_AM || ', ' || Y.VALUE_AM || ', ' || A.VALUE_AM)
            , v.VALUE_AM || ', ' || X.VALUE_AM || ', ' || Y.VALUE_AM || ', ' || A.VALUE_AM || ', ' || B.VALUE_AM)
        , v.VALUE_AM || ', ' || X.VALUE_AM || ', ' || Y.VALUE_AM || ', ' || A.VALUE_AM || ', ' || B.VALUE_AM || ', ' || C.VALUE_AM)
    , v.VALUE_AM || ', ' || X.VALUE_AM || ', ' || Y.VALUE_AM || ', ' || A.VALUE_AM || ', ' || B.VALUE_AM || ', ' || C.VALUE_AM || ', ' || D.VALUE_AM)
, v.VALUE_AM || ', ' || X.VALUE_AM || ', ' || Y.VALUE_AM || ', ' || A.VALUE_AM || ', ' || B.VALUE_AM || ', ' || C.VALUE_AM || ', ' || D.VALUE_AM || ', ' || E.VALUE_AM) as VALUE_AM, v.UNIT_CD
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEF v,
  (select v2.VARIANT_NO, v2.VALUE_AM, v2.UNIT_CD
  from PIM_STAGE.TEMP_RESISTANCEF v2
  where v2.VALUE_IDENTIFIER_CD = 'VALUE2') X,
    (select v3.VARIANT_NO, v3.VALUE_AM, v3.UNIT_CD
  from PIM_STAGE.TEMP_RESISTANCEF v3
  where v3.VALUE_IDENTIFIER_CD = 'VALUE3') Y,
    (select v4.VARIANT_NO, v4.VALUE_AM, v4.UNIT_CD
  from PIM_STAGE.TEMP_RESISTANCEF v4
  where v4.VALUE_IDENTIFIER_CD = 'VALUE4') A,
    (select v5.VARIANT_NO, v5.VALUE_AM, v5.UNIT_CD
  from PIM_STAGE.TEMP_RESISTANCEF v5
  where v5.VALUE_IDENTIFIER_CD = 'VALUE5') B,
    (select v6.VARIANT_NO, v6.VALUE_AM, v6.UNIT_CD
  from PIM_STAGE.TEMP_RESISTANCEF v6
  where v6.VALUE_IDENTIFIER_CD = 'VALUE6') C,
    (select v7.VARIANT_NO, v7.VALUE_AM, v7.UNIT_CD
  from PIM_STAGE.TEMP_RESISTANCEF v7
  where v7.VALUE_IDENTIFIER_CD = 'VALUE7') D,
    (select v8.VARIANT_NO, v8.VALUE_AM, v8.UNIT_CD
  from PIM_STAGE.TEMP_RESISTANCEF v8
  where v8.VALUE_IDENTIFIER_CD = 'VALUE8') E
where v.VALUE_IDENTIFIER_CD = 'VALUE1'
and v.VARIANT_NO = X.VARIANT_NO(+)
and v.VARIANT_NO = Y.VARIANT_NO(+)
and v.VARIANT_NO = A.VARIANT_NO(+)
and v.VARIANT_NO = B.VARIANT_NO(+)
and v.VARIANT_NO = C.VARIANT_NO(+)
and v.VARIANT_NO = D.VARIANT_NO(+)
and v.VARIANT_NO = E.VARIANT_NO(+)
--and v.VARIANT_NO = '50064963'
;
commit;

select *
from PIM_STAGE.TEMP_RESISTANCEF v
where v.VARIANT_NO = '43818353'
;
--------------------------------------------------------------------------------
-- Insert VALUE1 records for Single Values
--------------------------------------------------------------------------------
insert into PIM_STAGE.TEMP_RESISTANCEF 
(VARIANT_NO, ATTRIBUTE_NM, DATA_TYPE_CD, VALUE_IDENTIFIER_CD, VALUE_AM, UNIT_CD, ARTICLE_TYPE_CD)
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'VALUE1' as VALUE_IDENTIFIER_CD, 
v.VALUE_AM, v.UNIT_CD
,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEF v
where v.VALUE_IDENTIFIER_CD = 'DEFAULT'
and instr(v.VALUE_AM, ',') = 0
--and v.VARIANT_NO = '100010082465'
and v.VARIANT_NO not in (
select v.VARIANT_NO
from PIM_STAGE.TEMP_RESISTANCEF v
where v.VALUE_IDENTIFIER_CD = 'VALUE1')
;
commit;
--------------------------------------------------------------------------------
-- Add Ranges Back In
--------------------------------------------------------------------------------
insert into PIM_STAGE.TEMP_RESISTANCEF 
(VARIANT_NO, ATTRIBUTE_NM, DATA_TYPE_CD, VALUE_IDENTIFIER_CD, VALUE_AM, UNIT_CD, ARTICLE_TYPE_CD)
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'DEFAULT' as VALUE_IDENTIFIER_CD, v.VALUE_AM, v.UNIT_CD ,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEB v
where v.VALUE_IDENTIFIER_CD = 'DEFAULT'
and instr(v.VALUE_AM, ',') = 0
and v.VARIANT_NO in (
select v.VARIANT_NO
from PIM_STAGE.TEMP_RESISTANCEB v
where v.ERROR_FL = 'X'
and v.ERROR_TX like 'ERROR: Actual Value is Invalid%')
UNION
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'MIN' as VALUE_IDENTIFIER_CD, REGEXP_SUBSTR(v.VALUE_AM, '[^ to ]+', 1, 1) as VALUE_AM, v.UNIT_CD ,v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEB v
where v.VALUE_IDENTIFIER_CD = 'DEFAULT'
and instr(v.VALUE_AM, ',') = 0
and v.VARIANT_NO in (
select v.VARIANT_NO
from PIM_STAGE.TEMP_RESISTANCEB v
where v.ERROR_FL = 'X'
and v.ERROR_TX like 'ERROR: Actual Value is Invalid%')
UNION
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, 'MAX' as VALUE_IDENTIFIER_CD, REGEXP_SUBSTR(v.VALUE_AM, '[^ to ]+', 1, 2) as VALUE_AM, v.UNIT_CD, v.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCEB v
where v.VALUE_IDENTIFIER_CD = 'DEFAULT'
and instr(v.VALUE_AM, ',') = 0
and v.VARIANT_NO in (
select v.VARIANT_NO
from PIM_STAGE.TEMP_RESISTANCEB v
where v.ERROR_FL = 'X'
and v.ERROR_TX like 'ERROR: Actual Value is Invalid%')
;
commit;

-- Remove error records
DELETE PIM_STAGE.TEMP_RESISTANCEB v
where v.ERROR_FL = 'X'
and v.ERROR_TX like 'ERROR: Actual Value is Invalid%'
and v.VARIANT_NO in (
select v.VARIANT_NO
from PIM_STAGE.TEMP_RESISTANCEB v
where v.ERROR_FL = 'X'
and v.ERROR_TX like 'ERROR: Actual Value is Invalid%')
;
commit;
--------------------------------------------------------------------------------
-- Export this to a file
--------------------------------------------------------------------------------
select v.VARIANT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, v.VALUE_IDENTIFIER_CD, v.VALUE_AM, v.UNIT_CD
from PIM_STAGE.TEMP_RESISTANCEF v
where v.ARTICLE_TYPE_CD = 'Variants'
--and v.VARIANT_NO = '101199951'
order by v.VARIANT_NO, v.ATTRIBUTE_NM, v.VALUE_IDENTIFIER_CD
;
select v.VARIANT_NO as PRODUCT_NO, v.ATTRIBUTE_NM, v.DATA_TYPE_CD, v.VALUE_IDENTIFIER_CD, v.VALUE_AM, v.UNIT_CD
from PIM_STAGE.TEMP_RESISTANCEF v
where v.ARTICLE_TYPE_CD = 'Products'
--and v.VARIANT_NO = '101199951'
order by v.VARIANT_NO, v.ATTRIBUTE_NM, v.VALUE_IDENTIFIER_CD
;
--------------------------------------------------------------------------------
-- Step 13: Generate Error Table & Report
--------------------------------------------------------------------------------
-- General Counts
select v.ERROR_TX, count(*)
from PIM_STAGE.TEMP_RESISTANCEB v
where v.ERROR_FL = 'X'
group by v.ERROR_TX
;
--Detail Error
select ar."ID" as VARIANT_AR_ID, 
v.PART_ID as VARIANT_NO, v.ATTRIBUTE_VALUE_TX, v.ATTRIBUTE_UOM_CD, sgl."Name" as StructureGroupName
, ad."Res_Text100_01" as MFR_PART_NO--, ar2."ID" as VAIANT_AR_ID, ar2."Identifier" as VARIANT_NO, ar2."EntityID" as ENTITY_ID
, ad."Res_Text2G_02" as DATASHEET_URL
, vB.ERROR_TX
, vB.ARTICLE_TYPE_CD
from PIM_STAGE.TEMP_RESISTANCE v,
hpm_master."ArticleStructureMap" asm,
HPM_MAIN."StructureGroupRevision" sgr,
HPM_MAIN."StructureGroupLang" sgl,
hpm_master."ArticleRevision" ar -- Variant
,hpm_master."ArticleRevision" ar2 
,hpm_master."ArticleDetail" ad -- Manufacturer
,PIM_STAGE.TEMP_RESISTANCEB vB
where v.ID = ar."ID"(+)
and v.ID = asm."ArticleRevisionID"(+)
and asm."StructureGroupID" = sgr."StructureGroupID"(+)
and sgr.ID = sgl."StructureGroupRevisionID"(+) and sgl."LanguageID"(+) = 9
and ar."ID"= ar2."ID"(+)
and ar2."ID" = ad."ArticleRevisionID"(+)
and ar2."EntityID"(+) = 1200
and vB.VARIANT_NO = v.PART_ID
and vB.ERROR_FL = 'X'
;
-- Success Report
create table PIM_STAGE.TEMP_RESISTANCE_VALIDATION NOLOGGING as
select ar."ID" as VARIANT_AR_ID, 
v.PART_ID as VARIANT_NO,  sgl."Name" as StructureGroupName
, ad."Res_Text100_01" as MFR_PART_NO--, ar2."ID" as VAIANT_AR_ID, ar2."Identifier" as VARIANT_NO, ar2."EntityID" as ENTITY_ID
, ad."Res_Text2G_02" as DATASHEET_URL
,v.ATTRIBUTE_NM
,v.ATTRIBUTE_VALUE_TX
,v.ATTRIBUTE_UOM_CD
,vB.ATTRIBUTE_NM as NEW_ATTRIBUTE_NM
,vB.VALUE_IDENTIFIER_CD as VALUE_IDENTIFIER_CD 
,vB.VALUE_AM as NEW_VALUE_AM
,vB.UNIT_CD as NEW_UNIT_CD
, rownum as INDEX_NO
from PIM_STAGE.TEMP_RESISTANCE v,
hpm_master."ArticleStructureMap" asm,
HPM_MAIN."StructureGroupRevision" sgr,
HPM_MAIN."StructureGroupLang" sgl,
hpm_master."ArticleRevision" ar -- Variant
,hpm_master."ArticleRevision" ar2 
,hpm_master."ArticleDetail" ad -- Manufacturer
,PIM_STAGE.TEMP_RESISTANCEF vB
where v.ID = ar."ID"(+)
and v.ID = asm."ArticleRevisionID"(+)
and asm."StructureGroupID" = sgr."StructureGroupID"(+)
and sgr.ID = sgl."StructureGroupRevisionID"(+) and sgl."LanguageID"(+) = 9
and ar."ID"= ar2."ID"(+)
and ar2."ID" = ad."ArticleRevisionID"(+)
and ar2."EntityID"(+) = 1200
and vB.VARIANT_NO = v.PART_ID
--and v.PART_ID = '101166757'
order by v.PART_ID, vB.ATTRIBUTE_NM, vB.VALUE_IDENTIFIER_CD
;

create index PIM_STAGE.TEMP_RESIST_VALIDATION_IE01 on PIM_STAGE.TEMP_RESISTANCE_VALIDATION(INDEX_NO);
create index PIM_STAGE.TEMP_RESIST_VALIDATION_IE02 on PIM_STAGE.TEMP_RESISTANCE_VALIDATION(VARIANT_NO);
create index PIM_STAGE.TEMP_RESIST_VALIDATION_IE03 on PIM_STAGE.TEMP_RESISTANCE_VALIDATION(VARIANT_NO, NEW_VALUE_AM);
create index PIM_STAGE.TEMP_RESIST_VALIDATION_IE04 on PIM_STAGE.TEMP_RESISTANCE_VALIDATION(VALUE_IDENTIFIER_CD);

select *
from PIM_STAGE.TEMP_RESISTANCE_VALIDATION v
where v.INDEX_NO <=300000
;
--------------------------------------------------------------------------------
-- Step 14: Resistance Specific Reports
--------------------------------------------------------------------------------
-- 0 Ohm values
select *
from PIM_STAGE.TEMP_RESISTANCE_VALIDATION v
where v.NEW_VALUE_AM = '0'
and v.VALUE_IDENTIFIER_CD = 'DEFAULT'
;--425 materials

-- Duplicate Values on the same material
create table PIM_STAGE.TEMP_RESISTANCE_VALIDATIONB NOLOGGING as
select v.VARIANT_NO, v.NEW_VALUE_AM, count(*) as COUNT_AM
from PIM_STAGE.TEMP_RESISTANCE_VALIDATION v
where v.VALUE_IDENTIFIER_CD in ('VALUE1','VALUE2','VALUE3','VALUE4','VALUE5','VALUE6','VALUE7','VALUE8')
--and v.VARIANT_NO = '50064963'
group by v.VARIANT_NO, v.NEW_VALUE_AM
having count(*) > 1;

create index PIM_STAGE.TEMP_RESIST_VALIDATIONB_IE01 on PIM_STAGE.TEMP_RESISTANCE_VALIDATIONB(VARIANT_NO, NEW_VALUE_AM);

select v.*
from PIM_STAGE.TEMP_RESISTANCE_VALIDATION v,
PIM_STAGE.TEMP_RESISTANCE_VALIDATIONB vB
where v.VARIANT_NO = vB.VARIANT_NO
and v.VALUE_IDENTIFIER_CD = 'DEFAULT'
--and v.VARIANT_NO = '50064963'
;--452
--------------------------------------------------------------------------------
-- Step 15: Clean Up
--------------------------------------------------------------------------------
Drop Table PIM_STAGE.TEMP_RESISTANCE Purge;
Drop Table PIM_STAGE.TEMP_RESISTANCEB Purge;
Drop Table PIM_STAGE.TEMP_RESISTANCEC Purge;
Drop table PIM_STAGE.TEMP_RESISTANCEE Purge;
Drop table PIM_STAGE.TEMP_RESISTANCEF Purge;
Drop table PIM_STAGE.TEMP_RESISTANCE_DEFAULT Purge;
Drop table PIM_STAGE.TEMP_RESISTANCE_MIN Purge;
Drop table PIM_STAGE.TEMP_RESISTANCE_MAX Purge;
Drop table PIM_STAGE.TEMP_RESISTANCE_VALIDATION purge;
Drop table PIM_STAGE.TEMP_RESISTANCE_VALIDATIONB purge;