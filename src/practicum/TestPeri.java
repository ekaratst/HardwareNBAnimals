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
            //McuWithPeriBoard peri2 = new McuWithPeriBoard(devices[1]);

            System.out.format("** Practicum board found **\n");
            System.out.format("** Manufacturer: %s\n", peri.getManufacturer());
            System.out.format("** Product: %s\n", peri.getProduct());
//            System.out.format("** Manufacturer1: %s\n", peri2.getManufacturer());
//            System.out.format("** Product1: %s\n", peri2.getProduct());

            int count = 0;
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
            
//            int count_board2 = 0;
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

            while (true) 
            {
                Thread.sleep(200);
                //peri.setLedValue(count);
               boolean sw = peri.getSwitch();
//               boolean sw_board2 = peri2.getSwitch();
//                int light = peri.getLight();
                //System.out.format("LED set to %d | Switch state: %s | Light: %d\n",count, sw, light);
               //System.out.format("Switch state: %s\n", sw);
                
                int acceleroX = peri.getAcceleroX();
                int acceleroY = peri.getAcceleroY();
                int acceleroZ = peri.getAcceleroZ();
//                int acceleroX_board2 = peri2.getAcceleroX();
//                int acceleroY_board2 = peri2.getAcceleroY();
//                int acceleroZ_board2 = peri2.getAcceleroZ();
                
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
                		acceleroX1 = acceleroX;
                		acceleroY1 = acceleroY;
                		System.out.println("Pressed");
                		check++;
//                		System.out.println("---------before---------");
//                		System.out.println("check: " + check);
//                		System.out.println("acceleroAverage1 = "+acceleroAverage1);
//                		System.out.println("AcceleroX1: "+ acceleroX1);
                	}
//                	System.out.println("AcceleroAverage: " + acceleroAverage);
                } else {
                	if (check == 2) {
                	acceleroAverage2 = acceleroAverage;
                	deltaAcceleroAverage = acceleroAverage2 - acceleroAverage1;
                	acceleroX2 = acceleroX;
                	deltaAcceleroX = acceleroX2 - acceleroX1;
                	acceleroY2 = acceleroY;
                	deltaAcceleroY = acceleroY2 - acceleroY1;
                	System.out.println("Unpressed ///////////////////////////////////////");
                	System.out.println("---------after---------");
                	check = 1;
//                	System.out.println("check: " + check);
                	debug(acceleroAverage1, acceleroAverage2, deltaAcceleroAverage, acceleroX1, acceleroX2, deltaAcceleroX, acceleroY1, acceleroY2, deltaAcceleroY);
                	}
               }
//                if (sw_board2) {
//                	System.out.println("");
//                	System.out.println("Beforecheck_board2: " + check_board2);
//                	if (check_board2 == 1) {
//                		acceleroAverage1_board2 = acceleroAverage_board2;
//                		acceleroX1_board2 = acceleroX_board2;
//                		acceleroY1_board2 = acceleroY_board2;
//                		System.out.println("Pressed_board2");
//                		check_board2++;
////                		System.out.println("---------before---------");
////                		System.out.println("check: " + check);
////                		System.out.println("acceleroAverage1 = "+acceleroAverage1);
////                		System.out.println("AcceleroX1: "+ acceleroX1);
//                	}
////                	System.out.println("AcceleroAverage_board2: " + acceleroAverage_board2);
//                } else {
//                	if (check_board2 == 2) {
//                	acceleroAverage2_board2 = acceleroAverage_board2;
//                	deltaAcceleroAverage_board2 = acceleroAverage2_board2 - acceleroAverage1_board2;
//                	acceleroX2_board2 = acceleroX_board2;
//                	deltaAcceleroX_board2 = acceleroX2_board2 - acceleroX1_board2;
//                	acceleroY2_board2 = acceleroY_board2;
//                	deltaAcceleroY_board2 = acceleroY2_board2 - acceleroY1_board2;
//                	System.out.println("Unpressed_board2 ///////////////////////////////////////");
//                	System.out.println("---------after_board2---------");
//                	check_board2 = 1;
////                	System.out.println("check_board2: " + check_board);
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
    
    public static void debug(int acceleroAverage1, int acceleroAverage2, int deltaAcceleroAverage, int acceleroX1, int acceleroX2, int deltaAcceleroX, int acceleroY1, int acceleroY2, int deltaAcceleroY) {
    	System.out.println("...................................");
    	System.out.println("acceleroAverage1 = "+acceleroAverage1);
    	System.out.println("acceleroAverage2 = "+acceleroAverage2);
    	System.out.println("deltaAcceleroAverage = "+deltaAcceleroAverage);
    	System.out.println();
    	System.out.println("acceleroX1 = "+acceleroX1);
    	System.out.println("acceleroX2 = "+acceleroX2);
    	System.out.println("deltaAcceleroX = "+deltaAcceleroX);
    	System.out.println();
    	System.out.println("acceleroY1 = "+acceleroY1);
    	System.out.println("acceleroY2 = "+acceleroY2);
    	System.out.println("deltaAcceleroY = "+deltaAcceleroY);
    	System.out.println("...................................");
    }
    
    public static void debug_board2(int acceleroAverage1_board2, int acceleroAverage2_board2, int deltaAcceleroAverage_board2, int acceleroX1_board2, int acceleroX2_board2, int deltaAcceleroX_board2, int acceleroY1_board2, int acceleroY2_board2, int deltaAcceleroY_board2) {
    	System.out.println("======================================");
    	System.out.println("acceleroAverage1_board2 = "+acceleroAverage1_board2);
    	System.out.println("acceleroAverage2_board2 = "+acceleroAverage2_board2);
    	System.out.println("deltaAcceleroAverage_board2 = "+deltaAcceleroAverage_board2);
    	System.out.println();
    	System.out.println("acceleroX1_board2 = "+acceleroX1_board2);
    	System.out.println("acceleroX2_board2 = "+acceleroX2_board2);
    	System.out.println("deltaAcceleroX_board2 = "+deltaAcceleroX_board2);
    	System.out.println();
    	System.out.println("acceleroY1_board2 = "+acceleroY1_board2);
    	System.out.println("acceleroY2_board2 = "+acceleroY2_board2);
    	System.out.println("deltaAcceleroY_board2 = "+deltaAcceleroY_board2);
    	System.out.println("======================================");
    }
}
