package com.assignment.SpringCRUD;

public class isbn {
    public static boolean isValidISBN(String isbn) {
        if(isbn==null || isbn.length()!=10)
            return false;
        if(!isbn.matches("\\d{10}"))
            return false;
        int countryCode = Integer.parseInt(isbn.substring(0,2));
        if(countryCode<10 || countryCode>90)
            return false;
        if(isbn.charAt(9) != '0')
            return false;
        return true;
    }
}
