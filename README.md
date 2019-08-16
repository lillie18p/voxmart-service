# PHONE NUMBER SCENARIO

When a user calls a phone, they can dial a number in their own country by using a national number,
or dial a number in any country by using an international number. 

National numbers begin with a national trunk prefix, e.g. “02079560056”. 
National trunk prefixes can be any length (including zero), and vary by country. 
International numbers begin with “+” followed by the calling code of the country they wish to call, e.g. “+442079560056” (in this case, “44” is the country calling code). 
Calling codes are between 1 and 4 digits long, inclusive, and never begin with a “0”. 

National and international numbers can be converted between each other by changing the prefix and leaving the remaining digits the same. Thus, if the UK calling code is “44” and the national trunk prefix is “0”, the number “+442079460056” can be reached from another UK number by dialling “02079460056”. 

Phone numbers are considered to be in the same country if and only if their country calling codes are the same.

Note: This is a huge simplification of the real telephony system, and makes a lot of invalid assumptions, but will do for the purpose of this exercise.

# TASK
Write a class that, when given a dialled phone number (national or international) and the user’s international phone number, returns the dialled phone number in international format. 

The constructor should accept a mapping of country codes (e.g. “GB”, “US”) to the corresponding country calling codes (e.g. 44, 1), and a mapping of country codes to the corresponding national trunk prefixes (e.g. “0”, “1”)

Note for the purpose of this exercise the length of phone number was kept 10.

# Framework

	- SPRING BOOT STANDALONE
	- SPRING BOOT - ANNOTATION 
	- LOMBOK PROJECT - @SLF4J