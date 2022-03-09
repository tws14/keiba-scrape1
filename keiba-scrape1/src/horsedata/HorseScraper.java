package horsedata;

import java.util.Scanner;


public class HorseScraper {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
			System.out.println("URLを入力");
			
			String input = scanner.nextLine();
			
		ElementScrape ES = new ElementScrape();
				ES.scrape(input);
		
			
	}

}
