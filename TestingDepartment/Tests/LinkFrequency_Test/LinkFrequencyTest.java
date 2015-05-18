/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingbangsearch;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andra Maria
 */
public class LinkFrequencyTest {
    private boolean data;
    private boolean frequency;
    
    public LinkFrequencyTest() {
    }
    
    /**
     * Test of getData method, of class LinkFrequency.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        String data= "testdata";
        String message= "test esuat";
        LinkFrequency instance = new LinkFrequency();
        instance.setData(data);
        String expResult = "testdata";
        ResultG result = instance.getData();
        assertEquals(expResult, result);
        System.out.println(data);
    }

    /**
     * Test of getFrequency method, of class LinkFrequency.
     */
    @Test
    public void testGetFrequency() {
        System.out.println("getFrequency");
        int freq = 12;
        String message = "test esuat";
        LinkFrequency instance = new LinkFrequency() ;
        instance.setFrequency(freq);
        int expResult = 12;
        int result = instance.getFrequency();
        assertEquals(expResult, result);
      
    }
   /* @Test
    public void main() {
       System.out.println(data);
       System.out.println(frequency);
        
    }*/


    
}
