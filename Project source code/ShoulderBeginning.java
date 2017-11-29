
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deepak
 */
public class ShoulderBeginning {
    
    
    public static void main(String[] args) {
        new ShoulderBeginning().getShoulderStart();
    }
    
    public int getShoulderStart(){
        int startOfShouler = -1;
        int endOfShouler = -1;
        boolean startEncountered=false;
        boolean minusEncountered = false;
        
            
            System.loadLibrary("opencv_java249");
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

            Mat imgSource = Highgui.imread("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\CannyAfterClosing.jpg",Highgui.CV_LOAD_IMAGE_GRAYSCALE);

            ArrayList finalColumns = new ArrayList();
            finalColumns = new SegmentOutObjects().getFinalColumns();
                
            System.out.println("Size of finalColumns: " + finalColumns.size());
            
            
            ArrayList differenceList = new ArrayList();
            differenceList.clear();
            
            for(int i=0;i<finalColumns.size();){
                
                
                
                differenceList.clear();
                
                int first = (int)finalColumns.get(i);
                i++;
                if(i>=finalColumns.size()){
                    break;
                }
                int second = (int)finalColumns.get(i);
                
                System.out.println("First:"+first + " Second : " + second);
                
                int firstPixel=-1,secondPixel=-1;
                
                for(int row = 0 ; row<imgSource.height();row++){
                    
                    //for first:
                       
                    for(int j=first ;j<second-3; j++){
                        
                        if(imgSource.get(row, j)[0] > 200){
                            firstPixel = j;
                            break;
                        }
                    }
              
                            
                    //for second:
                    for(int j=second ;j>first+3 ; j--){
                        
                        if(imgSource.get(row, j)[0] > 200){
                            secondPixel = j;
                            break;
                        }
                    }
                            
                    int difference = secondPixel-firstPixel;
                    
                    differenceList.add(difference);
                }
                
                for(int k=0;k<differenceList.size();k++){
                    
                    System.out.print(differenceList.get(k) + " ");
                }
                System.out.println("");
                
                
               ArrayList slope = new ArrayList();
                
               //int prevSlope=0;
               
                for(int k=1;k<differenceList.size();k++){
                    
                    int val = ((int) ((int)differenceList.get(k) - (int)differenceList.get(k-1)) );
                    
                    if(val < 0 ){
                        val=-1;
                    }else if(val>0){
                        val=1;
                    }
                    slope.add(val);
                    
                    
                }
                
                for(int k=0;k<slope.size();k++){
                    
                    System.out.print(slope.get(k) + " ");
                }
                System.out.println("");
                
                
                
                // LOGIC FOR START:
                
                minusEncountered = false;
                
                for(int k=0;k<slope.size();k++){
                    
                    if( (int)slope.get(k) == -1){
                        minusEncountered = true;
                    }
                    
                    
                    if((int)slope.get(k) == 1){
                        
                        if( minusEncountered && check0(k,slope) && check1(k,slope)){
                            
                            
                            startOfShouler = k;
                            System.out.println("Found The Start of shoulder : " + startOfShouler);
                            startEncountered = true;
                            break;
                        } 
                    }
                }
                
                // LOGIC FOR END:
                
                    boolean zeroEncountered = false;

                    for(int k=startOfShouler;k<slope.size();k++){

                        if( (int)slope.get(k) == 0){
                            zeroEncountered = true;
                        }


                        if((int)slope.get(k) == 0){

                            if( zeroEncountered && checkS0(k,slope) && checkS1(k,slope)){


                                endOfShouler = k;
                                System.out.println("Found The End of shoulder : " + endOfShouler);
                                break;
                            } 
                        }
                    }
                }
            
            
                for(int col = 0;col<imgSource.width();col++){
                    double[] data = new double[1];
                    data[0]=255;
                    imgSource.put(startOfShouler,col,data);
                    imgSource.put(endOfShouler,col,data);
                }
            
                Highgui.imwrite("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\StartOfShoulder.jpg", imgSource);
        
        
        
        return startOfShouler;
    } 
    
    public boolean check0(int k,ArrayList slope){
        
        int cnt=0;
        for(int i=k;i>=k-10;i--){
            if((int)slope.get(i) == 0){
                cnt++;
            }
        }
        
        return cnt>=6;
    }
    
    public boolean check1(int k,ArrayList slope){
        int cnt=0;
        for(int i=k;i<=k+10;i++){
            if((int)slope.get(i) == 1){
                cnt++;
            }
        }
        
        return cnt>=6;
    }
    
    public boolean checkS1(int k,ArrayList slope){
        
        int cnt=0;
        for(int i=k;i>=k-10;i--){
            if(i<0){
                return false;
            }
            if((int)slope.get(i) == 1){
                cnt++;
            }
        }
        
        return cnt>=6;
    }
    
    public boolean checkS0(int k,ArrayList slope){
        int cnt=0;
        try{
        for(int i=k;i<=k+10;i++){
            if((int)slope.get(i) == 0){
                cnt++;
            }
        }
        
        
        }catch(Exception e){
            
        }
        return cnt>=6;
    }
}
