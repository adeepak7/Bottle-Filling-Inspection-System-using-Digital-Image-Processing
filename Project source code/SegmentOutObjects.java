
import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deepak
 */
public class SegmentOutObjects {
    
    public ArrayList getFinalColumns(){
        
      ArrayList segmentationColumns = new ArrayList();
      ArrayList finalSegmentedColumns = new ArrayList();
      ArrayList averageColumns = new ArrayList();
      
      try {
            
            System.loadLibrary("opencv_java249");
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

            Mat imgSource = Highgui.imread("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\AfterClosing.jpg",Highgui.CV_LOAD_IMAGE_GRAYSCALE);

            boolean gotFirst = false;
            
            for(int j=0;j<imgSource.width();j++){
                boolean flg=false;
                for(int i=0;i<imgSource.height();i++){
                    if( imgSource.get(i, j)[0] > 0 ){
                        flg=true;
                        break;
                    }
                }
                if(!flg)
                    segmentationColumns.add(j);
                
                if( !flg && (gotFirst==false) ){
                    gotFirst=true;
                }
                else if( flg && (gotFirst==true) ){
                    gotFirst = false;
                    finalSegmentedColumns.add(segmentationColumns.get(0));
                    finalSegmentedColumns.add(segmentationColumns.get(segmentationColumns.size()-1));
                    segmentationColumns.clear();
                }
                
            }
                
            System.out.println("Hello");
                   
            for(int i=0;i<finalSegmentedColumns.size();i++){
                System.out.println(finalSegmentedColumns.get(i));
            }

            for(int i=0;i<finalSegmentedColumns.size();){
                
                int first = (int)finalSegmentedColumns.get(i);
                i++;
                int second = (int)finalSegmentedColumns.get(i);
                i++;
                
                int average = (first+second)/2;
                averageColumns.add(average);
                
                for(int row=0;row<imgSource.height();row++){
                    double data[] = new double[1];
                    data[0] = 255;
                    imgSource.put(row, average, data);
                }
            }
            
            Highgui.imwrite("C:\\Users\\Deepak\\Desktop\\DIP Project Images\\Segmented.jpg", imgSource);
            
        }catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
      
      return averageColumns;
   }
}
