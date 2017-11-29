//https://github.com/anpilov/picturepo/find/master
//https://github.com/atduskgreg/opencv-processing/tree/master/examples

import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class CannyForLiquidLevel {

    public static void main( String[] args ) {
        new CannyForLiquidLevel().adjustThreshold();
    }
    
    public int getAveragePixelValue(){
        int average=0;
        try{
            ArrayList value = new ArrayList();
            System.loadLibrary("opencv_java249");
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
            Mat imgSource = Highgui.imread("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\sample.tif",Highgui.CV_LOAD_IMAGE_GRAYSCALE);
            
            for(int i=0;i<imgSource.height();i++){
                for(int j=0;j<imgSource.width();j++){
                    if( ((int)imgSource.get(i, j)[0] != 0) && ((int)imgSource.get(i, j)[0]!=255)){
                        value.add( (int)imgSource.get(i, j)[0] );
                    }
                }
            }
            
            
            
            for(int i=0;i<value.size();i++){
                
                average += (int)value.get(i);
            }
            
            average /= (int)value.size();
              
            System.out.println("Size of ArrayList: " + value.size());
           
            
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        
        return (int)average;
        
    }   
         
    public void adjustThreshold(){
        try {

              
              int avgVal = getAveragePixelValue();
              System.out.println("Average Liquid Pixel Value : " + avgVal );
              
              int range = 75;
             
              System.loadLibrary("opencv_java249");
              System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

              Mat imgSource = Highgui.imread("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\sample.tif",Highgui.CV_LOAD_IMAGE_GRAYSCALE);


              for(int i=0;i<imgSource.height();i++){
                  
                  for(int j=0;j<imgSource.width();j++){

                      if( (imgSource.get(i, j)[0] > avgVal-range) && (imgSource.get(i, j)[0] < avgVal+range)){
                          double data[] = new double[1];
                          data[0] = 113;
                          imgSource.put(i,j,data);
                      }
                  }
              }

              Highgui.imwrite("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\SeperationOfLiquid.jpg", imgSource);

              Imgproc.Canny(imgSource, imgSource, 300, 600, 5, true);
              
              Highgui.imwrite("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\CannyForLiquidLevel.jpg", imgSource);

        }catch (Exception e) {
           System.out.println("error: " + e.getMessage());
        }
   }
}