package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /**
     * CHALLENGE 0: Display Image
     * Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage Image = new APImage("cyberpunk2077.jpg");
        grayScale("cyberpunk2077.jpg");
        blackAndWhite("cyberpunk2077.jpg");
        edgeDetection("cyberpunk2077.jpg",35);
        reflectImage("cyberpunk2077.jpg");
        rotateImage("cyberpunk2077.jpg");
        Image.draw();
    }

    /**
     * CHALLENGE ONE: Grayscale
     * <p>
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     * <p>
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value.
     */
    public static void grayScale(String pathOfFile) {
        APImage Image = new APImage(pathOfFile);
        int wide = Image.getWidth();
        int length = Image.getHeight();

        for (int i = 0; i < wide; i++) {
            for (int c = 0; c < length; c++) {
                Pixel pix = Image.getPixel(i, c);
                int average = getAverageColour(pix);
                pix.setRed(average);
                pix.setBlue(average);
                pix.setGreen(average);
            }
        }
        Image.draw();
    }

    /**
     * A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     *
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
        return avg;
    }

    /**
     * CHALLENGE TWO: Black and White
     * <p>
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     * <p>
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white
     */
    public static void blackAndWhite(String pathOfFile) {
        APImage Image = new APImage(pathOfFile);
        int wide = Image.getWidth();
        int length = Image.getHeight();

        for (int i = 0; i < wide; i++) {

            for (int c = 0; c < length; c++) {
                Pixel pix = Image.getPixel(i, c);
                int average = getAverageColour(pix);

                if (average < 128) {
                    pix.setRed(0);
                    pix.setGreen(0);
                    pix.setBlue(0);
                } else {
                    pix.setRed(255);
                    pix.setBlue(255);
                    pix.setGreen(255);
                }
            }
        }
        Image.draw();
    }




    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
   public static void edgeDetection(String pathToFile, int threshold) {
       APImage Image = new APImage(pathToFile);
       APImage Copy = new APImage(pathToFile);
       int wide = Image.getWidth();
       int length = Image.getHeight();

       for (int i = 1; i<wide; i++){
           for (int c = 1; c<length; c++){

               Pixel one = Image.getPixel(i,c);
               Pixel left = Image.getPixel(i-1,c);
               Pixel bottom = Image.getPixel(i,c-1);
               Pixel copyPixel = Copy.getPixel(i,c);
               int oneAvg = ImageManipulation.getAverageColour(one);
               int leftAvg = ImageManipulation.getAverageColour(left);
               int bottomAvg = ImageManipulation.getAverageColour(bottom);

               if ((oneAvg > bottomAvg && oneAvg-bottomAvg>threshold)||
                       (oneAvg < bottomAvg && bottomAvg-oneAvg>threshold)||
                       (oneAvg < leftAvg && leftAvg-oneAvg>threshold)||
                       (oneAvg > leftAvg && oneAvg-leftAvg>threshold)) {
                   copyPixel.setRed(0);
                   copyPixel.setBlue(0);
                   copyPixel.setGreen(0);
               } else {
                   copyPixel.setRed(255);
                   copyPixel.setBlue(255);
                   copyPixel.setGreen(255);
               }
           }
       }
       Copy.draw();
   }


    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */

    public static void reflectImage(String pathToFile) {
        APImage Image = new APImage(pathToFile);
        APImage copy = new APImage(pathToFile);
        int wide = Image.getWidth();
        int length = Image.getHeight();

        for (int i = 0; i<wide; i++) {
            for (int c = 0; c<length; c++) {
                copy.setPixel(i, c, Image.getPixel(wide-i-1,c));
            }
        }
        copy.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */

    public static void rotateImage(String pathToFile) {
        APImage Image = new APImage(pathToFile);
        int wide = Image.getWidth();
        int length = Image.getHeight();
        APImage rotate = new APImage(length,wide);

        for (int i = 0; i<length; i++) {
            for (int c = 0; c < wide; c++) {
                rotate.setPixel(i, c,Image.getPixel(c,length-1-i));
            }
        }
        rotate.draw();
    }
}


