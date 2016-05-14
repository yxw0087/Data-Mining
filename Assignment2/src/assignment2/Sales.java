package assignment2;

public class Sales {
    String time_key;
    String item_key;
    String branch_key;
    String location_key;
    String dollars_sold;
    String units_sold;
    
    public Sales(String t, String i, String b, String l, String d, String u) {
        item_key = i;
        time_key = t;
        branch_key = b;
        location_key = l;        
        dollars_sold = d;
        units_sold = u;
    }
}
