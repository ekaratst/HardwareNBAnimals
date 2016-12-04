package practicum;

import org.usb4java.Device;

public class TestPeri
{
    public static void main(String[] args) throws Exception
    {
        McuBoard.initUsb();
        try
        {
        	Device[] devices = McuBoard.findBoards();
        	
        	if (devices.length == 0) {
                System.out.format("** Practicum board not found **\n");
                return;
        	}
        	else {
                System.out.format("** Found %d practicum board(s) **\n", devices.length);
        	}
            McuWithPeriBoard peri = new McuWithPeriBoard(devices[0]);
//            McuWithPeriBoard peri2 = new McuWithPeriBoard(devices[1]);

            System.out.format("** Practicum board found **\n");
            System.out.format("** Manufacturer: %s\n", peri.getManufacturer());
            System.out.format("** Product: %s\n", peri.getProduct());
//            System.out.format("** Manufacturer1: %s\n", peri2.getManufacturer());
//            System.out.format("** Product1: %s\n", peri2.getProduct());

            int countCheckSelect = 0;
            int checkRoll = 0;
            int check = 1;
            int acceleroAverage2 = 0;
            int acceleroAverage1 = 0;
            int deltaAcceleroAverage = 0;
            int acceleroX1 = 0;
            int acceleroX2 = 0;
            int deltaAcceleroX = 0;
            int acceleroY1 = 0;
            int acceleroY2 = 0;
            int deltaAcceleroY = 0;
            int acceleroZ1 = 0;
            int acceleroZ2 = 0;
            int deltaAcceleroZ = 0;
            
//            int countcountCheckSelect_board2 = 0;
//            int check_board2 = 1;
//            int acceleroAverage2_board2 = 0;
//            int acceleroAverage1_board2 = 0;
//            int deltaAcceleroAverage_board2 = 0;
//            int acceleroX1_board2 = 0;
//            int acceleroX2_board2 = 0;
//            int deltaAcceleroX_board2 = 0;
//            int acceleroY1_board2 = 0;
//            int acceleroY2_board2 = 0;
//            int deltaAcceleroY_board2 = 0;
            
           
            int acceleroXSelectCharacter1 = 0;
            int acceleroXSelectCharacter2 = 0;
            int deltaAcceleroXSelectCharacter = 0;
           
            
//            int acceleroXSelectCharacter_board2 = peri2.getAcceleroX();
        	           	
            
            while (true)
            {
            	Thread.sleep(300);
            	 boolean swSelectCharacter = peri.getSwitch();
//               boolean swSelectCharacter_board2 = peri2.getSwitch();
            	 
            	 int acceleroXSelectCharacter = peri.getAcceleroX();
                
                
            	if (swSelectCharacter)
            	{
            		if (countCheckSelect==0)
            		{
            			countCheckSelect++;
            			System.out.println("Started");
            		}
            		else if (countCheckSelect==1)
            		{
            			System.out.println("");
            			System.out.println("Selected");
            			break;
            		}
            	}
            	///เช็คค่าacc/////////////////////
            	if (countCheckSelect==1) 
            	{
            		if (checkRoll == 0)
            		{
            			acceleroXSelectCharacter1 = acceleroXSelectCharacter;
            			System.out.println("acceleroXSelectCharacter1: "+acceleroXSelectCharacter1);
            			checkRoll++;
            		}
            		else if (checkRoll ==1)
            		{
            			System.out.println("acceleroXSelectCharacter: "+acceleroXSelectCharacter);
            			int rollLeftRight = (acceleroXSelectCharacter)-(acceleroXSelectCharacter1);
            			System.out.println("");
            			System.out.println("rollLeft: "+rollLeftRight);
            			if ( rollLeftRight >= 4 || rollLeftRight <= -4)
            			{
            				acceleroXSelectCharacter2 = acceleroXSelectCharacter;
            				System.out.println("acceleroXSelectCharacter2: "+acceleroXSelectCharacter2);
            				deltaAcceleroXSelectCharacter = acceleroXSelectCharacter2 - acceleroXSelectCharacter1;
            				System.out.println("deltaAcceleroXSelectCharacter: "+deltaAcceleroXSelectCharacter);
            				checkRoll++;
            			}
            		}
            		Thread.sleep(300);
            	}
            	///////////////////////////
            }
            
            while (true) 
            {
                Thread.sleep(200);
                boolean sw = peri.getSwitch();
//              boolean sw_board2 = peri2.getSwitch();
               
               int acceleroX = peri.getAcceleroX();
               int acceleroY = peri.getAcceleroY();
               int acceleroZ = peri.getAcceleroZ();
//               int acceleroX_board2 = peri2.getAcceleroX();
//               int acceleroY_board2 = peri2.getAcceleroY();
//               int acceleroZ_board2 = peri2.getAcceleroZ();

                int acceleroExpoX = acceleroX * acceleroX;
                int acceleroExpoY = acceleroY * acceleroY;
                int acceleroExpoZ = acceleroZ * acceleroZ;
                int sumOfAccelero = acceleroExpoX + acceleroExpoY + acceleroExpoZ;	
                int acceleroAverage = (int) Math.sqrt(sumOfAccelero);
//                int acceleroExpoX_board2 = acceleroX_board2 * acceleroX_board2;
//                int acceleroExpoY_board2 = acceleroY_board2 * acceleroY_board2;
//                int acceleroExpoZ_board2 = acceleroZ_board2 * acceleroZ_board2;
//                int sumOfAccelero_board2 = acceleroExpoX_board2 + acceleroExpoY_board2 + acceleroExpoZ_board2;	
//                int acceleroAverage_board2 = (int) Math.sqrt(sumOfAccelero_board2);
         
                if (sw) {
                	System.out.println("Gooddddd");
                	if (check == 1) {
                		acceleroAverage1 = acceleroAverage;
//                		acceleroX1 = acceleroX;
                		acceleroY1 = acceleroY;
                		acceleroZ1 = acceleroZ;
                		check++;
                	}
                } else {
                	if (check == 2) {
                	acceleroAverage2 = acceleroAverage;
                	deltaAcceleroAverage = acceleroAverage2 - acceleroAverage1;
//                	acceleroX2 = acceleroX;
//                	deltaAcceleroX = acceleroX2 - acceleroX1;
                	acceleroY2 = acceleroY;
                	deltaAcceleroY = acceleroY2 - acceleroY1;
                	acceleroZ2 = acceleroZ;
                	deltaAcceleroZ = acceleroZ2 - acceleroZ1;
                	check = 1;
                	debug(acceleroAverage1, acceleroAverage2, deltaAcceleroAverage, acceleroZ1, acceleroZ2, deltaAcceleroZ, acceleroY1, acceleroY2, deltaAcceleroY);
                	}
               }
//                if (sw_board2) {
//                	if (check_board2 == 1) {
//                		acceleroAverage1_board2 = acceleroAverage_board2;
//                		acceleroX1_board2 = acceleroX_board2;
//                		acceleroY1_board2 = acceleroY_board2;
//                		check_board2++;
//                	}
//                } else {
//                	if (check_board2 == 2) {
//                	acceleroAverage2_board2 = acceleroAverage_board2;
//                	deltaAcceleroAverage_board2 = acceleroAverage2_board2 - acceleroAverage1_board2;
//                	acceleroX2_board2 = acceleroX_board2;
//                	deltaAcceleroX_board2 = acceleroX2_board2 - acceleroX1_board2;
//                	acceleroY2_board2 = acceleroY_board2;
//                	deltaAcceleroY_board2 = acceleroY2_board2 - acceleroY1_board2;
//                	check_board2 = 1;
//                	debug_board2(acceleroAverage1_board2, acceleroAverage2_board2, deltaAcceleroAverage_board2, acceleroX1_board2, acceleroX2_board2, deltaAcceleroX_board2, acceleroY1_board2, acceleroY2_board2, deltaAcceleroY_board2);
//                	}
//               }
            }
        }
        
        catch (Exception e)
        {
            System.out.println(e);
        }

        McuBoard.cleanupUsb();
    }
    
    public static void debug(int acceleroAverage1, int acceleroAverage2, int deltaAcceleroAverage, int acceleroZ1, int acceleroZ2, int deltaAcceleroZ, int acceleroY1, int acceleroY2, int deltaAcceleroY) {

    	System.out.println();
    	System.out.println("deltaAcceleroZ_Board1 = "+deltaAcceleroZ);
    	System.out.println();
    	System.out.println("deltaAcceleroY_Board1 = "+deltaAcceleroY);
    	System.out.println("...................................");
    }
    
    public static void debug_board2(int acceleroAverage1_board2, int acceleroAverage2_board2, int deltaAcceleroAverage_board2, int acceleroX1_board2, int acceleroX2_board2, int deltaAcceleroX_board2, int acceleroY1_board2, int acceleroY2_board2, int deltaAcceleroY_board2) {
    	System.out.println();
    	System.out.println("deltaAcceleroX_board2 = "+deltaAcceleroX_board2);
    	System.out.println();
    	System.out.println("deltaAcceleroY_board2 = "+deltaAcceleroY_board2);
    	System.out.println("======================================");
    }
}