//https://github.com/anpilov/picturepo/find/master
//https://github.com/atduskgreg/opencv-processing/tree/master/examples

import java.util.Vector;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

public class ApplyThreshold {

   static int width;
   static int height;
   static double alpha = 2;
   static double beta = 50;
   
   public static void main( String[] args ) {
   
      try {
         System.loadLibrary("opencv_java249");
         System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Mat source = Highgui.imread("C:\\Users\\Deepak\\Desktop\\roger.jpg",Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        // Mat destination = new Mat(source.rows(),source.cols(),source.type());
         
         //Imgproc.equalizeHist(source, destination);
         Mat imgSource = Highgui.imread("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\brightWithAlpha2Beta50.jpg",Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        // Imgproc.Canny(imgSource, imgSource, 300, 600, 5, true);
         
         for(int i=0;i<imgSource.height();i++){
             
              for(int j=0;j<imgSource.width();j++){
                  
                  double[] origData = imgSource.get(i, j);
                  double[] newData = new double[1];
                  if( origData[0] > 80 ){
                    newData[0]=255;
                  }else{
                      newData[0]=0;
                  }
                  
                 // System.out.print (imgSource.put(i, j,newData));
              }
              //System.out.println("");
                     
          }
         
         Highgui.imwrite("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\AfterThresholding.jpg", imgSource);
         
         //System.out.println("Size:" + imgSource.size() +" Type:" +  imgSource.type());
          
         Mat imgDest  = new Mat(imgSource.size(), imgSource.type());
         
         Mat kernel = new Mat(new Size(11,11),  imgSource.type());
         
         Imgproc.morphologyEx(imgSource, imgDest, Imgproc.MORPH_CLOSE, kernel);
         Highgui.imwrite("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\AfterClosing.jpg", imgDest);
          //System.out.println(imgSource);;
          //System.out.println(imgSource.width() +" "+ imgSource.height());
          
          double[] tmp = imgSource.get(100, 100);
          System.out.println(tmp.length);
          
           
          
          /*for(int i=0;i<imgSource.height();i++){
              for(int j=0;j<imgSource.width();j++){
                  
                  if( (imgSource.get(i, j)[0]) > 200){
                      System.out.println("Found" + i + " " +j);
                  }
            }
          }*/
         
      }catch (Exception e) {
         System.out.println("error: " + e.getMessage());
      }
   }
}