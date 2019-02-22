/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author priet
 */
public class ProcesadorDeExpresionesTest {
    
    public ProcesadorDeExpresionesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of procesarPostfija method, of class ProcesadorDeExpresiones.
     */
    @Test
    public void testProcesarPostfija() {
    }

    /**
     * Test of revisarExpresion method, of class ProcesadorDeExpresiones.
     */
    @Test
    public void testRevisarExpresion() {
        System.out.println("revisarExpresion");
        String[] expresiones = new String[]{
            "2(-2)",
            "2(2)",
            "5-(7-2-(1-9)-3+7)+4",
            "-5-(7-2-(1-9)-3+7)+4",
            "-(-(-(-1)+1))",
            "(983",
            "5.0.1",
            "0+*4"
        };
        
        boolean[] expResult = new boolean[]{
            true,
            true,
            true,
            true,
            true,
            false,
            false,
            false
        };
        int i=0;
        for(String expresion: expresiones){
            boolean result = ProcesadorDeExpresiones.revisarExpresion(expresion);
            assertEquals(expResult[i], result);
            i++;
        }
    }

    /**
     * Test of convertirPostfija method, of class ProcesadorDeExpresiones.
     */
    @Test
    public void testConvertirPostfija() {
    }

    /**
     * Test of procesarExpresion method, of class ProcesadorDeExpresiones.
     */
    @Test
    public void testProcesarExpresion() {
        System.out.println("procesarExpresion");
        String[] expresiones = new String[]{
            "2(-2)",
            "2(2)",
            "5-(7-2-(1-9)-3+7)+4",
            "-5-(7-2-(1-9)-3+7)+4",
            "-(-(-(-1)+1))",
        };
        
        double[] expResult = new double[]{
            -4,
            4,
            -8,
            -18,
            //0,
            2 
        };
        int i=0;
        for(String expresion: expresiones){
            double result = ProcesadorDeExpresiones.procesarExpresion(expresion);
            assertEquals(expResult[i], result, 0.0);
            i++;
        }
    }
    
}
