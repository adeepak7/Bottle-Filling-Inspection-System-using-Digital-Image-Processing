//https://github.com/anpilov/picturepo/find/master
//https://github.com/atduskgreg/opencv-processing/tree/master/examples

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class ApplyCanny {

    public static void main( String[] args ) {
   
      try {
         System.loadLibrary("opencv_java249");
         System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
         
         Mat imgSource1 = Highgui.imread("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\AfterThresholding.jpg",Highgui.CV_LOAD_IMAGE_GRAYSCALE);
         Imgproc.Canny(imgSource1, imgSource1, 300, 600, 3, true);
         
         Mat imgSource2 = Highgui.imread("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\AfterClosing.jpg",Highgui.CV_LOAD_IMAGE_GRAYSCALE);
         Imgproc.Canny(imgSource2, imgSource2, 300, 600, 3, true);
         
         
         for(int i=0;i<imgSource2.height();i++){
             
              for(int j=0;j<imgSource2.width();j++){
                  
                  System.out.print (imgSource2.get(i, j)[0] + " ");
              }
              System.out.println("");
                     
          }
         
            Highgui.imwrite("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\CannyAfterThreshold.jpg", imgSource1);
            Highgui.imwrite("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\CannyAfterClosing.jpg", imgSource2);
         
            
      }catch (Exception e) {
         System.out.println("error: " + e.getMessage());
      }
   }
}