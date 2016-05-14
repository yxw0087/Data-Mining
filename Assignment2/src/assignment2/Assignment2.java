package assignment2;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Assignment2 {

    public static void main(String[] args) {        
        Assignment2 a2 = new Assignment2();
    }
    
    public Assignment2() {       
        String Cuboid[] = {"time_key", "item_key", "location_key", "branch_key"};
        int cuboid_code[] = {1,1,1,1};
        String Query[] = {"", "", "", ""};
        Scanner sc = new Scanner(System.in);
        int dimension;
        int operation;
        String fileName;
        String line;        
        Vector<String> time_keys = new Vector<String>();
        Vector<String> item_keys = new Vector<String>();
        Vector<String> location_keys = new Vector<String>();
        Vector<String> branch_keys = new Vector<String>();
        Vector<Sales> sales_keys = new Vector<Sales>();
        Vector<Time> time = new Vector<Time>();
        Vector<Item> item = new Vector<Item>();
        Vector<Location> location = new Vector<Location>();
        Vector<Branch> branch = new Vector<Branch>();
        Vector<Sales> sales = new Vector<Sales>();
        try {
            fileName = "TimeDimension.txt";            
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                String [] column = line.split(",");
                Time t= new Time(column[0], column[1], column[2], column[3], column[4], column[5]);   
                time.add(t);
            }
            bufferedReader.close();  
            
            fileName = "ItemDimension.txt";            
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                String [] column = line.split(",");
                Item i = new Item(column[0], column[1], column[2], column[3], column[4]);   
                item.add(i);
            }
            bufferedReader.close(); 
            
            fileName = "LocationDimension.txt";            
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                String [] column = line.split(",");
                Location l = new Location(column[0], column[1], column[2], column[3], column[4]);   
                location.add(l);
            }
            bufferedReader.close();
            
            fileName = "BranchDimension.txt";            
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                String [] column = line.split(",");
                Branch b = new Branch(column[0], column[1], column[2]);   
                branch.add(b);
            }
            bufferedReader.close();
            
            fileName = "SalesFact.txt";            
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                String [] column = line.split(",");
                Sales s = new Sales(column[0], column[1], column[2], column[3], column[4], column[5]);   
                sales.add(s);
            }
            bufferedReader.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        
        while(true){
            System.out.print("Current cuboid: {");
            for(int i=0; i<4; i++){
                System.out.print(Cuboid[i]);
                if(i!=3){
                    System.out.print(" ,");
                }
            }
            System.out.println("}");
            System.out.print("Enter a dimension to perform roll-up/drill-down (1=time, 2=item, 3=location, 4=branch) or enter 0 to proceed to the next step: ");
            dimension = sc.nextInt();
            if(dimension == 0) {
                break;
            }
            if(dimension != 1 && dimension != 2 && dimension != 3 && dimension != 4){
                System.out.println("Enter a valid dimension (1=time, 2=item, 3=location, 4=branch) or enter 0 to proceed to the next step.");
                continue;
            }            
            System.out.print("Which operation to perform (1=roll-up, 2=drill-down)? ");
            operation = sc.nextInt();
            if(operation != 1 && operation != 2) {
                System.out.println("Enter a valid operation (roll-up, drill-down).");
                continue;
            }
            if(!performOperation(dimension, operation, Cuboid, cuboid_code)) {           
                System.out.println("Operation not possible for selected dimension");
            }
            else
                System.out.println("Operation performed.\n");
        }
        sc.nextLine();
        System.out.println("Enter the values for current cuboid (enter null if unspecidied):");
        for(int i=0; i<4; i++){
            System.out.print(Cuboid[i] + ": ");
            Query[i] = sc.next();
        }
        
        // Time
        switch (cuboid_code[0]) {
            case 1:
                if(Query[0] == "null") {break;}
                for (int i = 0; i < time.size(); i++) {
                    if (time.elementAt(i).key.equals(Query[0])) {
                       time_keys.add(time.elementAt(i).key);
                    }
                }
                break;
            case 2:
                if(Query[0] == "null") {break;}
                for (int i = 0; i < time.size(); i++) {
                    if (time.elementAt(i).day.equals(Query[0])) {
                       time_keys.add(time.elementAt(i).key);
                    }
                }
                break;
            case 3:
                if(Query[0] == "null") {break;}
                for (int i = 0; i < time.size(); i++) {
                    if (time.elementAt(i).day_of_the_week.equals(Query[0])) {
                       time_keys.add(time.elementAt(i).key);
                    }
                }
                break;
            case 4:
                if(Query[0] == "null") {break;}
                for (int i = 0; i < time.size(); i++) {
                    if (time.elementAt(i).month.equals(Query[0])) {
                       time_keys.add(time.elementAt(i).key);
                    }
                }
                break;
            case 5:
                if(Query[0] == "null") {break;}
                for (int i = 0; i < time.size(); i++) {
                    if (time.elementAt(i).quarter.equals(Query[0])) {
                       time_keys.add(time.elementAt(i).key);
                    }
                }
                break;
            default:
                if(Query[0] == "null") {break;}
                for (int i = 0; i < time.size(); i++) {
                    if (time.elementAt(i).year.equals(Query[0])) {
                       time_keys.add(time.elementAt(i).key);
                    }
                }
                break;
        }
        
        // Item
        switch (cuboid_code[1]) {
            case 1:
                if(Query[1] == "null") {break;}
                for (int i = 0; i < item.size(); i++) {
                    if (item.elementAt(i).key.equals(Query[1])) {
                        item_keys.add(item.elementAt(i).key);
                    }
                }
                break;
            case 2:
                if(Query[1] == "null") {break;}
                for (int i = 0; i < item.size(); i++) {
                    if (item.elementAt(i).name.equals(Query[1])) {
                        item_keys.add(item.elementAt(i).key);
                    }
                }
                break;
            case 3:
                if(Query[1] == "null") {break;}
                for (int i = 0; i < item.size(); i++) {
                    if (item.elementAt(i).brand.equals(Query[1])) {
                        item_keys.add(item.elementAt(i).key);
                    }
                }
                break;
            case 4:
                if(Query[1] == "null") {break;}
                for (int i = 0; i < item.size(); i++) {
                    if (item.elementAt(i).type.equals(Query[1])) {
                        item_keys.add(item.elementAt(i).key);
                    }
                }
                break;
            default:
                if(Query[1] == "null") {break;}
                for (int i = 0; i < item.size(); i++) {
                    if (item.elementAt(i).supplier_type.equals(Query[1])) {
                        item_keys.add(item.elementAt(i).key);
                    }
                }
                break;
        }
        
        // Location
        switch (cuboid_code[2]) {
            case 1:
                if(Query[2] == "null") {break;}
                for (int i = 0; i < location.size(); i++) {
                    if (location.elementAt(i).key.equals(Query[2])) {
                        location_keys.add(location.elementAt(i).key);
                    }
                }
                break;
            case 2:
                if(Query[2] == "null") {break;}
                for (int i = 0; i < location.size(); i++) {
                    if (location.elementAt(i).street.equals(Query[2])) {
                        location_keys.add(location.elementAt(i).key);
                    }
                }
                break;
            case 3:
                if(Query[2] == "null") {break;}
                for (int i = 0; i < location.size(); i++) {
                    if (location.elementAt(i).city.equals(Query[2])) {
                        location_keys.add(location.elementAt(i).key);
                    }
                }
                break;
            case 4:
                if(Query[2] == "null") {break;}
                for (int i = 0; i < location.size(); i++) {
                    if (location.elementAt(i).province_or_state.equals(Query[2])) {
                        location_keys.add(location.elementAt(i).key);
                    }
                }
                break;
            default:
                if(Query[2] == "null") {break;}
                for (int i = 0; i < location.size(); i++) {
                    if (location.elementAt(i).country.equals(Query[2])) {
                        location_keys.add(location.elementAt(i).key);
                    }
                }
                break;
        }
        
        // Branch
        switch (cuboid_code[3]) {
            case 1:
                if(Query[3] == "null") {break;}
                for (int i = 0; i < branch.size(); i++) {
                    if (branch.elementAt(i).key.equals(Query[3])) {
                        branch_keys.add(branch.elementAt(i).key);
                    }
                }
                break;
            case 2:
                if(Query[3] == "null") {break;}
                for (int i = 0; i < branch.size(); i++) {
                    if (branch.elementAt(i).name.equals(Query[3])) {
                        branch_keys.add(branch.elementAt(i).key);
                    }
                }
                break;
            default:
                if(Query[3] == "null") {break;}
                for (int i = 0; i < branch.size(); i++) {
                    if (branch.elementAt(i).type.equals(Query[3])) {
                        branch_keys.add(branch.elementAt(i).key);
                    }
                }
                break;
        }
        
        // Sales
        for (int i = 0; i < sales.size(); i++) {
            if((time_keys.contains(sales.elementAt(i).time_key) || time_keys.isEmpty()) && 
                    (item_keys.contains(sales.elementAt(i).item_key) || item_keys.isEmpty()) && 
                    (location_keys.contains(sales.elementAt(i).location_key) || location_keys.isEmpty()) && 
                    (branch_keys.contains(sales.elementAt(i).time_key) || branch_keys.isEmpty())) 
            {
                sales_keys.add(sales.elementAt(i));
            }        
        }
        
        // Display result
        System.out.println("\ntime_key, item_key, branch_key, location_key, dollars_sold, units_sold:");
        int sum_unit = 0;
        int sum_dollar = 0;
        for(int i = 0; i <sales_keys.size(); i++) {
            System.out.print(sales_keys.elementAt(i).time_key + ",");
            System.out.print(sales_keys.elementAt(i).item_key + ",");
            System.out.print(sales_keys.elementAt(i).branch_key + ",");
            System.out.print(sales_keys.elementAt(i).location_key + ",");
            System.out.print(sales_keys.elementAt(i).dollars_sold + ",");
            sum_dollar += Integer.parseInt(sales_keys.elementAt(i).dollars_sold);
            System.out.println(sales_keys.elementAt(i).units_sold);
            sum_unit += Integer.parseInt(sales_keys.elementAt(i).units_sold);
        }
        System.out.println("Total dollars_sold: " + sum_dollar);
        System.out.println("Total units_sold: " + sum_unit);
    }
    
    public boolean performOperation(int dimension, int operation, String Cuboid[], int cuboid_code[]) {
        if(operation == 1) {
            switch(dimension) {
                case 1: 
                    if(Cuboid[0] == "time_key") {Cuboid[0] = "day"; cuboid_code[0]++;}
                    else if(Cuboid[0] == "day") {Cuboid[0] = "day_of_the_week"; cuboid_code[0]++;}
                    else if(Cuboid[0] == "day_of_the_week") {Cuboid[0] = "month"; cuboid_code[0]++;}
                    else if(Cuboid[0] == "month") {Cuboid[0] = "quarter"; cuboid_code[0]++;}
                    else if(Cuboid[0] == "quarter") {Cuboid[0] = "year"; cuboid_code[0]++;}                    
                    else if(Cuboid[0] == "year") {return false;}
                    break;
                case 2: 
                    if(Cuboid[1] == "item_key") {Cuboid[1] = "item_name"; cuboid_code[1]++;}
                    else if(Cuboid[1] == "item_name") {Cuboid[1] = "brand"; cuboid_code[1]++;}
                    else if(Cuboid[1] == "brand") {Cuboid[1] = "type"; cuboid_code[1]++;}
                    else if(Cuboid[1] == "type") {Cuboid[1] = "supplier_type"; cuboid_code[1]++;}
                    else if(Cuboid[1] == "supplier_type") {return false;}
                    break;
                case 3: 
                    if(Cuboid[2] == "location_key") {Cuboid[2] = "street"; cuboid_code[2]++;}
                    else if(Cuboid[2] == "street") {Cuboid[2] = "city"; cuboid_code[2]++;}
                    else if(Cuboid[2] == "city") {Cuboid[2] = "province_or_state"; cuboid_code[2]++;}
                    else if(Cuboid[2] == "province_or_state") {Cuboid[2] = "country"; cuboid_code[2]++;}
                    else if(Cuboid[2] == "country") {return false;}
                    break;
                default: 
                    if(Cuboid[3] == "branch_key") {Cuboid[3] = "branch_name"; cuboid_code[3]++;}
                    else if(Cuboid[3] == "branch_name") {Cuboid[3] = "branch_type"; cuboid_code[3]++;}
                    else if(Cuboid[3] == "branch_type") {return false;}
                    break;
                }                               
            }
        else if(operation == 2) {
              switch(dimension) {
                case 1: 
                    if(Cuboid[0] == "time_key") {return false;}
                    else if(Cuboid[0] == "day") {Cuboid[0] = "time_key"; cuboid_code[0]--;}
                    else if(Cuboid[0] == "day_of_the_week") {Cuboid[0] = "day"; cuboid_code[0]--;}
                    else if(Cuboid[0] == "month") {Cuboid[0] = "day_of_the_week"; cuboid_code[0]--;}
                    else if(Cuboid[0] == "quarter") {Cuboid[0] = "month"; cuboid_code[0]--;}                    
                    else if(Cuboid[0] == "year") {Cuboid[0] = "quarter"; cuboid_code[0]--;}
                    break;
                case 2: 
                    if(Cuboid[1] == "item_key") {return false;}
                    else if(Cuboid[1] == "item_name") {Cuboid[1] = "item_key"; cuboid_code[1]--;}
                    else if(Cuboid[1] == "brand") {Cuboid[1] = "item_name"; cuboid_code[1]--;}
                    else if(Cuboid[1] == "type") {Cuboid[1] = "brand"; cuboid_code[1]--;}
                    else if(Cuboid[1] == "supplier_type") {Cuboid[1] = "type"; cuboid_code[1]--;}
                    break;
                case 3: 
                    if(Cuboid[2] == "location_key") {return false;}
                    else if(Cuboid[2] == "street") {Cuboid[2] = "location_key"; cuboid_code[2]--;}
                    else if(Cuboid[2] == "city") {Cuboid[2] = "street"; cuboid_code[2]--;}
                    else if(Cuboid[2] == "province_or_state") {Cuboid[2] = "city"; cuboid_code[2]--;}
                    else if(Cuboid[2] == "country") {Cuboid[2] = "province_or_state"; cuboid_code[2]--;}
                    break;
                default: 
                    if(Cuboid[3] == "branch_key") {return false;}
                    else if(Cuboid[3] == "branch_name") {Cuboid[3] = "branch_key"; cuboid_code[3]--;}
                    else if(Cuboid[3] == "branch_type") {Cuboid[3] = "branch_name"; cuboid_code[3]--;}
                    break;
                }        
        }        
        return true;
    }
    
}
