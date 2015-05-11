/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newsfeed;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Axenia
 */

public class SetRssTest {
    
    

    /**
     * Test of getTitle and setTitle method, of class SetRss.
     */

    @Test
    public void testTitle() {
        System.out.println("getTitle and setTitle");
        String title = "testtitle";
        String message = "test failed";
        SetRss instance = new SetRss();
        instance.setTitle(title);
        String expResult = "testtitle";
        String result = instance.getTitle();
        assertEquals(message,expResult, result);
               
    }

    /**
     * Test of setDescription and getDescription method, of class SetRss.
     */
 @Test
    public void testDescription() {
        System.out.println("getDescription and setDescription");
        String description = "testdescription";
        String message = "test failed";
        SetRss instance = new SetRss();
        instance.setDescription(description);
        String expResult = "testdescription";
        String result = instance.getDescription();
        assertEquals(message,expResult, result);
               
    }
     /**
     * Test of setLink and getLink method, of class SetRss.
     */
 @Test
    public void testLink() {
        System.out.println("getLink and setLink");
        String link = "http://students.info.uaic.ro/~andrei.damoc/articles.rss";
        String message = "test failed";
        SetRss instance = new SetRss();
        instance.setDescription(link);
        String expResult = "http://students.info.uaic.ro/~andrei.damoc/articles.rss";
        String result = instance.getDescription();
        assertEquals(message,expResult, result);
               
    }
  

}