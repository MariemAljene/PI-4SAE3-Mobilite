package tn.esprit.backend.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MessagerieUtils {
	//private static Scanner myReader;
	//public static String filterbadwords(String message) throws FileNotFoundException{
		private List<String> fetchBadWords() {
			List<String> badWords = new ArrayList<>();
			try {
				URL url = new URL("https://docs.google.com/spreadsheets/d/1hIEi2YG3ydav1E06Bzf2mQbGZ12kh2fe4ISgLg_UBuM/export?format=csv");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				InputStream inputStream = connection.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					String[] values = line.split(",");
					if (values.length > 0) {
						badWords.add(values[0]);
					}
				}
				bufferedReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return badWords;
		}
	}
//		String badwordchange = "@#^&$!";
////		System.out.println("votre path est : "+System.getProperty("user.dir"));
//		File myObj = new File("src/main/resources/badwords.txt");
//		myReader = new Scanner(myObj);
//		while (myReader.hasNextLine()) {
//			String data = myReader.nextLine().toLowerCase();
//			if ((message.toLowerCase()).contains(data)) {
//				Random rd = new Random();
//				String newdata = "";
//				for (int i = 0; i < data.length(); i++) {
//					char letter = badwordchange.charAt(rd.nextInt(badwordchange.length()));
//					newdata += letter;
//				}
//				System.out.println(newdata);
////				message = message.replace(data,newdata);
//				message = message.replaceAll("(?i)"+data,newdata); // regex used here for case insensitive
//				System.out.println("avant : "+message);
//			}
//		}
//		System.out.println("apres : "+message);
//		return message;
//	}
//}
