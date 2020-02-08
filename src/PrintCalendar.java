/***********************************************
 *  Michelle Wu
 *  This programs outputs the calendar based on info given by user
 **********************************************/

import java.util.Scanner;

public class PrintCalendar {
   //main method has only one line of code and it calls the process method
   private static Scanner console;
	public static void main(String [] args) {
      process();
   }
   
   /*This method ask the required info and calls the appropriate methods based
   on the user's input to either print the calendar for the month or for the year.*/
   public static void process() {
      console = new Scanner(System.in);
      System.out.print("How many time do you want to try this app? ");
      int count = console.nextInt();
      for (int i = 1 ; i <= count; i++) {
         System.out.println("To print the calendar choose one of the following options:");
         System.out.println("1. Year");
         System.out.println("2. Month\n");
         String choice = "";
         do { //validation
            System.out.print("Enter your choice --> ");
            choice = console.next();
         } while ((!choice.equalsIgnoreCase("year")) && (!choice.equalsIgnoreCase("month")));
         
         int year = 0;
         do { //validation
        	 System.out.print("\nEnter valid year after 1800: ");
        	 while (!console.hasNextInt()) {
        		 System.out.print("\nNot a number. Try again: ");
        		 console.next();
        	 }
        	 year = console.nextInt();
         } while (year < 1800);
         
         if (choice.equalsIgnoreCase("month")) {
        	 int month = 0;
        	 do { //validation
        		 System.out.print("\nEnter the month of the year that you want the calendar: ");
            	 while (!console.hasNextInt()) {
            		 System.out.print("\nNot a number. Try again: ");
            		 console.next();
            	 }
            	 month = console.nextInt();
             } while (month < 0 || month > 12);
            
            System.out.println();
            printMonth(year, month);
         } 
         else {
        	 System.out.println();
            for (int n = 1; n <= 12; n++) {
               printMonth(year, n);
            }
         }
      }
      System.out.print("Goodbye!\n");
   }
   
   /*this method prints the body of the calendar for the given month by calling
   the methods printMonthTable and printMonthBody*/
   public static void printMonth (int year, int month) {
      printMonthTitle(year, month);
      printMonthBody(year, month);
      System.out.println();
   }

   /*This method prints the title of the days in each week.this method gets the month as an integer and call getMonthNames
   method to get the name of the month*/
   public static void printMonthTitle (int year, int month) {
      String name = getMonthName(month);
      String dash = "";
      for (int i = 1; i <= 28; i++) {
         dash += "-";
      }
      String weeknames = "Sun Mon Tue Wed Thu Fri Sat";
      System.out.printf("%14s %2d \n" + dash + "\n %s \n", name, year, weeknames);
   }
   
   /*this method calls the method getStartDay to find out the first day of the month then it calls the method print
   by passing the startday, year and month*/
   public static void printMonthBody (int year, int month) {
      int first = getStartDay(year, month);
      print(first, year, month);
   }
   
   /*This method prints the days in the month, it calls the getNumberOfDaysInMonth method to get the number of days and
   will also print out the day that Thanksgiving is on if the month is November using thanks method*/
   public static void print(int startDay, int year, int month) {
      int count = startDay; //this is the set up to print the numbers under each month
      int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);
      int i = 0; //this place the first day on the correct week name
      for (i = 0; i < (count); i ++) {
         System.out.printf("    ");
      }
      for (i = 1; i <= numberOfDaysInMonth; i++) { //this creates a new line after 7 days
         count++;
         if (count % 7 == 0) {
            System.out.printf(" %-3d \n", i);
         } 
         else {
            System.out.printf(" %-3d", i);
         }
      }
      if (month == 11) {
    	//this explicitly tells which day is thanksgiving
         System.out.println("\n**Thanks giving day is on the " + thanks(startDay) + "th"); 
      }
      System.out.println();
      System.out.println();
   }
   
   /*This method finds out the day of thanksgiving by switch statements*/
   public static int thanks (int startDay) {
      int result = 0; //this returns the day of the thanks giving based on when is the first day
      switch (startDay) {
         case 0:
            result = 26;
            break;
         case 1:
            result = 25;
            break;
         case 2:
            result = 24;
            break;
         case 3:
            result = 23;
            break;
         case 4:
            result = 22;
            break;
         case 5:
            result = 21;
            break;
         case 6:
            result = 20;
            break;
      }
      return result;
   }
   
   /*This method gets the number of the month and returns the name of the month*/
   public static String getMonthName(int month) {
      String result = "";
      if (month == 1) { //this returns the name of a specific month name based on the number by user
         result = "January";
      } 
      else if (month == 2) {
         result = "February";
      } 
      else if (month == 3) {
         result = "March";
      } 
      else if (month == 4) {
         result = "April";
      } 
      else if (month == 5) {
         result = "May";
      } 
      else if (month == 6) {
         result = "June";
      } 
      else if (month == 7) {
         result = "July";
      } 
      else if (month == 8) {
         result = "August";
      } 
      else if (month == 9) {
         result = "September";
      } 
      else if (month == 10) {
         result = "October";
      } 
      else if (month == 11) {
         result = "November";
      } 
      else if (month == 12) {
         result = "December";
      }
      return result;
   }
   
   /*this method returns the first day of the month which is a number, Sunday - Saturday
   is assosiated with 0 - 6*/
   public static int getStartDay (int year, int month) {
      int result; //this calculate the first day by using total number days since 1800 and determine if it's Monday or something
      final int BEGIN = 3;
      int days = getTotalNumberOfDays(year, month);
      result = (int)((days + BEGIN) % 7);
      return result;
   }
   
   /*This calculates the total number of the days has passed since 1800*/
   public static int getTotalNumberOfDays (int year, int month) {
      int result = 0;
      for (int i = 1800; i <= (year - 1); i++) { //this is cumulative loops to get the total number of days since 1800
         if (isLeapYear(i)) {
            result = result + 366;
         } 
         else {
            result = result + 365;
         }
      }
      for (int n = 1; n < month; n++) {
         result = result + getNumberOfDaysInMonth(year, n);
      }
      return result;
   }
   
   /*This method returns the number of the days in the given month*/
   public static int getNumberOfDaysInMonth (int year, int month) {
      int result = 0; //the result returns the specific amount of days under each month
      if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
         result = 31;
      } 
      else if (month == 4 || month == 6 || month == 9 || month == 11) {
         result = 30;
      } 
      else if ((month == 2) && (isLeapYear(year))) {
         result = 29;
      } 
      else {
         result = 28;
      }
         
      return result;
   }
   
   /*returns true if a year is a leap year otherwise it will return false*/
   public static boolean isLeapYear (int year) {
      boolean result;
      if (((year % 4 == 0) && !(year % 100 == 0))|| (year % 400 == 0)) {
         result = true;
      } 
      else {
         result = false;
      }
      return result;
   }
   
}