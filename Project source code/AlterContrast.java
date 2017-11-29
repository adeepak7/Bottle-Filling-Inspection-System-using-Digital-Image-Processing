import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class AlterContrast {

   static int width;
   static int height;
   static double alpha = 2;
   static double beta = 25;
   
   public static void main( String[] args ) {
   
      try {
         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
         Mat source = Highgui.imread("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\sample.tif", 
         Highgui.CV_LOAD_IMAGE_GRAYSCALE);
         Mat destination = new Mat(source.rows(),source.cols(),source.type());
         
         Imgproc.equalizeHist(source, destination);
         Highgui.imwrite("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\contrast.jpg", destination);
         
      }catch (Exception e) {
         System.out.println("error: " + e.getMessage());
      }
   }
}