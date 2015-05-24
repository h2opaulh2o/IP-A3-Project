/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingbangsearch;

import java.util.ArrayList;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Cristina
 */
public class BingBangSearchTest {

    /**
     * Test of search method, of class BingBangSearch.
     */
    @Test
    public void testSearch() throws Exception {
        System.out.println("search");
        String x = "";
        String z = "google";
        String[][] expResult = new String[100][2];
        expResult[0][0]=null;
        expResult[0][1]=null;
        String[][] result = BingBangSearch.search(x, z);
        for (int row=0; row < result.length; row++) {
            System.out.println(result[row]);    
        }
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getDataFromGoogle method, of class BingBangSearch.
     */
    @Test
    public void testGetDataFromGoogle() {
        System.out.println("getDataFromGoogle");
        String query = "apple";
        String engine = "google";
        //Set<ResultG> expResult = null;
        Set<ResultG> result = BingBangSearch.getDataFromGoogle(query, engine);
        int ok = 0;
        for (ResultG s : result) {
            if (ok == 0&&s.getTitle().contains("Apple")) {
                ok = 1;
                assertTrue(s.getTitle().contains("Apple"));
            }
           // System.out.println(s.getTitle()+" "+s.getUrl());
        }
        
    } 
    
}




