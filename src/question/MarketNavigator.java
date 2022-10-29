
package question;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;  
import java.util.*;

public class MarketNavigator
{  
	
	/* Method that gives the distance between two points */
	public static double distanceFinder(int x1, int y1, int x2, int y2) {
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}

	public static int pathFinder(String filename) {
		
		/* Find the smallestTotalDistance */
		double smallestTotalDistance = 0;
		
		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
		ArrayList<String> SmallestPathTravel = new ArrayList<String>(); 
		
		try {
			/* reading the coordinate files */
			FileInputStream fis=new FileInputStream(filename);        
			Scanner sc=new Scanner(fis);    
			while(sc.hasNextLine())  
			{  
				String line = sc.nextLine();
				SmallestPathTravel.add(line);
			}  
			sc.close();     //closes the scanner  
		}  
		catch(IOException e)  
		{  
			e.printStackTrace();  
		} 
		
		ArrayList<House> HouseList = new ArrayList<House>();
		
		for (int i = 0; i < SmallestPathTravel.size(); i++) {
			String hello = SmallestPathTravel.get(i);
		
			int j = hello.indexOf(" ");
			int k = hello.lastIndexOf(" ");
			
			// getting the house name from the file as a string
			String myName = hello.substring(0,j);       
			
			// getting the x coordinate from the file
			String x_co = hello.substring(j+1,k);
			int x_coordinate = Integer.parseInt(x_co);
			
			// getting the y coordinate from the file
			String y_co = hello.substring(k+1,hello.length());
			int y_coordinate = Integer.parseInt(y_co);
			
			// Creating myHouse object to reach House class
			House myHouse = new House(myName,x_coordinate, y_coordinate);   
			
			// collecting all the values in an array list  
			HouseList.add(myHouse);
		}
			
		ArrayList<House> WithoutFirstHouseList= WithoutFirst(HouseList);
			
		    // calling the methods needed
		ArrayList<ArrayList<House>> result = new ArrayList<ArrayList<House>>();
		myPermutation(WithoutFirstHouseList,0, result);
		smallestTotalDistance = findingTheAnswer(result, HouseList);

		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
		
		int roundedValue = (int) Math.round(smallestTotalDistance);
		return roundedValue;
		
	}
	
	public static ArrayList<House> WithoutFirst(ArrayList<House> houseArray) {
		ArrayList<House> myFirstList= new ArrayList<>();
		for (House curHouse : houseArray) {
			if(!curHouse.getName().equals("Migros")) {
				myFirstList.add(curHouse);
			}
		}
		return myFirstList;
	}
	
	public static void myPermutation(ArrayList<House> arr, int p, ArrayList<ArrayList<House>> allPermutations){
        for(int n = p; n < arr.size(); n++){
            Collections.swap(arr, n, p);
            myPermutation(arr, p+1, allPermutations);
            Collections.swap(arr, n, p);
        }
        if (p == arr.size() -1){
            allPermutations.add(WithoutFirst(arr));
        }
	}
	
	public static double findingTheAnswer(ArrayList<ArrayList<House>> allPerms, ArrayList<House> houses) {
		double minSum = Double.MAX_VALUE ;
		for (ArrayList<House> curPerm : allPerms) {
			double curSum = 0;
			House migros = houses.get(0);
			House firstHouse = curPerm.get(0);
			House lastHouse = curPerm.get(curPerm.size()-1);
			curSum += distanceFinder(firstHouse.getX(), firstHouse.getY(), migros.getX(), migros.getY()) + distanceFinder(lastHouse.getX(), lastHouse.getY(), migros.getX(), migros.getY());
			for(int i = 0; i < curPerm.size() -1 ;i ++) {
				House first  = curPerm.get(i);
				House latter = curPerm.get(i+1);
				curSum += distanceFinder(first.getX(), first.getY(), latter.getX(), latter.getY());
			}
			if (curSum < minSum) {
				minSum = curSum;
						
			}
				
		}
		
		return minSum;
		
	}
	
	
	public static void main(String[] args) {
		
		/* This part is for you to test your method, no points will be given from here */
		String path = MarketNavigator.class.getProtectionDomain().getCodeSource().getLocation().getPath() + File.separator + ".." + File.separator+"coordinates.txt";
		int distance = pathFinder(path);
		System.out.println("Smallest distance:" + distance);
		
	}
	
}  

