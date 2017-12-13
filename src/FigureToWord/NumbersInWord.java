package FigureToWord;
import java.util.*;

public class NumbersInWord {
	public static void main(String[] args) {	
		// Create a Scanner 
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a figure you want converted: ");
		long figure = input.nextLong(); 	

		System.out.println(convertToWord(figure));
	}
	
	public static String convertToWord(long fig) {
		// Convert the figure to a string value
		String stringFig = String.valueOf(fig);		

		String FormedWords = formWords(stringFig);
		// Print the formed words
		return FormedWords;
 	}	

 	public static String formWords(String digits) {
 		/** This will compute for tens */
 		if (digits.length() <= 2) {
 			return tens(digits);
 		}
 		/** This will compute for hundreds */
 		else if (digits.length() == 3){
 			return hundreds(digits);
 		}
 		/** This will compute for thousands */
 		else if (digits.length() == 4) {
 			return thousands(digits);
 		}
 		else if (digits.length() == 5) {
 			return tenThousands(digits);
 		}
 		else if (digits.length() == 6) {
 			return hunThousands(digits);
 		}
 		else if (digits.length() >= 7 && digits.length() <= 9) {
 			return millions(digits);
 		}
 		else if (digits.length() >= 10 && digits.length() <= 12) {
 			return billions(digits);
 		}
 		else if (digits.length() == 13 )
 			return trillions(digits);

 		return "This program only convert figures from One to One Trillion!";
 	}


 	// Method for Tens
 	public static String tens(String digts) {
 		if  (digts.length() == 1)
 			return getDigitWord(digts);
 		else {
 			String formedWord = "";
 			String secondChar = String.valueOf(digts.charAt(1));
 			if (digts.charAt(0) == '0') 
		 		formedWord = getDigitWord(secondChar);
		 	else
		 		formedWord = getDigitWord(digts);
			
			return formedWord;
	 	}
 	}

 	// Method for Hundreds
 	public static String hundreds(String digits) {
 		String firstChar = String.valueOf(digits.charAt(0));
 		
 		// This will take the second and last digit
 		String others = String.valueOf(digits.charAt(1)) + String.valueOf(digits.charAt(2));

		if (others.equals("00")) {
			String formedWords = getDigitWord(firstChar) + " Hundred";
			return formedWords;
		}
		else {
			return getDigitWord(firstChar) + " Hundred and " + tens(others);
		}
 	}

 	// Method for Thousands
 	public static String thousands(String digits) {
 		String firstChr = String.valueOf(digits.charAt(0));
 		String secondChr = String.valueOf(digits.charAt(1));
 		String others = String.valueOf(digits.charAt(2)) + String.valueOf(digits.charAt(3));
 		String last3Digits = secondChr + others;
 		String formedWords = "";

 		if (last3Digits.equals("000")) {
 		 	formedWords = getDigitWord(firstChr) + " Thousand";
 		 	return formedWords;
 		}
	 	else if (last3Digits.charAt(0) == '0') {
	 		formedWords = getDigitWord(firstChr) + " Thousand and " + tens(others);
			return formedWords;
 	 	}
 	 	else {
 	 		formedWords = getDigitWord(firstChr) + " Thousand " + hundreds(last3Digits); 
 	 		return formedWords;	
 	 	}
 	}

 	// Method for Ten Thousands
 	public static String tenThousands(String digits) {
 		String firstChr = String.valueOf(digits.charAt(0));		// first digit
 		String secondChr = String.valueOf(digits.charAt(1));	// second digit
 		String thirdChr = String.valueOf(digits.charAt(2));		// third digit
 		String fourthChr = String.valueOf(digits.charAt(3));	// fourth digit
 		String fifthChr = String.valueOf(digits.charAt(4));		// fifth digit
 		
 		String tenThth = firstChr + secondChr;					// the first two digits
 		String thsndth = thirdChr + fourthChr + fifthChr;		// last three digit
 		String tenth = fourthChr + fifthChr;					// last two digit

 		if (thsndth.equals("000")) 
 			return getDigitWord(tenThth) + " Thousand";
 		else if (digits.charAt(2) == '0') 
 			return getDigitWord(tenThth) + " Thousand and " + tens(tenth);
 		else
 			return getDigitWord(tenThth) + " Thousand " + hundreds(thsndth);
 	}

 	// Method for Hundred Thousands
 	public static String hunThousands(String digits) {
 		String hundThth = digits.substring(0, 3);	// First three digit
 		String others = digits.substring(3);		// last three digit
 		String tenth = others.substring(1);

 		if (others.equals("000"))
 			return hundreds(hundThth) + " Thousand";
 		else if (others.charAt(0) == '0')
 			return hundreds(hundThth) + " Thousand and " + tens(tenth);
 		else
 			return hundreds(hundThth) + " Thousand " + hundreds(others);
 	}

 	// Method for Millions
 	public static String millions(String digits) {
 		String firstChr = "";
 		String others = "";
 		if (digits.length() == 7) {
 			firstChr = String.valueOf(digits.charAt(0));		// first digit
 			others = digits.substring(1);						// other digits
 		}
 		else if (digits.length() == 8) {
 			firstChr = digits.substring(0, 2);
 			others = digits.substring(2);						// other digits
 		}
 		else {
 			firstChr = digits.substring(0, 3);
 			others = digits.substring(3);						// other digits
 		}
 		String secondChr = String.valueOf(others.charAt(0));	// second digit
 		String hunThth = others.substring(0);					// Hundred thousandth
 		String tenThth = others.substring(1);					// ten thousandth
 		String thsndth = others.substring(0, 3);				// First hundredth
 		String hundth = others.substring(3);					// second hundredth
 		String tenth = others.substring(4);						// last two digits

 		// test if nth million
 		String nthMillion = (firstChr.length() < 3) ? tens(firstChr) : hundreds(firstChr);

 		if (hunThth.equals("000000"))
 			return nthMillion + " Million";

 		else if (thsndth.equals("000")) {
 			// test the first digit of the last three digits.
 			if (hundth.charAt(0) > '0' )				
 				return nthMillion + " Million " + hundreds(hundth);
 			else
 				return nthMillion + " Million and " + tens(tenth);
 		}

 		else if (thsndth.substring(0, 2).equals("00")) 
 			return nthMillion + " Million " + thousands(others.substring(2));

 		else if (secondChr.equals("0"))
 			return nthMillion + " Million " + tenThousands(tenThth);

 		else 
 			return nthMillion + " Million " + hunThousands(hunThth);
 	}

 	// Method for Billions
 	public static String billions(String digits) {
 		String firstDigt = "";									// place holder for first character
 		String others = "";										// place holder for the remaining digits
 		
 		if (digits.length() == 10) {
 			firstDigt = String.valueOf(digits.charAt(0));		// first digit
 			others = digits.substring(1);						// other digits
 		}
 		else if (digits.length() == 11) {
 			firstDigt = digits.substring(0, 2);
 			others = digits.substring(2);						// other digits
 		}
 		else {
 			firstDigt = digits.substring(0, 3);
 			others = digits.substring(3);						// other digits
 		}

  		String hundMlth = others.substring(0);					// hundreds of million
 		String tenMlth = others.substring(1);					// tens of million
 		String Mlth = others.substring(2);						// million
 		String hunThth = others.substring(3);					// hundreds of thousand
 		String tenThth = others.substring(4);					// tens of thousand
 		String thsndTh = others.substring(5);					// thousand

 		String millionth = others.substring(0, 3);				// first three digits after nth million 
 		String thsndth = others.substring(3, 6);				// second three digits 
 		String hundth = others.substring(6);					// third three digit
 		String tenth = others.substring(7);						// last two digits
 		String sixDigit = millionth + thsndth;

 		// test if nth billion
 		String nthBillion = (firstDigt.length() < 3) ? tens(firstDigt) : hundreds(firstDigt);

 		if (hundMlth.equals("000000000"))
 			return nthBillion + " Billion";

 		else if (sixDigit.equals("000000")) {
 			
 			if (hundth.charAt(0) == '0')
 				return nthBillion + " Billion and " + tens(tenth);
 			else
 				return nthBillion + " Billion " + hundreds(hundth);
 		}

 		else if (millionth.equals("000")) {
 			if (thsndth.equals("000")) 
 				return nthBillion + " Billion " + hundreds(hundth);

 			else if (thsndth.charAt(0) == '0')
 				return nthBillion + " Billion " + tenThousands(tenThth);
 			
 			else if (thsndth.charAt(0) == '0' && thsndth.charAt(0) == '0')
 				return nthBillion + " Billion " + thousands(thsndTh);
 			
 			else
 				return nthBillion + " Billion " + hunThousands(hunThth);
 		}
 		else if (others.charAt(0) == '0' && others.charAt(1) == '0')
 			return nthBillion + " Billion " + millions(Mlth);
 		
 		else if (others.charAt(0) == '0')							// first digit after nth billion
 			return nthBillion + " Billion " + millions(tenMlth);

 		else
 			return nthBillion + " Billion " + millions(hundMlth);
 	}

 	// Method for Trillions 
 	public static String trillions(String digits) {
 		String firstChr = String.valueOf(digits.charAt(0));
 		String others = digits.substring(1);

 		if (others.equals("000000000000"))
 			return getDigitWord(firstChr) + " Trillion";
 		else {
 			return "This program only convert figures from 1 to 1 Trillion!";
 		}
 	}


 	public static String getDigitWord(String ch) {
 		if (ch.length() == 1) {
 			switch(ch) {
	 			case "1": return "One"; 
	 			case "2": return "Two"; 
	 			case "3": return "Three"; 
	 			case "4": return "Four"; 
	 			case "5": return "Five"; 
	 			case "6": return "Six"; 
	 			case "7": return "Seven"; 
	 			case "8": return "Eight"; 
	 			case "9": return "Nine"; 
	 		}
	 	}
 		else if (ch.length() == 2) { 
 			switch(ch) {
 				case "10": return "Ten"; 
	 			case "11": return "Eleven"; 
	 			case "12": return "Twelve"; 
	 			case "13": return "Thirteen"; 
	 			case "14": return "Fourteen"; 
	 			case "15": return "Fifteen"; 
	 			case "16": return "Sixteen"; 
	 			case "17": return "Seventeen"; 
	 			case "18": return "Eighteen"; 
	 			case "19": return "Nineteen"; 
	 			case "20": return "Twenty"; 
	 			case "21": return "Twenty One"; 
	 			case "22": return "Twenty Two"; 
	 			case "23": return "Twenty Three"; 
	 			case "24": return "Twenty Four"; 
	 			case "25": return "Twenty Five"; 
	 			case "26": return "Twenty Six"; 
	 			case "27": return "Twenty Seven"; 
	 			case "28": return "Twenty Eight"; 
	 			case "29": return "Twenty Nine"; 
	 			case "30": return "Thirty"; 
	 			case "31": return "Thirty One"; 
	 			case "32": return "Thirty Two"; 
	 			case "33": return "Thirty Three"; 
	 			case "34": return "Thirty Four"; 
	 			case "35": return "Thirty Five"; 
	 			case "36": return "Thirty Six"; 
	 			case "37": return "Thirty Seven"; 
	 			case "38": return "Thirty Eight"; 
	 			case "39": return "Thirty Nine"; 
	 			case "40": return "Forty"; 
	 			case "41": return "Forty One"; 
	 			case "42": return "Forty Two"; 
	 			case "43": return "Forty Three"; 
	 			case "44": return "Forty Four"; 
	 			case "45": return "Forty Five"; 
	 			case "46": return "Forty Six"; 
	 			case "47": return "Forty Seven"; 
	 			case "48": return "Forty Eight"; 
	 			case "49": return "Forty Nine"; 
	 			case "50": return "Fifty"; 
	 			case "51": return "Fifty One"; 
	 			case "52": return "Fifty Two"; 
	 			case "53": return "Fifty Three"; 
	 			case "54": return "Fifty Four"; 
	 			case "55": return "Fifty Five"; 
	 			case "56": return "Fifty Six"; 
	 			case "57": return "Fifty Seven"; 
	 			case "58": return "Fifty Eight"; 
	 			case "59": return "Fifty Nine"; 
	 			case "60": return "Sixty"; 
	 			case "61": return "Sixty One"; 
	 			case "62": return "Sixty Two"; 
	 			case "63": return "Sixty Three"; 
	 			case "64": return "Sixty Four"; 
	 			case "65": return "Sixty Five"; 
	 			case "66": return "Sixty Six"; 
	 			case "67": return "Sixty Seven"; 
	 			case "68": return "Sixty Eight"; 
	 			case "69": return "Sixty Nine"; 
	 			case "70": return "Seventy"; 
	 			case "71": return "Seventy One"; 
	 			case "72": return "Seventy Two"; 
	 			case "73": return "Seventy Three"; 
	 			case "74": return "Seventy Four"; 
	 			case "75": return "Seventy Five"; 
	 			case "76": return "Seventy Six"; 
	 			case "77": return "Seventy Seven"; 
	 			case "78": return "Seventy Eight"; 
	 			case "79": return "Seventy Nine"; 
	 			case "80": return "Eighty"; 
	 			case "81": return "Eighty One"; 
	 			case "82": return "Eighty Two"; 
	 			case "83": return "Eighty Three"; 
	 			case "84": return "Eighty Four"; 
	 			case "85": return "Eighty Five"; 
	 			case "86": return "Eighty Six"; 
	 			case "87": return "Eighty Seven"; 
	 			case "88": return "Eighty Eight"; 
	 			case "89": return "Eighty Nine"; 
	 			case "90": return "Ninety"; 
	 			case "91": return "Ninety One"; 
	 			case "92": return "Ninety Two"; 
	 			case "93": return "Ninety Three"; 
	 			case "94": return "Ninety Four"; 
	 			case "95": return "Ninety Five"; 
	 			case "96": return "Ninety Six"; 
	 			case "97": return "Ninety Seven"; 
	 			case "98": return "Ninety Eight"; 
	 			case "99": return "Ninety Nine"; 		 			 			 			 			
	 		}
	 	}
	 	return "This program only convert figures from 1 to 1 Trillion!";
 	}
}