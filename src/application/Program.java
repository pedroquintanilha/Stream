package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Product> list = new ArrayList<>();

		System.out.print("Please enter file full path: ");
		File file = new File(sc.nextLine());
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = br.readLine();

			while (line != null) {
				String [] str = line.split(",");
				list.add(new Product(str[0],Double.parseDouble(str[1])));
				line = br.readLine();
			}
			
		}
		catch (IOException e) {
			System.out.print("Error: " + e.getMessage());
		}
	
		Double average = list.stream().map(p -> p.getPrice()).reduce(0.0, (subtotal, element) -> subtotal + element)/list.size();
		
		System.out.println("Average price: " + String.format("%.2f", average));
		
		
		//To sort the elements of the list in descending order		
		Comparator<String> comp = (str1, str2) -> str1.toUpperCase().compareTo(str2.toUpperCase());
		
		List<String> listResult1 = list.stream().filter(p -> p.getPrice() < average).map(p -> p.getName()).sorted(comp.reversed()).collect(Collectors.toList());
		
		//Another way to sort the list
		List<String> listResult2 = list.stream().filter(p -> p.getPrice() < average).map(p -> p.getName()).collect(Collectors.toList());
		Collections.sort(listResult2, Collections.reverseOrder());;
		
		//Another way to sort the list
		listResult2.sort(comp.reversed());
	
		listResult1.forEach(System.out::println);
		
		sc.close();
	}
}



	
	
	
