//https://github.com/anpilov/picturepo/find/master
//https://github.com/atduskgreg/opencv-processing/tree/master/examples

import java.util.Vector;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

public class ApplyClosing {

   public static void main( String[] args ) {
   
      try {
         
          System.loadLibrary("opencv_java249");
          System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
   
         Mat imgSource = Highgui.imread("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\brightWithAlpha2Beta50.jpg",Highgui.CV_LOAD_IMAGE_GRAYSCALE);

         Mat imgDest  = new Mat(imgSource.size(), imgSource.type());
         
         Mat kernel = new Mat(new Size(11,11),  imgSource.type());
         
         Imgproc.morphologyEx(imgSource, imgDest, Imgproc.MORPH_CLOSE, kernel);
         
         Highgui.imwrite("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\AfterClosing.jpg", imgDest);
       
          
      }catch (Exception e) {
         System.out.println("error: " + e.getMessage());
      }
   }
}